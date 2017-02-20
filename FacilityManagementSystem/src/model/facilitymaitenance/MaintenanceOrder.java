package model.facilitymaitenance;

import java.sql.Date;

public class MaintenanceOrder {

	private int orderNo;
	private Date orderDate;	
	//private Schedule schedule;
	private String orderStatus;	
	private Date finishedDate;
	private Cost cost;
	
	
	public void setOrderNo(int orderNo){
		this.orderNo = orderNo;
	}
    
	public int getOrderNo(){
		return orderNo;
	}
	
	public void setOrderDate(Date orderDate){
		this.orderDate = orderDate;
	}
    
	public Date getOrderDate(){
		return orderDate;
	}
	
	//public void setSchedule(Schedule schedule){
		//this.schedule = schedule;
	//}
    
	//public Schedule getSchedule(){
		//return schedule;
	//}
	
	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}
    
	public String getOrderStatus(){
		return orderStatus;
	}
	
	public void setFinishedDate(Date finishedDate){
		this.finishedDate = finishedDate;
	}
    
	public Date getFinishedDate(){
		return finishedDate;
	}
	
	public void setCost(Cost cost){
		this.cost = cost;
	}
    
	public Cost getCost(){
		return cost;
	}
	


}
