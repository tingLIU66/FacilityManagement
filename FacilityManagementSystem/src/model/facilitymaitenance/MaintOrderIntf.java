package model.facilitymaitenance;

import java.sql.Date;

public interface MaintOrderIntf {

	public void setOrderNo(int orderNo);
    
	public int getOrderNo();
	
	public void setOrderDate(Date orderDate);
    
	public Date getOrderDate();

	public void setOrderStatus(String orderStatus);
    
	public String getOrderStatus();
	
	public void setFinishedDate(Date finishedDate);
    
	public Date getFinishedDate();
	
	public void setCost(Cost cost);
    
	public Cost getCost();
}
