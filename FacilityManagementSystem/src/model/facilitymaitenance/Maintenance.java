package model.facilitymaitenance;

import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

import dal.FacilityMaintenanceDAO;
import model.facility.Staff;
import model.facilityuse.AptUser;

public class Maintenance implements MaitenanceIntf{
	
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
	private Log log;
		
	FacilityMaintenanceDAO mdao = new FacilityMaintenanceDAO();
	
	
	
    public int getMaintenanceNo() {
		return maintenanceNo;
	}

	public void setMaintenanceNo(int maintenanceNo) {
		this.maintenanceNo = maintenanceNo;
	}

	public MaintenanceRequest getMaintrequest() {
		return maintrequest;
	}

	public void setMaintrequest(MaintenanceRequest maintrequest) {
		this.maintrequest = maintrequest;
	}

	public MaintenanceOrder getMaintorder() {
		return maintorder;
	}

	public void setMaintorder(MaintenanceOrder maintorder) {
		this.maintorder = maintorder;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
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
