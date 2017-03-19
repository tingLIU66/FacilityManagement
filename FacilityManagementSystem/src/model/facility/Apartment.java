package model.facility;

import java.util.HashSet;
import java.util.Set;

import dal.FacilityDAO;
import model.facilitymaitenance.Maintenance;
import model.facilityuse.FacilityUse;

public class Apartment {
	
	private String apartmentID;
	private String name;
	private Set<Unit> units;
	private FacilityDetail fctdetail;
	private Set<Maintenance> maintainances = new HashSet<Maintenance>();
	private FacilityUse fctuse;
	private Set<Inspection> inspections;
	
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
	
	public void setUnit(Set<Unit> units){
		
		this.units = units;
	}
	
	public Set<Unit> getUnit(){
		
		return units;
	}
	
	public void setFacilityDetail(FacilityDetail fctdetail){
		
		this.fctdetail = fctdetail;
	}
	
	public FacilityDetail getFacilityDetail(){
		
		return fctdetail;
	}
	
	
	public void setMaintenance(Set<Maintenance> maintainances){
		
		this.maintainances = maintainances;
	}
	
	public Set<Maintenance> getMaintenance(){
		
		return maintainances;
	}
	
	public void setFacilityUse(FacilityUse fctuse){
		
		this.fctuse = fctuse;
	}
	
	public FacilityUse getFacilityUse(){
		
		return fctuse;
	}
	
	public void setInspection(Set<Inspection> inspections){
		
		this.inspections = inspections;
	}
	
	public Set<Inspection> getInspection(){
		
		return inspections;
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
    
  //  public void addMaintenance(Maintenance maint){
    	
    	//facilitydao.addMaintenance(maint);
    	
   // }

}