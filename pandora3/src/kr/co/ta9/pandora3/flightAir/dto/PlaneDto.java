package kr.co.ta9.pandora3.flightAir.dto;

public class PlaneDto {
	private String plane_num;
	private int first_capacity;
	private int second_capacity;
	private String add_date;
	private String recent_update;
	
	public PlaneDto() {
		super();
	}

	public PlaneDto(String plane_num, int first_capacity, int second_capacity, String add_date, String recent_update) {
		super();
		this.plane_num = plane_num;
		this.first_capacity = first_capacity;
		this.second_capacity = second_capacity;
		this.add_date = add_date;
		this.recent_update = recent_update;
	}

	public String getPlane_num() {
		return plane_num;
	}

	public void setPlane_num(String plane_num) {
		this.plane_num = plane_num;
	}

	public int getFirst_capacity() {
		return first_capacity;
	}

	public void setFirst_capacity(int first_capacity) {
		this.first_capacity = first_capacity;
	}

	public int getSecond_capacity() {
		return second_capacity;
	}

	public void setSecond_capacity(int second_capacity) {
		this.second_capacity = second_capacity;
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
