package beans;

public class Training {

	private String name;
	private TrainingType type;
	private SportsFacility sportsFacility;
	private double duration;
	private String trainer;
	private String description;
	private String picture;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TrainingType getType() {
		return type;
	}
	public void setType(TrainingType type) {
		this.type = type;
	}
	public SportsFacility getSportsFacility() {
		return sportsFacility;
	}
	public void setSportsFacility(SportsFacility sportsFacility) {
		this.sportsFacility = sportsFacility;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Training(String name, TrainingType type, SportsFacility sportsFacility, double duration, String trainer,
			String description, String picture) {
		super();
		this.name = name;
		this.type = type;
		this.sportsFacility = sportsFacility;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.picture = picture;
	}
	public Training() {
		super();
	}
	
	
	
}
