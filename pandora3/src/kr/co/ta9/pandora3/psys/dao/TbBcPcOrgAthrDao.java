package kr.co.ta9.pandora3.psys.dao;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.dao.CommonDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;

/**
 * TbBcPcOrgAthrDao - DAO(Data Access Object) class for table [TB_BCPC_ORGATHR].
 *
 * <pre>
 *   Generated by CodeProcessor. You can freely modify this generated file.
 *   Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2020. 02. 28
 */
@Repository
public class TbBcPcOrgAthrDao extends CommonDao {

	/**
	 * 통합권한목록 조회
	 * @param  parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGrpRolList(ParameterMap parameterMap) throws Exception {
		return getSqlSession().selectList("TbBcPcOrgAthr.selectGrpRolList", parameterMap);
	}

	/**
	 * 자점목록 조회
	 * @param  parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCstrList(ParameterMap parameterMap) throws Exception {
	    return getSqlSession().selectList("TbBcPcOrgAthr.selectCstrList", parameterMap);
	}

	/**
	 * 자점목록 조회 str_cl_cd in ( '0', '1' ,'5' , '6') 만 조회 
	 * @param  parameterMap
	 * @throws Exception
	 */
	public JSONObject selectCstrFiterGrdList(ParameterMap parameterMap) throws Exception {
	    return queryForGrid("TbBcPcOrgAthr.selectCstrFiterGrdList", parameterMap);
	}

	/**
	 * 자점목록 조회 str_cl_cd in ( '0', '1' ,'5' , '6') 만 조회 
	 * @param  parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCstrFiterList(ParameterMap parameterMap) throws Exception {
		 return getSqlSession().selectList("TbBcPcOrgAthr.selectCstrFiterList", parameterMap);
	}
	
	
    /**
     * 조직별권한관리 > 조직별 직책권한 목록 조회
     * @param parameterMap
     * @throws Exception
     */
    public JSONObject selectOrgAthrList(ParameterMap parameterMap) throws Exception {
        return queryForGrid("TbBcPcOrgAthr.selectOrgAthrList", parameterMap);
    }

    /**
     * 조직별권한관리 > 직책권한 저장 전 중복체크
     * @param parameterMap
     * @throws Exception
     */
    public int selectDupOrgAthr(ParameterMap parameterMap) throws Exception {
        return getSqlSession().selectOne("TbBcPcOrgAthr.selectDupOrgAthr", parameterMap);
    }

    /**
     * 조직별직책권한관리 > 팝업 > BI라이센스 계정 목록 조회
     * @param parameterMap
     * @throws Exception
     */
    public JSONObject selectBiList(ParameterMap parameterMap) throws Exception {
        return queryForGrid("TbBcPcOrgAthr.selectBiList", parameterMap);
    }
    
    /**
     * 해당 조직의 소속점 변경
     * @param  parameterMap
     * @throws Exception
     */
    public int updateOrgCstrCd(ParameterMap parameterMap) throws Exception {
        return getSqlSession().update("TbBcPcOrgAthrTrx.updateOrgCstrCd", parameterMap);
    }

    /**
     * 해당조직의 직책별 권한 저장
     * @param  parameterMap
     * @throws Exception
     */
    public int insertOrgAthr(ParameterMap parameterMap) throws Exception {
        return getSqlSession().insert("TbBcPcOrgAthr.insertOrgAthr", parameterMap);
    }

    /**
     * 해당조직의 직책별 권한 변경
     * @param  parameterMap
     * @throws Exception
     */
    public int updateOrgAthr(ParameterMap parameterMap) throws Exception {
        return getSqlSession().insert("TbBcPcOrgAthr.updateOrgAthr", parameterMap);
    }

    /**
     * 해당조직의 직책별 권한 삭제
     * @param  parameterMap
     * @throws Exception
     */
    public int deleteOrgAthr(ParameterMap parameterMap) throws Exception {
        return getSqlSession().insert("TbBcPcOrgAthr.deleteOrgAthr", parameterMap);
    }

}