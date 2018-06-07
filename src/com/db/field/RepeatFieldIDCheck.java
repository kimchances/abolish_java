package com.db.field;

import java.util.HashMap;
import java.util.LinkedList;

import com.db.SQLClient;
/**
 * Base表名字查询ID,查询无结果新增再查询
 * @author Lucas
 * @date 2017年6月8日 上午11:11:12
 */
public class RepeatFieldIDCheck {

	SQLClient sqlClient;

	public RepeatFieldIDCheck(SQLClient sqlClient) {
		this.sqlClient = sqlClient;
	}
	
	/**
	 * 查询ID
	 * @return lastId
	 * @throws Exception
	 */
	public String findCreateID(String table,String name) throws Exception {
		
		LinkedList<HashMap<String, String>> idList = this.findID(table, name);

		if(idList.size()>0) {
			return idList.get(0).get("findId");
		} else {
			this.add(table, name);
			idList = this.findID(table, name);
			return idList.get(0).get("findId");
		}
		 
	}
	
	
	public LinkedList<HashMap<String, String>> findID(String table,String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("   ID AS findId  ");
		sql.append(" FROM  ");
		sql.append("   `"+table+"`  ");
		sql.append(" WHERE  ");
		sql.append("   `NameZh-CN` = ?  ");
		sqlClient.addParameter(name);

		
		return sqlClient.execQuery(sql.toString());
	}
	
	public int add(String table,String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO  ");
		sql.append("   `"+table+"`  ");
		sql.append("   (  ");
		sql.append("   `NameZh-CN`  ");
		sql.append("   )  ");
		sql.append(" VALUES (  ");
		sql.append("   ?  ");
		sql.append("   )  ");
		sqlClient.addParameter(name);
		
		return sqlClient.execUpdate(sql.toString());
	} 
	
	public int addZone(String table,String name,String keyCode) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO  ");
		sql.append("   `"+table+"`  ");
		sql.append("   (  ");
		sql.append("   `NameZh-CN`  ");
		sql.append("   ,KeyCode  ");
		sql.append("   )  ");
		sql.append(" VALUES (  ");
		sql.append("   ?  ");
		sql.append("   ,?  ");
		sql.append("   )  ");
		sqlClient.addParameter(name);
		sqlClient.addParameter(keyCode);
		return sqlClient.execUpdate(sql.toString());
	} 
	
}
