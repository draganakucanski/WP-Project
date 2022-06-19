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
		String line, name = "", works="";
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
					works = st.nextToken().trim();
				}
				SportsFacility facility = new SportsFacility(name,  Boolean.parseBoolean(works));
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
