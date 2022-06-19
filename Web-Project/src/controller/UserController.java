package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.User;

import services.UserService;
import utils.GsonSerializer;



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
	
	public static void login() {
		get("rest/users/login/", (req, res) -> {
			res.type("application/json");
			String username = req.queryParams("username");
            String password = req.queryParams("password");
            System.out.println(username);
            System.out.println(password);
            return g.toJson(userService.login(username, password));
		});       
	}
	
}
