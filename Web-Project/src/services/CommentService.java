package services;

import java.util.ArrayList;
import java.util.Collection;

import beans.Comment;
import beans.Comments;
import beans.Training;


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
}
