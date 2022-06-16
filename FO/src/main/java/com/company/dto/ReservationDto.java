package com.company.dto;

import lombok.Data;

@Data
public class ReservationDto {
	private int reserve_num;
	private String date;
	private String state;
	private String email;
	private String go_schedule;
	private String back_schedule;
	private String go_seat_class;
	private String back_seat_class;
	private String cancellation;
}
