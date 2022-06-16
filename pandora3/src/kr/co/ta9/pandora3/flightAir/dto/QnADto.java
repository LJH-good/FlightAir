package kr.co.ta9.pandora3.flightAir.dto;

public class QnADto {

	private int board_num;
	private String email;
	private String category;
	private String title;
	private String content;
	private String answer;
	private String date;
	private String cancellation;
	private String user_name;
	public QnADto() {
		super();
	}
	
	public QnADto(int board_num, String email, String category, String title, String content, String answer,
			String date, String cancellation, String user_name) {
		super();
		this.board_num = board_num;
		this.email = email;
		this.category = category;
		this.title = title;
		this.content = content;
		this.answer = answer;
		this.date = date;
		this.cancellation = cancellation;
		this.user_name = user_name;
	}
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCancellation() {
		return cancellation;
	}
	public void setCancellation(String cancellation) {
		this.cancellation = cancellation;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
