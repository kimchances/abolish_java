package com.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;



public class SQLClient{
	
	
	
	

	
	private Logger logger = Logger.getLogger(this.getClass());
	private Connection conn;
	protected LinkedList<String> parameterList = new LinkedList<String>(); 
	
	private boolean debug = false;
	
	private String   sqlString  ="";
	private String   sqlString1 ="";
	private String   dataSourceName  = "dataSource";
	
	protected int start     = 0;
	protected int offset    = 0;
	protected int dataTotal = 0;
	
	public SQLClient()
	{
		
	}
	public SQLClient( String name)
	{
		dataSourceName = name;
	}
	
	public int getDataTotal(){
		return this.dataTotal;
	}
	
	public void setDebug(boolean _debug)
	{
		debug = _debug;
	}
	
	public void setPageInfo( int start ,int offset  ,int dataTotal )
	{
		this.start     = start;
		this.offset    = offset;
		this.dataTotal = dataTotal;
	}
	

	
	
	public void addParameter(String _parameter)
	{
		parameterList.addLast(_parameter);
	}
	
	
	public int execUpdate(String _sql) throws SQLException
	{
		
		int updateIdx = -1;
		try
		{
			if(conn == null)
			{
				conn = ConnectionPool.getConnection(dataSourceName);
			}
			
			PreparedStatement pstmt = conn.prepareStatement(_sql);
			int parameterIdx = 1;
			while(parameterList.size() > 0)
			{
				pstmt.setString(parameterIdx++, parameterList.removeFirst());
			}
			
			
			sqlString = pstmt.toString();
				
			long time1 = System.currentTimeMillis();
			updateIdx = pstmt.executeUpdate();
			
			long time2 = System.currentTimeMillis();
			double  totalSec = (time2 - time1) /1000.0;
					
			showSQL(sqlString+"\ntotal:"+updateIdx +" ("+ totalSec +" sec)");
			
			if(conn.getAutoCommit())
			{
				conn.close();
				conn = null;
			}
			
			
		}
		catch(SQLException e)
		{
			
			logger.error(sqlString);
			
			parameterList.clear();
			closeConnection();
			throw e;
		}
		
		parameterList.clear();
		return updateIdx;
	}
	
	

	public LinkedList<HashMap<String, String>> execQuery(String sql) throws SQLException{
		
		LinkedList<HashMap<String, String>> resultList = new LinkedList<HashMap<String, String>>() ;
		if(this.offset >0){
			
			dataTotal = execQuery(resultList , sql ,true ,start,offset ,dataTotal);
			this.start  = 0;
			this.offset = 0;
			
		}else{ 
			dataTotal = execQuery(resultList , sql ,false ,0,0,0);
		}
		return resultList;
		
	}
	
	
	
	public int execQuery(  List<HashMap<String, String>> resultList,   String sql ,  int start ,int offset) throws SQLException{
		return execQuery(resultList , sql ,true ,start,offset,0);
	}
	

