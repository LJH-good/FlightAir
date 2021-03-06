package kr.co.ta9.pandora3.psys.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.mysql.jdbc.StringUtils;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.app.servlet.controller.CommonActionController;
import kr.co.ta9.pandora3.app.util.CodeUtil;
import kr.co.ta9.pandora3.common.conf.Config;
import kr.co.ta9.pandora3.common.conf.Configuration;
import kr.co.ta9.pandora3.common.conf.Const;
import kr.co.ta9.pandora3.common.servlet.download.FileDownLoad;
import kr.co.ta9.pandora3.common.util.ResponseUtil;
import kr.co.ta9.pandora3.common.util.TextUtil;
import kr.co.ta9.pandora3.pcommon.dto.TbbsFlInf;
import kr.co.ta9.pandora3.pcommon.dto.TcmnCdDtl;
import kr.co.ta9.pandora3.pcommon.dto.TmbrAdmLgnInf;
import kr.co.ta9.pandora3.pcommon.dto.TsysAdmMnu;
import kr.co.ta9.pandora3.pcommon.dto.usrdef.CommonInfo;
import kr.co.ta9.pandora3.psys.manager.PsysCommonMgr;
/**
* <pre>
* 1. ???????????? : PsysCommonController
* 2. ?????? : Psys ?????? ????????????
* 3. ????????? : 2018-03-27
* 4. ????????? : TANINE
* </pre>
*/
@Controller
public class PsysCommonController extends CommonActionController{

	@Autowired
	private PsysCommonMgr psysCommonMgr;

