package beans;

public class Comment {

	private int id;
	private String customer;
	private String facilityName;
	private String content;
	private int grade;
	private boolean approved;
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
	public Comment(int id, String customer, String facilityName, String content, int grade) {
		super();
		this.id = id;
		this.customer = customer;
		this.facilityName = facilityName;
		this.content = content;
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Comment(int id, String customer, String facilityName, String content, int grade, boolean approved) {
		super();
		this.id = id;
		this.customer = customer;
		this.facilityName = facilityName;
		this.content = content;
		this.grade = grade;
		this.approved = approved;
	}
	
	
}
