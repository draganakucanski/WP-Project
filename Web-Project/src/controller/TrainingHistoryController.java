package controller;

import static spark.Spark.get;

import com.google.gson.Gson;

import beans.Role;
import beans.User;
import dto.FacilitySearchDTO;
import dto.TrainingSearchDTO;
import services.TrainingHistoryService;
import services.UserService;

public class TrainingHistoryController {

	private static Gson g = new Gson();
	private static TrainingHistoryService historyService = new TrainingHistoryService();
	private static UserService userService = new UserService();
	
	public static void getAllHistories() {
		get("rest/trainings/getAllHistories", (req, res) -> {
			res.type("application/json");
			return g.toJson(historyService.getAll());
			
		});
	}
	public static void getUsersHistories() {
		get("rest/trainings/getUsersHistories", (req, res) -> {
		res.type("application/json");
		User us = userService.getUser(req.session().attribute("logedinUser"));
		String username = us.getUsername();
		res.status(200);
		if(us.getRole()==Role.MANAGER) {
			return g.toJson(historyService.getManagersHistories(us.getSportsFacility().getName()));
		}else	
		return g.toJson(historyService.getUsersHistories(username));
	});
	}
	public static void TrainingSearch() {
		get("rest/trainings/getTrainingSearch", (req, res) -> {
			res.type("application/json");
			String name = req.queryParams("name");
			String priceFrom = req.queryParams("priceStart");
			String priceTo = req.queryParams("priceTo");
			String dateFrom = req.queryParams("dateFrom");
			String dateTo = req.queryParams("dateTo");
			TrainingSearchDTO search = new TrainingSearchDTO(name, priceFrom, priceTo, dateFrom, dateTo);
			res.status(200);		
			System.out.println(search);
			return g.toJson(historyService.SearchTrainings(search));
		});
	}
	public static void getDateAscHistories() {
		get("rest/trainings/getDateAscHistories", (req, res) -> {
		res.type("application/json");
		User us = userService.getUser(req.session().attribute("logedinUser"));
		String username = us.getUsername();
		res.status(200);		
		return g.toJson(historyService.getDateAscUsersHistories(username));
	});
	}
	public static void getDateDiscHistories() {
		get("rest/trainings/getDateDiscHistories", (req, res) -> {
		res.type("application/json");
		User us = userService.getUser(req.session().attribute("logedinUser"));
		String username = us.getUsername();
		res.status(200);		
		return g.toJson(historyService.getDateDiscUsersHistories(username));
	});
	}
	public static void GetGyms() {
		get("rest/trainings/getGyms", (req, res) -> {
			res.type("application/json");
			res.status(200);		
			return g.toJson(historyService.GetGyms());
		});
	}
	public static void GetPools() {
		get("rest/trainings/getPools", (req, res) -> {
			res.type("application/json");
			res.status(200);		
			return g.toJson(historyService.GetPools());
		});
	}
	public static void GetDanceS() {
		get("rest/trainings/getDanceS", (req, res) -> {
			res.type("application/json");
			res.status(200);		
			return g.toJson(historyService.GetDanceS());
		});
	}
	public static void GetSportsC() {
		get("rest/trainings/getSportsC", (req, res) -> {
			res.type("application/json");
			res.status(200);		
			return g.toJson(historyService.GetSportsC());
		});
	}
	public static void GetGymTrainings() {
		get("rest/trainings/getGymsT", (req, res) -> {
			res.type("application/json");
			res.status(200);		
			return g.toJson(historyService.GetGymsT());
		});
	}
	public static void GetGroup() {
		get("rest/trainings/getGroup", (req, res) -> {
			res.type("application/json");
			res.status(200);		
			return g.toJson(historyService.GetGroup());
		});
	}
	public static void GetPersonal() {
		get("rest/trainings/getPersonal", (req, res) -> {
			res.type("application/json");
			res.status(200);		
			return g.toJson(historyService.GetPersonal());
		});
	}
}
