package controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;


import com.google.gson.Gson;

import beans.User;
import services.UserService;



public class UserController {

	private static Gson g = new Gson();
	private static UserService userService = new UserService();
	

	public static void getUsers() {
		get("rest/users/", (req, res) -> {
			res.type("application/json");
			return g.toJson(userService.getUsers());
		});
	}
	
	public static void getUser() {
		get("rest/users/:id", (req, res) -> {
			res.type("application/json");
			String username = req.params("id");
			return g.toJson(userService.getUser(username));
		});
	}
	
	public static void addUser() {
		post("rest/users/add", (req, res) -> {
			res.type("application/json");
			User u = g.fromJson(req.body(), User.class);
			userService.addUser(u);
			return "SUCCESS";
		});
	}
	

}
