package model.facilitymaitenance;

import java.sql.Date;

import model.facilityuse.AptUser;


public class MaintenanceRequest {
		
	private int requestNo;
	private AptUser aptuser;
	private FacilityProblem fctproblem;
	private String problemDescription;
	private Date requestDate;
	public int getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}
	public AptUser getAptuser() {
		return aptuser;
	}
	public void setAptuser(AptUser aptuser) {
		this.aptuser = aptuser;
	}
	public FacilityProblem getFctproblem() {
		return fctproblem;
	}
	public void setFctproblem(FacilityProblem fctproblem) {
		this.fctproblem = fctproblem;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}	

	
}
