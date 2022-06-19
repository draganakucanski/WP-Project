package controller;

import static spark.Spark.get;

import com.google.gson.Gson;

import services.FacilitiesService;

public class FacilitiesController {
	
	private static Gson g = new Gson();
	private static FacilitiesService facilitiesService = new FacilitiesService();
	
	private static void getFacilities() {
		get("/rest/facilities/getJustFacilities", (req, res) -> {
			res.type("application/json");
			return g.toJson(facilitiesService.getAll());
			
		});
	}
}
