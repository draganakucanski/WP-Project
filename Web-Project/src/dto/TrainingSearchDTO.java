package dto;

import java.time.LocalDate;


public class TrainingSearchDTO {
	public String name;
	public double priceFrom;
	public double priceTo;
	public LocalDate from;
	public LocalDate to;
	
	public TrainingSearchDTO(String name, String priceFrom, String priceTo, String from, String to) {
		super();
		this.name = name;
		if(priceFrom=="") {
			this.priceFrom = 0;
		}else {
			this.priceFrom = Double.valueOf(priceFrom);
		}
		if(priceTo=="") {
			this.priceTo = 0;
		}else {
			this.priceTo = Double.valueOf(priceTo);
		}
		if(from=="") {
			this.from = null;
		}else {
			this.from = LocalDate.parse(from);
		}
		if(to=="") {
			this.to = null;
		}else {
			this.to = LocalDate.parse(to);
		}
	}
	
}