	public int execQuery(  List<HashMap<String, String>> resultList,  String sql , boolean isPage, int start ,int offset ,int total) throws SQLException{
	
		
		
		try
		{
			
			
			if(conn == null)
			{
				conn = ConnectionPool.getConnection(dataSourceName);
			}
			
			
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			int parameterIndex  = 0;
			
		
			long time1 = System.currentTimeMillis();
			
		
			if(isPage &total==0){
				
				
				
				String sqlTemp  = "SELECT COUNT(0) FROM ("+sql+") AS tempTable ";
				preparedStatement = conn.prepareStatement(sqlTemp);
				for (String params : parameterList) {
					preparedStatement.setString(++parameterIndex, params);
				}
				
				showSQL(preparedStatement.toString());
				resultSet = preparedStatement.executeQuery();         // 執行取得該SQL指令結果
				resultSet.first();
				total = resultSet.getInt(1);
					
				parameterIndex = 0;
				resultSet.close();			
				preparedStatement.close();
				
				sqlString1 = sqlString;
				
		
				
			}
			if(isPage) {
				sql = sql+ " LIMIT "+start+","+offset;
			}
			
			
			long time2 = System.currentTimeMillis();
			
			
			
			preparedStatement = conn.prepareStatement(sql);
			for (String params : parameterList) {
				preparedStatement.setString(++parameterIndex, params);
			}
			parameterList.clear();
			
			String preSQL1 = preparedStatement.toString();
			sqlString = preSQL1;
		
			resultSet = preparedStatement.executeQuery();        
			
		
			
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();   
			int columnCount = resultSetMetaData.getColumnCount();            
		
			
			long time3 = System.currentTimeMillis();				
			
			while(resultSet.next())
			{
				
				HashMap<String,String> columnObject = new HashMap<String,String>();
				resultList.add(columnObject);

				
				for (int i = 0; i < columnCount; i++) {
						
					String columnName  = resultSetMetaData.getColumnLabel(i+1);
					String      value  =         resultSet.getString(i+1);
					
					if (value == null) value="";
				
					columnObject.put(columnName, value);
					
						
				}
				
				
			}
		
			long time4 = System.currentTimeMillis();
			
			resultSet.close();			
			preparedStatement.close();
			
			if(total == 0) total = resultList.size();
			
			if(conn.getAutoCommit())
			{
				conn.close();
				conn = null;
			}
			
			
		
			
			double querySec1 = (time2 - time1) /1000.0;
			double querySec2 = (time3 - time2) /1000.0;
			double   loadSec = (time4 - time3) /1000.0;
			double  totalSec = (time4 - time1) /1000.0;
			
			
			
			if(totalSec > 6){
				
				
				logger.warn("##########  Query Too Long Time ########## ");
				
				logger.warn("[query1]\t" +querySec1+" Sec");
				logger.warn("[query2]\t" +querySec2+" Sec");
				logger.warn("[parse ]\t" +  loadSec+" Sec");
				logger.warn("[dataTotal]\t" + totalSec+" Sec");
				logger.warn("[count]\t" +  total);
			
				
				StackTraceElement[] stack = new Exception("executeQuery").getStackTrace();
				for (int i = 0; i < stack.length; i++) {
					if (stack[i].toString().startsWith("com"))	logger.warn(stack[i]);
				}
				
				
				logger.warn(sqlString1+"\r\n");
				logger.warn(sqlString+"\r\n");
				
			
				
			}
			
			showSQL(preSQL1+"\ntotal:"+total +" ("+ totalSec +" sec)");
		
			
		}
		catch(Exception e)
		{
			
			parameterList.clear();
			logger.error(sqlString+"\r\n");
			closeConnection();
			throw e;
		}	
		
		
		return total;
		
	}
	
	
	
	
	/**
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public LinkedList<LinkedList<HashMap<String, String>>> executeQueryMore(String sql) throws SQLException{
		LinkedList<LinkedList<HashMap<String, String>>> resultLists = new LinkedList<>();

		if(conn == null){
			conn = ConnectionPool.getConnection(dataSourceName);
		}
		
		CallableStatement callableStatement = null;
//		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int parameterIndex  = 0;
		
//		preparedStatement = conn.prepareStatement(sql);
		callableStatement = conn.prepareCall(sql);
		for (String params : parameterList) {
			callableStatement.setString(++parameterIndex, params);
		}
		parameterList.clear();
		
		showSQL(callableStatement.toString());
		callableStatement.executeQuery();
		
		resultSet = callableStatement.getResultSet();   
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();   
		int columnCount = resultSetMetaData.getColumnCount();            
		LinkedList<HashMap<String, String>> resultList = new LinkedList<HashMap<String, String>>();
		while(resultSet.next()){
			HashMap<String,String> columnObject = new HashMap<String,String>();
			resultList.add(columnObject);
			for (int i = 0; i < columnCount; i++) {
				String columnName  = resultSetMetaData.getColumnName(i+1);
				String      value  =         resultSet.getString(columnName);
				if (value == null) value="";
				columnObject.put(columnName, value);
			}
		}
		resultLists.add(resultList);
		
		while(callableStatement.getMoreResults()){
			resultSet = callableStatement.getResultSet();
			ResultSetMetaData resultSetMetaData0 = resultSet.getMetaData();   
			int columnCount0 = resultSetMetaData0.getColumnCount();            
			LinkedList<HashMap<String, String>> resultList0 = new LinkedList<HashMap<String, String>>();
			while(resultSet.next()){
				HashMap<String,String> columnObject = new HashMap<String,String>();
				resultList0.add(columnObject);
				for (int i = 0; i < columnCount0; i++) {
					String columnName  = resultSetMetaData0.getColumnName(i+1);
					String      value  =         resultSet.getString(columnName);
					if (value == null) value="";
					columnObject.put(columnName, value);
				}
			}
			resultLists.add(resultList0);
		}
	
		
		if(conn.getAutoCommit()){
			conn.close();
			conn = null;
		}
		parameterList.clear();
		closeConnection();
		
		return resultLists;
	}
	
	

	
	
	
	
	
	
	
	public void commit()
	{
		if(conn != null)
		{
			try {
				conn.commit();
				conn.setAutoCommit(true);
				conn.close();
				conn = null;
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			
		}
	}


	
	
	public void rollBack()
	{
		if(conn != null)
		{	
			try {
				conn.rollback();
				conn.setAutoCommit(true);
				conn.close();
				conn = null;
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			
		}
	}
	
	public void setAutoCommit(boolean _commit)
	{
		
		try {
			conn = ConnectionPool.getConnection(dataSourceName);
			conn.setAutoCommit(_commit);
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public boolean getAutoCommit()
	{
		
		
		try {
			return conn.getAutoCommit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	
	public void closeConnection()
	{
		try 
		{
			if(conn != null)
			{
				if(!conn.getAutoCommit())
				{
					conn.rollback();
					conn.setAutoCommit(true);
				}
				conn.close();
				conn = null;
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	

	public void showSQL(String sql){
		
		sqlString = sql;
	
		int idx = sqlString.indexOf("ServerPreparedStatement");
		if(idx>1){
			sqlString = sqlString.substring(idx+23);
		}
		
		
		if(debug){
			logger.info( sqlString);
		}else{
			logger.debug(sqlString);
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		
		
		
		String sql = " SELECT 1 FROM BFSMOpts      ";
		SQLClient sqlClient = new SQLClient("dataSource");
		
		sqlClient.setPageInfo(0,20,0);
		
		
		LinkedList<HashMap<String, String>> list = sqlClient.execQuery(sql);
		int count = sqlClient.getDataTotal();
		
		System.out.println("[dataTotal] "+ count + " [list size]"  +list.size());
		
		
	}
	
	
	
}
