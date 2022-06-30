package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.FacilityType;
import beans.SportsFacility;
import beans.UserType;
import beans.UserTypeName;

public class FacilityTypeDAO {
	
private static Map<FacilityType, SportsFacility> facilityTypes = new HashMap<>();
	
	public FacilityTypeDAO() {
		loadFacilityTypes();
	}
	
	public Map<FacilityType, SportsFacility> getFacilityTypes() {
		return facilityTypes;
	}
	private void loadFacilityTypes() {
		BufferedReader in = null;
		try {
			File file = new File("static/facilityTypes.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0) {
					continue;
				}
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					FacilityType typeName = FacilityType.valueOf(st.nextToken().trim());
					
					facilityTypes.put(typeName, new SportsFacility(typeName));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
	public static SportsFacility getFacilityTypeByName(String name) {
		return facilityTypes.get(name);
	}
}


