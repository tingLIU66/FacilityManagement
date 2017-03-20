package view;


import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.facility.Apartment;
import model.facility.FacilityAdmin;
import model.facility.FacilityDetail;

public class FacilityClient {
	
	public static void main(String args[]) throws Exception {

	//Client will use the facility client to access to anything related to facility functionality.
	
	ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/facility-app.xml");
	Scanner sc = new Scanner(System.in);
    System.out.println("*************** Creating Facility related objects *************************");
   
    FacilityAdmin fadmin = (FacilityAdmin)context.getBean("facilityadmin");
    Apartment apt = (Apartment)context.getBean("apartment");
    //FacilityDetail fdetail = (FacilityDetail)context.getBean("facilitydetail");
    FacilityDetail fdetail = apt.getFctdetail();
    Set<Apartment> apartments = fadmin.listFacilities();
	
	System.out.println("-----------------List All the Facility under management: --------------------------------");
		System.out.println();	       
		Iterator<Apartment> it = apartments.iterator();
		while(it.hasNext()) {
			Apartment apt1 = (Apartment) it.next();		
			System.out.println("ApartmentID: " + apt1.getApartmentID());
			System.out.println("ApartmentName: " + apt1.getName());
			System.out.println();
		}
	
	System.out.println("-----------------Input ApartmentID to get Facility Information:------------------------------");
	    System.out.println("Input apartmentID:");
	   // Scanner sc = new Scanner(System.in);
	    String aptID = sc.next();
	    fdetail = apt.getFacilityInformation(aptID);
	    System.out.println();   
		System.out.println("ApartmentID: " + aptID);
		System.out.println("Address: " + fdetail.getAddress() );
		System.out.println("Zipcode: " + fdetail.getZipcode());
		System.out.println("Age: " + fdetail.getAge());
		System.out.println("Capacity: " + fdetail.getCapacity());
		System.out.println("Parking: " + fdetail.getParking());
		System.out.println();
	
	System.out.println("---------------- Add a new Facility to manage: -----------------------------------------------");
		//Scanner sc1 = new Scanner(System.in);
	    System.out.println("Input apartmentID:");
		String addaptid = sc.next();
		System.out.println("Input apartment name:");
	    String name = sc.next();	
		fadmin.adddNewFacility(addaptid, name);
		System.out.println("Aartment( " + addaptid + ", " + name + ")added successfully!");
		System.out.println();
	
	System.out.println("-----------------Remove a new Facility:--------------------------------------------------------");
		System.out.println("Input apartmentID you want you remove:"); 
		String delaptid = sc.next();
		System.out.println(fadmin.removeFacility(delaptid));	
		System.out.println();
		
	System.out.println("-----------------Request the number of units available in a specific apartment:------------------");
		System.out.println("Input apartmentID:");
		String reqaptID = sc.next();
		int unitavb = apt.requestAvailableCapacity(reqaptID);
		System.out.println("Apartment " + reqaptID + " has " + unitavb + " units available");
		System.out.println();
	
	System.out.println("-----------------Add detail to a new added apartment:---------------------------------------------");
		System.out.println("Input apartmentID:");
		String addtaptID = sc.next();
		//Initiate a facilitydetail
		FacilityDetail fdetail2 = (FacilityDetail)context.getBean("facilitydetail");
		fdetail2.setAdress("601 W Jackson Blvd, Chicago, IL");
		fdetail2.setAge(10);
		fdetail2.setCapacity(135);
		fdetail2.setParking("Yes");
		fdetail2.setZipcode("60661");
		
	    System.out.println();   
		System.out.println(apt.addFacilityDetail(addtaptID, fdetail2));		
		System.out.println();
}		
	
}
