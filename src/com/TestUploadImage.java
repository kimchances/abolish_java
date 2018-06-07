package com;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class TestUploadImage {
	
	static 	int maxId = 0;
	
	private File realPathFile ;
	
		
	
	public void init(HttpServletRequest request ) {
		
		ServletContext servletContext =request.getServletContext();
	
		realPathFile =   new File( servletContext.getRealPath(File.separator) );
		realPathFile =   new File( realPathFile , "image-user");
			
		if(!realPathFile.exists()){
			realPathFile.mkdirs();
		}
		
		System.out.println(realPathFile.getAbsolutePath());

	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		init(request);	
		upload(request,response );
		
	}
	
	protected void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("============== FunUploadImage ================");
		
		String responseString = "";
		String userId = "";
		String point = "0";
		
		HttpSession httpSession = request.getSession();
		
		
		
		
		
		Date d  = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddhhmmssSSS");
		String fontName =  sdf1.format(d);
		int count  = -1;
		
		
		
		
		JSONListFormat  jFormat = new JSONListFormat();
		jFormat.setServerCode(request.getParameter("serverCode"));
    	jFormat.setServerMsg("error");
	
    	HashMap<String ,String > dateMap =  new HashMap<String ,String > ();
    	
    	
		if(responseString==""){
			
		
			
			
			try {
			
				
				
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);


				List<FileItem> multiparts = upload.parseRequest(request);

				for (FileItem item : multiparts) {
					
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize() / 8;
					
					//System.out.println("fieldName=" + fieldName);
					//System.out.println("fileName=" + fileName);
					//System.out.println("contentType=" + contentType);
					//System.out.println("isInMemory=" + isInMemory);
					//System.out.println("sizeInBytes=" + (sizeInBytes) + "Byte");

					String subFileName = "";
				
					if (!item.isFormField()) {
						
						if("image/jpeg".equals(contentType))subFileName = "jpg";
						if("image/png".equals(contentType))subFileName  = "png";
						
						if(subFileName.length() >0){
							
							count++;
							
							String newName = userId +"."+subFileName;
							File dataFile = new File(realPathFile ,newName);
							//System.out.println(dataFile.getAbsolutePath());
							item.write(dataFile);
							responseString = "success";
						
						
							dateMap.put("fileName", newName);
							
						
							
					
							
							
						}
						
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				
			}
		
		}
		
		if("success".equals(responseString)){
			
			try {
				
				double pointValue = 0.0;
				
				dateMap.put("point",String.valueOf(pointValue) );
				jFormat.addObject(dateMap);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			
		}
		
		
		
		
		if(responseString!="")jFormat.setServerMsg(responseString);
				
		
		response.getWriter().print(jFormat.toString());
		
		
	

	}
	

}
