package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import beans.Comment;
import beans.User;
import dto.AddCommentDTO;
import dto.ScheduleTrainingDTO;
import dto.TrainingAddingDTO;
import services.CommentService;


public class CommentController {
	
	private static Gson g = new Gson();
	private static CommentService commentService = new CommentService();
	
	public static void getAll() {
		get("rest/comments/getComments", (req, res) -> {
			res.type("application/json");
			return g.toJson(commentService.getAll());
			
		});
	}
	public static void approveComment() {
		post("/rest/comments/approve", (req, res) -> {
			res.type("application/json");
			Comment c = g.fromJson(req.body(),Comment.class);
            commentService.Approve(c);
            return "OK";
		});
	}
	public static void getFacilityComments() {
		get("rest/comments/getFComments", (req, res) -> {
			res.type("application/json");
			String name = req.queryParams("name");
			return g.toJson(commentService.getFacilityComments(name));
			
		});
	}
	public static void AddComment() {
		post("/rest/comments/add", (req, res) -> {
			res.type("application/json");
			AddCommentDTO info = g.fromJson(req.body(),AddCommentDTO.class);
			String username = req.session().attribute("logedinUser");
			String content = info.comment;
			String grade = info.grade;
			String facilityName = info.facility;
            return commentService.Add(username, content, grade, facilityName);
		});
	}
}
