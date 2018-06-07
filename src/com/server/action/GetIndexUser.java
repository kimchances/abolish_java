package com.server.action;

import com.JSONListFormat;
import com.db.SQLClient;
import com.server.db.serverDB;
import com.web.WebUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 查询主页的用户信息
 * @author kim
 *
 */
public class GetIndexUser implements Controller {

	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp)  throws Exception {
		
		String responseMessage = "";
		
		JSONListFormat  jsonFormat = WebUtil.createJSONListFormat(req, false);
		
		
		SQLClient sqlClient = new SQLClient();
		serverDB customDB = new serverDB(sqlClient);
		if(responseMessage==""){
			
			sqlClient.setAutoCommit(false);
			
			try{
				LinkedList<HashMap<String, String>> data = customDB.findIndexUser();
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
