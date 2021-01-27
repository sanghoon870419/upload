package com.spring.upload.vo;
import lombok.Builder;
import lombok.Value;

// 서비스단에서 주거니 받거니 할 객체
// 생성자에다 모든 값을 지정하도록 함
// @Value 를 부여하면 set 메소드는 생성되지 않음 (의도적)
@Value
// 다만 생성자의 인수가 길어지므로 코딩 스타일 상 @Builder 를 부여
@Builder
public class ExcelData {
	String matnr;
	String pno;
	String dme_Pno;
	String gmdl_Pl_Cd;
	String gmdl_pl_no;
	String product_name;
	String sub_product_name;
	String model_no;
	String vender;
	String route;
	String region;
	String product_category;
	String mfg_no;
	String janUpcEan;
	String global_sku;
	String carton_on;
	String fcs_date;	
	String regist_day;

	}
