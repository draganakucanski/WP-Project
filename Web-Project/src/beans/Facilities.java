package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

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
		String line, name = "", wholeLoc="", wholeAdd="", street="", number="", city="";
		double grade = 0;
		double lat;
		double longi;
		boolean works = false;
		int zip;
		FacilityType type =FacilityType.GYM;
		Location loc = new Location();
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
//					location = st.nextToken().trim();
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
				}
				SportsFacility facility = new SportsFacility(name,type, works,loc,grade);
				facilities.put(name, facility);
				facilitiesList.add(facility);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Collection<SportsFacility> values() {
		return facilities.values();
	}

	/** Vraca kolekciju fasilija. */
	public Collection<SportsFacility> getValues() {
		return facilities.values();
	}

	/** Vraca proizvod na osnovu njegovog imena. */
	public SportsFacility getFacility(String name) {
		return facilities.get(name);
	}

	/** Vraca listu fasilitija. */
	public ArrayList<SportsFacility> getfacilitiesList() {
		return facilitiesList;
	}
}
