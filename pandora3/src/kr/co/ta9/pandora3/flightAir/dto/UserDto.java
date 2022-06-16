package kr.co.ta9.pandora3.flightAir.dto;

import kr.co.ta9.pandora3.app.bean.CommonBean;

public class UserDto extends CommonBean {
	private String user_name;
	private String surname;
	private String name;
	private String birthday;
	private String pass_num;
	private String email;
	private String password;
	private String post;
	private String addr1;
	private String addr2;
	private String phone;
	private String date;
	private String recent_update;

	public UserDto() {
		super();
	}

	public UserDto(String user_name, String surname, String name, String birthday, String pass_num, String email,
			String password, String post, String addr1, String addr2, String phone, String date, String recent_update) {
		super();
		this.user_name = user_name;
		this.surname = surname;
		this.name = name;
		this.birthday = birthday;
		this.pass_num = pass_num;
		this.email = email;
		this.password = password;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.phone = phone;
		this.date = date;
		this.recent_update = recent_update;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPass_num() {
		return pass_num;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRecent_update() {
		return recent_update;
	}

	public void setRecent_update(String recent_update) {
		this.recent_update = recent_update;
	}

}
