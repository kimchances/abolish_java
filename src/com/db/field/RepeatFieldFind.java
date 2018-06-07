package com.db.field;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.db.SQLClient;
/**
 * 查重判断
 * @author Lucas
 * @date 2017年6月8日 下午7:13:51
 */
public class RepeatFieldFind {

	SQLClient sqlClient;

	public RepeatFieldFind(SQLClient sqlClient) {
		this.sqlClient = sqlClient;
	}
	
	/**
	 * 单个字段查询重复
	 * @param table
	 * @param field
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public boolean IsRepeatOne(String table,String field,String value) throws Exception {
		LinkedList<HashMap<String, String>> data = findOneRepeat(table, field, value);
		if(data.size()>0) return true;
		
		return false;
	} 
	/**
	 * 多个字段查询重复
	 * @param table
	 * @param fields
	 * @return
	 * @throws Exception 
	 */
	public boolean IsRepeatMany(String table,HashMap<String, String> fields) throws Exception {
		LinkedList<HashMap<String, String>> data = findManyRepeat(table, fields);
		if(data.size()>0) return true;
		
		return false;
	}  
	
	// 单条件查重 SQL
	public LinkedList<HashMap<String, String>> findOneRepeat(String table,String field,String value) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("   ID AS findId  ");
		sql.append(" FROM  ");
		sql.append("   `"+table+"`  ");
		sql.append(" WHERE  ");
		sql.append("   `"+field+"` = ?  ");
		sqlClient.addParameter(value);

		return sqlClient.execQuery(sql.toString());
	}
	
	// 多条件查重 SQL
	public LinkedList<HashMap<String, String>> findManyRepeat(String table,HashMap<String, String> fields) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("   ID AS findId  ");
		sql.append(" FROM  ");
		sql.append("   `"+table+"`  ");
		sql.append(" WHERE  1 = 1"); 
		for(Map.Entry<String, String> entry:fields.entrySet()) {
			sql.append(" AND `"+entry.getKey()+"` = ?"); 
			sqlClient.addParameter(entry.getValue());
		}
		
		return sqlClient.execQuery(sql.toString());
	}  
	
	public static void main(String[] args) throws Exception {
		SQLClient sqlClient = new SQLClient();
		RepeatFieldFind repeatFieldFind = new RepeatFieldFind(sqlClient);
		
		String table = "_BankBase";
		String field = "NameZh-CN";
		String value = "中国银行";
		System.out.println(repeatFieldFind.IsRepeatOne(table, field, value));
		
		String field2 = "NameZh-EN";
		String value2 = "ChineseBack";
		HashMap<String, String> fields = new HashMap<>();
		fields.put(field, value);
		fields.put(field2, value2);
		
		System.out.println(repeatFieldFind.IsRepeatMany(table, fields));
		
	}

}
