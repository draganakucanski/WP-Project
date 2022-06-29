package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import dto.FacilityAddingDTO;
import dto.FacilitySearchDTO;
import dto.UserRegistrationDTO;
import services.FacilitiesService;

public class FacilitiesController {
	
	private static Gson g = new Gson();
	private static FacilitiesService facilitiesService = new FacilitiesService();
	
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
		public static void addObjec() {
			post("rest/facilities/addNewObject/", (req, res) -> {
				res.type("application/json");
				res.status(200);
				FacilityAddingDTO objectInfo = g.fromJson(req.body(),FacilityAddingDTO.class);
				facilitiesService.FacilityAdding(objectInfo);
			return "OK";
			});
		}
}
