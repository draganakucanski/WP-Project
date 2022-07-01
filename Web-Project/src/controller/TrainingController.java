package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import services.TrainingService;


public class TrainingController {

	private static Gson g = new Gson();
	private static TrainingService trainingService = new TrainingService();
	
	public static void getTrainings() {
		get("rest/trainings/getTrainings", (req, res) -> {
			res.type("application/json");
			return g.toJson(trainingService.getAll());
			
		});
	}
		
}
