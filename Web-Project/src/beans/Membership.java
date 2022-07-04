package beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Membership {

	private String id;
	private MembershipType type;
	private LocalDate payDate;
	private LocalDateTime validTime;
	private double price;
	private String customer;
	private boolean active;
	private int dailyVisit;
	private boolean gotPoints;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MembershipType getType() {
		return type;
	}
	public void setType(MembershipType type) {
		this.type = type;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	public LocalDateTime getValidTime() {
		return validTime;
	}
	public void setValidTime(LocalDateTime validTime) {
		this.validTime = validTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getDailyVisit() {
		return dailyVisit;
	}
	public void setDailyVisit(int dailyVisit) {
		this.dailyVisit = dailyVisit;
	}
	public Membership(String id, MembershipType type, LocalDate payDate, LocalDateTime validTime, double price,
			String customer, boolean active, int dailyVisit) {
		super();
		this.id = id;
		this.type = type;
		this.payDate = payDate;
		this.validTime = validTime;
		this.price = price;
		this.customer = customer;
		this.active = active;
		this.dailyVisit = dailyVisit;
	}
	public Membership() {
		super();
	}
	public Membership(String id, MembershipType type, LocalDate payDate, LocalDateTime validTime, double price,
			String customer, boolean active, int dailyVisit, boolean gotPoints) {
		super();
		this.id = id;
		this.type = type;
		this.payDate = payDate;
		this.validTime = validTime;
		this.price = price;
		this.customer = customer;
		this.active = active;
		this.dailyVisit = dailyVisit;
		this.gotPoints = gotPoints;
	}
	public boolean isGotPoints() {
		return gotPoints;
	}
	public void setGotPoints(boolean gotPoints) {
		this.gotPoints = gotPoints;
	}
	
	
}
