package com.company.dto;

import lombok.Data;

@Data
public class UserReservationDto {
	private String email;
	private String surname;
	private String name;
	private String birthday;
	private String pass_num;
	private String reserve_num;
	private String state;
	private String cancellation;
}
