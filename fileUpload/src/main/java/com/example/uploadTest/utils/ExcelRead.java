package com.example.uploadTest.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
 
 
 
public class ExcelRead {
     public static List<Map<String, String>> read(ExcelReadOption excelReadOption) {
           
            Workbook wb = ExcelFileType.getWorkbook(excelReadOption.getFilePath());
            int sheetNum = wb.getNumberOfSheets(); //시트의 수를 가져오기 위한 변수 
            int numOfCells = 0;
            Row row = null;
            Cell cell = null;
            String cellName = "";
            Map<String, String> map = null;
            List<Map<String, String>> result = new ArrayList<Map<String, String>>(); 
            for(int i =0; i<sheetNum; i++){
                System.out.println("Sheet 이름: "+ wb.getSheetName(i));
                Sheet sheet = wb.getSheetAt(i);
                int numOfRows = sheet.getPhysicalNumberOfRows();   
                for(int rowIndex = excelReadOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {
                row = sheet.getRow(rowIndex);
                if(row != null) {
                	 numOfCells = row.getLastCellNum();
                    map = new HashMap<String, String>();
                    for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
                        cell = row.getCell(cellIndex);
                        cellName = ExcelCellRef.getName(cell, cellIndex);
                        if( !excelReadOption.getOutputColumns().contains(cellName) ) {
                            continue;
                        		}
                        map.put(cellName, ExcelCellRef.getValue(cell));
                    		}
                    result.add(map);   
                	}
                }
            }
            return result;       
     	 }      
	 }


