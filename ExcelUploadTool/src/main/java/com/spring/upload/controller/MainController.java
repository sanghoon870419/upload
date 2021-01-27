package com.spring.upload.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.upload.repository.ExcelRepository;
import com.spring.upload.repository.ExcelService;
import com.spring.upload.vo.ExcelData;

import java.util.List;
import java.util.UUID;

@Controller
// final 인 멤버변수를 인수로 가지는 constructor 를 자동 생성
// 요걸 쓰면 일일이 @Autowired 를 안써도 됨
@RequiredArgsConstructor
public class MainController {

    // 엑셀 파일의 MIME 타입
    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types 참조
    private static final String EXCEL_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private final ExcelService excelService;

    
    @GetMapping("/")
    public String employees(
    		@RequestParam(required = false) String matnr, Model model) {
        // 컨트롤러단에서는 업무 로직은 구현하지 않고 서비스단이랑 객체 주거니 받거니만 할 것
        //List<ExcelData> employees = excelService.search(matnr);
        //model.addAttribute("employees", employees);
        return "employees";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        List<ExcelData> employees = excelService.convert(file);
        System.out.println(employees);
      excelService.persist(employees);
        return "redirect:/";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam(required = false) String matnr) {
        List<ExcelData> employees = excelService.search(matnr);
        byte[] bytes = excelService.convert(employees);

        // 리스폰스 헤더 설정은 웹상의 로직, 즉 업무 로직이라고 보기 힘들기 때문에 컨트롤러에서 담당
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, EXCEL_MIME_TYPE);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; " +
                "filename=employees_" + UUID.randomUUID().toString() + ".xlsx");
        header.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }
}
