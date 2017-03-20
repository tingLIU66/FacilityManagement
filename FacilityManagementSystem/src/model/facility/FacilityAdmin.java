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
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminLocation() {
		return adminLocation;
	}

	public void setAdminLocation(String adminLocation) {
		this.adminLocation = adminLocation;
	}

	public String getOfficePhoneNo() {
		return officePhoneNo;
	}

	public void setOfficePhoneNo(String officePhoneNo) {
		this.officePhoneNo = officePhoneNo;
	}

	public Set<Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(Set<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Set<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
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
