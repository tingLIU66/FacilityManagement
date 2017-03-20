package model.facilityuse;

public class AptUser implements AptUserIntf
{
	private String UserName;
	//private String userFname;
	///
	private String PhoneNo;
	private String UnitNo;
	private String ApartmentID;
	


	public void setUserName (String UserName){
		this.UserName = UserName;
	}
    
	public String getUserName(){
		return UserName;
	}
	
	
	public void setPhoneNo(String PhoneNo){
		this.PhoneNo = PhoneNo;
	}
    
	public String getPhoneNo(){
		return PhoneNo;
	}
	
	public void setUnitNo(String UnitNo){
		this.UnitNo = UnitNo;
	}
    
	public String getUnitNo(){
		return UnitNo;
	}
	
	public void setApartmentID(String ApartmentID){
		this.ApartmentID = ApartmentID;
	}
    
	public String getApartmentID(){
		return ApartmentID;
	}
	

}
