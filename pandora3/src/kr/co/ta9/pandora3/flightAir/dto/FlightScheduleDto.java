package kr.co.ta9.pandora3.flightAir.dto;

import kr.co.ta9.pandora3.app.bean.CommonBean;

public class FlightScheduleDto extends CommonBean{
	private String schedule_num;
	private String de_country;
	private String arr_country;
	private String de_time;
	private String arr_time;
	private String flight_time;
	private String de_date;
	private String admin_num;
	private String plane_num;
	private String state;
	private String add_date;
	private String recent_update; 
	
	public FlightScheduleDto() {
		super();
	}

	public FlightScheduleDto(String schedule_num, String de_country, String arr_country, String de_time,
			String arr_time, String flight_time, String de_date, String admin_num, String plane_num, String state,
			String add_date, String recent_update) {
		super();
		this.schedule_num = schedule_num;
		this.de_country = de_country;
		this.arr_country = arr_country;
		this.de_time = de_time;
		this.arr_time = arr_time;
		this.flight_time = flight_time;
		this.de_date = de_date;
		this.admin_num = admin_num;
		this.plane_num = plane_num;
		this.state = state;
		this.add_date = add_date;
		this.recent_update = recent_update;
	}

	public String getSchedule_num() {
		return schedule_num;
	}

	public void setSchedule_num(String schedule_num) {
		this.schedule_num = schedule_num;
	}

	public String getDe_country() {
		return de_country;
	}

	public void setDe_country(String de_country) {
		this.de_country = de_country;
	}

	public String getArr_country() {
		return arr_country;
	}

	public void setArr_country(String arr_country) {
		this.arr_country = arr_country;
	}

	public String getDe_time() {
		return de_time;
	}

	public void setDe_time(String de_time) {
		this.de_time = de_time;
	}

	public String getArr_time() {
		return arr_time;
	}

	public void setArr_time(String arr_time) {
		this.arr_time = arr_time;
	}

	public String getFlight_time() {
		return flight_time;
	}

	public void setFlight_time(String flight_time) {
		this.flight_time = flight_time;
	}

	public String getDe_date() {
		return de_date;
	}

	public void setDe_date(String de_date) {
		this.de_date = de_date;
	}

	public String getAdmin_num() {
		return admin_num;
	}

	public void setAdmin_num(String admin_num) {
		this.admin_num = admin_num;
	}

	public String getPlane_num() {
		return plane_num;
	}

	public void setPlane_num(String plane_num) {
		this.plane_num = plane_num;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRecent_update() {
		return recent_update;
	}

	public void setRecent_update(String recent_update) {
		this.recent_update = recent_update;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
	

}
