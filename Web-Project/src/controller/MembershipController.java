package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.User;
import services.MembershipService;
import services.UserService;

public class MembershipController {

	private static Gson g = new Gson();
	private static MembershipService membershipService = new MembershipService();
	private static UserService userService  = new UserService();
	
	public static void getMemberships() {
		get("rest/memberships/getMemberships", (req, res) -> {
			res.type("application/json");
			return g.toJson(membershipService.getAll());
			
		});
	}
	public static void CreateMembership() {
		post("rest/memberships/createMembership", (req, res) -> {
			res.type("application/json");
			res.status(200);
			User us = userService.getUser(req.session().attribute("logedinUser"));
			String username = us.getUsername();
			String type = g.fromJson(req.body(),String.class);
			membershipService.CreateMembership(username, type);
		return "OK";
		});
	}
}
