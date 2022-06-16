package kr.co.ta9.pandora3.psys.dao;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;

/**
 * TsysAdmGrpRolDao - DAO(Data Access Object) class for table [tsys_adm_grp_rol].
 *
 * <pre>
 *   Generated by CodeProcessor. You can freely modify this generated file.
 *   Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 10. 28
 */
@Repository
public class TsysAdmGrpRolDao extends BaseDao {

	/**
	 * 그룹권한목록 조회(그리드형)
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectTsysAdmGrpRolGridList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TsysAdmGrpRol.selectTsysAdmGrpRolGridList", parameterMap);
	}
	
	/**
	 * 그룹권한목록 개수 조회
	 * @param parameterMap
	 * @return int
	 * @throws Exception
	 */
	public int selectTsysAdmGrpRolCnt(ParameterMap parameterMap) throws Exception {
		return getSqlSession().selectOne("TsysAdmGrpRol.selectTsysAdmGrpRolCnt", parameterMap);
	}


	public JSONObject selectTsysAdmGrpRolList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TsysAdmGrpRol.selectTsysAdmGrpRolList", parameterMap);
	}

	/**
	 * 권한 그룹 명 조회
	 * @param parameterMap
	 * @return
	 */
	public String selectTsysAdmGrpRolNms(ParameterMap parameterMap) {
		return getSqlSession().selectOne("TsysAdmGrpRol.selectTsysAdmGrpRolNms", parameterMap);
	}
	

}