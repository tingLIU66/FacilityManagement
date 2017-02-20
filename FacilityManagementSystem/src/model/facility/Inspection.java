package model.facility;

public class Inspection 
{
	private String inspectionINFO;
	private String inspectionType;
	private String technician;
	private int facilityID;
	
	public void setinspectionINFO(String inspectionINFO)
	{
		this.inspectionINFO = inspectionINFO;
		
	}
	
	public String getinspectionINFO ()
	{
		return inspectionINFO; 
	}
	
	public void setinspectionType(String inspectionType)
	{
		this.inspectionType = inspectionType;
		
	}
	
	public String getinspectionType ()
	{
		return inspectionType; 
	}
	
	public void setfacilityID (int facilityID)
	{
		this.facilityID = facilityID;
		
	}
	
	public int getfacilityID ()
	{
		return facilityID; 
	}
	
	public void setTechnician(String technician)
	{
		this.technician = technician;
		
	}
	
	public String getTechnician ()
	{
		return technician; 
	}
	
	public String printFull_Info()
	{
		return "Inspection: \t" + getinspectionINFO () + "\t\t" + "Inspection Type: " + getinspectionType () + "Facility ID: \t" + getfacilityID()+ "Technician: \t" + getTechnician (); 
	}
	
	/*public void listInspections()
	{
		
	} */
	

}
