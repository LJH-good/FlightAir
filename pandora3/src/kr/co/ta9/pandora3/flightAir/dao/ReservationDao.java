package kr.co.ta9.pandora3.flightAir.dao;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;

/**
* <pre>
* 1. 클래스명 : ReservationDao
* 2. 설명 : 예약 관리 Dao
* 3. 작성일 : 2022-05-25
* 4. 작성자 : LJH
* </pre>
*/
@Repository
public class ReservationDao extends BaseDao{

	/**
	 * 예약 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject reservationList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Reservation.reservationList", parameterMap);
	}

	/**
	 * 잔여석 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getSpareSeat(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Reservation.getSpareSeat", parameterMap);
	}
	
	/**
	 * 전체석 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getTotalSeat(ParameterMap parameterMap) throws Exception {
		return queryForGrid("Reservation.getTotalSeat", parameterMap);
	}

}
