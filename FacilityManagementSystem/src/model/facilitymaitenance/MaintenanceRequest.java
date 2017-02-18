package model.facilitymaitenance;

import java.sql.Date;

import model.facilityuse.AptUser;


public class MaintenanceRequest {
		
	private int requestNo;
	private String problemDescription;
	private Date requestDate;	
	private int problemtypeNo;
	private AptUser aptuser;
	
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
	public void setProblemTypeNo(int problemtypeNo){
		this.problemtypeNo = problemtypeNo;
	}
    
	public int getProblemTypeNo(){
		return problemtypeNo;
	}
	
	public void setAptUser(AptUser aptuser){
		this.aptuser = aptuser;
	}
    
	public AptUser getAptUser(){
		return aptuser;
	}
}
