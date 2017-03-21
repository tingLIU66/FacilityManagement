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
			System.out.println("MaintenanceNo:\t\t\t" + singlemaint.getMaintenanceNo());
			System.out.println("ApartmentID:\t\t\t" + singlemaint.getMaintrequest().getAptuser().getApartmentID());
			System.out.println("Unit:\t\t\t\t" + singlemaint.getMaintrequest().getAptuser().getUnitNo());
			System.out.println("Request Date:\t\t\t" + singlemaint.getMaintrequest().getRequestDate());
			System.out.println("Prolem:\t\t\t\t" + singlemaint.getMaintrequest().getFctproblem().getProblemType());
			System.out.println("Finished Date:\t\t\t" + singlemaint.getMaintorder().getFinishedDate());
			System.out.println("Maintenance Technician:\t\t" + singlemaint.getSchedule().getStaff().getstaffFname());			
			
			System.out.println();
		}
	    
	    
		
		System.out.println("----------------------------List all maintenance request--------------------------------------------");
		Set<MaintenanceRequest> requests = maint.listMaintRequests();
		Iterator<MaintenanceRequest> it = requests.iterator();
		while(it.hasNext()) {
			MaintenanceRequest request = (MaintenanceRequest) it.next();
			System.out.println("RuestNo:\t\t" + request.getRequestNo());
			System.out.println("Date:\t\t\t" + request.getRequestDate());
			System.out.println("Request UserName:\t" + request.getAptuser().getUserName());
			System.out.println("ApartmentID:\t\t" + request.getAptuser().getApartmentID() );
			System.out.println("Unit:\t\t\t" + request.getAptuser().getUnitNo());
			System.out.println("Phone:\t\t\t" + request.getAptuser().getPhoneNo());
			System.out.println("Problem:\t\t" + request.getProblemDescription());
			System.out.println();
		}
		
		
		
		System.out.println("------------------------------Calculate the cost of a specific apartment in a given year-----------------");
		System.out.print("Input apartmentID:");
		String aptID = sc.next();   
	    Cost cost = maint.calcMaintenanceCostForFacility(aptID);
		System.out.println("The cost of apartment " + aptID + " in 2016 is as below:");
		System.out.println("\tLaborCost:\t\t" + "$"+ cost.getLaborCost());
		System.out.println("\tMaterialCost:\t\t" + "$"+ cost.getMaterialCost());
		System.out.println("\tTotal\t\t\t" + "$"+ cost.getTotal());
		System.out.println();
		
		System.out.println("---------------------Calculate the monthly problem rate of a specific apartment in a given year-------------");
		System.out.print("Input apartmentID:");
		String rateaptID = sc.next();   
		float prate = maint.calcProblemRateForFacility(rateaptID);
		System.out.println("The monthly problem rate of apartment " + rateaptID + " in 2016 is: "+ prate +"%");
		System.out.println();
	   
		System.out.println("----------------------List the problems reported for a specific apartment, sorted by times--------------------");
		System.out.print("Input apartmentID:");
		String paptID = sc.next();   
		HashMap<String, Integer> pmap = maint.listFacilityProblems(paptID);		
		System.out.println("The problems reported for " + paptID+ " is as below:");
		System.out.println();
		System.out.println("\tProbelm reported\t\t|\tTimes ");
		Iterator iter = pmap.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    String ptype = (String) entry.getKey(); 
		    int times = (int) entry.getValue(); 
		    System.out.println("\t"+ptype +"\t\t\t|\t"+ times);
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
	    System.out.println("RuestNo:\t\t" + mrequest.getRequestNo());
		System.out.println("Date:\t\t\t" + mrequest.getRequestDate());
		System.out.println("Request UserName:\t" + mrequest.getAptuser().getUserName());
		System.out.println("ApartmentID:\t\t" + mrequest.getAptuser().getApartmentID());
		System.out.println("Unit:\t\t\t" + mrequest.getAptuser().getUnitNo());
		System.out.println("Phone:\t\t\t" + mrequest.getAptuser().getPhoneNo());
		System.out.println("Problem:\t\t" + mrequest.getProblemDescription());
		System.out.println();
		
		maint1.setMaintenanceNo(mrequest.getRequestNo());
		maint1.setMaintrequest(mrequest);
		
		System.out.println("----------------------------------------------Schedule a maintenance------------------------------------------------");
	    //Initial a Staff
	    //Schedule schedule = new Schedule();
	    //Staff staff = (Staff)context.getBean("staff");
		Staff staff = maint1.getSchedule().getStaff();
	    staff.setstaffID(2);
	    staff.setstaffFname("Kavin");
	    staff.setstaffLname("Clain");
	    staff.setSpecialty("Electricity");
	    
		Date sdate = new Date(2016, 2, 22);	  
	   
		Schedule schedule = maint.scheduleMaintenance(maint1.getMaintenanceNo(), sdate, staff);
	    System.out.println("===========A schedule has been placed=================================");
	    System.out.println("ScheduleNo:\t\t" + schedule.getScheduleNo());
		System.out.println("ScheduleDate:\t\t" + schedule.getScheduleDate());
		System.out.println("Served Staff name:\t" + staff.getstaffFname() + " " +staff.getstaffLname());
		System.out.println("Staff specialty:\t" + staff.getSpecialty());
		System.out.println();
		maint1.setSchedule(schedule);
		
	}		
		
		
		
}
