package model.facility;

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
	

	
	public FacilityDetail getFacilityInformation(String apartmentID) {
		
		return facilitydao.getFacilityInformation(apartmentID);
	}
    
    public int requestAvailableCapacity(String apartmentID) {
        
    	return facilitydao.requestAvailableCapacity(apartmentID);
       
      }
    
    public String addFacilityDetail(String apartmentID,FacilityDetail facilitydetail) {
    	
    	return facilitydao.addFacilityDetail(apartmentID, facilitydetail);
    	
    }

}