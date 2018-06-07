package com.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpRequest {


    private HttpClient httpClient = new HttpClient();
    private Logger logger = Logger.getLogger(this.getClass());


    public HttpRequest() {

        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);


    }


    public String portFile(String url, File file) {

        String response = null;
        if (!file.exists()) {
            return null;
        }

        PostMethod postMethod = new PostMethod(url);


        try {


            FilePart fp = new FilePart("filedata", file);
            fp.setContentType("utf-8");
            Part[] parts = {fp};


            MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(mre);


            int status = httpClient.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            } else {
                response = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }

        return response;


    }


    public String get(String url) {


        GetMethod method = new GetMethod(url);


        try {
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {

                byte[] responseBody = method.getResponseBody();
                return new String(responseBody);

            } else {

                return null;

            }

        } catch (Exception e) {

            logger.error("[HTTP GET]" + url);
            logger.error(e);

        } finally {

            method.releaseConnection();
        }

        return null;
    }


    public String post(String url, String str) {
        PostMethod postMethod = new PostMethod(url);
        try {

            RequestEntity requestEntity = new StringRequestEntity(str, "text/xml", "UTF-8");
            postMethod.setRequestEntity(requestEntity);

            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {

                byte[] responseBody = postMethod.getResponseBody();
                return new String(responseBody);

            } else {

                return null;

            }

        } catch (Exception e) {

            logger.error("[HTTP POST]" + url);
            logger.error(e, e);

        } finally {

            postMethod.releaseConnection();
        }

        return null;
    }


    public String post(String url, Map<String, String> params) {


        //String serverCode = params.get("serverCode");
        //if(serverCode ==null)serverCode="";

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String name : params.keySet()) {

            String nameVal = params.get(name);
            list.add(new NameValuePair(name, nameVal));

        }


        NameValuePair[] paramsArray = (NameValuePair[]) list.toArray(new NameValuePair[list.size()]);

        PostMethod postMethod = new PostMethod(url);
        String response = null;


        try {


            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            postMethod.setRequestBody(paramsArray);


            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {

                response = postMethod.getResponseBodyAsString();

            }


        } catch (SocketTimeoutException e1) {
            logger.warn("[HTTP POST] SocketTimeoutException" + url);
        } catch (SocketException e1) {
            logger.warn("[HTTP POST] SocketException" + url);
        } catch (Exception e) {

            logger.error("[HTTP POST]" + url);
            logger.error(e, e);


        } finally {

            postMethod.releaseConnection();

        }


        return response;


    }


    public String downLoadFile(String url, File file) {

        String response = "";

        GetMethod httpGet = new GetMethod(url);


        try {

            httpClient.executeMethod(httpGet);
            InputStream in = httpGet.getResponseBodyAsStream();
            FileOutputStream out = new FileOutputStream(file);

            byte[] b = new byte[4096];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
                System.out.print(".");
            }
            in.close();
            out.close();

            response = "success";

        } catch (HttpException e) {
            response = "error-exception";
            e.printStackTrace();

        } catch (IOException e) {
            response = "error-exception";
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }

        return response;


    }

    public static void main(String[] args) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        HttpRequest httpRequest = new HttpRequest();
        String info = httpRequest.post("http://localhost:8080/myServer/serveraction.do", map);

    }
}

