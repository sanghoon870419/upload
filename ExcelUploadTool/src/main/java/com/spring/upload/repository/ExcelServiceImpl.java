package com.spring.upload.repository;



import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.upload.entity.DBConnectEntity;
import com.spring.upload.exception.IllegalFileFormatException;

import com.spring.upload.vo.ExcelData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// 스프링에서 클래스에 부여한 @Transactional 는 각 메소드가 상속함
@Transactional(readOnly = true)
// Stream API (람다식) 를 좀 써봤음다..
public class ExcelServiceImpl implements ExcelService {

    @AllArgsConstructor
    // 엑셀 시트 상의 각 필드의 열 번호 정의
    // 다른 곳에서 참조할 일은 아마 없을 것이니 private 한 inner class 로 선언
    private enum EmployeeCellDescriptor {
        PNO(0), DME_PNO(1), MATNR(2), GMDL_PL_CD(3), GMDL_PL_NO(4),PRODUCT_NAME(5)
        ,SUB_PRODUCT_NAME(6),MODEL_NO(7),VENDER(8),ROUTE(9),REGION(10),PRODUCT_CATEGORY(11)
        ,MFG_NO(12),JAN_UPC_EAN(13),GLOBAL_SKU(14),CARTON_ON(15),FCS_DATE(16),REGIST_DAY(17);
        int columnNo;
    }

    private static final int NO_OF_COLUMNS = EmployeeCellDescriptor.values().length;

    private final ExcelRepository excelRepository;

    @Override
    public List<ExcelData> search(String matnr) {
    	System.out.println("--------DB검색 작업 시작 --------------");
        DBConnectEntity excelEntity = new DBConnectEntity();
        excelEntity.setMatnr(matnr);
        Example<DBConnectEntity> example = Example.of(excelEntity, ExampleMatcher.matching()
                .withIgnoreNullValues().withIgnoreCase());
        
        List<DBConnectEntity> excelEntities = excelRepository.findAll(example);
        return excelEntities.stream()
                .map(entity -> ExcelData.builder()
                		.matnr(entity.getMatnr())
                		.build())
                .collect(Collectors.toList());     	

    }

    @Override
    @Transactional
    public void persist(List<ExcelData> employees) {
    	
       List<DBConnectEntity> employeeEntities = employees.stream()
               .map(employee -> {
                    DBConnectEntity entity = new DBConnectEntity();
                   entity.setMatnr(employee.getMatnr());
                    return entity;
                })
                .collect(Collectors.toList());
        System.out.println(employeeEntities);
        excelRepository.saveAll(employeeEntities);
       
    }

