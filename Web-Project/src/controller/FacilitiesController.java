package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.SportsFacility;
import beans.User;
import dto.FacilityAddingDTO;
import dto.FacilitySearchDTO;
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
				System.out.println(sf.getName());
				userService.editUsersFacility(objectInfo.username, sf);
			return "OK";
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
}
