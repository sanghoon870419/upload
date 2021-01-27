package com.example.uploadTest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RENRAKUTSUCHI_V")
public class RenrakutusuchiList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PNO") 
	private String pno;
	@Column(name="DME_PNO")
	private String dme_Pno;
	@Column(name="MATNR")
	private String matnr;
	@Column(name="GMDL_PL_CD")
	private String gmd_lPl_Cd;
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
	
	@Builder
	public RenrakutusuchiList(String pno, String dme_Pno, String matnr, String gmd_lPl_Cd, String gmdl_pl_no,
			String product_name, String sub_product_name, String model_no, String vender, String route, String region,
			String product_category, String mfg_no, String janUpcEan, String global_sku, String carton_on,
			String fcs_date, String regist_day) {
		super();
		this.pno = pno;
		this.dme_Pno = dme_Pno;
		this.matnr = matnr;
		this.gmd_lPl_Cd = gmd_lPl_Cd;
		this.gmdl_pl_no = gmdl_pl_no;
		this.product_name = product_name;
		this.sub_product_name = sub_product_name;
		this.model_no = model_no;
		this.vender = vender;
		this.route = route;
		this.region = region;
		this.product_category = product_category;
		this.mfg_no = mfg_no;
		this.janUpcEan = janUpcEan;
		this.global_sku = global_sku;
		this.carton_on = carton_on;
		this.fcs_date = fcs_date;
		this.regist_day = regist_day;
	}

	@Override
	public String toString() {
		return "RenrakutusuchiList [pno=" + pno + ", dme_Pno=" + dme_Pno + ", matnr=" + matnr + ", gmd_lPl_Cd="
				+ gmd_lPl_Cd + ", gmdl_pl_no=" + gmdl_pl_no + ", product_name=" + product_name + ", sub_product_name="
				+ sub_product_name + ", model_no=" + model_no + ", vender=" + vender + ", route=" + route + ", region="
				+ region + ", product_category=" + product_category + ", mfg_no=" + mfg_no + ", janUpcEan=" + janUpcEan
				+ ", global_sku=" + global_sku + ", carton_on=" + carton_on + ", fcs_date=" + fcs_date + ", regist_day="
				+ regist_day + "]";
	}
	
}
