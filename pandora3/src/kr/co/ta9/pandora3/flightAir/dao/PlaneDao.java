package kr.co.ta9.pandora3.flightAir.dao;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;
/**
* <pre>
* 1. 클래스명 : PlaneDao
* 2. 설명 : 회원 관리 Dao
* 3. 작성일 : 2022-05-25
* 4. 작성자 : LJH
* </pre>
*/
@Repository
public class PlaneDao extends BaseDao{

	/**
	 * 항공기 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject planeList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Plane.planeList", parameterMap);
	}

	/**
	 * 항공기 번호 가져오기 - 중복검사
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getPlaneNum(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Plane.planeNumList", parameterMap);
	}

}
