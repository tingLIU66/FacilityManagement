package model.facilitymaitenance;

import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

import dal.FacilityMaintenanceDAO;
import model.facility.Staff;
import model.facilityuse.AptUser;

public class Maintenance {
	
/*	public object makeFacilityMaintRequest()
	o public object scheduleMaintenance()
	o public object calcMaintenanceCostForFacility()
	o public object calcProblemRateForFacility()
	o public object calcDownTimeForFacility()
	o public object listMaintRequests()
	o public object listMaintenance()
	o public object listFacilityProblems() 
*/	
	private int maintenanceNo;
	private String apartmentID;
	private String unitNo;
	private Date requestdate;
	private String problemType;
	private String maintenanceTechnician;
	private Date finisheddate;
	
	FacilityMaintenanceDAO mdao = new FacilityMaintenanceDAO();
	
	public void setMaintenanceNo(int maintenanceNo){
		this.maintenanceNo = maintenanceNo;
	}
    
	public int getMaintenanceNo(){
		return maintenanceNo;
	}
	
	public void setApartmentID(String apartmentID){
		this.apartmentID = apartmentID;
	}
    
	public String getApartmentID(){
		return apartmentID;
	}

	
	public void setUnitNo(String unitNo){
		this.unitNo = unitNo;
	}
    
	public String getUnitNo(){
		return unitNo;
	}
	
	public void setRequestdate(Date requestdate){
		this.requestdate = requestdate;
	}
    
	public Date getRequestdate(){
		return requestdate;
	}
	
	public void setProblemType(String problemType){
		this.problemType = problemType;
	}
    
	public String getProblemType(){
		return problemType;
	}

	
	public void setMaintenanceTechnician(String maintenanceTechnician){
		this.maintenanceTechnician = maintenanceTechnician;
	}
    
	public String getMaintenanceTechnician(){
		return maintenanceTechnician;
	}
	    
	public void setFinisheddate(Date finisheddate){
		this.finisheddate = finisheddate;
	}
    
	public Date getFinisheddate(){
		return finisheddate;
	}
	
	
	public MaintenanceRequest makeFacilityMaintRequest(int problemtypeNo, String pdescription, AptUser aptuser){
		return mdao.makeFacilityMaintRequest(problemtypeNo, pdescription, aptuser);
			
	}
	
	 public Cost calcMaintenanceCostForFacility(String apartmentID){
		 return mdao.calcMaintenanceCostForFacility(apartmentID);
	 }
	 
	 public float calcProblemRateForFacility(String apartmentID){
		 return mdao.calcProblemRateForFacility(apartmentID);
	 }
	 
	 public HashMap<String,Integer> listFacilityProblems(String apartmentID) {
		 return mdao.listFacilityProblems(apartmentID);
	 }

	 public Set<Maintenance> listMaintenance(String apartmentID){
		 return mdao.listMaintenance(apartmentID);
	 }
	 
	 public Set<MaintenanceRequest> listMaintRequests(){
		 return mdao.listMaintRequests();
	 }
	 
	 public Schedule scheduleMaintenance(int maintenanceNo, Date sdate, Staff staff ){
		 return mdao.scheduleMaintenance(maintenanceNo, sdate, staff);
	 }
}
