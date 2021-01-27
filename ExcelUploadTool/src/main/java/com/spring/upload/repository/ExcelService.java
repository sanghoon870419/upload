package com.spring.upload.repository;

import org.springframework.web.multipart.MultipartFile;


import com.spring.upload.vo.ExcelData;

import java.util.List;

public interface ExcelService {
    List<ExcelData> search(String matnr);
   void persist(List<ExcelData> employees);
    
    List<ExcelData> convert(MultipartFile file);
    byte[] convert(List<ExcelData> employees);
	
}
