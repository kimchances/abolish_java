package com.db.util;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import com.db.SQLClient;

public class SQLFormatInsert {
	
	private String  table = "";
	
	
	private LinkedList<String >  nameList  = new LinkedList<String > ();
	private LinkedList<String > valueList  = new LinkedList<String > ();
	
	
	private SQLClient sqlClient ;
	
	public SQLFormatInsert(SQLClient sqlClient){
		this.sqlClient = sqlClient ;
		
	}
	
	
	public void setTable(String table0 ){
		
		table = table0.trim();
	}
	
	

	public void addValue(Map<String,String> map){
		
		for (String name : map.keySet()) {
			
			String value = map.get(name);
			
			  nameList.add("`"+name.trim()+"`");
			 valueList.add("?");
			 
			 sqlClient.addParameter(value.trim());
			 
		}

	}
	
	
	
	public int addValue(String name ,String value){
		
		 nameList.add("`"+name.trim()+"`");
		 valueList.add("?");
		 sqlClient.addParameter(value.trim());
		 
		return nameList.size();
		
	}
	
	public int addAction(String name ,String value){
		
		
		  nameList.add("`"+name.trim()+"`");
		 valueList.add(" "+ value.trim()+" ");
		
		return nameList.size();
		
	}
	
	
	public String toString(){
		
		StringBuffer sql = new StringBuffer();
		
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		
		sql1.append(" "+nameList.remove()+" ");
		while( nameList.size() > 0 )sql1.append(","+nameList.remove()+" ");
		sql2.append(" "+valueList.remove()+" ");
		while( valueList.size() > 0 )sql2.append(","+valueList.remove()+" ");
		
		sql.append("  INSERT INTO  `"+ table +"`  (  "  );
		sql.append( sql1 );
		sql.append("  )    ");
		sql.append("  VALUES (    ");
		sql.append( sql2 );
		sql.append("  )    ");
		
		return sql.toString();
		
	}
	
	public int execUpdate() throws SQLException{
		return sqlClient.execUpdate(toString());
	}		
	
}
