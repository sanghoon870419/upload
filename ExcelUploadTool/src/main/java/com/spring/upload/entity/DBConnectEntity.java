package com.spring.upload.entity;
import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "RENRAKUTSUCHI_V")
@Data
public class DBConnectEntity {
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="MATNR")
	private String matnr;
	
	@Column(name="PNO") 
	private String pno;
	@Column(name="DME_PNO")
	private String dme_Pno;

	
	@Column(name="GMDL_PL_CD")
	private String gmdl_Pl_Cd;
	@Column(name="GMDL_PL_NO")
	private String gmdl_pl_no;
	@Column(name="PRODUCT_NAME")
	private String product_name;
	@Column(name="SUB_PRODUCT_NAME")
	private String sub_product_name;
	@Column(name="MODEL_NO")
	private String model_no;
	@Column(name="VENDER")
	private String vender;
	@Column(name="ROUTE")
	private String route;
	@Column(name="REGION")
	private String region;
	@Column(name="PRODUCT_CATEGORY")
	private String product_category;
	@Column(name="MFG_NO")
	private String mfg_no;
	@Column(name="JAN_UPC_EAN")
	private String janUpcEan;
	@Column(name="GLOBAL_SKU")
	private String global_sku;
	@Column(name="CARTON_ON")
	private String carton_on;
	@Column(name="FCS_DATE")
	private String fcs_date;
	@Column(name="REGIST_DAY")
	private String regist_day;
}
