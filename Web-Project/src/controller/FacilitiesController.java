package controller;

import static spark.Spark.get;

import com.google.gson.Gson;

import dto.FacilitySearchDTO;
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
}
