package com;

import org.json.JSONObject;

import com.http.HttpRequest;

public class IPToAddress {
	
	public static String daAction(String ip) throws Exception{
		
		if("0:0:0:0:0:0:0:1".equals(ip)){
			return "服务器本地";
		}else{
			HttpRequest httpRequest = new HttpRequest();
			String string =httpRequest.get("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip);
			
			int aa= string.indexOf("{");
			string = string.substring(aa);
			
			JSONObject jsonObject = new JSONObject(string);
			System.out.println(jsonObject.toString(4));
			
			String province = jsonObject.getString("province");
			String city = jsonObject.getString("city");
			
			return province +"-"+ city;
		}
		
	}
	
	
	

	public static void main(String[] args) throws Exception {
		
		System.out.println(IPToAddress.daAction("61.11.128.0"));
	}

	
	
	
}
