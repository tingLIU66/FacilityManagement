package view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import model.facilitymaitenance.Cost;
import model.facilitymaitenance.Maintenance;

public class MaintenanceClient {

		
		public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
	    System.out.println("*************** Creating Maintenance related objects *************************");
	    Maintenance maint = new Maintenance(); 
	    Cost cost = new Cost();
	  		
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
		
		System.out.println("*************Calculate the monthly problem rate of a specific apartment in a given year****************");
		System.out.println("Input apartmentID:");
		String rateaptID = sc.next();   
		float prate = maint.calcProblemRateForFacility(rateaptID);
		System.out.println("The monthly problem rate of apartment " + aptID + " in 2016 is: "+ prate*100 +"%");
		System.out.println();
	   
		System.out.println("*************List the problems reported for a specific apartment, from most to least times****************");
		System.out.println("Input apartmentID:");
		String paptID = sc.next();   
		HashMap<String, Integer> pmap = new HashMap<>();
		pmap = maint.listFacilityProblems(paptID);
		Iterator iter = pmap.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    String ptype = (String) entry.getKey(); 
		    int times = (int) entry.getValue(); 
		    System.out.println("ProbelmType reported: " + ptype + " | Times: " + times);
		  
		} 
	}		
		
}
