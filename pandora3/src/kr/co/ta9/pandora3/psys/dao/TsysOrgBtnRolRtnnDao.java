package kr.co.ta9.pandora3.psys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.pcommon.dto.TsysOrgBtnRolRtnn;

/**
 * TsysOrgBtnRolRtnnDao - DAO(Data Access Object) class for table [TSYS_ORG_BTN_ROL_RTNN].
 *
 * <pre>
 *   Generated by CodeProcessor. You can freely modify this generated file.
 *   Copyright &amp;copy 2004 by Pionnet, Inc. All rights reserved.
 * </pre>
 *
 * @since 2019. 03. 13
 */
@Repository
public class TsysOrgBtnRolRtnnDao extends BaseDao {

	public List<TsysOrgBtnRolRtnn> selectTsysOrgBtnRolRtnn(TsysOrgBtnRolRtnn tsysOrgBtnRolRtnn) throws Exception {
		return getSqlSession().selectList("TsysOrgBtnRolRtnn.selectTsysOrgBtnRolRtnn", tsysOrgBtnRolRtnn);
	}
	
	/**
	 * @param tsysOrgBtnRolRtnn
	 * @throws Exception
	 */
	public int deleteTsysOrgBtnRolRtnn(TsysOrgBtnRolRtnn tsysOrgBtnRolRtnn) throws Exception {
		return getSqlSession().delete("TsysOrgBtnRolRtnn.deleteTsysOrgBtnRolRtnn", tsysOrgBtnRolRtnn);
	}

}