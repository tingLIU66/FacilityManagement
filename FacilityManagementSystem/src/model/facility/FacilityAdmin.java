package model.facility;

import java.util.Set;

import dal.FacilityDAO;

public class FacilityAdmin {
	
	private String adminName;
	private String adminLocation;
	private String officePhoneNo;
	private Set<Apartment> apartments;
	private Set<Staff> staffs;
	
	FacilityDAO facilitydao = new FacilityDAO();
	
	public void setAdminName(String adminName){
		this.adminName = adminName;
	}
    
	public String getAdminName(){
		return adminName;
	}
	
	public void setAdminLocation(String adminLocation){
		this.adminLocation = adminLocation;
	}
    
	public String getAdminLocation(){
		return adminLocation;
	}
	
	public void setOfficePhoneNon(String officePhoneNo){
		this.officePhoneNo = officePhoneNo;
	}
    
	public String getOfficePhoneNo(){
		return officePhoneNo;
	}
	
	public void setApartment(Set<Apartment> apartments){
		
		this.apartments = apartments;
	}
	
	public Set<Apartment> getApartment() {
		
		return apartments;
	}
	
	public void setStaff(Set<Staff> staffs){
		
		this.staffs = staffs;
	}
	
	public Set<Staff> getStaff() {
		
		return staffs;
	}
	

	
	public Set<Apartment> listFacilities() {
		
		return facilitydao.listFacilities();
	}
	
	public Apartment adddNewFacility(String apartmentID, String name){
		return facilitydao.addNewFacility(apartmentID, name);
	}
	
	public String removeFacility(String apartmentID){
		return facilitydao.removeFacility(apartmentID);
	}
}
