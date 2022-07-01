package services;

import java.util.Collection;

import beans.Training;
import beans.Trainings;


public class TrainingService {

	private Trainings trainings = new Trainings();
	
	public Collection<Training> getAll(){
		return trainings.values();
	}
}