	/**
	 * ?????? ?????? ??????(??????)
	 * ????????? : 2018-03-27
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/psys/getPsysCommon", method = RequestMethod.POST)
	public void getPsysCommon(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// result ??????
		String result = Const.BOOLEAN_SUCCESS;
		// json ??????
		JSONObject json = new JSONObject();
		List<Map<String,Object>> selectData = new ArrayList<Map<String,Object>>();
		try {
			// ?????? ?????? ??????
			List<TcmnCdDtl> multiTcmnCdDtl = psysCommonMgr.selectTcmnCdDtlMultiList(parameterMap);
			String[] mst_cd_arr = parameterMap.getValue("mst_cd_arr").split(",");

			if("ERIKIN_BOARD".equals(mst_cd_arr[0])) {
				int size = multiTcmnCdDtl.size();
				if(size > 0) {
					List<HashMap<String , Object>> module_srl_list =  new ArrayList<HashMap<String , Object>>();
					for(int i=0; i<multiTcmnCdDtl.size(); i++) {
						HashMap<String , Object> map = new HashMap<String, Object>();
						map.put("module_srl", multiTcmnCdDtl.get(i).getCd());
						module_srl_list.add(map);
					}
					parameterMap.put("module_srl_list", module_srl_list);
				}
			}

			for(TcmnCdDtl SysCd : multiTcmnCdDtl){
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("MST_CD"   , SysCd.getMst_cd());
				data.put("MST_CD_NM", SysCd.getMst_cd_nm());
				data.put("CD"       , SysCd.getCd());
				data.put("CD_NM"    , SysCd.getCd_nm());
         		data.put("REF_1"    , SysCd.getRef_1());
				data.put("REF_2"    , SysCd.getRef_2());
				data.put("REF_3"    , SysCd.getRef_3());
				data.put("SRT_SEQ"  , SysCd.getSrt_sqn());
				data.put("CD_DESC"  , SysCd.getCd_desc());
				selectData.add(data);
			}
		}
		catch (Exception e) {
			// Exception??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json??? SYSCDDTL ??????
		json.put("selectData",selectData);
		// json??? ?????? ??????
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	 * select box ????????? ??????
	 * ????????? : 2019-08-06
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/psys/getPsysCommonInfoList", method = RequestMethod.POST)
	public void getPsysCommonInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// result ??????
		String result = Const.BOOLEAN_SUCCESS;
		// json ??????
		JSONObject json = new JSONObject();
		List<Map<String,Object>> selectCommData = new ArrayList<Map<String,Object>>();
		try {
			// select box ????????? ??????
			List<CommonInfo> CommonInfo = psysCommonMgr.getPsysCommonInfoList(parameterMap);

			for(CommonInfo comCd : CommonInfo){

				Map<String,Object> data = new HashMap<String,Object>();

				data.put("CODE_CD"   , comCd.getCode_cd());		// ??????
				data.put("CODE_NM"   , comCd.getCode_nm());		// ???
				data.put("CODE_ETC"  , comCd.getCode_etc());		// ??????

				data.put("CODE_CD_1" , comCd.getCode_cd1());
				data.put("CODE_CD_2" , comCd.getCode_cd2());
				data.put("CODE_CD_3" , comCd.getCode_cd3());
				data.put("CODE_CD_4" , comCd.getCode_cd4());
				data.put("CODE_CD_5" , comCd.getCode_cd5());
				data.put("CODE_CD_6" , comCd.getCode_cd6());
				data.put("CODE_CD_7" , comCd.getCode_cd7());
				data.put("CODE_CD_8" , comCd.getCode_cd8());
				data.put("CODE_CD_9" , comCd.getCode_cd9());

				data.put("CODE_NM_1" , comCd.getCode_nm1());
				data.put("CODE_NM_2" , comCd.getCode_nm2());
				data.put("CODE_NM_3" , comCd.getCode_nm3());
				data.put("CODE_NM_4" , comCd.getCode_nm4());
				data.put("CODE_NM_5" , comCd.getCode_nm5());
				data.put("CODE_NM_6" , comCd.getCode_nm6());
				data.put("CODE_NM_7" , comCd.getCode_nm7());
				data.put("CODE_NM_8" , comCd.getCode_nm8());
				data.put("CODE_NM_9" , comCd.getCode_nm9());

				data.put("CODE_ETC_1" , comCd.getCode_etc1());
				data.put("CODE_ETC_2" , comCd.getCode_etc2());
				data.put("CODE_ETC_3" , comCd.getCode_etc3());
				data.put("CODE_ETC_4" , comCd.getCode_etc4());
				data.put("CODE_ETC_5" , comCd.getCode_etc5());
				data.put("CODE_ETC_6" , comCd.getCode_etc6());
				data.put("CODE_ETC_7" , comCd.getCode_etc7());
				data.put("CODE_ETC_8" , comCd.getCode_etc8());
				data.put("CODE_ETC_9" , comCd.getCode_etc9());

				selectCommData.add(data);
			}
		}
		catch (Exception e) {
			// Exception??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json??? SYSCDDTL ??????
		json.put("selectCommData",selectCommData);
		// json??? ?????? ??????
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	* ?????? > ???????????? - ????????? ??????
	* ????????? : 2017-12-05
	* @param request
	* @param response
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/psys/sndCertEml", method = RequestMethod.POST)
	public void sndCertEml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// result ??????
		String result = Const.BOOLEAN_SUCCESS;

		try {
			result = psysCommonMgr.sndCertEml(parameterMap, request, response);
		}
		catch (Exception e) {
			// Exception ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json ??????
		JSONObject json = new JSONObject();
		// json??? ?????? ??????
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}


	/**
	 * ?????????????????? > ????????? ?????? ?????? ??????
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/psys/JnEml.do")
	public ModelAndView JnEml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ModelAndView ??????
		ModelAndView mav = new ModelAndView();
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// parameter ??????
		// String login_id = (null == request.getParameter("login_id") || "".equals(request.getParameter("login_id"))) ? "" : request.getParameter("login_id");
		String jn_ctf_key = (null == request.getParameter("jn_ctf_key") || "".equals(request.getParameter("jn_ctf_key"))) ? "" : request.getParameter("jn_ctf_key");
		// result ??????
		String result = "";
		try {

			parameterMap.put("jn_ctf_key", jn_ctf_key);
			mav.addObject("JN_CTF_KEY", jn_ctf_key);
			// ????????? ????????????

			List<TmbrAdmLgnInf> tmbrAdmLgnInfList = psysCommonMgr.selectTmbrAdmLgnInfList(parameterMap);

			if(tmbrAdmLgnInfList != null && (!tmbrAdmLgnInfList.isEmpty())) {
				TcmnCdDtl cert_term = CodeUtil.getTcmnCdDtl("LOGIN_PROP", "JOIN_TERM");
//				int join_key_cert_term = Integer.parseInt(cert_term.getRef_1())*1000*60*60*24;

				TmbrAdmLgnInf tmbrAdmLgnInf = tmbrAdmLgnInfList.get(0);

				if("1".equals(tmbrAdmLgnInf.getUsr_stat_cd())) {
					mav.addObject("CERT_KEY_STATUS", "ALREADY_CERT_KEY");
				} else {
					// ????????? ???????????? (???????????? : Day??????)
					Long join_term = Long.parseLong(cert_term.getRef_1())*1000*60*60*24;
					// ????????? ????????? Long
					// 2019-02-20 ????????????  Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(tmbrAdmLgnInf.getJn_ctf_dttm());
					long l_join_key_date = tmbrAdmLgnInf.getJn_ctf_dttm().getTime();
					// ???????????? long
					long currentTime = System.currentTimeMillis();
					// ????????? ????????? + ????????? ??????????????? ?????????????????? ????????? : ????????? ??????
					if(join_term + l_join_key_date > currentTime || join_term == 0) {
//					if(sysUser.getJoin_cert_key_term() < join_key_cert_term) {
						// ????????? ?????? ??????
						if(join_term == 0) {
							mav.addObject("CERT_KEY_STATUS", "PASSOK_CERT_KEY");
						} else {
							mav.addObject("CERT_KEY_STATUS", "VALID_CERT_KEY");
							// ?????? ?????? ??????
							String key_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(tmbrAdmLgnInf.getJn_ctf_dttm());
							//String key_date = tmbrAdmLgnInf.getJn_ctf_dttm().toString();
							//String join_max_term = DateUtil.format(Integer.parseInt(cert_term.getRef_1()), "yyyy-MM-dd hh:mm:ss", key_date);
							mav.addObject("JOIN_MAX_TERM", key_date);
						}
						mav.addObject("LGN_ID"       , tmbrAdmLgnInf.getLgn_id());
						mav.addObject("USR_EML_ADDR"  , tmbrAdmLgnInf.getUsr_eml_addr());
						mav.addObject("USER_SS_CD"   , tmbrAdmLgnInf.getUsr_stat_cd());
					} else {
						mav.addObject("CERT_KEY_STATUS", "EXPIRED_CERT_KEY");
					}
				}
			} else { // ?????? ????????? ???????????? ?????? ?????? (????????? ???????????? ???????????? ???????????? ???????????? ????????? ??????)
				mav.addObject("CERT_KEY_STATUS", "EXPIRED_CERT_KEY");
			}
		} catch (Exception e) {
			// Exception ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// ????????? ??????
		mav.setViewName("/pandora3/psys/psys1017signToEmail");
		mav.addObject("RESULT", result);
		return mav;
	}
	/**
	 * ?????????????????? > ????????? ?????? ?????? ??????
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/psys/saveUsrCertInf")
	public void saveUsrCertInf (HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// parameter ??????
//		String login_id = (null == request.getParameter("lgn_id") || "".equals(request.getParameter("lgn_id"))) ? "" : request.getParameter("lgn_id");
		String jn_ctf_key = (null == request.getParameter("jn_ctf_key") || "".equals(request.getParameter("jn_ctf_key"))) ? "" : request.getParameter("jn_ctf_key");
		// result ??????
		String result = Const.BOOLEAN_FAIL;
		// json ??????
		JSONObject json = new JSONObject();
		try {
			parameterMap.put("jn_ctf_key", jn_ctf_key);
			// ???????????? ??????
			List<TmbrAdmLgnInf> tmbrAdmLgnInfList = psysCommonMgr.selectTmbrAdmLgnInfList(parameterMap);

			if (tmbrAdmLgnInfList != null && (!tmbrAdmLgnInfList.isEmpty())) {

				TmbrAdmLgnInf tmbrAdmLgnInf = tmbrAdmLgnInfList.get(0);
				TmbrAdmLgnInf admin = new TmbrAdmLgnInf();

				// ????????? ?????? ??????
				TcmnCdDtl cert_term = CodeUtil.getTcmnCdDtl("LOGIN_PROP", "JOIN_TERM");
				// ????????? ???????????? (???????????? : Day??????)
				Long join_term = Long.parseLong(cert_term.getRef_1())*1000*60*60*24;
				// ????????? ????????? Long
				// 2019-02-20 ???????????? Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(tmbrAdmLgnInf.getJn_ctf_dttm());
				long l_join_key_date = tmbrAdmLgnInf.getJn_ctf_dttm().getTime();
				// ???????????? long
				long currentTime = System.currentTimeMillis();
				// ????????? ????????? + ????????? ??????????????? ?????????????????? ????????? : ????????? ??????
				if(join_term + l_join_key_date > currentTime || join_term == 0) {
					// ??????????????? ???????????? ?????? ???????????? (????????? -> ??????)
					if("3".equals(tmbrAdmLgnInf.getUsr_stat_cd())) {
						admin.setUpdr_id(tmbrAdmLgnInf.getUsr_id());
						admin.setUsr_id(tmbrAdmLgnInf.getUsr_id());
						admin.setUsr_stat_cd("1");

						psysCommonMgr.updateTmbrAdmLgnInf(admin);

						result = Const.BOOLEAN_SUCCESS;
					} else {
						// ?????? ????????? ??????
						result = "ALREADY_CERT_KEY";
					}
				} else { // ???????????? ??????????????? ?????????
					result = "EXPIRED_CERT_KEY";
				}
			}
		} catch (Exception e) {
			// Exception ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json??? ?????? ??????
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	 * ?????? > ????????? ??? BO ???????????? ?????? ??? ????????? ?????? ??? ???????????? ?????? ?????? :: 2017
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/psys/getTsysAdmMnuList", method = RequestMethod.POST)
	public void getTsysAdmMnuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// menuList ??????
		List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();

		List<Map<String,Object>> shortCutList = new ArrayList<Map<String,Object>>();

		// result ??????
		String result = Const.BOOLEAN_SUCCESS;
		try {

			List<TsysAdmMnu> tsysAdmMnuList = null;
			// rol_app ??? ?????? ?????? ?????? ?????? ????????? ?????? ??????
			if(TextUtil.isEmpty(parameterMap.getValue("rol_app"))) {
				tsysAdmMnuList = psysCommonMgr.selectTsysAdmMnuListByMnuList(parameterMap);
			} else {
				tsysAdmMnuList = psysCommonMgr.getTsysAdmRolAppMnu(parameterMap);
			}
			// ????????? ?????? ????????? : convert object to map for json
			menuList = new ArrayList<Map<String,Object>>();

			if(tsysAdmMnuList != null && (!tsysAdmMnuList.isEmpty())){
				for (TsysAdmMnu tsysAdmMnu : tsysAdmMnuList) {
					Map<String,Object> data = new HashMap<String,Object>();
					data.put("TOP_MNU_SEQ"   , tsysAdmMnu.getTop_mnu_seq());	// ??????????????????
					data.put("TOP_MNU_NM"    , tsysAdmMnu.getTop_mnu_nm());		// ???????????????
					data.put("TOP_SRT_SEQ"   , tsysAdmMnu.getTop_srt_seq());	// ??????????????????
					data.put("MIDD_MNU_SEQ"  , tsysAdmMnu.getMidd_mnu_seq());	// ??????????????????
					data.put("MIDD_MNU_NM"   , tsysAdmMnu.getMidd_mnu_nm());	// ???????????????
					data.put("MIDD_SRT_SEQ"  , tsysAdmMnu.getMidd_srt_seq());	// ??????????????????
					data.put("MNU_SEQ"       , tsysAdmMnu.getMnu_seq());		// ??????????????????
					data.put("MNU_NM"        , tsysAdmMnu.getMnu_nm());			// ???????????????
					data.put("SRT_SEQ"       , tsysAdmMnu.getSrt_sqn());		// ??????????????????
					data.put("URL"           , tsysAdmMnu.getUrl());			// URL
					data.put("MNU_TP_CD"     , tsysAdmMnu.getMnu_tp_cd());		// ??????????????????
					data.put("BTN_INFO"      , tsysAdmMnu.getBtn_info());		// ????????????[??????]
					data.put("CURR_URL"      , request.getServletPath());		// ???????????????
					data.put("VDI_SCRN_YN"  , tsysAdmMnu.getVdi_scrn_yn());		// VDI ??????
					data.put("PRN_INF_SCRN_YN"  , tsysAdmMnu.getPrn_inf_scrn_yn());	// 
					menuList.add(data);
				}
			}

		} catch (Exception e) {
			// Exceptin ??? ??????
			result = Const.BOOLEAN_FAIL;
			logger.error(e);
		}


		JSONObject menuJson= new JSONObject();
		menuJson.put("SESSION_MENU_LIST", menuList);
		HttpSession session = request.getSession();
		session.setAttribute("MENU_LIST", menuJson.toJSONString());



		// json ??????
		JSONObject json = new JSONObject();
		// json??? ?????? ??????
		json.put("RESULT", result);
		// json??? menu_list ??????
		json.put("MENU_LIST", menuList);
		// json??? shcut_list ??????
		json.put("SHCUT_LIST", shortCutList);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	 * ?????? > breadCrumb(??? > ??????????????? > ???????????? > ????????????) ?????? ??????
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/psys/getTsysAdmMnuBreadCrumb", method = RequestMethod.POST)
	public void getTsysAdmMnuBreadCrumb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);

		// result ??????
		String result = Const.BOOLEAN_SUCCESS;

		// json ??????
		JSONObject json = new JSONObject();

		try {

			json = psysCommonMgr.selectTsysAdmMnuBreadCrumb(parameterMap);

		} catch (Exception e) {
			// Exceptin ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json??? ?????? ??????
		json.put("RESULT" , result);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	 * ????????? ?????? ???????????? ??????
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/common/dwldXlsx")
	public void dwldXlsx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// gridHeader ??????
		Map<String,String> gridHeader = parameterMap.parseGridHeader();
		// gridList ??????
		List<Map<String,String>> gridList = parameterMap.parseGridData();
		// fileDownLoad
		FileDownLoad.exportExcel(response, gridHeader, gridList);
	}

	/**
	*???????????? ??????
	* ????????? : 2017-12-05
	* @param request
	* @param response
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/psys/insertTsysAdmFvrt", method = RequestMethod.POST)
	public void insertTsysAdmFvrt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// result ??????
		String result = Const.BOOLEAN_SUCCESS;

		try {
			 psysCommonMgr.insertTsysAdmFvrt(parameterMap);
		}
		catch (Exception e) {
			// Exception ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json ??????
		JSONObject json = new JSONObject();
		// json??? ?????? ??????
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	*???????????? ??????????????????
	* ????????? : 2017-12-05
	* @param request
	* @param response
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/psys/selectExistTsysAdmFvrt", method = RequestMethod.POST)
	public void getTsysAdmFvrt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// result ??????
		String result = Const.BOOLEAN_SUCCESS;
		String existYn ="";

		try {
			existYn = psysCommonMgr.selectExistTsysAdmFvrt(parameterMap);
		}
		catch (Exception e) {
			// Exception ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json ??????
		JSONObject json = new JSONObject();
		// json??? ?????? ??????
		json.put("RESULT", result);
		json.put("EXIST_YN", existYn);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	 * ???????????? ?????? ??????
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/psys/getTsysAdmFvrtList", method = RequestMethod.POST)
	public void getTsysAdmFvrtList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);

		// result ??????
		String result = Const.BOOLEAN_SUCCESS;
		// json ??????
		JSONObject json = new JSONObject();
		try {

			json = psysCommonMgr.getTsysAdmFvrtList(parameterMap);

		} catch (Exception e) {
			// Exceptin ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json??? ?????? ??????
		json.put("RESULT" , result);
		ResponseUtil.write(response, json.toJSONString());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/psys/deleteTsysAdmFvrt", method = RequestMethod.POST)
	public void deleteTsysAdmFvrt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// result ??????
		String result = Const.BOOLEAN_SUCCESS;

		try {
			 psysCommonMgr.deleteTsysAdmFvrt(parameterMap);
		}
		catch (Exception e) {
			// Exception ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json ??????
		JSONObject json = new JSONObject();
		// json??? ?????? ??????
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/psys/deleteUpMnuTsysAdmFvrt", method = RequestMethod.POST)
	public void deleteUpMnuTsysAdmFvrt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap ??????
		ParameterMap parameterMap = getParameterMap(request, response);
		// result ??????
		String result = Const.BOOLEAN_SUCCESS;

		try {
			 psysCommonMgr.deleteUpMnuTsysAdmFvrt(parameterMap);
		}
		catch (Exception e) {
			// Exception ??? ??????
			result = Const.BOOLEAN_FAIL;
		}
		// json ??????
		JSONObject json = new JSONObject();
		// json??? ?????? ??????
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}


	/**
	 * ?????? ????????????
	 * @param  request
	 * @param  response
	 * @throws Exception
	 */
	@RequestMapping(value="/content/filedownload")
	public void getFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ?????? ??????
		String fl_seq = (null == request.getParameter("fl_seq") || "".equals(request.getParameter("fl_seq"))) ? "" : request.getParameter("fl_seq");
//		String wbzn_seq = (null == request.getParameter("wbzn_seq") || "".equals(request.getParameter("wbzn_seq"))) ? "" : request.getParameter("wbzn_seq");
		OutputStream out = null;
		InputStream inputStream = null;
		int bufferSize = 1024;
		String FullPath = "";

