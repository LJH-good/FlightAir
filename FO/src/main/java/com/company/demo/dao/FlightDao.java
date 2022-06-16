package com.company.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.company.dto.FlightScheduleDto;
import com.company.dto.ReservationDto;
import com.company.dto.ReservationScheduleDto;
import com.company.dto.SchedulePriceDto;
import com.company.dto.UserReservationDto;
/**
* <pre>
* 1. 클래스명 : FlightDao
* 2. 설명 : 스케줄 Dao
* 3. 작성일 : 2022-04-30
* 4. 작성자 : LJH
* </pre>
*/
@Mapper
public interface FlightDao {

	/**
	 * 스케줄 조회 - 출발나라   
	 * 
	 * @return List<FlightScheduleDto>
	 */
	public List<FlightScheduleDto> schedule();
	
	/**
	 * 스케줄 조회 - 도착나라   
	 *  
	 * @return List<FlightScheduleDto>
	 */
	public List<FlightScheduleDto> schedule2();

	/**
	 * 스케줄 조회 - 가는 편   
	 * 
	 * @param dto
	 * @return List<FlightScheduleDto>
	 */
	public List<FlightScheduleDto> searchdeparture(FlightScheduleDto dto);

	/**
	 * 스케줄 조회 - 오는 편
	 * 
	 * @param dto
	 * @return List<FlightScheduleDto>
	 */
	public List<FlightScheduleDto> searchdeparture2(FlightScheduleDto dto);
	
	/**
	 * 잔여석 조회   
	 * 
	 * @param dto
	 * @return int
	 */
	public int spareseat(SchedulePriceDto dto);

	/**
	 * 전체석 조회   
	 * 
	 * @param dto
	 * @return int
	 */
	public int totalseat(SchedulePriceDto dto);
	
	/**
	 * 선택한 스케줄 조회   
	 * 
	 * @param dto
	 * @return SchedulePriceDto
	 */
	public SchedulePriceDto getschedule(SchedulePriceDto dto);

	/**
	 * 예약 입력 - 왕복 
	 * 
	 * @param dto
	 * @return int
	 */
	public int insertreservation(ReservationDto dto);

	/**
	 * 예약 입력 - 편도 
	 * 
	 * @param dto
	 * @return int
	 */
	public int insertreservation2(ReservationDto dto);
	
	/**
	 * 예약 조회 
	 * 
	 * @param dto
	 * @return List<ReservationDto>
	 */
	public List<ReservationDto> reservationnumber(ReservationDto dto);

	/**
	 * 예약 상세 조회 - 예약 스케줄 조회 
	 * 
	 * @param dto
	 * @return ReservationScheduleDto
	 */
	public ReservationScheduleDto reservationinfo(ReservationScheduleDto dto);

	/**
	 * 예약 상세 조회 - 승객 정보 조회 
	 * 
	 * @param dto
	 * @return UserReservationDto
	 */
	public UserReservationDto reservationinfo2(UserReservationDto dto);

	/**
	 * 예약 취소 
	 * 
	 * @param dto
	 * @return int
	 */
	public int deletereservation(ReservationDto dto);

	/**
	 * 중복 예약 확인 
	 * 
	 * @param dto
	 * @return int
	 */
	public int beforereservation(ReservationDto dto);

}
