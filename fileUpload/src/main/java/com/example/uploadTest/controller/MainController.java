package com.example.uploadTest.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.uploadTest.repository.RenrakutusuchiListRepository;

import com.example.uploadTest.utils.ExcelData;
import com.example.uploadTest.utils.ExcelRead;
import com.example.uploadTest.utils.ExcelReadOption;
import com.example.uploadTest.utils.ExcelUpload;

import lombok.RequiredArgsConstructor;


@Controller
public class MainController {
	 private static final String EXCEL_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	
	  @GetMapping("/")
	  public String home() {
		  return "index";
	  }
 
	 @ResponseBody
	 @RequestMapping(value="/excelUploadAjax",method = RequestMethod.POST)
	 public ModelAndView excleUpload(MultipartRequest request)  throws Exception{
		ExcelUpload excelupload = new ExcelUpload();
		List<ExcelData> excelData = excelupload.upload(request);
	    ArrayList<String> datas = new ArrayList<>();
		for(int i = 0; i < excelData.size(); i++) {
			String ss = excelData.get(i).getScode();	
			datas.add(ss);
		}
		 ModelAndView view = new ModelAndView();
		 view.addObject("datas",datas);
		 view.setViewName("list");
  	
		return view;
	  	}

	 @GetMapping(path="/download/sample", produces = "application/vnd.ms-excel")
	public String downloadExcel() { 
		return "sampleXls"; 
	}
}
