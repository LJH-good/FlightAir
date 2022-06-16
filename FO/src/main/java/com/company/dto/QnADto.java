package com.company.dto;

import lombok.Data;

@Data
public class QnADto {
	private int board_num;
	private String email;
	private String category;
	private String title;
	private String content;
	private String answer;
	private String date;
	private String cancellation;
}
