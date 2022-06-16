package kr.co.ta9.pandora3.flightAir.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.flightAir.dao.ReservationDao;
import kr.co.ta9.pandora3.flightAir.dto.ReservationDto;

@Service
public class ReservationMgr {

	@Autowired
	private ReservationDao reservationDao;

	/**
	 * 예약 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject reservationList(ParameterMap parameterMap) throws Exception {
		return reservationDao.reservationList(parameterMap);
	}

	/**
	 * 예약 상태 변경
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void reservationState(ParameterMap parameterMap) throws Exception {
		List<ReservationDto> insertList = new ArrayList<ReservationDto>();
		List<ReservationDto> updateList = new ArrayList<ReservationDto>();
		List<ReservationDto> deleteList = new ArrayList<ReservationDto>();
		parameterMap.populates(ReservationDto.class, insertList, updateList, deleteList, "reservationData");

		ReservationDto[] update = updateList.toArray(new ReservationDto[0]);

		reservationDao.updateMany("Reservation.updateReservation", update);
	}

	/**
	 * 잔여석 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getSpareSeat(ParameterMap parameterMap) throws Exception {
		return reservationDao.getSpareSeat(parameterMap);
	}
	
	/**
	 * 전체석 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getTotalSeat(ParameterMap parameterMap) throws Exception {
		return reservationDao.getTotalSeat(parameterMap);
	}
}
