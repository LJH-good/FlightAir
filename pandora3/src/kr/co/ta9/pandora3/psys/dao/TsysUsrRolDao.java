package kr.co.ta9.pandora3.psys.dao;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.pcommon.dto.TsysUsrRol;
import kr.co.ta9.pandora3.psys.dao.base.BaseTsysUsrRolDao;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

/**
 * TsysUsrRolDao - DAO(Data Access Object) class for table [TSYS_USR_ROL].
 *
 * <pre>
 *   Generated by CodeProcessor. You can freely modify this generated file.
 *   Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 02. 16
 */
@Repository
public class TsysUsrRolDao extends BaseTsysUsrRolDao {
	
	/**
	 * 사용자 권한 그리드 리스트 조회
	 * @param  parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectTsysUsrRolGridList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TsysUsrRol.selectTsysUsrRolGridList", parameterMap);
	}
	
	/**
	 * 사용자 권한수
	 * @param  parameterMap
	 * @return int
	 * @throws Exception
	 */
	public int selectTsysUsrRolCnt(ParameterMap parameterMap) throws Exception {
		return getSqlSession().selectOne("TsysUsrRol.selectTsysUsrRolCnt", parameterMap);
	}
	
	/**
	 * 사용자 권한 목록 (전체)
	 * @param  parameterMap
	 * @return List<TsysUsrRol>
	 * @throws Exception
	 */
	public List<TsysUsrRol> selectTsysUsrRolList(ParameterMap parameterMap) throws Exception {
		return getSqlSession().selectList("TsysUsrRol.selectTsysUsrRolList", parameterMap);
	}
	
}