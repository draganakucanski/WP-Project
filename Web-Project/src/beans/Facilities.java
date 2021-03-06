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

import beans.Facilities;
import beans.SportsFacility;

public class Facilities {
	

	private HashMap<String, SportsFacility> facilities = new HashMap<String, SportsFacility>();
	private ArrayList<SportsFacility> facilitiesList = new ArrayList<SportsFacility>();

	public Facilities() {
		this("static");
	}

	public Facilities(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/facilities.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readFacilities(in);
			setGrades();
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
	
	private void readFacilities(BufferedReader in) {
		String line, name = "", wholeLoc="", wholeAdd="", street="", number="", city="", logo="", workingHours="";
		double grade = 0;
		double lat;
		double longi;
		boolean works = false;
		boolean deleted = false; 
		int zip;
		FacilityType type =FacilityType.GYM;
		Location loc = new Location();
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					name = st.nextToken().trim();
					works = Boolean.valueOf(st.nextToken().trim());
					grade = Double.valueOf(st.nextToken().trim());
					type = FacilityType.valueOf(st.nextToken().trim());
					wholeLoc=st.nextToken().trim();
					String[] arr = wholeLoc.split("-");
					lat = Double.valueOf(arr[0]);
					longi =Double.valueOf(arr[1]);
					wholeAdd = arr[2];
					String[] arrAdd = wholeAdd.split(",");
					street = arrAdd[0];
					number = arrAdd[1];
					city = arrAdd[2];
					zip = Integer.valueOf(arrAdd[3]);
					loc = new Location(lat,longi,new Address(street,number,city,zip));
					logo = st.nextToken().trim();
					workingHours = st.nextToken().trim();
					deleted = Boolean.valueOf(st.nextToken().trim());
				}
				SportsFacility facility = new SportsFacility(name,type, works,loc,grade, logo, workingHours);
				facility.setDeleted(deleted);
				facilities.put(name, facility);
				facilitiesList.add(facility);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
public void saveData(){
		
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("static/facilities.txt", "UTF-8");
			
			for (SportsFacility facility : facilities.values()) {
				if(facility.getWorkingHours()==null) {
					writer.println(String.format("%s;%s;%s;%s;%s-%s-%s,%s,%s,%s;%s;null;%s", 
							facility.getName(), facility.isWorks(), facility.getAverageGrade(), facility.getType(), facility.getLocation().getLatitude(), facility.getLocation().getLongitude(), facility.getLocation().getAddress().getStreet(), facility.getLocation().getAddress().getNumber(), facility.getLocation().getAddress().getCity(), facility.getLocation().getAddress().getZipCode(),facility.getLogo(), facility.isDeleted()));
				}else
				writer.println(String.format("%s;%s;%s;%s;%s-%s-%s,%s,%s,%s;%s;%s;%s", 
						facility.getName(), facility.isWorks(), facility.getAverageGrade(), facility.getType(), facility.getLocation().getLatitude(), facility.getLocation().getLongitude(), facility.getLocation().getAddress().getStreet(), facility.getLocation().getAddress().getNumber(), facility.getLocation().getAddress().getCity(), facility.getLocation().getAddress().getZipCode(),facility.getLogo(), facility.getWorkingHours(), facility.isDeleted()));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	/*public Collection<SportsFacility> values() {
		return facilities.values();
	} */
	
	public ArrayList<SportsFacility> getValues() {
		ArrayList<SportsFacility> sfs= new ArrayList<SportsFacility>();
		for(SportsFacility sf: facilitiesList)
			if(sf.isDeleted()==false)
				sfs.add(sf);
		return sfs;
	}
	/** Vraca kolekciju fasilija. */
	/*public Collection<SportsFacility> getValues() {
		return facilities.values();
	} */

	/** Vraca proizvod na osnovu njegovog imena. */
	public SportsFacility getFacility(String name) {
		return facilities.get(name);
	}

	/** Vraca listu fasilitija. */
	public ArrayList<SportsFacility> getfacilitiesList() {
		return facilitiesList;
	}
	public void addFacility(SportsFacility facility) {
		if (facilities.containsKey(facility.getName())) {
			return;
		} else {
			this.facilities.put(facility.getName(), facility);
			this.facilitiesList.add(facility);
			saveData();
		}
	}
	public void AddFacilityLogo(SportsFacility sf, String imageFile) {
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

	    String imageName= "img/"+ sf.getName() + ".png";
	   
	    try {
	    	File outputfile = new File(new File("./static").getCanonicalPath()+File.separator+imageName);
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    sf.setLogo(imageName);
		saveData();
		

	}
	public void edit(String name, SportsFacility sf) {
		this.facilities.put(name, sf);
		saveData();
	}
	public void editList(String name, SportsFacility sf) {
		int index = -1;
		for(SportsFacility fac : facilitiesList)
			if(fac.getName().equals(name)) {
				index = facilitiesList.indexOf(fac);
			}
		facilitiesList.set(index, sf);
		//facilitiesList.set(0, sf)
		/*for(SportsFacility fac : facilitiesList)
			if(fac.getName().equals(name)) {
				facilitiesList.set(fac., sf)
				facilitiesList.remove(fac);
				facilitiesList.add(sf);
			}
			*/
	}
	
	private void setGrades() {
		Comments comments = new Comments();
		for(SportsFacility f: facilities.values()) {
			double avGrade = 0;
			int gradeCounter = 0;
			for(Comment c: comments.GetValues()) {
				if(c.getFacilityName().equals(f.getName()) && c.isApproved()) {
					avGrade += c.getGrade();
					gradeCounter++;
				}
			}
			if(gradeCounter!=0) {
			f.setAverageGrade(avGrade/gradeCounter);
			}
		}
		saveData();
	}
}