    @Override
    public List<ExcelData> convert(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            checkFormat(workbook);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<ExcelData> employees = new ArrayList<>();
            // skip header
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                ExcelData employee = toExcelData(row);
                employees.add(employee);    
            }
            return employees;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } // TODO POI 라이브러리의 에러 체크
    }
    

    private void checkFormat(XSSFWorkbook workbook) {
        if (workbook.getNumberOfSheets() <= 0) {
           throw new IllegalFileFormatException("no of sheets not positive");
        }

    }

 private ExcelData toExcelData (XSSFRow row) {
	 	XSSFCell matnrCell = row.getCell(0);
	 	String matnr = "";
	 	if(matnrCell.getStringCellValue() != "") {
	 		matnr = matnrCell.getStringCellValue();
	 	}
	
	 	System.out.println(matnr);
        return ExcelData.builder()		
       		.matnr(matnr)
            .build();
 }
    
	@Override
    public byte[] convert(List<ExcelData> employees) {
    	
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("DownloadData");
        XSSFRow headerRow = sheet.createRow(0);
        writeHeader(headerRow);
        for (int i = 0; i < employees.size(); i++) {
            int rowNo = i + 1;
            XSSFRow row = sheet.createRow(rowNo);
            ExcelData employee = employees.get(i);
            writeRow(row, employee);
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void writeHeader(XSSFRow row) {
        row.createCell(EmployeeCellDescriptor.PNO.columnNo)
                .setCellValue("PNO");
        row.createCell(EmployeeCellDescriptor.DME_PNO.columnNo)
                .setCellValue("DME_PNO");
        row.createCell(EmployeeCellDescriptor.MATNR.columnNo)
                .setCellValue("MATNR");
        row.createCell(EmployeeCellDescriptor.GMDL_PL_CD.columnNo)
                .setCellValue("GMDL_PL_CD");
        row.createCell(EmployeeCellDescriptor.GMDL_PL_NO.columnNo)
        		.setCellValue("GMDL_PL_NO");
        row.createCell(EmployeeCellDescriptor.PRODUCT_NAME.columnNo)
				.setCellValue("PRODUCT_NAME");
        row.createCell(EmployeeCellDescriptor.SUB_PRODUCT_NAME.columnNo)
				.setCellValue("SUB_PRODUCT_NAME");
        row.createCell(EmployeeCellDescriptor.MODEL_NO.columnNo)
				.setCellValue("MODEL_NO");
        row.createCell(EmployeeCellDescriptor.VENDER.columnNo)
				.setCellValue("VENDER");
        row.createCell(EmployeeCellDescriptor.ROUTE.columnNo)
				.setCellValue("ROUTE");
        row.createCell(EmployeeCellDescriptor.REGION.columnNo)
				.setCellValue("REGION");
        row.createCell(EmployeeCellDescriptor.PRODUCT_CATEGORY.columnNo)
				.setCellValue("PRODUCT_CATEGORY");
        row.createCell(EmployeeCellDescriptor.MFG_NO.columnNo)
				.setCellValue("MFG_NO");
        row.createCell(EmployeeCellDescriptor.JAN_UPC_EAN.columnNo)
				.setCellValue("JAN_UPC_EAN");
        row.createCell(EmployeeCellDescriptor.GLOBAL_SKU.columnNo)
				.setCellValue("GLOBAL_SKU");
        row.createCell(EmployeeCellDescriptor.FCS_DATE.columnNo)
				.setCellValue("FCS_DATE");
        row.createCell(EmployeeCellDescriptor.REGIST_DAY.columnNo)
				.setCellValue("REGIST_DAY");
    }

    private void writeRow(XSSFRow row, ExcelData employee) {
        row.createCell(EmployeeCellDescriptor.PNO.columnNo)
                .setCellValue(employee.getPno());
        row.createCell(EmployeeCellDescriptor.DME_PNO.columnNo)
                .setCellValue(employee.getDme_Pno());
        row.createCell(EmployeeCellDescriptor.MATNR.columnNo)
                .setCellValue(employee.getMatnr());
        row.createCell(EmployeeCellDescriptor.GMDL_PL_CD.columnNo)
                .setCellValue(employee.getGmdl_Pl_Cd());
        row.createCell(EmployeeCellDescriptor.GMDL_PL_NO.columnNo)
        		.setCellValue(employee.getGmdl_pl_no());
        row.createCell(EmployeeCellDescriptor.PRODUCT_NAME.columnNo)
        		.setCellValue(employee.getProduct_name());
        row.createCell(EmployeeCellDescriptor.SUB_PRODUCT_NAME.columnNo)
        		.setCellValue(employee.getSub_product_name());
        row.createCell(EmployeeCellDescriptor.MODEL_NO.columnNo)
        		.setCellValue(employee.getModel_no());
        row.createCell(EmployeeCellDescriptor.VENDER.columnNo)
        		.setCellValue(employee.getVender());
        row.createCell(EmployeeCellDescriptor.ROUTE.columnNo)
        		.setCellValue(employee.getRoute());
        row.createCell(EmployeeCellDescriptor.REGION.columnNo)
        		.setCellValue(employee.getRegion());
        row.createCell(EmployeeCellDescriptor.PRODUCT_CATEGORY.columnNo)
        		.setCellValue(employee.getProduct_category());
        row.createCell(EmployeeCellDescriptor.MFG_NO.columnNo)
        		.setCellValue(employee.getMfg_no());
        row.createCell(EmployeeCellDescriptor.JAN_UPC_EAN.columnNo)
        		.setCellValue(employee.getJanUpcEan());
        row.createCell(EmployeeCellDescriptor.GLOBAL_SKU.columnNo)
        		.setCellValue(employee.getGlobal_sku());
        row.createCell(EmployeeCellDescriptor.CARTON_ON.columnNo)
        		.setCellValue(employee.getCarton_on());
       row.createCell(EmployeeCellDescriptor.FCS_DATE.columnNo)
        		.setCellValue(employee.getFcs_date());
        row.createCell(EmployeeCellDescriptor.REGIST_DAY.columnNo)
        		.setCellValue(employee.getRegist_day());
        
    }

}
