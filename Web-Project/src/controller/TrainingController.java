package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.SportsFacility;
import beans.User;
import dto.FacilityAddingDTO;
import dto.TrainingAddingDTO;
import dto.UserEditDTO;
import services.TrainingService;
import services.UserService;


public class TrainingController {

	private static Gson g = new Gson();
	private static TrainingService trainingService = new TrainingService();
	private static UserService userService = new UserService();
	
	public static void getTrainings() {
		get("rest/trainings/getTrainings", (req, res) -> {
			res.type("application/json");
			return g.toJson(trainingService.getAll());
			
		});
	}
	public static void getManagersTrainings() {
		get("rest/trainings/getMTrainings", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			if(us.getSportsFacility() == null) {
				return null;
			}
			String facilityName = us.getSportsFacility().getName();
			return g.toJson(trainingService.getManagersAll(facilityName));
			
		});
	}
	public static void ContentNameExists() {
		post("/rest/trainings/NameExists", (req, res) -> {
			res.type("application/json");
			res.status(200);
			String name = g.fromJson(req.body(),String.class);
			return trainingService.NameExists(name);
		});
	}
	public static void AddTraining() {
		post("rest/trainings/addContent", (req, res) -> {
			res.type("application/json");
			res.status(200);
			TrainingAddingDTO objectInfo = g.fromJson(req.body(),TrainingAddingDTO.class);
			User us = userService.getUser(req.session().attribute("logedinUser"));
			SportsFacility facility = us.getSportsFacility();
			trainingService.addTraining(objectInfo, facility);
		return "OK";
		});
	}
	public static void editTraining() {
		post("rest/trainings/editData/", (req, res) -> {
			res.type("application/json");
			TrainingAddingDTO objectInfo = g.fromJson(req.body(),TrainingAddingDTO.class);
            trainingService.editTraining(objectInfo);
           
            
            return "OK";
		});
	}
}
