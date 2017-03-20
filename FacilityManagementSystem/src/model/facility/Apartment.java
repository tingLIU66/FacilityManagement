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
	private Set<Maintenance> maintenances;
	private FacilityUse fctuse;
	private Set<Inspection> inspections;
	
	FacilityDAO facilitydao= new FacilityDAO();



	public String getApartmentID() {
		return apartmentID;
	}

	public void setApartmentID(String apartmentID) {
		this.apartmentID = apartmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Unit> getUnits() {
		return units;
	}

	public void setUnits(Set<Unit> units) {
		this.units = units;
	}

	public FacilityDetail getFctdetail() {
		return fctdetail;
	}

	public void setFctdetail(FacilityDetail fctdetail) {
		this.fctdetail = fctdetail;
	}

	public Set<Maintenance> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(Set<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}

	public FacilityUse getFctuse() {
		return fctuse;
	}

	public void setFctuse(FacilityUse fctuse) {
		this.fctuse = fctuse;
	}

	public Set<Inspection> getInspections() {
		return inspections;
	}

	public void setInspections(Set<Inspection> inspections) {
		this.inspections = inspections;
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