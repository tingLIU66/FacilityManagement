package view;

//import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.facility.Apartment;
import model.facility.FacilityAdmin;

import model.facility.FacilityDetail;
import model.facility.Inspection;
import model.facility.Unit;
import model.facilityuse.AptUser;
import model.facilityuse.FacilityUse;

public class FacilityUseClient {
	
	
	

	public static void main (String args[]) throws Exception {
		
	ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/facility-app.xml");
	Scanner sc = new Scanner(System.in);
    System.out.println("*************** Creating Facility related objects *************************");
    
    FacilityAdmin fadmin = (FacilityAdmin)context.getBean("facilityadmin");
    Apartment apt = (Apartment)context.getBean("apartment");
    FacilityDetail fdetail = (FacilityDetail)context.getBean("facilitydetail");
    Set<Apartment> apartments = fadmin.listFacilities();
    Unit aptUnit = (Unit) context.getBean("unit");
    FacilityUse fuse = (FacilityUse)context.getBean("facilityuse");
    
    
    
    
    ///////////////////////////////////////////////
    /*FacilityAdmin fadmin = new FacilityAdmin(); 
    Apartment apt = new Apartment();
    FacilityDetail fdetail = new FacilityDetail();
    Set<Apartment> apartments = new HashSet<Apartment>();
    
    Unit aptUnit = new Unit();
    FacilityUse fuse = new FacilityUse();
    
    apartments = fadmin.listFacilities();	*/

	
	System.out.println("*************** Check if Facility is in use *****************************");
	System.out.println("Input apartmentID and UnitNO to check Unit Status"); 
	System.out.println("Input apartmentID:");
	String inputaptID = sc.next();
	System.out.println("Input Unit No:");
	String inputUnitNo = sc.next();
	
	System.out.println("Unit " + inputUnitNo + " " +  fuse.isInUseDuringInterval(inputaptID, inputUnitNo));	
	System.out.println();
	
	System.out.println("*************** Calculate Facility Usage Rate *****************************");
	System.out.println("Input apartmentID to calculate facility's usage rate"); 
	System.out.println("Input apartmentID:");
	String inputaptID2 = sc.next();
	
	System.out.println("The useagerate of Apartment " + inputaptID2 + " is: " + fuse.calcUsagerate(inputaptID2) + "%");
	System.out.println();

	System.out.println("*************** Vacate A Facility *****************************");
	System.out.println("Input apartmentID and UnitNo to vacante a facility"); 
	System.out.println("Input apartmentID:");
	String inputaptID4 = sc.next();
	System.out.println("Input UnitNo:");
	String unitNoID4 = sc.next();
	System.out.println(fuse.vacateFacility(inputaptID4,unitNoID4 ));
	System.out.println();
	
	System.out.println("*************** Assign A Facility *****************************");
	System.out.println("Input apartmentID and UnitNo to vacante a facility"); 
	System.out.println("Input apartmentID:");
	String inputaptID5 = sc.next();
	System.out.println("Input UnitNo:");
	String unitNoID5 = sc.next();
	
	AptUser aptuser = (AptUser)context.getBean("aptuser");
	//AptUser aptuser = new AptUser();
	aptuser.setUserName("test user");
	aptuser.setPhoneNo("1111111111");
	aptuser.setApartmentID(inputaptID5);
	aptuser.setUnitNo(unitNoID5);
	
	fuse.assignFacility(inputaptID5, unitNoID5, aptuser);
	System.out.println();
	
	
	System.out.println("*************** List Inspections *****************************");
	System.out.println("Input apartmentID to list inspections"); 
	System.out.println("Input apartmentID:");
	String inputaptID6 = sc.next();
	Set<Inspection> inspections = fuse.listInspections(inputaptID6);
	/*Set<Inspection> inspections = new HashSet<Inspection>();
	inspections = fuse.listInspections(inputaptID6);	*/
	
	Iterator<Inspection> it = inspections.iterator();
	while(it.hasNext()) {
		Inspection isp = (Inspection) it.next();
		
		System.out.println("ApartmentID: " + isp.getfacilityID());
		System.out.println("InspectionID: " + isp.getinspectionID());
		System.out.println("InspectionINFO: " + isp.getinspectionINFO());
		System.out.println("InspectionType: " + isp.getinspectionType());
		System.out.println("Technician: " + isp.getTechnician());
		System.out.println();
	}

}		
	
}

