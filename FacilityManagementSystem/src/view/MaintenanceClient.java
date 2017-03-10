package view;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import model.facility.Staff;
import model.facilitymaitenance.Cost;
import model.facilitymaitenance.Maintenance;
import model.facilitymaitenance.MaintenanceRequest;
import model.facilitymaitenance.Schedule;
import model.facilityuse.AptUser;

public class MaintenanceClient {

		
		public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
	    System.out.println("*************** Creating Maintenance related objects ***************************");
	    Maintenance maint = new Maintenance(); 
	    Cost cost = new Cost();
	    
	    System.out.println("*************List all the maintenances of a specific apartment*************************");
		Set<Maintenance> allmaints = new HashSet<>();
		System.out.println("Input apartmentID you want to check:");
		String aptid = sc.next();   
		allmaints = maint.listMaintenance(aptid);
		Iterator<Maintenance> mit = allmaints.iterator();
		while(mit.hasNext()) {
			Maintenance singlemaint = (Maintenance) mit.next();
			System.out.println("MaintenanceNo: " + singlemaint.getMaintenanceNo());
			System.out.println("ApartmentID: " + singlemaint.getApartmentID());
			System.out.println("Unit: " + singlemaint.getUnitNo());
			System.out.println("Request Date: " + singlemaint.getRequestdate());
			System.out.println("Prolem: " + singlemaint.getProblemType());
			System.out.println("Finished Date: " + singlemaint.getFinisheddate());
			System.out.println("Maintenance Technician: " + singlemaint.getMaintenanceTechnician());			
			
			System.out.println();
		}
	    
	    
		
		System.out.println("*************List all maintenance request******************************************");
		Set<MaintenanceRequest> requests = new HashSet<>();
		requests = maint.listMaintRequests();
		Iterator<MaintenanceRequest> it = requests.iterator();
		while(it.hasNext()) {
			MaintenanceRequest request = (MaintenanceRequest) it.next();
			System.out.println("RuestNo: " + request.getRequestNo());
			System.out.println("Date: " + request.getRequestDate());
			System.out.println("Request UserName: " + request.getAptUser().getUserName());
			System.out.println("ApartmentID: " + request.getAptUser().getApartmentID() );
			System.out.println("Unit: " + request.getAptUser().getUnitNo());
			System.out.println("Phone: " + request.getAptUser().getPhoneNo());
			System.out.println("Problem: " + request.getProblemDescription());
			System.out.println();
		}
		
		
		
		System.out.println("*************Calculate the cost of a specific apartment in a givin year****************");
		System.out.println("Input apartmentID:");
		String aptID = sc.next();   
		cost = maint.calcMaintenanceCostForFacility(aptID);
		System.out.println("============================================================================");   
		System.out.println("The cost of apartment " + aptID + " in 2016 is as below:");
		System.out.println("LaborCost: " + cost.getLaborCost());
		System.out.println("MaterialCost: " + cost.getMaterialCost());
		System.out.println("Total " + cost.getTotal());
		System.out.println();
		
		System.out.println("******Calculate the monthly problem rate of a specific apartment in a given year********");
		System.out.println("Input apartmentID:");
		String rateaptID = sc.next();   
		float prate = maint.calcProblemRateForFacility(rateaptID);
		System.out.println("The monthly problem rate of apartment " + rateaptID + " in 2016 is: "+ prate +"%");
		System.out.println();
	   
		System.out.println("*************List the problems reported for a specific apartment, sorted by times********");
		System.out.println("Input apartmentID:");
		String paptID = sc.next();   
		HashMap<String, Integer> pmap = new HashMap<>();		
		pmap = maint.listFacilityProblems(paptID);
		System.out.println("The problems reported for " + paptID+ " is as below:");
		System.out.println();
		System.out.println("Probelm reported | Times ");
		Iterator iter = pmap.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    String ptype = (String) entry.getKey(); 
		    int times = (int) entry.getValue(); 
		    System.out.println("   "+ptype +"      |  "+ times);
		    System.out.println();
		} 
		
		
		System.out.println("*************Make a maintenance requset*****************************************");
	    //Initial a user
	    MaintenanceRequest mrequest = new MaintenanceRequest();
	    AptUser aptuser = new AptUser();
	    aptuser.setUserName("Ting Liu");
	    aptuser.setPhoneNo("3124446666");
	    aptuser.setUnitNo("1A");
	    aptuser.setApartmentID("APT001");
	    
	    String pdescription = "Light switch broken";
	    
	    mrequest = maint.makeFacilityMaintRequest(2, pdescription, aptuser);
	    System.out.println("=======Your request is setted, please check the details=======================");
	    System.out.println("RuestNo: " + mrequest.getRequestNo());
		System.out.println("Date: " + mrequest.getRequestDate());
		System.out.println("Request UserName: " + aptuser.getUserName());
		System.out.println("ApartmentID: " + aptuser.getApartmentID());
		System.out.println("Unit: " + aptuser.getUnitNo());
		System.out.println("Phone: " + aptuser.getPhoneNo());
		System.out.println("Problem: " + mrequest.getProblemDescription());
		System.out.println();
		
		
		System.out.println("*************Schedule a maintenance**************************************************");
	    //Initial a Staff
	    Schedule schdule = new Schedule();
	    Staff staff = new Staff();
	    staff.setstaffID(2);
	    staff.setstaffFname("Kavin");
	    staff.setstaffLname("Clain");
	    staff.setSpecialty("Electricity");
	    
		Date sdate = new Date(2016, 2, 22);	  
	   
	    schdule = maint.scheduleMaintenance(6, sdate, staff);
	    System.out.println("===========A schedule has been palced=================================");
	    System.out.println("ScheduleNo: " + schdule.getScheduleNo());
		System.out.println("ScheduleDate: " + schdule.getScheduleDate());
		System.out.println("Served Staff name: " + staff.getstaffFname() + " " +staff.getstaffLname());
		System.out.println("Staff specialty: " + staff.getSpecialty());
		System.out.println();
	}		
		
		
		
}
