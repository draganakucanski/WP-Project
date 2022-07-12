package beans;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import dto.TrainingAddingDTO;

public class Trainings {

	private HashMap<String, Training> trainings = new HashMap<String, Training>();
	private ArrayList<Training> trainingList = new ArrayList<Training>();

	public Trainings() {
		this("static");
	}

	public Trainings(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/training.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readTrainings(in);
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
	
	private void readTrainings(BufferedReader in) {
		String line, name = "", facilityName="", trainerUsername="", description="", picture="";
		double duration = 0 ;
		SportsFacility sf = null;
		TrainingType type =TrainingType.GROUP;
		boolean deleted = false;
		//Location location = new Location();
		//boolean works = true;// ovo je drugacije, bio je string
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					name = st.nextToken().trim();
					facilityName = st.nextToken().trim();
					trainerUsername = st.nextToken().trim();
					description = st.nextToken().trim();
					picture = st.nextToken().trim();
					type = TrainingType.valueOf(st.nextToken().trim());
					duration = Double.valueOf(st.nextToken().trim());
					Facilities f = new Facilities();
					sf  = f.getFacility(facilityName);
					deleted =  Boolean.valueOf(st.nextToken().trim());
				}
				Training training = new Training(name, type, sf, duration, trainerUsername, description, picture);
				training.setDeleted(deleted);
				trainings.put(name, training);
				trainingList.add(training);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
public void saveData(){
		
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("static/training.txt", "UTF-8");
			
			for (Training training : trainings.values()) {
				writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s", 
						training.getName(), training.getSportsFacility().getName(), training.getTrainer(), training.getDescription(), training.getPicture(), training.getType(), training.getDuration(), training.isDeleted()));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public Collection<Training> values() {
		return trainings.values();
	}
	/*
	public Collection<Training> getValues() {
		return trainings.values();
	}
	*/
	public ArrayList<Training> getValues() {
		ArrayList<Training> trainingL= new ArrayList<Training>();
		for(Training t:trainingList)
			if(t.isDeleted()==false)
				trainingL.add(t);
		return trainingL;
	}
	public Training getTraining(String name) {
		return trainings.get(name);
	}

	public ArrayList<Training> getTrainingList() {
		return trainingList;
	}
	public void addTraining(Training training) {
		if (trainings.containsKey(training.getName())) {
			return;
		} else {
			System.out.println(training.getName());
			this.trainings.put(training.getName(), training);
			saveData();
		}
	}
	public void AddTrainingLogo(Training t, String imageFile) {
		String imageString = imageFile.split(",")[1];
		
		BufferedImage image = null;
	    byte[] imageByte;

	    imageByte = Base64.getDecoder().decode(imageString);
	    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	    try {
			image = ImageIO.read(bis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    String imageName= "img/"+ t.getName() + ".png";
	   
	    try {
	    	File outputfile = new File(new File("./static").getCanonicalPath()+File.separator+imageName);
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    t.setPicture(imageName);
		saveData();
		

	}
	public void edit(Training t, TrainingAddingDTO objectInfo) {
		this.trainings.put(t.getName(), t);
		if(objectInfo.imageFile!=null) {
			AddTrainingLogo(t, objectInfo.imageFile);
		}
		saveData();
	}
	public void editList(String name, Training t) {
		int index = -1;
		for(Training tr : trainingList)
			if(tr.getName().equals(name)) {
				index = trainingList.indexOf(tr);
			}
		trainingList.set(index, t);
	}
	public void editNew(String name, Training t) {
		this.trainings.put(name, t);
		saveData();
	}
}
