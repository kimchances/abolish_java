package com.web;

import java.text.SimpleDateFormat;
import java.util.Random;


public class RandomLetterUtil {
	public static String getLetter() {
		//获取字母
		String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Random random = new Random();
		
		//获取日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new java.util.Date());
		String[] dateArr = date.split("-");
		
		StringBuilder sBuilder = new StringBuilder();
		
		//生成字符串
		for(int i=0;i<7;i++){
			if(i!=2){
				int index = random.nextInt(26);
				sBuilder.append(letters[index]);
			}else{
				sBuilder.append(dateArr[0]);
				sBuilder.append(dateArr[1]);
				sBuilder.append(dateArr[2]);
			}
		}
		
		return sBuilder.toString();
	}
}
