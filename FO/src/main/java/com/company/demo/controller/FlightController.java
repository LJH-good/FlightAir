package com.company.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.service.FlightService;
import com.company.dto.FlightScheduleDto;
import com.company.dto.ReservationDto;
import com.company.dto.ReservationScheduleDto;
import com.company.dto.SchedulePriceDto;
import com.company.dto.UserReservationDto;

/**
* <pre>
* 1. 클래스명 : FlightController
* 2. 설명 : 스케줄 컨트롤러
* 3. 작성일 : 2022-04-30
* 4. 작성자 : LJH
* </pre>
*/
@RestController
@RequestMapping("/flight")
public class FlightController { 
	@Autowired private FlightService service;
	
	/**
	 * 스케쥴 조회 - 출발 나라
	 * 
	 * @return List<FlightScheduleDto>
	 * Home.vue
	 */
	@PostMapping("/schedule")
	public List<FlightScheduleDto> schedule() {
		return service.schedule();
	}
	
	/**
	 * 스케쥴 조회 - 도착 나라 
	 * 
	 * @return List<FlightScheduleDto>
	 * Home.vue
	 */
	@PostMapping("/schedule2")
	public List<FlightScheduleDto> schedule2() {
		return service.schedule2();
	}
	
	/**
	 * 스케쥴 조회 - 가는 편
	 * 
	 * @param dto
	 * @return List<FlightScheduleDto>
	 * SchdeuleSelect.vue
	 */
	@PostMapping("/searchdeparture")
	public List<FlightScheduleDto> searchdeparture(FlightScheduleDto dto){
		if (dto.getDe_date().equals(LocalDate.now().toString())) {
			dto.setIsToday("Y");
		} else {
			dto.setIsToday("N");
		}
		if(service.searchdeparture(dto).isEmpty()) { return null; }
		else { return service.searchdeparture(dto); }
	}
	
	/**
	 * 스케쥴 조회 - 오는 편
	 * 
	 * @param dto
	 * @return List<FlightScheduleDto>
	 * SchdeuleSelect2.vue
	 */
	@PostMapping("/searchdeparture2")
	public List<FlightScheduleDto> searchdeparture2(FlightScheduleDto dto){
		if(service.searchdeparture2(dto).isEmpty()) { return null; }
		else { return service.searchdeparture2(dto); }
	}
	
	/**
	 * 스케쥴 조회 - 잔여석 조회(가는 편)
	 * 
	 * @param dto
	 * @return String 
	 * SchdeuleSelect.vue
	 */
	@PostMapping("/spareseat")
	public String spareseat(SchedulePriceDto dto){
		int spareseat = service.spareseat(dto);
		int totalseat = service.totalseat(dto);
		return (totalseat - spareseat) + "/" + totalseat;
	}
	
	/**
	 * 선택한 스케쥴 조회
	 * 
	 * @param dto
	 * @return SchedulePriceDto
	 * CheckOut.vue
	 */
	@PostMapping("/getschedule")
	public SchedulePriceDto getschedule(SchedulePriceDto dto) {
		return service.getschedule(dto);
	}
	
	/**
	 * 예약 조회 
	 * 
	 * @param dto
	 * @return List<ReservationDto>
	 * MyReservation.vue
	 */
	@PostMapping("/reservationnumber")
	public List<ReservationDto> reservationnumber(ReservationDto dto){
		if(service.reservationnumber(dto).isEmpty()) {return null;}
		else {return service.reservationnumber(dto);}
	}
	
	/** 
	 * 예약 상세 조회 - 예약 스케쥴 조회
	 * 
	 * @param dto
	 * @return ReservationScheduleDto
	 * ReservationCheck.vue
	 */
	@PostMapping("/reservationinfo")
	public ReservationScheduleDto reservationinfo(ReservationScheduleDto dto){
		return service.reservationinfo(dto);
	}
	
	/**
	 * 예약 상세 조회 - 승객 정보 조회
	 * 
	 * @param dto
	 * @return UserReservationDto
	 * MyReservation.vue
	 */
	@PostMapping("/reservationinfo2")
	public UserReservationDto reservationinfo2(UserReservationDto dto){
		return service.reservationinfo2(dto);
	}
	
	/**
	 * 중복 예약 확인
	 * 
	 * @param dto
	 * @return String
	 * CheckOut.vue
	 */
	@PostMapping("/beforereservation")
	public String beforereservation(ReservationDto dto) throws Exception {
		if (service.beforereservation(dto) > 0) {
			return "success";
		}
		return null;
	}
	
	/**
	 * 예약 입력 - 왕복
	 * 
	 * @param dto
	 * @return String
	 * CheckOut.vue
	 */
	@PostMapping("/insertreservation")
	public String insertreservation(ReservationDto dto) throws Exception {
		if(service.insertreservation(dto) >0 ){return"success";}
		return "fail";
	}
	
	/**
	 * 예약 입력 - 편도
	 * 
	 * @param dto
	 * @return String
	 * CheckOut2.vue
	 */
	@PostMapping("/insertreservation2")
	public String insertreservation2(ReservationDto dto) throws Exception {
		if(service.insertreservation2(dto) >0) {return"success";}
		return "fail";
		
	}
	
	/**
	 * 예약 취소 
	 * 
	 * @param dto
	 * @return String
	 * ReservationCheck.vue
	 */
	@PostMapping("/deletereservation")
	public String deletereservation(ReservationDto dto) {
		if(service.deletereservation(dto)>0) {return "success";}
		return "fail";
	}
}