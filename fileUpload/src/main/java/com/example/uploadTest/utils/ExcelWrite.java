package com.example.uploadTest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.example.uploadTest.entity.RenrakutusuchiList;
import com.example.uploadTest.repository.RenrakutusuchiListRepository; 

@Component("sampleXls") 
public class ExcelWrite extends AbstractXlsView { 
	 @Autowired
	  DataSource dataSource;
	  
	  @Autowired
	  RenrakutusuchiListRepository renrakuRepository;
	  
	  @Autowired
	  ExcelUpload excelupload = new ExcelUpload();
	  
	  @Autowired
	  ServletContext context;
	  @Override
	  protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, HttpServletRequest request, 
			HttpServletResponse response) throws Exception { 
			response.setHeader("Content-Disposition", "attachment; filename=\"DownLoad.xls\"");
		
			CellStyle numberCellStyle = workbook.createCellStyle(); 
			DataFormat numberDataFormat = workbook.createDataFormat(); 
			numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0")); 
		
			Sheet sheet = workbook.createSheet("downLoad"); 
			Row row0 = sheet.createRow(0); 
			//헤드 셀
			Cell cell0 = row0.createCell(0); 
			cell0.setCellValue("PNO"); 
			Cell cell1 = row0.createCell(1); 
			cell1.setCellValue("DME_PNO"); 
			Cell cell2 = row0.createCell(2); 
			cell2.setCellValue("MATNR"); 
			Cell cell3 = row0.createCell(3); 
			cell3.setCellValue("GMDL_PL_CD"); 
			Cell cell4 = row0.createCell(4); 
			cell4.setCellValue("GMDL_PL_NO"); 
			Cell cell5 = row0.createCell(5); 
			cell5.setCellValue("PRODUCT_NAME"); 
			Cell cell6 = row0.createCell(6); 
			cell6.setCellValue("SUB_PRODUCT_NAME"); 
			Cell cell7 = row0.createCell(7); 
			cell7.setCellValue("MODEL_NO"); 
			Cell cell8 = row0.createCell(8); 
			cell8.setCellValue("VENDER"); 
			Cell cell9 = row0.createCell(9); 
			cell9.setCellValue("ROUTE"); 
			Cell cell10 = row0.createCell(10); 
			cell10.setCellValue("REGION"); 
			Cell cell11 = row0.createCell(11); 
			cell11.setCellValue("PRODUCT_CATEGORY"); 
			Cell cell12 = row0.createCell(12); 
			cell12.setCellValue("MFG_NO"); 
			Cell cell13 = row0.createCell(13); 
			cell13.setCellValue("JAN_UPC_EAN"); 
			Cell cell14 = row0.createCell(14); 
			cell14.setCellValue("GLOBAL_SKU"); 
			Cell cell15 = row0.createCell(15); 
			cell15.setCellValue("CARTON_ON"); 
			Cell cell16 = row0.createCell(16); 
			cell16.setCellValue("FCS_DATE"); 
			Cell cell17 = row0.createCell(17); 
			cell17.setCellValue("REGIST_DAY"); 
			//출력값 입력
			
			
//			List<ExcelData> excelData = excelupload.upload(file);
//			System.out.println(excelData);
			
			Row row1 = sheet.createRow(1); 
			Iterable<RenrakutusuchiList> excelList = renrakuRepository.findByMatnr("A1PBS10E112H");
			
				for(RenrakutusuchiList ren : excelList) {
				//값출력 셀
				cell0 = row1.createCell(0); 
				cell0.setCellValue(ren.getPno()); 
				cell1 = row1.createCell(1); 
				cell1.setCellValue(ren.getDme_Pno());
				cell2 = row1.createCell(2); 
				cell2.setCellValue(ren.getMatnr()); 
				cell3 = row1.createCell(3); 
				cell3.setCellValue(ren.getGmd_lPl_Cd()); 
				cell4 = row1.createCell(4); 
				cell4.setCellValue(ren.getGmdl_pl_no()); 
				cell5 = row1.createCell(5); 
				cell5.setCellValue(ren.getProduct_name()); 
				cell6 = row1.createCell(6); 
				cell6.setCellValue(ren.getSub_product_name()); 
				cell7 = row1.createCell(7); 
				cell7.setCellValue(ren.getModel_no()); 
				cell8 = row1.createCell(8); 
				cell8.setCellValue(ren.getVender()); 
				cell9 = row1.createCell(9); 
				cell9.setCellValue(ren.getRoute()); 
				cell10 = row1.createCell(10); 
				cell10.setCellValue(ren.getRegion()); 
				cell11 = row1.createCell(11); 
				cell11.setCellValue(ren.getProduct_category()); 
				cell12 = row1.createCell(12); 
				cell12.setCellValue(ren.getMfg_no()); 
				cell13 = row1.createCell(13); 
				cell13.setCellValue(ren.getJanUpcEan()); 
				cell14 = row1.createCell(14); 
				cell14.setCellValue(ren.getGlobal_sku()); 
				cell15 = row1.createCell(15); 
				cell15.setCellValue(ren.getCarton_on()); 
				cell16 = row1.createCell(16); 
				cell16.setCellValue(ren.getFcs_date().toString()); 
				cell17 = row1.createCell(17); 
				cell17.setCellValue(ren.getRegist_day().toString()); 
				}
			}
			
		}
