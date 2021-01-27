package com.example.uploadTest.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;




@Service
public class ExcelUpload {

    public List<ExcelData> upload(MultipartRequest request) {
		MultipartFile file = request.getFile("excelFile");
		File destFile = new File("C:\\test\\"+file.getOriginalFilename());
			if (file == null || file.isEmpty()) {
				throw new RuntimeException("ファイルに内容がありません。");
			}
			try {
				file.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e);
			}

			ExcelReadOption excelReadOption = new ExcelReadOption();
			excelReadOption.setFilePath(destFile.getAbsolutePath());
			excelReadOption.setOutputColumns("A");
			excelReadOption.setStartRow(2);
			List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);
			String scodeList = "";
			List<ExcelData> dataList = new ArrayList<>();
	  		for(Map<String, String> article: excelContent){
			  scodeList = article.get("A");
			  ExcelData excelData = new ExcelData();
			  excelData.setScode(scodeList);
			  dataList.add(excelData);
	  		}
	  		return dataList;
		}	
	}