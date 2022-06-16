package kr.co.ta9.pandora3.flightAir.dto;

public class ReservationDto {
	private String reserve_num;
	private String surname;
	private String name;
	private String pass_num;
	private String birthday;
	private String date;
	private String state;
	private String user_no;
	private String go_schedule;
	private String back_schedule;
	private String go_seat_class;
	private String back_seat_class;
	
	public ReservationDto() {
		super();
	}
	
	public ReservationDto(String reserve_num, String surname, String name, String pass_num, String birthday,
			String date, String state, String user_no, String go_schedule, String back_schedule, String go_seat_class,
			String back_seat_class) {
		super();
		this.reserve_num = reserve_num;
		this.surname = surname;
		this.name = name;
		this.pass_num = pass_num;
		this.birthday = birthday;
		this.date = date;
		this.state = state;
		this.user_no = user_no;
		this.go_schedule = go_schedule;
		this.back_schedule = back_schedule;
		this.go_seat_class = go_seat_class;
		this.back_seat_class = back_seat_class;
	}
	
	@Override
	public String toString() {
		return "ReservationDto [reserve_num=" + reserve_num + ", surname=" + surname + ", name=" + name + ", pass_num="
				+ pass_num + ", birthday=" + birthday + ", date=" + date + ", state=" + state + ", user_no=" + user_no
				+ ", go_schedule=" + go_schedule + ", back_schedule=" + back_schedule + ", go_seat_class="
				+ go_seat_class + ", back_seat_class=" + back_seat_class + "]";
	}
	public String getReserve_num() {
		return reserve_num;
	}
	public void setReserve_num(String reserve_num) {
		this.reserve_num = reserve_num;
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
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
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
	
}
