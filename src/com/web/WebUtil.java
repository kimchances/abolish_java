package com.web;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.CheckUtil;
import com.JSONListFormat;
import com.db.SQLClient;


public class WebUtil {

	public static final String SUCCESS_SHOW_MSG = "处理完成";
	
	static{
		
	}
	
	
	
	
	
	
	
	
	public static  JSONListFormat  createJSONListFormat( HttpServletRequest req ,boolean isPage){
		
		
		HttpSession httpSession = req.getSession();
		String languageName = (String) httpSession.getAttribute("languageName");

		JSONListFormat  jsonListFormat = new JSONListFormat();
		jsonListFormat.setServerCode(req.getParameter("serverCode"));
		jsonListFormat.setShowMsg("");
		
		if(languageName==null) languageName= "NameZh-CN";
		jsonListFormat.setLanguageName(languageName);
		
	
		
		
		if(isPage){
			
			String   start  = req.getParameter("start");
			String   offset = req.getParameter("offset");
			
			if(CheckUtil.isInteger(start )==false) start= "0";
			if(CheckUtil.isInteger(offset)==false)offset="10";
			jsonListFormat.setLimit(Integer.valueOf(start), Integer.valueOf(offset));
			
		}
		
		
		return jsonListFormat;
		
		
		
	}
	

	
	
	
	
	
	public static boolean checkVerifyCode( HttpServletRequest req){
		
		String reqVerifyCode = req.getParameter("verifyCode");
		
		HttpSession httpSession = 	req.getSession();
		String httpSessiontVerifyCode = (String)httpSession.getAttribute("verifyCode");
				
		if(         reqVerifyCode == null) return false;
		if(httpSessiontVerifyCode == null) return false;
		if(reqVerifyCode.length() < 4    ) return false;
		
		httpSessiontVerifyCode = httpSessiontVerifyCode.toUpperCase();
		         reqVerifyCode = reqVerifyCode.toUpperCase();
		if(httpSessiontVerifyCode.equals(reqVerifyCode)){
			return true;
		}
		
		return false;
		
		
	}
	
	
	
	public static  int optInt(HttpServletRequest req , String name , int defaut){
		
		String  value  =  req.getParameter(name);
		if(CheckUtil.isInteger(value))
			return Integer.valueOf(value);
		else
			return defaut;
		
	}
	
	
	public static  void loadPageInfo(HttpServletRequest req , SQLClient sqlClient){
		
		int start     = optInt(req,"start"    ,0);
		int offset    = optInt(req,"offset"   ,10);
		int dataTotal = optInt(req,"dataTotal",0);
		sqlClient.setPageInfo(  start , offset  ,dataTotal);
	}
	
	
	public static  File findWebPathFile(HttpServletRequest req  , String webURL){
		
		
		ServletContext servletContext = req.getServletContext();
		File realPathFile =   new File( servletContext.getRealPath(File.separator) );
		if(webURL.startsWith("/")) webURL = webURL.substring(1, webURL.length());
		String path = webURL.replaceAll(Matcher.quoteReplacement(File.separator), "/");
		File urlFile = new File ( realPathFile ,path);
		urlFile.getParentFile().mkdirs();
		return urlFile;
		
	}
	
	
	
	
	

}
