package kr.co.ta9.pandora3.flightAir.dto;

public class PriceDto {
	
	private String price_num;
	private int price;
	private String seat_class;
	private String schedule_num;
	private String add_date;
	private String recent_update;
	
	public PriceDto() {
		super();
	}
	
	public PriceDto(String price_num, int price, String seat_class, String schedule_num, String add_date,
			String recent_update) {
		super();
		this.price_num = price_num;
		this.price = price;
		this.seat_class = seat_class;
		this.schedule_num = schedule_num;
		this.add_date = add_date;
		this.recent_update = recent_update;
	}

	public String getPrice_num() {
		return price_num;
	}
	public void setPrice_num(String price_num) {
		this.price_num = price_num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSeat_class() {
		return seat_class;
	}
	public void setSeat_class(String seat_class) {
		this.seat_class = seat_class;
	}
	public String getSchedule_num() {
		return schedule_num;
	}
	public void setSchedule_num(String schedule_num) {
		this.schedule_num = schedule_num;
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
