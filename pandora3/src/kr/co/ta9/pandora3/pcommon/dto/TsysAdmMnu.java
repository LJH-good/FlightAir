package kr.co.ta9.pandora3.pcommon.dto;

import kr.co.ta9.pandora3.pcommon.dto.base.BaseTsysAdmMnu;

/**
 * TsysAdmMnu - ValueObject Extended class for table [TSYS_ADM_MNU].
 *
 * <pre>
 *     Generated by CodeProcessor. Yon can freely modify this generated file.
 *     Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 02. 16
 */
public class TsysAdmMnu extends BaseTsysAdmMnu {
	private String rol_id;         // 롤아이디
	private String rol_nm;
	private String top_mnu_seq;     // 최상단메뉴아이디
	private String top_mnu_nm;     // 최상단메뉴아이디
	private int top_srt_seq;       // 최상위정렬순서
	private String midd_mnu_seq;    // 중단메뉴아이디
	private String midd_mnu_nm;    // 중단메뉴아이디
	private int midd_srt_seq;      // 중위정렬순서
	private String depth_fullname;
	private String pgm_nm;
	private String target_url;
	private String required_login;
	private String mid_group;
	private String btn_info;
	private int dsply_no;
	private String f_upd_dttm;
	private String tmpl_url;
	private int up_tmpl_seq;
	private String dsply_yn;
	private String pgm_btn;
	private String rol_btn;
	private String usr_pgm_btn;	//사용자별 버튼 권한
	private String usr_nm;
	private String sys_nm;
	private String up_mnu_nm;
	
	
	public String getUp_mnu_nm() {
		return up_mnu_nm;
	}
	public void setUp_mnu_nm(String up_mnu_nm) {
		this.up_mnu_nm = up_mnu_nm;
	}
	public String getRol_nm() {
		return rol_nm;
	}
	public void setRol_nm(String rol_nm) {
		this.rol_nm = rol_nm;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	public String getUsr_pgm_btn() {
		return usr_pgm_btn;
	}
	public void setUsr_pgm_btn(String usr_pgm_btn) {
		this.usr_pgm_btn = usr_pgm_btn;
	}
	
	public int getUp_tmpl_seq() {
		return up_tmpl_seq;
	}
	public void setUp_tmpl_seq(int up_tmpl_seq) {
		this.up_tmpl_seq = up_tmpl_seq;
	}
	public String getTmpl_url() {
		return tmpl_url;
	}
	public int getDsply_no() {
		return dsply_no;
	}
	public void setDsply_no(int dsply_no) {
		this.dsply_no = dsply_no;
	}
	public void setTmpl_url(String tmpl_url) {
		this.tmpl_url = tmpl_url;
	}
	public String getRol_id() {
		return rol_id;
	}
	public void setRol_id(String rol_id) {
		this.rol_id = rol_id;
	}
	public String getTop_mnu_seq() {
		return top_mnu_seq;
	}
	public void setTop_mnu_seq(String top_mnu_seq) {
		this.top_mnu_seq = top_mnu_seq;
	}
	public String getTop_mnu_nm() {
		return top_mnu_nm;
	}
	public void setTop_mnu_nm(String top_mnu_nm) {
		this.top_mnu_nm = top_mnu_nm;
	}
	public int getTop_srt_seq() {
		return top_srt_seq;
	}
	public void setTop_srt_seq(int top_srt_seq) {
		this.top_srt_seq = top_srt_seq;
	}
	public String getMidd_mnu_seq() {
		return midd_mnu_seq;
	}
	public void setMidd_mnu_seq(String midd_mnu_seq) {
		this.midd_mnu_seq = midd_mnu_seq;
	}
	public String getMidd_mnu_nm() {
		return midd_mnu_nm;
	}
	public void setMidd_mnu_nm(String midd_mnu_nm) {
		this.midd_mnu_nm = midd_mnu_nm;
	}
	public int getMidd_srt_seq() {
		return midd_srt_seq;
	}
	public void setMidd_srt_seq(int midd_srt_seq) {
		this.midd_srt_seq = midd_srt_seq;
	}
	public String getDepth_fullname() {
		return depth_fullname;
	}
	public void setDepth_fullname(String depth_fullname) {
		this.depth_fullname = depth_fullname;
	}
	public String getPgm_nm() {
		return pgm_nm;
	}
	public void setPgm_nm(String pgm_nm) {
		this.pgm_nm = pgm_nm;
	}
	public String getTarget_url() {
		return target_url;
	}
	public void setTarget_url(String target_url) {
		this.target_url = target_url;
	}
	public String getRequired_login() {
		return required_login;
	}
	public void setRequired_login(String required_login) {
		this.required_login = required_login;
	}
	public String getMid_group() {
		return mid_group;
	}
	public void setMid_group(String mid_group) {
		this.mid_group = mid_group;
	}
	public String getBtn_info() {
		return btn_info;
	}
	public void setBtn_info(String btn_info) {
		this.btn_info = btn_info;
	}
	public String getF_upd_dttm() {
		return f_upd_dttm;
	}
	public void setF_upd_dttm(String f_upd_dttm) {
		this.f_upd_dttm = f_upd_dttm;
	}
	public String getDsply_yn() {
		return dsply_yn;
	}
	public void setDsply_yn(String dsply_yn) {
		this.dsply_yn = dsply_yn;
	}
	public String getPgm_btn() {
		return pgm_btn;
	}
	public void setPgm_btn(String pgm_btn) {
		this.pgm_btn = pgm_btn;
	}
	public String getRol_btn() {
		return rol_btn;
	}
	public void setRol_btn(String rol_btn) {
		this.rol_btn = rol_btn;
	}
	public String getSys_nm() {
		return sys_nm;
	}
	public void setSys_nm(String sys_nm) {
		this.sys_nm = sys_nm;
	}
}