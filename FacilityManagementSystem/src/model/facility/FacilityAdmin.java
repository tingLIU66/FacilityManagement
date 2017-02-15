package model.facility;

public class FacilityAdmin {
	
	private String adminName;
	private String adminLocation;
	private String officePhoneNo;
	
	
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
}
