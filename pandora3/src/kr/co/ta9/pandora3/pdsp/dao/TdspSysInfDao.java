package kr.co.ta9.pandora3.pdsp.dao;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.pcommon.dto.TdspSysInf;

/**
 * TdspSysInfDao - DAO(Data Access Object) class for table [TDSP_SYS_INF].
 *
 * <pre>
 *   Generated by CodeProcessor. You can freely modify this generated file.
 *   Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 02. 16
 */
@Repository
public class TdspSysInfDao extends BaseDao {

	/**
	 * 사이트 조회
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject selectTdspSysInfList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TdspSysInf.selectTdspSysInfList", parameterMap);
	}

	/**
	 * 사이트 콤보 조회
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject selectTdspSysInfComboList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TdspSysInf.selectTdspSysInfComboList", parameterMap);
	}
	
	/**
	 * 로그인 이력 적재 가능 사이트  조회
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject selectTdspSysInfLogComboList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TdspSysInf.selectTdspSysInfLogComboList", parameterMap);
	}

	/**
	 * 접근 가능한 사이트 정보 조회
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAccessSitList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TdspSysInf.getAccessSitList", parameterMap);
	}

	/**
	 *
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public List<TdspSysInf> selectSitList(ParameterMap parameterMap) throws Exception {
		return getSqlSession().selectList("TdspSysInf.selectSitList");
	}

	/**
	 * 디봇접속가능여부
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public int getAccessSitCnt(ParameterMap parameterMap) throws Exception {
		return getSqlSession().selectOne("TdspSysInf.getAccessSitCnt");
	}




}