package model.facility;

public class FacilityDetail {
	
	private String facilityID;
	private String address;
	private int age;
	private int capacity;
	
	public void setFacilityID(String facilityID){
		this.facilityID = facilityID;
	}
    
	public String getFacilityID(){
		return facilityID;
	}
	
	public void setAdress(String address){
		this.address = address;
	}
    
	public String getAddress(){
		return address;
	}
	public void setAge(int age){
		this.age = age;
	}
    
	public int getAge(){
		return age;
	}
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
    
	public int getCapacity(){
		return capacity;
	}

}
