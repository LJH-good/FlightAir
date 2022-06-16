package com.company.dto;

import lombok.Data;

@Data
public class SchedulePriceDto {
	private  String schedule_num;
	private  String plane_num;
	private  String de_country;
	private  String arr_country;
	private  String de_time;
	private  String arr_time;
	private  String flight_time;
	private  String de_date;
	private  int 	price;
	private  String seat_class;
	private  String price_num;
}
