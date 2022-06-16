package kr.co.ta9.pandora3.psys.dao;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.pcommon.dto.TbBcpcAthrApp;

/**
 * TbLgapAthrappHDao - DAO(Data Access Object) class for table [TB_LGAP_ATHRAPP_H].
 *
 * <pre>
 *   Generated by CodeProcessor. You can freely modify this generated file.
 *   Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 11. 05
 */
@Repository
public class TbLgapAthrappHDao extends BaseDao {

	/**
	 * 권한 관리 > 권한 신청 이력
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject selectTbLgapAthrappHList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("TbLgapAthrappH.selectTbLgapAthrappHList", parameterMap);
	}
	
	/**
	 * 개인 권한 신청 및 승인 이력 처리.
	 * @param tbBcpcAthrApp
	 * @throws Exception
	 */
	public int insertTbLgapAthrappH(TbBcpcAthrApp tbBcpcAthrApp) throws Exception {
		return getSqlSession().insert("TbLgapAthrappHTrx.insertTbLgapAthrappH", tbBcpcAthrApp);
		
	}

}