package kr.co.ta9.pandora3.flightAir.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.flightAir.dao.FlightDao;
import kr.co.ta9.pandora3.flightAir.dto.FlightScheduleDto;
import kr.co.ta9.pandora3.flightAir.dto.PriceDto;

/**
* <pre>
* 1. 클래스명 : FlightMgr
* 2. 설명 : 스케줄 관리 서비스
* 3. 작성일 : 2022-05-23
* 4. 작성자 : LJH
* </pre>
*/
@Service
public class FlightMgr {
	
	@Autowired
	private FlightDao flightDao;
	
	/**
	 * 스케줄 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectScheduleList(ParameterMap parameterMap) throws Exception {
		return flightDao.selectScheduleList(parameterMap);
	}

	/**
	 * 스케줄 예약 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject scheduleReserveList(ParameterMap parameterMap) throws Exception {
		return flightDao.scheduleReserveList(parameterMap);
	}
	
	/**
	 * 가격 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject priceList(ParameterMap parameterMap) throws Exception {
		return flightDao.priceList(parameterMap);
	}

	/**
	 * 가격 행 추가
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void priceInsert(ParameterMap parameterMap) throws Exception {
		List<PriceDto> insertList = new ArrayList<PriceDto>();
		List<PriceDto> updateList = new ArrayList<PriceDto>();
		List<PriceDto> deleteList = new ArrayList<PriceDto>();
		parameterMap.populates(PriceDto.class, insertList, updateList, deleteList, "priceData");

		PriceDto[] insert = insertList.toArray(new PriceDto[0]);
		PriceDto[] update = updateList.toArray(new PriceDto[0]);

		flightDao.insertMany("Flight.insertPrice", insert);
		flightDao.updateMany("Flight.updatePrice", update);
	}
	
	/**
	 * 가격 번호 가져오기 - 가격 입력시 자동 생성
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject pricenumInsert(ParameterMap parameterMap) throws Exception {
		return flightDao.pricenumInsert(parameterMap);
	}
	
	/**
	 * 스케줄 번호 가져오기 - select option
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getScheduleNum(ParameterMap parameterMap) throws Exception {
		return flightDao.getScheduleNum(parameterMap);
	}

	/**
	 * 스케줄 행 추가 / 수정
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void scheduleInsert(ParameterMap parameterMap) throws Exception {
		List<FlightScheduleDto> insertList = new ArrayList<FlightScheduleDto>();
		List<FlightScheduleDto> updateList = new ArrayList<FlightScheduleDto>();
		List<FlightScheduleDto> deleteList = new ArrayList<FlightScheduleDto>();
		parameterMap.populates(FlightScheduleDto.class, insertList, updateList, deleteList, "scheduleData");

		FlightScheduleDto[] insert = insertList.toArray(new FlightScheduleDto[0]);
		FlightScheduleDto[] update = updateList.toArray(new FlightScheduleDto[0]);
		
		// 스케줄 비활성 시 모든 예약 취소
		for(int i = 0; i < update.length; i++) {
			if(update[i].getState().equals("비활성")) {
				flightDao.updateMany("Reservation.cancelReservation", update[i]);
			}
		}
		flightDao.insertMany("Flight.insertSchedule", insert);
		flightDao.updateMany("Flight.updateSchedule", update);
	}
	
	/**
	 * 스케줄 행 삭제 
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void scheduleDelete(ParameterMap parameterMap) throws Exception {
		List<FlightScheduleDto> insertList = new ArrayList<FlightScheduleDto>();
		List<FlightScheduleDto> updateList = new ArrayList<FlightScheduleDto>();
		List<FlightScheduleDto> deleteList = new ArrayList<FlightScheduleDto>();
		parameterMap.populates(FlightScheduleDto.class, insertList, updateList, deleteList, "scheduleData");

		FlightScheduleDto[] delete = deleteList.toArray(new FlightScheduleDto[0]);

		flightDao.deleteMany("Flight.scheduleDelete", delete);
	}
	
	/**
	 * 스케줄 번호 가져오기 - 행 추가 시 자동생성
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject schedulenumInsert(ParameterMap parameterMap) throws Exception {
		return flightDao.schedulenumInsert(parameterMap);
	}

	/**
	 * 항공기 번호 가져오기 - select option
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	
	 public JSONObject getSelectPlane(ParameterMap parameterMap) throws Exception{ 
		 return flightDao.getSelectPlane(parameterMap); 
	}
	 

	/**
	 * 가격 행 삭제 
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void priceDelete(ParameterMap parameterMap) throws Exception {
		List<PriceDto> insertList = new ArrayList<PriceDto>();
		List<PriceDto> updateList = new ArrayList<PriceDto>();
		List<PriceDto> deleteList = new ArrayList<PriceDto>();
		parameterMap.populates(PriceDto.class, insertList, updateList, deleteList, "priceData");

		PriceDto[] delete = deleteList.toArray(new PriceDto[0]);

		flightDao.deleteMany("Flight.priceDelete", delete);
	}

	/**
	 * 가격 스케줄/좌석 중복확인 
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject overlapPrice(ParameterMap parameterMap) throws Exception {
		return flightDao.overlapPrice(parameterMap);
	}

}
