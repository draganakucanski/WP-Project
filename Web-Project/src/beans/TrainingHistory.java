package beans;

import java.time.LocalDateTime;

public class TrainingHistory {
	
	private int id;
	private LocalDateTime dateTime;
	private Training training;
	private String customer;
	private String trainer;
	private boolean canceled;
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public TrainingHistory(LocalDateTime dateTime, Training training, String customer, String trainer) {
		super();
		this.dateTime = dateTime;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}
	public TrainingHistory() {
		super();
	}
	public TrainingHistory(int id, LocalDateTime dateTime, Training training, String customer, String trainer) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	
	
}
