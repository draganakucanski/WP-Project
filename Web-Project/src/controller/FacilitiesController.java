package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.SportsFacility;
import beans.User;
import dto.AddManagerTrainerDTO;
import dto.FacilityAddingDTO;
import dto.FacilitySearchDTO;
import dto.UserFacilityDTO;
import services.FacilitiesService;
import services.UserService;

public class FacilitiesController {
	
	private static Gson g = new Gson();
	private static FacilitiesService facilitiesService = new FacilitiesService();
	private static UserService userService = new UserService();

	
	public static void getFacilities() {
		get("rest/facilities/getJustFacilities/", (req, res) -> {
			res.type("application/json");
			return g.toJson(facilitiesService.getAll());
			
		});
	}
	
	public static void FacilitySearch() {
		get("rest/facilities/getFacilitiesSearch", (req, res) -> {
			res.type("application/json");
			String name = req.queryParams("name");
			String location = req.queryParams("location");
			String grade = req.queryParams("grade");
			String type = req.queryParams("type");
			FacilitySearchDTO search = new FacilitySearchDTO(name, location, grade, type);
			res.status(200);		
			System.out.println(search);
			return g.toJson(facilitiesService.SearchFacility(search));
		});
	}
		public static void GetGyms() {
			get("rest/facilities/getGyms", (req, res) -> {
				res.type("application/json");
				res.status(200);		
				return g.toJson(facilitiesService.GetGyms());
			});
		}
		public static void GetPools() {
			get("rest/facilities/getPools", (req, res) -> {
				res.type("application/json");
				res.status(200);		
				return g.toJson(facilitiesService.GetPools());
			});
		}
		public static void GetDanceS() {
			get("rest/facilities/getDanceS", (req, res) -> {
				res.type("application/json");
				res.status(200);		
				return g.toJson(facilitiesService.GetDanceS());
			});
		}
		public static void GetSportsC() {
			get("rest/facilities/getSportsC", (req, res) -> {
				res.type("application/json");
				res.status(200);		
				return g.toJson(facilitiesService.GetSportsC());
			});
		}
		public static void GetOpened() {
			get("rest/facilities/getOpened", (req, res) -> {
				res.type("application/json");
				res.status(200);		
				return g.toJson(facilitiesService.GetOpened());
			});
		}
		public static void GetClosed() {
			get("rest/facilities/getClosed", (req, res) -> {
				res.type("application/json");
				res.status(200);		
				return g.toJson(facilitiesService.GetClosed());
			});
		}
		public static void AddObject() {
			post("rest/addNewObject", (req, res) -> {
				res.type("application/json");
				res.status(200);
				FacilityAddingDTO objectInfo = g.fromJson(req.body(),FacilityAddingDTO.class);
				SportsFacility sf = facilitiesService.FacilityAdding(objectInfo);
				userService.editUsersFacility(objectInfo.username, sf);
			return "OK";
			});
		}
		public static void AddFacilityWithManager() {
			post("rest/addNewObjectWithManager", (req, res) -> {
				res.type("application/json");
				res.status(200);
				UserFacilityDTO allInfo = g.fromJson(req.body(),UserFacilityDTO.class);
				FacilityAddingDTO objectInfo = new FacilityAddingDTO(allInfo.name, allInfo.number, allInfo.street, allInfo.city, allInfo.zip, allInfo.longi, allInfo.lat, allInfo.workingHours, allInfo.type, allInfo.logo, allInfo.imageFile, allInfo.username);
				AddManagerTrainerDTO userInfo = new AddManagerTrainerDTO(allInfo.username, allInfo.password, allInfo.firstname, allInfo.lastname, allInfo.gender, allInfo.dateOfBirth, "MANAGER");
				userService.AddManagerTrainer(userInfo);
				SportsFacility sf = facilitiesService.FacilityAdding(objectInfo);
				userService.editUsersFacility(objectInfo.username, sf);
			return "OK";
			});
		}
		public static void nameExists() {
			post("rest/facilities/nameExists", (req, res) -> {
				res.type("application/json");
				res.status(200);
				String name = g.fromJson(req.body(),String.class);
				return facilitiesService.NameExists(name);
			});
		}
		public static void getManagersFacility() {
			get("rest/facilities/getManagersFacility", (req, res) -> {
				res.type("application/json");
				User us = userService.getUser(req.session().attribute("logedinUser"));
				String username = us.getUsername();
				return g.toJson(facilitiesService.GetManagersFacility(username));
				
			});
		}
		public static void getFacilityTrainers() {
			get("rest/facilities/getFacilityTrainers", (req, res) -> {
				res.type("application/json");
				User us = userService.getUser(req.session().attribute("logedinUser"));
				String username = us.getUsername();
				SportsFacility objectInfo = facilitiesService.GetManagersFacility(username);
				return g.toJson(facilitiesService.GetFacilityTrainers(objectInfo));
				
			});
		}
		public static void getFacilityCustomers() {
			get("rest/facilities/getFacilityCustomers", (req, res) -> {
				res.type("application/json");
				User us = userService.getUser(req.session().attribute("logedinUser"));
				String username = us.getUsername();
				SportsFacility objectInfo = facilitiesService.GetManagersFacility(username);
				return g.toJson(facilitiesService.GetFacilityCustomers(objectInfo));
				
			});
		}
		public static void getFacilityByName() {
			get("rest/facilities/getFacilityByName", (req, res) -> {
				res.type("application/json");
				String name = req.queryParams("name");
				return g.toJson(facilitiesService.GetFacilityByName(name));
				
			});
		}
}
