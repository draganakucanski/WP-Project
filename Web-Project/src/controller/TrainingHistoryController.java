package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.Comment;
import beans.Role;
import beans.TrainingHistory;
import beans.User;
import dto.AddManagerTrainerDTO;
import dto.FacilitySearchDTO;
import dto.ScheduleTrainingDTO;
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
			if(us.getSportsFacility()==null)
				return null;
			return g.toJson(historyService.getManagersHistories(us.getSportsFacility().getName()));
		}else	
			return g.toJson(historyService.getUsersHistories(username));
	});
	}
	public static void getManagersCustomers() {
		get("rest/trainings/getCustomers", (req, res) -> {
		res.type("application/json");
		User us = userService.getUser(req.session().attribute("logedinUser"));
		//String username = us.getUsername();
		res.status(200);
		if(us.getSportsFacility()==null) {
			return null;
		}
		return g.toJson(historyService.getManagersCustomers(us.getSportsFacility().getName()));
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
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);
			if(us.getRole()==Role.MANAGER) {
				String facName = us.getSportsFacility().getName();
				return g.toJson(historyService.SearchManagerTrainings(search,facName));
			}else	
				return g.toJson(historyService.SearchTrainings(search, username));
		});
	}
	public static void getDateAscHistories() {
		get("rest/trainings/getDateAscHistories", (req, res) -> {
		res.type("application/json");
		User us = userService.getUser(req.session().attribute("logedinUser"));
		String username = us.getUsername();
		res.status(200);
		if(us.getRole()==Role.MANAGER) {
			String facName = us.getSportsFacility().getName();
			return g.toJson(historyService.getManagerDateAscUsersHistories(facName));
		}else	
			return g.toJson(historyService.getDateAscUsersHistories(username));
	});
	}
	public static void getDateDiscHistories() {
		get("rest/trainings/getDateDiscHistories", (req, res) -> {
		res.type("application/json");
		User us = userService.getUser(req.session().attribute("logedinUser"));
		String username = us.getUsername();
		res.status(200);	
		if(us.getRole()==Role.MANAGER) {
			String facName = us.getSportsFacility().getName();
			return g.toJson(historyService.getManagerDateDiscUsersHistories(facName));
		}else	
			return g.toJson(historyService.getDateDiscUsersHistories(username));
	});
	}
	public static void GetGyms() {
		get("rest/trainings/getGyms", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);		
			return g.toJson(historyService.GetGyms(username));
		});
	}
	public static void GetPools() {
		get("rest/trainings/getPools", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);		
			return g.toJson(historyService.GetPools(username));
		});
	}
	public static void GetDanceS() {
		get("rest/trainings/getDanceS", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);		
			return g.toJson(historyService.GetDanceS(username));
		});
	}
	public static void GetSportsC() {
		get("rest/trainings/getSportsC", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);		
			return g.toJson(historyService.GetSportsC(username));
		});
	}
	public static void GetGymTrainings() {
		get("rest/trainings/getGymsT", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);		
			if(us.getRole()==Role.MANAGER) {
				String facName = us.getSportsFacility().getName();
				return g.toJson(historyService.GetManagerGymsT(facName));
			}else	
				return g.toJson(historyService.GetGymsT(username));
		});
	}
	public static void GetGroup() {
		get("rest/trainings/getGroup", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);	
			if(us.getRole()==Role.MANAGER) {
				String facName = us.getSportsFacility().getName();
				return g.toJson(historyService.GetManagerGroup(facName));
			}else	
				return g.toJson(historyService.GetGroup(username));
		});
	}
	public static void GetPersonal() {
		get("rest/trainings/getPersonal", (req, res) -> {
			res.type("application/json");
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			res.status(200);	
			if(us.getRole()==Role.MANAGER) {
				String facName = us.getSportsFacility().getName();
				return g.toJson(historyService.GetManagerPersonal(facName));
			}else	
				return g.toJson(historyService.GetPersonal(username));
		});
	}
	public static void CancelTraining() {
		post("/rest/histories/cancel", (req, res) -> {
			res.type("application/json");
			TrainingHistory th = g.fromJson(req.body(),TrainingHistory.class);
            historyService.Cancel(th);
            return th.isCanceled();
		});
	}
	public static void SignUp() {
		post("/rest/histories/signUp", (req, res) -> {
			res.type("application/json");
			ScheduleTrainingDTO info = g.fromJson(req.body(),ScheduleTrainingDTO.class);
			String scheduledFor = info.date;
			String customer = info.customer;
			String trainingName = info.trainingName;
            return historyService.SignUp(scheduledFor, customer, trainingName);
		});
	}
}
