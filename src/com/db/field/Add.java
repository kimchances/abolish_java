package com.db.field;

import java.util.HashMap;
import java.util.LinkedList;

import com.db.SQLClient;
/**
 * 查重判断
 * @author Lucas
 * @date 2017年6月8日 下午7:13:51
 */
public class Add {

	SQLClient sqlClient;

	public Add(SQLClient sqlClient) {
		this.sqlClient = sqlClient;
		
	}
	
	public static void main(String[] args) throws Exception {
		SQLClient sqlClient = new SQLClient();
		Add add = new Add(sqlClient);
		
		LinkedList<HashMap<String, String>> data = add.findDealer();
		while(data.size()>0) {
			String dealerId = data.remove().get("ID");
//			String carAgentId = add.getCarAgentID();
//			add.updateCarAgent(dealerId, carAgentId);
			System.out.println(dealerId);
		}
	}
	
	public int updateCarAgent(String dealerId,String carAgentId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE  ");
		sql.append("   DealerMain  ");
		sql.append(" SET  ");
		sql.append("   DealerMain.CarMainAgentID = ? ");
		sql.append(" WHERE  ");
		sql.append("   DealerMain.ID = ?  ");
		sqlClient.addParameter(carAgentId);
		sqlClient.addParameter(dealerId);


		return sqlClient.execUpdate(sql.toString());
		
	}
	
	public LinkedList<HashMap<String, String>> findDealer() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append("   DealerMain.ID   ");
		sql.append(" FROM  ");
		sql.append("   DealerMain  ");
		sql.append(" WHERE  ");
		sql.append("   DealerMain.ExamineFlag = 1  ");
		sql.append(" AND DealerMain.Disabled = 0  ");
		sql.append(" AND DealerMain.CarMainAgentID = 1  ");

		
		return sqlClient.execQuery(sql.toString());
	}
	
	public String getCarAgentID() throws Exception {
		addCarAgent();
		LinkedList<HashMap<String, String>> data = findLastAddId();
		
		return data.get(0).get("lastId");
	}
	
	
	// 新增代理人表
	public int addCarAgent() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO  ");
		sql.append("   CarMainAgent  ");
		sql.append("   ( ");
		sql.append("   CarMainAgent.CategoryBaseCatetoryID ");
		sql.append("   )  ");
		sql.append(" VALUES ( ");
		sql.append("   3 ");
		sql.append(" ) ");

		return sqlClient.execUpdate(sql.toString());
		
	}
	
	/**
	 * 查询最后一笔新增的Id
	 * @return lastId
	 * @throws Exception
	 */
	public LinkedList<HashMap<String, String>> findLastAddId() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT LAST_INSERT_ID() AS lastId ");
		
		return sqlClient.execQuery(sql.toString());
	}

}
