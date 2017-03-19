package model.facilitymaitenance;

import java.sql.Date;

import model.facility.Staff;

public interface ScheduleIntf {
	public void setScheduleNo(int scheduleNo);
	public int getScheduleNo();
	public void setScheduleDate(Date scheduleDate);
	public Date getScheduleDate();
	public void setStaff(Staff staff);
	public Staff getStaff();
}
