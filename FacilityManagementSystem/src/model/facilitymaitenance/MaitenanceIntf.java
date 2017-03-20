package model.facilitymaitenance;

import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

import model.facility.Staff;

public interface MaitenanceIntf {
	public int getMaintenanceNo();
	public void setMaintenanceNo(int maintenanceNo);
	public MaintenanceRequest getMaintrequest();
	public void setMaintrequest(MaintenanceRequest maintrequest);
	public MaintenanceOrder getMaintorder();
	public void setMaintorder(MaintenanceOrder maintorder);
	public Schedule getSchedule();
	public void setSchedule(Schedule schedule);
	public Log getLog();
	public void setLog(Log log);
	public MaintenanceRequest makeFacilityMaintRequest(int problemtypeNo, String pdescription, String username);
	 public Cost calcMaintenanceCostForFacility(String apartmentID);
	 public float calcProblemRateForFacility(String apartmentID);
	 public HashMap<String,Integer> listFacilityProblems(String apartmentID);
	 public Set<Maintenance> listMaintenance(String apartmentID);
	 public Set<MaintenanceRequest> listMaintRequests();
	 public Schedule scheduleMaintenance(int maintenanceNo, Date sdate, Staff staff );
}
