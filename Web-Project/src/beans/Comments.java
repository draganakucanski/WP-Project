package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Comments {

	private HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private ArrayList<Comment> commentList = new ArrayList<Comment>();

	public Comments() {
		this("static");
	}

	public Comments(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/comments.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readComments(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		
	}
	
	private void readComments(BufferedReader in) {
		String line, customer = "", facilityName="", content="";
		int id = 0;
		int grade = 0;
		boolean approved = false;
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.valueOf(st.nextToken().trim());
					customer = st.nextToken().trim();
					facilityName = st.nextToken().trim();
					content = st.nextToken().trim();
					grade = Integer.valueOf(st.nextToken().trim());
					approved = Boolean.valueOf(st.nextToken().trim());
				}
				Comment comment = new Comment(id, customer, facilityName, content, grade, approved);
				comments.put(id, comment);
				commentList.add(comment);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
public void saveData(){
		
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("static/comments.txt", "UTF-8");
			
			for (Comment c : comments.values()) {
				writer.println(String.format("%s;%s;%s;%s;%s;%s", 
						c.getId(), c.getCustomer(), c.getFacilityName(), c.getContent(), c.getGrade(), c.isApproved()));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public Collection<Comment> GetValues() {
		return comments.values();
	}

	public Comment GetComment(int id) {
		return comments.get(id);
	}

	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
	public void addComment(Comment c) {
		int id = GenerateNewID();
	  	c.setId(id);
	  	c.setApproved(false);
		this.comments.put(c.getId(), c);
		saveData();
	}
	public void Edit(Comment c) {
		this.comments.put(c.getId(), c);
		saveData();
	}
	public int GenerateNewID() 
    {
        int max = 0;
        if (comments.size() != 0)
            for(Comment c: comments.values())
                if (max < c.getId())
                    max = c.getId();
        return max + 1;
    }
}
