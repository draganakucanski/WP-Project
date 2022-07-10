package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;

import java.io.File;
import java.security.Key;


import com.google.gson.Gson;


import beans.User;
import controller.CommentController;
import controller.FacilitiesController;
import controller.MembershipController;
import controller.TrainingController;
import controller.TrainingHistoryController;
import controller.UserController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import spark.Session;
import ws.WsHandler;

public class SparkAppMain {
	private static Gson g = new Gson();
	
	// ovde sam obrisao nesto sto je bilo zakomentarisano jer je imalo karaktere koje ne podrzava program, ako bude greska savucao si na desktopu tekst
	static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static void main(String[] args) throws Exception {
		port(8080);

		webSocket("/ws", WsHandler.class);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		
		UserController.getUsers();
		UserController.getUser();
		UserController.addUser();
		UserController.Registration();
		UserController.login();
		UserController.logout();
		UserController.getLogedUser();
		UserController.usernameExists();
		UserController.editUser();
		UserController.getAllUsers();
		UserController.getCustomers();
		UserController.getManagers();
		UserController.getTrainers();
		UserController.getAdmins();
		UserController.getBronzeUsers();
		UserController.getSilverUsers();
		UserController.getGoldUsers();
		UserController.getAllTypesOfUsers();
		UserController.UserSearch();
		UserController.getFreeManagers();
		FacilitiesController.getFacilities();
		FacilitiesController.FacilitySearch();
		FacilitiesController.GetGyms();
		FacilitiesController.GetDanceS();
		FacilitiesController.GetPools();
		FacilitiesController.GetSportsC();
		FacilitiesController.GetClosed();
		FacilitiesController.GetOpened();
		FacilitiesController.AddObject();
		FacilitiesController.nameExists();
		FacilitiesController.AddFacilityWithManager();
		FacilitiesController.getFacilityByName();
		TrainingController.getTrainings();
		TrainingController.getFacilityTrainings();
		TrainingHistoryController.getAllHistories();
		TrainingHistoryController.getUsersHistories();
		TrainingHistoryController.TrainingSearch();
		TrainingHistoryController.getDateAscHistories();
		TrainingHistoryController.getDateDiscHistories();
		TrainingHistoryController.GetDanceS();
		TrainingHistoryController.GetGyms();
		TrainingHistoryController.GetPools();
		TrainingHistoryController.GetSportsC();
		TrainingHistoryController.GetGymTrainings();
		TrainingHistoryController.GetGroup();
		TrainingHistoryController.GetPersonal();
		TrainingHistoryController.CancelTraining();
		MembershipController.getMemberships();
		MembershipController.CreateMembership();
		UserController.AddManagerTrainer();
		FacilitiesController.getManagersFacility();
		FacilitiesController.getFacilityCustomers();
		FacilitiesController.getFacilityTrainers();
		TrainingController.getManagersTrainings();
		TrainingController.AddTraining();
		TrainingController.ContentNameExists();
		TrainingController.editTraining();
		CommentController.getAll();
		CommentController.approveComment();
		CommentController.getFacilityComments();
		
		
		post("/rest/demo/login", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			User u = g.fromJson(payload, User.class);
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if (user == null) {
				user = u;
				ss.attribute("user", user);
			}
			return g.toJson(user);
		});
		
		
		
		
		
		get("/rest/demo/testlogin", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			
			if (user == null) {
				return "No user logged in.";  
			} else {
				return "User " + user + " logged in.";
			}
		});

		get("/rest/demo/logout", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			
			if (user != null) {
				ss.invalidate();
			}
			return true;
		});
		
		
		get("/rest/demo/testloginJWT", (req, res) -> {
			String auth = req.headers("Authorization");
			System.out.println("Authorization: " + auth);
			if ((auth != null) && (auth.contains("Bearer "))) {
				String jwt = auth.substring(auth.indexOf("Bearer ") + 7);
				try {
				    Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
				    // ako nije bacio izuzetak, onda je OK
					return "User " + claims.getBody().getSubject() + " logged in.";
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return "No user logged in.";
		});

	}

	
}
