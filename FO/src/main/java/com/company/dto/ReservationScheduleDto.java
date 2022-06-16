package com.company.dto;

import lombok.Data;

@Data
public class ReservationScheduleDto {
	private String de_country;
	private String arr_country;
	private String de_time;
	private String arr_time;
	private String de_date;
	private String plane_num;
	private String seat_class;
	private String schedule_num;
	private int price;
}
