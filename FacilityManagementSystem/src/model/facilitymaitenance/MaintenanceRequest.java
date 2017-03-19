package model.facilitymaitenance;

import java.sql.Date;

import model.facilityuse.AptUser;


public class MaintenanceRequest {
		
	private int requestNo;
	private AptUser aptuser;
	private FacilityProblem fctproblem;
	private String problemDescription;
	private Date requestDate;	
	//private int problemtypeNo;
	
		public MaintenanceRequest(int requestNo, AptUser aptuser, FacilityProblem fctproblem, String problemDescription,
			Date requestDate) {
		this.requestNo = requestNo;
		this.aptuser = aptuser;
		this.fctproblem = fctproblem;
		this.problemDescription = problemDescription;
		this.requestDate = requestDate;
	}
		
		
	public void setRequestNo(int requestNo){
		this.requestNo = requestNo;
	}
    


	public int getRequestNo(){
		return requestNo;
	}
	
	public void setProblemDescriptin(String problemDescription){
		this.problemDescription = problemDescription;
	}
    
	public String getProblemDescription(){
		return problemDescription;
	}
	
	public void setRequestDate(Date requestDate){
		this.requestDate = requestDate;
	}
    
	public Date getRequestDate(){
		return requestDate;
	}
	public void setFacilityProblem(FacilityProblem fctproblem){
		this.fctproblem = fctproblem;
	}
    
	public FacilityProblem getFacilityProblem(){
		return fctproblem;
	}
	
	public void setAptUser(AptUser aptuser){
		this.aptuser = aptuser;
	}
    
	public AptUser getAptUser(){
		return aptuser;
	}
}
