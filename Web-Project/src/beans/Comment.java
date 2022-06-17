package beans;

public class Comment {

	private String customer;
	private String facilityName;
	private String content;
	private int grade;
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Comment(String customer, String facilityName, String content, int grade) {
		super();
		this.customer = customer;
		this.facilityName = facilityName;
		this.content = content;
		this.grade = grade;
	}
	public Comment() {
		super();
	}
	
	
}
