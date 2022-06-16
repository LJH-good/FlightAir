package kr.co.ta9.pandora3.pcommon.dto;

import kr.co.ta9.pandora3.pcommon.dto.base.BaseTcmnCdDtl;

/**
 * TcmnCdDtl - ValueObject Extended class for table [TCMN_CD_DTL].
 *
 * <pre>
 *     Generated by CodeProcessor. Yon can freely modify this generated file.
 *     Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 02. 16
 */
public class TcmnCdDtl extends BaseTcmnCdDtl {
	private String mst_cd_nm;
	private String f_upd_dttm;
	private String lgn_id;
	private String reg_lgn_id;
	

	public String getMst_cd_nm() {
		return mst_cd_nm;
	}

	public void setMst_cd_nm(String mst_cd_nm) {
		this.mst_cd_nm = mst_cd_nm;
	}

	public String getF_upd_dttm() {
		return f_upd_dttm;
	}

	public void setF_upd_dttm(String f_upd_dttm) {
		this.f_upd_dttm = f_upd_dttm;
	}

	public String getLgn_id() {
		return lgn_id;
	}

	public void setLgn_id(String lgn_id) {
		this.lgn_id = lgn_id;
	}

	public String getReg_lgn_id() {
		return reg_lgn_id;
	}

	public void setReg_lgn_id(String reg_lgn_id) {
		this.reg_lgn_id = reg_lgn_id;
	}
	
}