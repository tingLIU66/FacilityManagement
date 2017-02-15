package model.facilitymaitenance;

import java.sql.Date;

public class MaintenanceOrder {

	private int orderNo;
	private Date orderDate;	
	private String orderStatus;	
	private int costNo;
	
	
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
	
	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}
    
	public String getOrderStatus(){
		return orderStatus;
	}
	
	
	public void setCostNo(int costNo){
		this.costNo = costNo;
	}
    
	public int getCostNo(){
		return costNo;
	}
	


}
