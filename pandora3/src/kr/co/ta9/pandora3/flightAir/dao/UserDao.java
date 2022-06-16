package kr.co.ta9.pandora3.flightAir.dao;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;

/**
* <pre>
* 1. 클래스명 : UserDao
* 2. 설명 : 회원 관리 Dao
* 3. 작성일 : 2022-05-20
* 4. 작성자 : LJH
* </pre>
*/
@Repository
public class UserDao extends BaseDao {

	/**
	 * 회원 관리
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectUserList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("User.selectUserList", parameterMap);
	}
	
	/**
	 * 회원 삭제
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject deleteUser(ParameterMap parameterMap) throws Exception {
		return queryForGrid("User.deleteUser", parameterMap);
	}

	/**
	 * 회원 삭제 시 예약 내역 확인
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject reservationCheck(ParameterMap parameterMap) throws Exception {
		return queryForGrid("User.reservationCheck", parameterMap);
	}

}