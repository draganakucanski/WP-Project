package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.User;
import dto.UserEditDTO;
import dto.UserRegistrationDTO;
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
	
	public static void usernameExists() {
		post("rest/usernameExists", (req, res) -> {
			res.type("application/json");
			res.status(200);
			String username = g.fromJson(req.body(),String.class);
			return userService.UsernameExists(username);
		});
	}
	public static void Registration() {
		post("rest/registration", (req, res) -> {
			res.type("application/json");
			res.status(200);
			UserRegistrationDTO customerInfo = g.fromJson(req.body(),UserRegistrationDTO.class);
			userService.CustomerRegistration(customerInfo);
		return "OK";
		});
	}
	public static void login() {
		get("rest/users/login/", (req, res) -> {
			res.type("application/json");
			String username = req.queryParams("username");
            String password = req.queryParams("password");
            System.out.println(username);
            System.out.println(password);
            User us = userService.login(username, password);
            if (us != null) {            	
            	req.session().attribute("logedinUser", username);
            }
            return g.toJson(us);
		});       
	}
	public static void logout() {
		post("rest/logOut", (req, res) -> {
			res.type("application/json");
			req.session().invalidate();
			return "OK logout";
		});
	}
	public static void getLogedUser() {
		get("rest/users/getLogedUser/", (req, res) -> {
			res.type("application/json");
            User us = userService.getUser(req.session().attribute("logedinUser"));
            System.out.println("LOGED USER: " + us);
            if (us == null) {            	
            	return "404";
            }
            return g.toJson(us);
		});   
	}
	public static void editUser() {
		post("rest/users/editData/", (req, res) -> {
			res.type("application/json");
			// System.out.println(req.body());
			UserEditDTO customerInfo = g.fromJson(req.body(),UserEditDTO.class);
			//System.out.println(customerInfo.firstName);
            User us = userService.editUser(customerInfo.username, customerInfo.firstName,customerInfo.lastName, customerInfo.password, customerInfo.dateOfBirth,customerInfo.gender);
           
            
            return g.toJson(us);
		});
	}
}
