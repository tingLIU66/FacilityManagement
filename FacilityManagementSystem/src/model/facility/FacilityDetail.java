package model.facility;

public class FacilityDetail {
		
	private String address;
	private String zipcode;
	private int age;
	private String parking;
	private int capacity;
		
	
	public void setAdress(String address){
		this.address = address;
	}
    
	public String getAddress(){
		return address;
	}
	
	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}
    
	public String getZipcode(){
		return zipcode;
	}
	
	public void setAge(int age){
		this.age = age;
	}
    
	public int getAge(){
		return age;
	}
	
	public void setParking(String parking){
		this.parking = parking;
	}
    
	public String getParking(){
		return parking;
	}
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
    
	public int getCapacity(){
		return capacity;
	}

}
