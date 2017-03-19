package model.facilityuse;
import java.util.Set;

import model.facility.Inspection;
public interface FacilityUseIntf {
	public String isInUseDuringInterval(String apartmentID, String UnitNo);
 	public float calcUsagerate(String apartmentID);
 	public String vacateFacility(String apartmentID, String unitNo);
 	public String assignFacility(String apartmentID, String unitNo, AptUser useApt);
 	public Set<Inspection> listInspections(String apartmentID);

}
