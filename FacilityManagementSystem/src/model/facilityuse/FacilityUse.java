package model.facilityuse;

import dal.FacilityUseDAO;
import model.facilityuse.AptUser;
import model.facility.Inspection;
import model.facility.Unit; 
import java.util.List;



public class FacilityUse {
	
	private FacilityUseDAO facUseDAO = new FacilityUseDAO();
	
	public String isInUseDuringInterval(String apartmentID, String UnitNo)
	{
		return facUseDAO.isInUseDuringInterval(apartmentID, UnitNo);
		
		
	}
	public void calcUsagerate(String apartmentID)
	{
		facUseDAO.calcUsagerate(apartmentID);
		
	}
	
	public String vacateFacility(String apartmentID, String unitNo)
	{
		return facUseDAO.vacateFacility(apartmentID, unitNo);
		
	}
	
	public void assignFacility(String apartmentID, String unitNo, AptUser useApt)
	{
		facUseDAO.assignFacility(apartmentID, unitNo, useApt);
		
	}
	
	public List<Inspection> listInspections(String apartmentID)
	{
		return facUseDAO.listInspections(apartmentID);
		
	}

}
