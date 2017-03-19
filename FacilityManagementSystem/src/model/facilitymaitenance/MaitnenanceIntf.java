package model.facilitymaitenance;

import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

import model.facility.Staff;

public interface MaitnenanceIntf {
public void setMaintenanceNo(int maintenanceNo);
    
	public int getMaintenanceNo();
	
	public void setMaintenanceRequest(MaintenanceRequest maintrequest);
    
	public MaintenanceRequest getMaintenanceRequest();
	
	public void setSchedule(Schedule schedule);
    
	public Schedule getSchedule();
	
	public void setMaintenanceOrder(MaintenanceOrder maintorder);
    
	public MaintenanceOrder getMaintenanceOrder();
	
	public void setLog(Log log);
    
	public Log getLog();	

	public MaintenanceRequest makeFacilityMaintRequest(int problemtypeNo, String pdescription, String username);
	
	 public Cost calcMaintenanceCostForFacility(String apartmentID);
	 
	 public float calcProblemRateForFacility(String apartmentID);
	 
	 public HashMap<String,Integer> listFacilityProblems(String apartmentID);

	 public Set<Maintenance> listMaintenance(String apartmentID);
	 
	 public Set<MaintenanceRequest> listMaintRequests();
	 
	 public Schedule scheduleMaintenance(int maintenanceNo, Date sdate, Staff staff );
}
