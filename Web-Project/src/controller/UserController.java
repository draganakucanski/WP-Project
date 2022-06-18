package controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.User;
import service.UserService;


public class UserController {

	private static Gson g = new Gson();
	private static UserService userService = new UserService();
	
	public static void getUser() {
		get("rest/products/", (req, res) -> {
			res.type("application/json");
			return g.toJson(userService.getUser());
		});
	}

}
