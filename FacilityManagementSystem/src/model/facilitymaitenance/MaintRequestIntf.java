package model.facilitymaitenance;

import java.sql.Date;

import model.facilityuse.AptUser;

public interface MaintRequestIntf {
	
	public void setRequestNo(int requestNo);
	public int getRequestNo();
	public void setProblemDescriptin(String problemDescription);
	public String getProblemDescription();
	public void setRequestDate(Date requestDate);
	public Date getRequestDate();
	public void setFacilityProblem(FacilityProblem fctproblem);
	public FacilityProblem getFacilityProblem();
	public void setAptUser(AptUser aptuser);
	public AptUser getAptUser();
}
