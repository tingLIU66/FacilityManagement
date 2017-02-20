package view;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import model.facility.Apartment;
import model.facility.FacilityAdmin;
import model.facility.FacilityDetail;

public class FacilityClient {
	
	public static void main(String args[]) throws Exception {

	//Client will use the customer service to have access to anything related to customer functionality.
		Scanner sc = new Scanner(System.in);
    System.out.println("*************** Creating Facility related objects *************************");
    FacilityAdmin fadmin = new FacilityAdmin(); 
    Apartment apt = new Apartment();
    FacilityDetail fdetail = new FacilityDetail();
    Set<Apartment> apartments = new HashSet<Apartment>();
    apartments = fadmin.listFacilities();
	
	System.out.println("*************List All the Facility under management ****************");
	       
	Iterator<Apartment> it = apartments.iterator();
	while(it.hasNext()) {
		Apartment pdt = (Apartment) it.next();
		
		System.out.println("ApartmentID: " + pdt.getApartmentID());
		System.out.println("ApartmentName: " + pdt.getName());
		System.out.println();
	}
	
	System.out.println("*************Input ApartmentID to get Facility Information:*********************************");
	   // Scanner sc = new Scanner(System.in);
	    String aptID = sc.next();
	    fdetail = apt.getFacilityInformation(aptID);
	    System.out.println("============================================================================");   
		System.out.println("ApartmentID: " + aptID);
		System.out.println("Address: " + fdetail.getAddress() );
		System.out.println("Zipcode: " + fdetail.getZipcode());
		System.out.println("Age: " + fdetail.getAge());
		System.out.println("Capacity: " + fdetail.getCapacity());
		System.out.println("Parking: " + fdetail.getParking());
		System.out.println();
	
	System.out.println("*************** Add a new Facility to manage *****************************");
	//Scanner sc1 = new Scanner(System.in);
    System.out.println("Input apartmentID:");
	String addaptid = sc.next();
	System.out.println("Input apartment name:");
    String name = sc.next();	
	fadmin.adddNewFacility(addaptid, name);
	System.out.println("Aartment( " + addaptid + ", " + name + ")added successfully!");
	System.out.println();
	
	System.out.println("*************** Remove a new Facility to manage *****************************");
	System.out.println("Input apartmentID you want you remove:"); 
	String delaptid = sc.next();
	System.out.println(fadmin.removeFacility(delaptid));	
	
	
}		
	
}
