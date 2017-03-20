package model.facility;

public class Unit implements UnitIntf{
		
	private String unitNo;
	private int bedroom;
	private String unitstatus;
	
	public void setUnitNo(String unitNo){
		this.unitNo = unitNo;
	}
	
	public String getUnitNo(){
		return unitNo;
	}
	
	public void setBedroom(int bedroom){
		this.bedroom = bedroom;	
	}
	
	public int getBedroom(){
		return bedroom;
	}
	
	public void setUnitStatus(String unitstatus){
		this.unitstatus = unitstatus;
	}
	
	public String getUnitStatus(){
		return unitstatus;
	}
	
}
