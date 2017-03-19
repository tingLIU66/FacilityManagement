package model.facilitymaitenance;

import java.sql.Date;

import model.facility.Staff;

public class Schedule {
	
	private int scheduleNo;
	private Date scheduleDate;	
	private Staff staff;
	//private MaintenanceRequest maintrequest;
	
	public Schedule(int scheduleNo, Date scheduleDate, Staff staff) {
		
		this.scheduleNo = scheduleNo;
		this.scheduleDate = scheduleDate;
		this.staff = staff;
	}
		
		
	public void setScheduleNo(int scheduleNo){
		this.scheduleNo = scheduleNo;
	}
    


	public int getScheduleNo(){
		return scheduleNo;
	}
	
	public void setScheduleDate(Date scheduleDate){
		this.scheduleDate = scheduleDate;
	}
    
	public Date getScheduleDate(){
		return scheduleDate;
	}
	
	public void setStaff(Staff staff){
		this.staff = staff;
	}
    
	public Staff getStaff(){
		return staff;
	}
	
	//public void setMaintenanceRequest(MaintenanceRequest maintrequest){
		//this.maintrequest = maintrequest;
	//}
    
	//public MaintenanceRequest getMaintenanceRequest(){
		//return maintrequest;
	//}
	
	
}
