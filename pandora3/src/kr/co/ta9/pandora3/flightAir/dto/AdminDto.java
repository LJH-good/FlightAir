package kr.co.ta9.pandora3.flightAir.dto;

public class AdminDto {

	private String admin_num;

	public AdminDto() {
		super();
	}

	public AdminDto(String admin_num) {
		super();
		this.admin_num = admin_num;
	}

	@Override
	public String toString() {
		return "AdminDto [admin_num=" + admin_num + "]";
	}

	public String getAdmin_num() {
		return admin_num;
	}

	public void setAdmin_num(String admin_num) {
		this.admin_num = admin_num;
	}
	
	
}
