package services;

import java.util.ArrayList;
import java.util.Collection;

import beans.Address;
import beans.Comment;
import beans.Comments;
import beans.Location;
import beans.SportsFacility;
import beans.Training;
import dto.FacilityAddingDTO;


public class CommentService {

	private Comments comments = new Comments();
	
	public Collection<Comment> getAll(){
		return comments.GetValues();
	}
	public void Approve(Comment c) {
		c.setApproved(true);
		this.comments.Edit(c);
	}
	public ArrayList<Comment> getFacilityComments(String name){
		ArrayList<Comment> ret = new ArrayList<Comment>();
		for(Comment c : comments.GetValues()) {
			if(c.getFacilityName().equals(name))
				ret.add(c);
		}
		return ret;
	}
	public Comment Add(String username, String content, String grade, String facility) {
		int grad = Integer.valueOf(grade);
		Comment c = new Comment(username, facility, content, grad);
		this.comments.addComment(c);
		return c;
	}
}
