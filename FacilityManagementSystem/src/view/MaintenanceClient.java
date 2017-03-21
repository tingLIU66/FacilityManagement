package view;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.facility.Staff;
import model.facilitymaitenance.Cost;
import model.facilitymaitenance.Maintenance;
import model.facilitymaitenance.MaintenanceRequest;
import model.facilitymaitenance.Schedule;
import model.facilityuse.AptUser;

public class MaintenanceClient {

		
		public static void main(String args[]) throws Exception {
			
			
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/facility-app.xml");
		Scanner sc = new Scanner(System.in);
	   
	    Maintenance maint = (Maintenance)context.getBean("maintenance");
	   // Cost cost = new Cost();
	    
	    System.out.println("----------------------List all the maintenances summary of a specific apartment------------------");
		System.out.print("Input apartmentID you want to check:");
		String aptid = sc.next();   
		Set<Maintenance> allmaints = maint.listMaintenance(aptid);
		Iterator<Maintenance> mit = allmaints.iterator();
		while(mit.hasNext()) {
			Maintenance singlemaint = (Maintenance) mit.next();
			System.out.println("MaintenanceNo: " + singlemaint.getMaintenanceNo());
			System.out.println("ApartmentID: " + singlemaint.getMaintrequest().getAptuser().getApartmentID());
			System.out.println("Unit: " + singlemaint.getMaintrequest().getAptuser().getUnitNo());
			System.out.println("Request Date: " + singlemaint.getMaintrequest().getRequestDate());
			System.out.println("Prolem: " + singlemaint.getMaintrequest().getFctproblem().getProblemType());
			System.out.println("Finished Date: " + singlemaint.getMaintorder().getFinishedDate());
			System.out.println("Maintenance Technician: " + singlemaint.getSchedule().getStaff().getstaffFname());			
			
			System.out.println();
		}
	    
	    
		
		System.out.println("----------------------------List all maintenance request--------------------------------------------");
		Set<MaintenanceRequest> requests = maint.listMaintRequests();
		Iterator<MaintenanceRequest> it = requests.iterator();
		while(it.hasNext()) {
			MaintenanceRequest request = (MaintenanceRequest) it.next();
			System.out.println("RuestNo: " + request.getRequestNo());
			System.out.println("Date: " + request.getRequestDate());
			System.out.println("Request UserName: " + request.getAptuser().getUserName());
			System.out.println("ApartmentID: " + request.getAptuser().getApartmentID() );
			System.out.println("Unit: " + request.getAptuser().getUnitNo());
			System.out.println("Phone: " + request.getAptuser().getPhoneNo());
			System.out.println("Problem: " + request.getProblemDescription());
			System.out.println();
		}
		
		
		
		System.out.println("------------------------------Calculate the cost of a specific apartment in a given year-----------------");
		System.out.print("Input apartmentID:");
		String aptID = sc.next();   
	    Cost cost = maint.calcMaintenanceCostForFacility(aptID);
		System.out.println();   
		System.out.println("The cost of apartment " + aptID + " in 2016 is as below:");
		System.out.println("LaborCost: " + cost.getLaborCost());
		System.out.println("MaterialCost: " + cost.getMaterialCost());
		System.out.println("Total " + cost.getTotal());
		System.out.println();
		
		System.out.println("---------------------Calculate the monthly problem rate of a specific apartment in a given year-------------");
		System.out.println("Input apartmentID:");
		String rateaptID = sc.next();   
		float prate = maint.calcProblemRateForFacility(rateaptID);
		System.out.println("The monthly problem rate of apartment " + rateaptID + " in 2016 is: "+ prate +"%");
		System.out.println();
	   
		System.out.println("----------------------List the problems reported for a specific apartment, sorted by times--------------------");
		System.out.println("Input apartmentID:");
		String paptID = sc.next();   
		HashMap<String, Integer> pmap = maint.listFacilityProblems(paptID);		
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
		
		
		System.out.println("--------------------------------------------Make a maintenance requset--------------------------------------------");
	    //Initial a user
		Maintenance maint1 = (Maintenance)context.getBean("maintenance");
	    
	    int problemtypeNo = 2;
	    String pdescription = "Light switch broken";
	    String username = "Ting Liu";
	    
	    MaintenanceRequest mrequest = maint.makeFacilityMaintRequest(problemtypeNo, pdescription, username);
	    System.out.println("=======Your request is setted, please check the details=======================");
	    System.out.println("RuestNo: " + mrequest.getRequestNo());
		System.out.println("Date: " + mrequest.getRequestDate());
		System.out.println("Request UserName: " + mrequest.getAptuser().getUserName());
		System.out.println("ApartmentID: " + mrequest.getAptuser().getApartmentID());
		System.out.println("Unit: " + mrequest.getAptuser().getUnitNo());
		System.out.println("Phone: " + mrequest.getAptuser().getPhoneNo());
		System.out.println("Problem: " + mrequest.getProblemDescription());
		System.out.println();
		
		maint1.setMaintenanceNo(mrequest.getRequestNo());
		maint1.setMaintrequest(mrequest);
		
		System.out.println("----------------------------------------------Schedule a maintenance------------------------------------------------");
	    //Initial a Staff
	    //Schedule schedule = new Schedule();
	    Staff staff = (Staff)context.getBean("staff");
	    staff.setstaffID(2);
	    staff.setstaffFname("Kavin");
	    staff.setstaffLname("Clain");
	    staff.setSpecialty("Electricity");
	    
		Date sdate = new Date(2016, 2, 22);	  
	   
		Schedule schedule = maint.scheduleMaintenance(maint1.getMaintenanceNo(), sdate, staff);
	    System.out.println("===========A schedule has been palced=================================");
	    System.out.println("ScheduleNo: " + schedule.getScheduleNo());
		System.out.println("ScheduleDate: " + schedule.getScheduleDate());
		System.out.println("Served Staff name: " + staff.getstaffFname() + " " +staff.getstaffLname());
		System.out.println("Staff specialty: " + staff.getSpecialty());
		System.out.println();
		maint1.setSchedule(schedule);
		
	}		
		
		
		
}
