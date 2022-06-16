package com.company.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.dao.FlightDao;
import com.company.dto.FlightScheduleDto;
import com.company.dto.ReservationDto;
import com.company.dto.ReservationScheduleDto;
import com.company.dto.SchedulePriceDto;
import com.company.dto.UserReservationDto;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired FlightDao dao;
	
	/**
	 * 스케줄 조회 - 출발나라   
	 * 
	 * @return List<FlightScheduleDto>
	 */
	@Override
	public List<FlightScheduleDto> schedule() {
		return dao.schedule();
	}
	
	/**
	 * 스케줄 조회 - 도착나라   
	 *  
	 * @return List<FlightScheduleDto>
	 */
	@Override
	public List<FlightScheduleDto> schedule2() {
		return dao.schedule2();
	}
	
	/**
	 * 스케줄 조회 - 가는 편   
	 * 
	 * @param dto
	 * @return List<FlightScheduleDto>
	 */
	@Override
	public List<FlightScheduleDto> searchdeparture(FlightScheduleDto dto) {
		return dao.searchdeparture(dto);
	}

	/**
	 * 선택한 스케줄 조회   
	 * 
	 * @param dto
	 * @return SchedulePriceDto
	 */
	@Override
	public SchedulePriceDto getschedule(SchedulePriceDto dto) {
		return dao.getschedule(dto);
	}

	/**
	 * 예약 입력 - 왕복 
	 * 
	 * @param dto
	 * @return int
	 */
	@Override
	public int insertreservation(ReservationDto dto) {
		return dao.insertreservation(dto);
	}

	/**
	 * 예약 조회 
	 * 
	 * @param dto
	 * @return List<ReservationDto>
	 */
	@Override
	public List<ReservationDto> reservationnumber(ReservationDto dto) {
		return dao.reservationnumber(dto);
	}

	/**
	 * 예약 상세 조회 - 예약 스케줄 조회 
	 * 
	 * @param dto
	 * @return ReservationScheduleDto
	 */
	@Override
	public ReservationScheduleDto reservationinfo(ReservationScheduleDto dto) {
		return dao.reservationinfo(dto);
	}

	/**
	 * 예약 상세 조회 - 승객 정보 조회 
	 * 
	 * @param dto
	 * @return UserReservationDto
	 */
	@Override
	public UserReservationDto reservationinfo2(UserReservationDto dto) {
		return dao.reservationinfo2(dto);
	}

	/**
	 * 예약 입력 - 편도 
	 * 
	 * @param dto
	 * @return int
	 */
	@Override
	public int insertreservation2(ReservationDto dto) {
		return dao.insertreservation2(dto);
	}

	/**
	 * 예약 취소 
	 * 
	 * @param dto
	 * @return int
	 */
	@Override
	public int deletereservation(ReservationDto dto) {
		return dao.deletereservation(dto);
	}

	/**
	 * 스케줄 조회 - 오는 편 
	 * 
	 * @param dto
	 * @return List<FlightScheduleDto>
	 */
	@Override
	public List<FlightScheduleDto> searchdeparture2(FlightScheduleDto dto) {
		return dao.searchdeparture2(dto);
	}

	/**
	 * 잔여석 조회   
	 * 
	 * @param dto
	 * @return int
	 */
	@Override
	public int spareseat(SchedulePriceDto dto) {
		return dao.spareseat(dto);
	}

	/**
	 * 전체석 조회   
	 * 
	 * @param dto
	 * @return int
	 */
	@Override
	public int totalseat(SchedulePriceDto dto) {
		return dao.totalseat(dto);
	}

	/**
	 * 중복 예약 확인 
	 * 
	 * @param dto
	 * @return int
	 */
	@Override
	public int beforereservation(ReservationDto dto) {
		return dao.beforereservation(dto);
	}

}