package model.facility;

public class Staff 
{
	private int staffID;
	private String staffLname;
	private String staffFname;
	private String specialty;
	
	
	public void setstaffLname(String staffLname){
		this.staffLname = staffLname;
	}
    
	public String getstaffLname(){
		return staffLname;
	}
	
	public void setstaffFname(String staffFname){
		this.staffFname = staffFname;
	}
    
	public String getstaffFname(){
		return staffFname;
	}
	
	public void setstaffID(int staffID){
		this.staffID = staffID;
	}
    
	public int getstaffID(){
		return staffID;
	}
	
	public void setSpecialty(String specialty){
		this.specialty = specialty;
	}
    
	public String getSpecialty(){
		return specialty;
	}

}
