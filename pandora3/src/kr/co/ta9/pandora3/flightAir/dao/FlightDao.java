package kr.co.ta9.pandora3.flightAir.dao;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;

/**
* <pre>
* 1. 클래스명 : FlightDao
* 2. 설명 : 스케줄 관리 Dao
* 3. 작성일 : 2022-05-23
* 4. 작성자 : LJH
* </pre>
*/
@Repository
public class FlightDao extends BaseDao{
	
	/**
	 * 스케줄 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectScheduleList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Flight.selectScheduleList", parameterMap);
	}

	/**
	 * 스케줄 예약 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject scheduleReserveList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Flight.scheduleReserveList", parameterMap);
	}

	/**
	 * 가격 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject priceList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Flight.priceList", parameterMap);
	}
	
	/**
	 * 가격 번호 가져오기 - 가격 입력시 자동생성
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject pricenumInsert(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Flight.pricenumInsert", parameterMap);
	}
	
	/**
	 * 스케줄 번호 가져오기 - select option
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getScheduleNum(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Flight.getScheduleNum", parameterMap);
	}
	
	/**
	 * 스케줄 번호 가져오기 - 행 추가 시 자동생성
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject schedulenumInsert(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Flight.schedulenumInsert", parameterMap);
	}

	/**
	 * 항공기 번호 가져오기 - select option
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getSelectPlane(ParameterMap parameterMap) throws Exception{ 
		return queryForGrid("Flight.getSelectPlane", parameterMap); 
	}

	/**
	 * 가격 스케줄/좌석 중복확인 
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject overlapPrice(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Flight.overlapPrice", parameterMap);
	}
	
	
}
