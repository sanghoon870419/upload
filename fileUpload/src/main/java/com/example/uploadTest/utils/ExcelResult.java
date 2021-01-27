package com.example.uploadTest.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("excelReader") 
public class ExcelResult{
/* excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
		  MultipartFile excelFile =request.getFile("excelFile");
				  System.out.println("---------kita1--------");
				  System.out.println("엑셀 파일 업로드 컨트롤러");
			  if(excelFile==null || excelFile.isEmpty()){
			      throw new RuntimeException("엑셀파일을 선택 해 주세요.");
			  }
		  	System.out.println("---------kita2--------");
		  
		  	File destFile = new File("C:\\test\\"+excelFile.getOriginalFilename());

			  try{
			      excelFile.transferTo(destFile);
			  }catch(IllegalStateException | IOException e){
			      throw new RuntimeException(e);
			  }
		  
			  System.out.println("---------kita3--------");
			  //Service 단에서 가져온 코드 
			  ExcelReadOption excelReadOption = new ExcelReadOption();
			  excelReadOption.setFilePath(destFile.getAbsolutePath());
			  excelReadOption.setOutputColumns("A");
			  excelReadOption.setStartRow(2);
		  
		  	List<Map<String, String>>excelContent =ExcelRead.read(excelReadOption);
			  	String scodeList = "";
			  	ArrayList<String> dataList = new ArrayList<String>();
			  	ExcelData excelData = null;
		  	for(Map<String, String> article: excelContent){
				  scodeList = article.get("A");
				  excelData  = new ExcelData();    
				  //ExcelDate값저장
				  excelData.setScode(scodeList);	
				  dataList.add(excelData.getScode().toString());
				  System.out.println(excelData.getScode());
			  		}*/
}
