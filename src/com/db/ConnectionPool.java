package com.db;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
	
   
    public static HashMap<String,ComboPooledDataSource> dataSourceMap =  new HashMap<String,ComboPooledDataSource>();
    
    static{
		
		String fileName = "beans-config-C3P0.xml";
		File file = new File(fileName);
	
		if(file.exists()){
			
				FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(fileName);
				String[] names = context.getBeanDefinitionNames();
				for (int i = 0; i < names.length; i++) {
					
					Object beanObject = context.getBean(names[i]);
					if (beanObject instanceof ComboPooledDataSource) {
						dataSourceMap.put(names[i], (ComboPooledDataSource)beanObject);
					}
				}
				context.close();
			 
			 
		}else{
			
			    ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext(fileName);
				
				String[] names = context.getBeanDefinitionNames();
				for (int i = 0; i < names.length; i++) {
					
					Object beanObject = context.getBean(names[i]);
					if (beanObject instanceof ComboPooledDataSource) {
						dataSourceMap.put(names[i], (ComboPooledDataSource)beanObject);
					}
				}
				context.close();
		}
		
	
		
		
		
		
	}
	

   
   
    
    
    public static Connection getConnection(String name) throws SQLException      {   
    	
     
        return dataSourceMap.get(name).getConnection();
         
      
    }   
    	
    	
    	
    	
    	
    	
        
    
    
    
    
    
    
}