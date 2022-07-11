package services;

import java.util.ArrayList;
import java.util.Collection;

import beans.Address;
import beans.Gender;
import beans.Location;
import beans.SportsFacility;
import beans.Training;
import beans.TrainingType;
import beans.Trainings;
import beans.User;
import beans.Users;
import dto.FacilityAddingDTO;
import dto.TrainingAddingDTO;


public class TrainingService {

	private Trainings trainings = new Trainings();
	
	public Collection<Training> getAll(){
		return trainings.values();
	}
	public ArrayList<Training> getManagersAll(String name){
		ArrayList<Training> ret = new ArrayList<Training>();
		for(Training t : trainings.values()) {
			if(t.getSportsFacility().getName().equals(name))
				ret.add(t);
		}
		return ret;
	}
	public ArrayList<User> getManagersTrainers(String name){
		ArrayList<User> ret = new ArrayList<User>();
		Users us = new Users();
		for(Training t: getManagersAll(name)) {
			if(!ret.contains(us.getUser(t.getTrainer())))
				ret.add(us.getUser(t.getTrainer()));
		}
		System.out.println("OVDDEE");
		System.out.println(ret);
		return ret;
	}
	public boolean NameExists(String name) {
		for (Training t : this.trainings.getValues()) {
			if(t.getName().equals(name)) 
				return true;
		}
		return false;
	}
	public void addTraining(TrainingAddingDTO objectInfo, SportsFacility sf) {
		String logo = null;
		Training training = new Training(objectInfo.name, objectInfo.type, sf, Double.valueOf(objectInfo.duration), objectInfo.trainer, objectInfo.description, logo);
		this.trainings.addTraining(training);
		this.trainings.AddTrainingLogo(training, objectInfo.imageFile);
	}
	public void editTraining(TrainingAddingDTO objectInfo) {
		Training t = trainings.getTraining(objectInfo.name);
		t.setDescription(objectInfo.description);
		t.setDuration(Double.valueOf(objectInfo.duration));
		t.setTrainer(objectInfo.trainer);
		t.setType(objectInfo.type);
		//t.setPicture(null);
		this.trainings.edit(t, objectInfo);
	}
}
