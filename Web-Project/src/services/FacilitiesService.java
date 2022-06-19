package services;

import java.util.Collection;

import beans.Facilities;
import beans.SportsFacility;

public class FacilitiesService {
	
	private Facilities facilities = new Facilities();
	
	public Collection<SportsFacility> getAll(){
		System.out.println(facilities.values().size());
		return facilities.values();
	}
}

