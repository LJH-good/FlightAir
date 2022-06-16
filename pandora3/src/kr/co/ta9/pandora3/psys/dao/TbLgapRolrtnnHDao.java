package kr.co.ta9.pandora3.psys.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;

/**
 * TbLgapRolrtnnHDao - DAO(Data Access Object) class for table [TB_LGAP_ROLRTNN_H].
 *
 * <pre>
 *   Generated by CodeProcessor. You can freely modify this generated file.
 *   Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 11. 14
 */
@Repository
public class TbLgapRolrtnnHDao extends BaseDao {
	
	/**
	 * 사용자 권한 이력 insert
	 * @param tbLgapRolrtnnH
	 * @return int
	 * @throws Exception
	 */
	public int insertTbLgapRolrtnnH(Map<String, Object> tsysAdmRolRtnnMap) throws Exception {
		return getSqlSession().insert("TbLgapRolrtnnHTrx.insertTbLgapRolrtnnH", tsysAdmRolRtnnMap);
	}

}