package model.facility;

import java.util.HashSet;
import java.util.Set;

import dal.FacilityDAO;

public class Apartment {
	

	FacilityDAO facilitydao= new FacilityDAO();
	
	public Set<Apartment> listFacilities() {
		
		return facilitydao.listFacilities();
	}

    public FacilityDetail getFacilityInformation(int apartmentID) {
		
		return facilitydao.getFacilityInformation(apartmentID);
	}
    
    public int requestAvailableCapacity(int apartmentID) {
        
    	return facilitydao.requestAvailableCapacity(apartmentID);
       
      }

}