package com.server;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.JSONListFormat;
import com.server.action.GetIndexCategory;
import com.server.action.GetIndexUser;
import com.server.action.GetIndexProjects;
import com.server.action.GetProjectDetail;

public class WebAction implements Controller {

    private Logger logger = Logger.getLogger(this.getClass());
    private Map<String, Controller> controllerMap = new HashMap<String, Controller>();

    public WebAction() {
        //拿主页的用户数据
        controllerMap.put("getIndexUser", new GetIndexUser());
        //拿主页的分类数据
        controllerMap.put("getIndexCategory", new GetIndexCategory());
        //拿主页的项目数据
        controllerMap.put("getIndexProjects", new GetIndexProjects());
        //拿主页的项目数据
        controllerMap.put("getProjectDetail", new GetProjectDetail());
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {

        res.setContentType("text/json");
        res.setCharacterEncoding("UTF-8");

        String serverCode = req.getParameter("serverCode");
        if (serverCode == null)
            serverCode = "";

        // 打印出前端传来的值


        StringBuffer params = new StringBuffer();
        params.append("\r\n[RequestURL]\t" + req.getRequestURL());
        params.append("\r\n[IP]\t\t" + req.getRemoteAddr());


        String charset = req.getCharacterEncoding();
        Enumeration<String> enumeration = req.getParameterNames();

        while (enumeration.hasMoreElements()) {

            String name = enumeration.nextElement();
            String value = req.getParameter(name);

            if (charset == null)
                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
            if (value.length() > 51)
                value = value.substring(0, 50) + "...";

            String parmStr = "\r\n[" + name + "]\t" + value;
            params.append(parmStr);


        }

        System.out.println(params);


        try {


            Controller actionController = controllerMap.get(serverCode);
            if (actionController == null) {

                logger.error(params);
                logger.error("error-serverCode");

                JSONListFormat jFormat = new JSONListFormat();
                jFormat.setServerCode(serverCode);
                jFormat.setServerMsg("error-serverCode");
                jFormat.setShowMsg("找不到此API");
                PrintWriter out = res.getWriter();
                out.println(jFormat.toString());
                out.close();

            } else {
                return actionController.handleRequest(req, res);
            }

        } catch (Exception e) {


            params.insert(0, serverCode);
            params.append("\r\n[Exception]\t" + e);

            logger.error(params, e);
            JSONListFormat jFormat = new JSONListFormat();
            jFormat.setServerCode(serverCode);
            jFormat.setServerMsg(e.toString());
            jFormat.setShowMsg("不合法的请求");
            PrintWriter out = res.getWriter();
            out.println(jFormat.toString());
            out.close();
        }

        return null;

    }

}