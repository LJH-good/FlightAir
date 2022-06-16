package kr.co.ta9.pandora3.flightAir.dto;

public class ReservationUserDto {
	private String reserve_num;
	private String surname;
	private String name;
	private String pass_num;
	private String birthday;
	private String email;
	private String date;
	private String state;
	private String go_schedule;
	private String back_schedule;
	private String go_seat_class;
	private String back_seat_class;
	private String user_name;
	private String cancellation;
	
	public ReservationUserDto() {
		super();
	}
	

	public ReservationUserDto(String reserve_num, String surname, String name, String pass_num, String birthday,
			String email, String date, String state, String go_schedule, String back_schedule, String go_seat_class,
			String back_seat_class, String user_name, String cancellation) {
		super();
		this.reserve_num = reserve_num;
		this.surname = surname;
		this.name = name;
		this.pass_num = pass_num;
		this.birthday = birthday;
		this.email = email;
		this.date = date;
		this.state = state;
		this.go_schedule = go_schedule;
		this.back_schedule = back_schedule;
		this.go_seat_class = go_seat_class;
		this.back_seat_class = back_seat_class;
		this.user_name = user_name;
		this.cancellation = cancellation;
	}


	public String getReserve_num() {
		return reserve_num;
	}
	public void setReserve_num(String reserve_num) {
		this.reserve_num = reserve_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGo_schedule() {
		return go_schedule;
	}
	public void setGo_schedule(String go_schedule) {
		this.go_schedule = go_schedule;
	}
	public String getBack_schedule() {
		return back_schedule;
	}
	public void setBack_schedule(String back_schedule) {
		this.back_schedule = back_schedule;
	}
	public String getGo_seat_class() {
		return go_seat_class;
	}
	public void setGo_seat_class(String go_seat_class) {
		this.go_seat_class = go_seat_class;
	}
	public String getBack_seat_class() {
		return back_seat_class;
	}
	public void setBack_seat_class(String back_seat_class) {
		this.back_seat_class = back_seat_class;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCancellation() {
		return cancellation;
	}
	public void setCancellation(String cancellation) {
		this.cancellation = cancellation;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass_num() {
		return pass_num;
	}
	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}
