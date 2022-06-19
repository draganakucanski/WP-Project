package services;

import java.util.ArrayList;
import java.util.Collection;

import beans.Facilities;
import beans.SportsFacility;
import dto.FacilitySearchDTO;

public class FacilitiesService {
	
	private Facilities facilities = new Facilities();
	
	public Collection<SportsFacility> getAll(){
		System.out.println(facilities.values().size());
		return facilities.values();
	}
	public ArrayList<SportsFacility> SearchFacility(FacilitySearchDTO search) {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(search.allTypes || sf.getType() == search.type) {
				if(sf.getName().toLowerCase().contains(search.name.toLowerCase())){
					if(sf.getLocation().getAddress().getCity().toLowerCase().contains(search.location.toLowerCase())) {
						if(sf.getAverageGrade() >= search.grade) {
							ret.add(sf);
							}	
						}
					}
				}
			}
		
		return ret;
	}
}

