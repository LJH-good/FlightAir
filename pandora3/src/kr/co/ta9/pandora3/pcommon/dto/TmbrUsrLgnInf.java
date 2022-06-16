package kr.co.ta9.pandora3.pcommon.dto;

import kr.co.ta9.pandora3.pcommon.dto.base.BaseTmbrUsrLgnInf;

/**
 * TmbrUsrLgnInf - ValueObject Extended class for table [TMBR_USR_LGN_INF].
 *
 * <pre>
 *     Generated by CodeProcessor. Yon can freely modify this generated file.
 *     Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 02. 16
 */
public class TmbrUsrLgnInf extends BaseTmbrUsrLgnInf {
	private String lgn_id;
	private String f_lgn_dttm;

	public String getLgn_id() {
		return lgn_id;
	}

	public void setLgn_id(String lgn_id) {
		this.lgn_id = lgn_id;
	}
	
	public String getF_lgn_dttm() {
		return f_lgn_dttm;
	}

	public void setF_lgn_dttm(String f_lgn_dttm) {
		this.f_lgn_dttm = f_lgn_dttm;
	}
	
}