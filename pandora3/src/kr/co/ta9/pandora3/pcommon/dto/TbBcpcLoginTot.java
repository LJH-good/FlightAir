 package kr.co.ta9.pandora3.pcommon.dto;

import kr.co.ta9.pandora3.pcommon.dto.base.BaseTbBcpcLoginTot;

/**
 * TbBcpcLoginTot - ValueObject Extended class for table [TB_BCPC_LOGIN_TOT].
 *
 * <pre>
 *     Generated by CodeProcessor. Yon can freely modify this generated file.
 *     Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2020. 04. 06
 */
public class TbBcpcLoginTot extends BaseTbBcpcLoginTot {
	
	private String sys_nm;
	private String usr_cnt;
	private String prd_uv;
	private String day_uv;
	private String prd_uv_acs;
	private String day_uv_acs;
	private String prd_vst_cust_cnt;
	private String day_vst_cust_cnt;
	private String mstr_cd;
	private String mstr_nm;
	
	
	public String getMstr_cd() {
		return mstr_cd;
	}
	public void setMstr_cd(String mstr_cd) {
		this.mstr_cd = mstr_cd;
	}
	public String getMstr_nm() {
		return mstr_nm;
	}
	public void setMstr_nm(String mstr_nm) {
		this.mstr_nm = mstr_nm;
	}
	public String getUsr_cnt() {
		return usr_cnt;
	}
	public void setUsr_cnt(String usr_cnt) {
		this.usr_cnt = usr_cnt;
	}
	public String getPrd_uv() {
		return prd_uv;
	}
	public void setPrd_uv(String prd_uv) {
		this.prd_uv = prd_uv;
	}
	public String getDay_uv() {
		return day_uv;
	}
	public void setDay_uv(String day_uv) {
		this.day_uv = day_uv;
	}
	public String getPrd_uv_acs() {
		return prd_uv_acs;
	}
	public void setPrd_uv_acs(String prd_uv_acs) {
		this.prd_uv_acs = prd_uv_acs;
	}
	public String getDay_uv_acs() {
		return day_uv_acs;
	}
	public void setDay_uv_acs(String day_uv_acs) {
		this.day_uv_acs = day_uv_acs;
	}
	public String getPrd_vst_cust_cnt() {
		return prd_vst_cust_cnt;
	}
	public void setPrd_vst_cust_cnt(String prd_vst_cust_cnt) {
		this.prd_vst_cust_cnt = prd_vst_cust_cnt;
	}
	public String getDay_vst_cust_cnt() {
		return day_vst_cust_cnt;
	}
	public void setDay_vst_cust_cnt(String day_vst_cust_cnt) {
		this.day_vst_cust_cnt = day_vst_cust_cnt;
	}
	public String getSys_nm() {
		return sys_nm;
	}
	public void setSys_nm(String sys_nm) {
		this.sys_nm = sys_nm;
	}
}