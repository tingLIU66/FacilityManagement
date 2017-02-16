package model.facility;

import java.util.HashSet;
import java.util.Set;

import dal.FacilityDAO;

public class Apartment {
	
	private String apartmentID;
	private String name;
	
	FacilityDAO facilitydao= new FacilityDAO();
	
	public void setApartmentID(String apartmentID){
		
		this.apartmentID = apartmentID;
	}
	
	public String getApartmentID(){
		
		return apartmentID;
	}
	
	public void setName(String name){
		
		this.name = name;
	}
	
	public String getName(){
		
		return name;
	}
	
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