package com.server.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.CheckUtil;
import com.JSONListFormat;
import com.code.MD5Util;
import com.db.SQLClient;
import com.web.WebUtil;
import com.server.db.serverDB;

/**
 * 查询主页的分类
 * @author kim
 *
 */
public class GetProjectDetail implements Controller {

	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp)  throws Exception {
		
		String responseMessage = "";
		
		JSONListFormat  jsonFormat = WebUtil.createJSONListFormat(req, false);
		String projectId = req.getParameter("projectId");
		
		SQLClient sqlClient = new SQLClient();
		serverDB customDB = new serverDB(sqlClient);
		if(responseMessage==""){
			
			sqlClient.setAutoCommit(false);
			
			try{
				LinkedList<HashMap<String, String>> data = customDB.findProjectDetail(projectId);
				while(data.size() > 0){
					HashMap<String, String> map = data.remove();
					jsonFormat.addObject(map);
				}
				
				sqlClient.commit();
				
				
			}catch(Exception e){
				sqlClient.rollBack();
				throw e;
			}
			
		}
		
		String showMessage = WebUtil.SUCCESS_SHOW_MSG;
		if(responseMessage == "") responseMessage = "success";
//		jsonFormat.setServerMsg(responseMessage);
//		jsonFormat.setShowMsg(showMessage);
		PrintWriter out = resp.getWriter();
		out.println(jsonFormat.toString());
		out.close();
		return null;
	}
	

}