		// ???????????? ????????? userAgent
		String header = request.getHeader("User-Agent");
		if (header.indexOf("Chrome") > -1) header = "Chrome";

		// PrintWriter ??????
		PrintWriter printWriter = null;
		try {
			TbbsFlInf sysFile = null;
			String db_file_full_path = "";
			String db_file_org_name = "";
			if(!StringUtils.isNullOrEmpty(fl_seq)) {
				sysFile = psysCommonMgr.selectTbbsFlInfInfo(fl_seq);
				if(sysFile != null) {
					db_file_full_path = sysFile.getUpl_fl_nm();
					db_file_full_path = db_file_full_path.replace("/", "");
					db_file_full_path = db_file_full_path.replace("&", "");
					db_file_full_path = db_file_full_path.replace("\"", "");
					db_file_full_path = db_file_full_path.replace("\\", "");
					db_file_org_name = sysFile.getSrc_fl_nm();
				}
			}
			if(!StringUtils.isNullOrEmpty(db_file_full_path) && !StringUtils.isNullOrEmpty(db_file_org_name)) {
				FullPath = Config.get("app.file.download.path") + File.separator + db_file_full_path;
				// ????????? ?????? ??? Chrome ???????????? ???????????? ??????
				String fileName = "";
				if(!"Chrome".equalsIgnoreCase(header)) {
					fileName = URLEncoder.encode(db_file_org_name, "UTF-8").replaceAll("\\+", "%20");
				}
				else {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < db_file_org_name.length(); i++) {
						char c = db_file_org_name.charAt(i);
						if (c > '~') {
							sb.append(URLEncoder.encode("" + c, "UTF-8"));
						}
						else {
							sb.append(c);
						}
					}
					fileName = sb.toString();
				}
				inputStream = new FileInputStream(FullPath);
				response.setContentType("binary/octet-stream");
				response.addHeader("Content-disposition", "attachment; filename=" + fileName);
				out = response.getOutputStream();

				byte[] bytes = new byte[bufferSize];
				int size = 0;
				while (-1 != (size = inputStream.read(bytes))) {
					out.write(bytes, 0, size);
				}
				out.flush();
				if(sysFile != null) {
					psysCommonMgr.updateTbbsFlInfDwldCnt(fl_seq);
				}
			} else {
				throw new IllegalArgumentException("file not found");
			}
		} catch(FileNotFoundException e){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			printWriter = response.getWriter();
			printWriter.println("<script type='text/javascript'>alert('?????? ????????? ???????????? ????????????.'); window.onload=window.close();</script>");
			printWriter.flush();
		} finally {
			try { inputStream.close(); } catch (Exception e) {log.debug(e);}
			try { out.close(); } catch (Exception e) {log.debug(e);}
		}
	}

	/**
	 * ?????? ????????????
	 * @param  request
	 * @param  response
	 * @throws Exception
	 */
	@RequestMapping(value="/content/filedownloads3")
	public void getS3File(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ?????? ??????
		String fl_seq = (null == request.getParameter("fl_seq") || "".equals(request.getParameter("fl_seq"))) ? "" : request.getParameter("fl_seq");
		OutputStream out = null;
		InputStream inputStream = null;
		BufferedReader reader = null;
		int bufferSize = 1024;
		String FullPath = "";

		// ???????????? ????????? userAgent
		String header = request.getHeader("User-Agent");
		if (header.indexOf("Chrome") > -1 ||  header.indexOf("Trident") > -1  ) header = "Chrome";

		// PrintWriter ??????
		PrintWriter printWriter = null;
		try {
			TbbsFlInf sysFile = null;
			String db_file_full_path = "";
			String db_file_org_name = "";
			if(!StringUtils.isNullOrEmpty(fl_seq)) {
				sysFile = psysCommonMgr.selectTbbsFlInfInfo(fl_seq);
				if(sysFile != null) {
					db_file_full_path = sysFile.getUpl_fl_nm();
					db_file_org_name = sysFile.getSrc_fl_nm();
				}
			}
			if(!StringUtils.isNullOrEmpty(db_file_full_path) && !StringUtils.isNullOrEmpty(db_file_org_name)) {
				FullPath =db_file_full_path; //Config.get("app.file.download.path") + File.separator + db_file_full_path;
				// ????????? ?????? ??? Chrome ???????????? ???????????? ??????
				String fileName = "";
				String s3FileName = "";
				String lastIndexStr = "/";
				s3FileName = FullPath.substring(FullPath.lastIndexOf(lastIndexStr)+1);
				if(!"Chrome".equalsIgnoreCase(header)) {
					fileName = URLEncoder.encode(db_file_org_name, "UTF-8").replaceAll("\\+", "%20");
					s3FileName = URLEncoder.encode(s3FileName, "UTF-8").replaceAll("\\+", "%20");
				}
				else {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < db_file_org_name.length(); i++) {
						char c = db_file_org_name.charAt(i);
						if (c > '~') {
							sb.append(URLEncoder.encode("" + c, "UTF-8"));
						}
						else {
							sb.append(c);
						}
					}
					fileName = sb.toString();
				}
				Configuration configuration = Configuration.getInstance();
//				String s3Url = configuration.get("app.amazone.s3.url");
				String bucketName = configuration.get("app.s3path.bdp.bucketName");
				String accessKey  = configuration.get("app.s3path.bdp.accessKey");
				String secretKey  = configuration.get("app.s3path.bdp.secretKey");
				String basePath = configuration.get("app.s3path.bdp.upload.path");
				// ?????? ???????????? ???????????? ????????? ???????????? ?????? ??????.
				AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
				AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
						.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
						.withRegion("ap-northeast-2")
						.build();
				response.setContentType("binary/octet-stream");
				response.addHeader("Content-disposition", "attachment; filename=" + fileName);

				S3Object object = amazonS3.getObject(new GetObjectRequest(bucketName+basePath, s3FileName));
	            inputStream = object.getObjectContent();
	            out = response.getOutputStream();
				byte[] bytes = new byte[bufferSize];
				int size = 0;
				while (-1 != (size = inputStream.read(bytes))) {
					out.write(bytes, 0, size);
				}
				out.flush();
				if(sysFile != null) {
					psysCommonMgr.updateTbbsFlInfDwldCnt(fl_seq);
				}
			} else {
				throw new IllegalArgumentException("file not found");
			}
		} catch(FileNotFoundException e){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			printWriter = response.getWriter();
			printWriter.println("<script type='text/javascript'>alert('?????? ????????? ???????????? ????????????.'); window.onload=window.close();</script>");
			printWriter.flush();
		} finally {
			try {
				inputStream.close();
				if(reader != null)
					 reader.close();
			  } catch (Exception e) {log.debug(e);}
			try { out.close(); } catch (Exception e) {log.debug(e);}
		}
	}



}
