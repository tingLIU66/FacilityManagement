package model.facilitymaitenance;

import java.sql.Date;

public class Schedule {
	
	private int scheduleNo;
	private Date scheduleDate;	
	private int StaffID;
	
	
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

	
	public void setStaffID(int StaffID){
		this.StaffID = StaffID;
	}
    
	public int getStaffID(){
		return StaffID;
	}
	
}
