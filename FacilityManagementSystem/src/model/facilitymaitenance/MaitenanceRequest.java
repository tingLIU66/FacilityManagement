package model.facilitymaitenance;

import java.sql.Date;


public class MaitenanceRequest {
		
	private int requestNo;
	private String problemDescription;
	private Date requestDate;	
	private int problemtypeNo;
	private String username;
	
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
	
	public void setUserName(String username){
		this.username = username;
	}
    
	public String getUserName(){
		return username;
	}
}
