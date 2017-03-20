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
	private MaintenanceRequest maintrequest;
	private MaintenanceOrder maintorder;
	private Schedule schedule;
	//private FacilityProblem fctproblem;
	private Log log;
	
	FacilityMaintenanceDAO mdao = new FacilityMaintenanceDAO();
	
	public void setMaintenanceNo(int maintenanceNo){
		this.maintenanceNo = maintenanceNo;
	}
    
	public int getMaintenanceNo(){
		return maintenanceNo;
	}
	
	public void setMaintenanceRequest(MaintenanceRequest maintrequest){
		this.maintrequest = maintrequest;
	}
    
	public MaintenanceRequest getMaintenanceRequest(){
		return maintrequest;
	}
	
	public void setSchedule(Schedule schedule){
		this.schedule = schedule;
	}
    
	public Schedule getSchedule(){
		return schedule;
	}
	
	public void setMaintenanceOrder(MaintenanceOrder maintorder){
		this.maintorder = maintorder;
	}
    
	public MaintenanceOrder getMaintenanceOrder(){
		return maintorder;
	}
	
	public void setLog(Log log){
		this.log = log;
	}
    
	public Log getLog(){
		return log;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public MaintenanceRequest makeFacilityMaintRequest(int problemtypeNo, String pdescription, String username){
		return mdao.makeFacilityMaintRequest(problemtypeNo, pdescription, username);
			
	}
	
	 public Cost calcMaintenanceCostForFacility(String apartmentID){
		 return mdao.calcMaintenanceCostForFacility(apartmentID);
	 }
	 
	 public float calcProblemRateForFacility(String apartmentID){
		 return mdao.getProblemcount(apartmentID)/12*100;
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
