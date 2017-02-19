package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import model.facility.Apartment;
import model.facility.Staff;
import model.facilitymaitenance.Maintenance;
import model.facilitymaitenance.MaintenanceRequest;
import model.facilitymaitenance.Schedule;
import model.facilityuse.AptUser;

public class FacilityMaintenanceDAO extends DBoperate{
	/*	public object makeFacilityMaintRequest()
	o public object scheduleMaintenance()
	o public object calcMaintenanceCostForFacility()
	o public object calcProblemRateForFacility()
	o public object calcDownTimeForFacility()
	o public object listMaintRequests()
	o public object listMaintenance()
	o public object listFacilityProblems() 
*/
	public FacilityMaintenanceDAO(){
			super();
	}
	
	public MaintenanceRequest makeFacilityMaintRequest(int problemtypeNo, String pdescription, AptUser aptuser){
		
		int requestNo = 0;
		MaintenanceRequest maintrequest = new MaintenanceRequest();
		String addquery = "INSERT INTO maintenancerequest(`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES(?,?,?);";
		String selectquery = "Select * From maintenancerequest where `RequestNo` = ?";
		String addmaintrecordquery = "INSERT INTO maintenance(`ApartmentID`,`UnitNo`,`RequestNo`, `ProblemTypeNo`) VALUES(?,?,?,?);";
		
		//"INSERT INTO maintenance(`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES(?,?,?);";

		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();

			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery);
			preStatement.setInt(1, problemtypeNo);
			preStatement.setString(2, pdescription);
			preStatement.setString(3, aptuser.getUserName());
			preStatement.setInt(4, problemtypeNo);

			preStatement.executeUpdate();
			
			//get the auto generated RequestNo first and then get all the maintenancerequest info
			ResultSet rs = preStatement.getGeneratedKeys();
			requestNo = rs.getInt(1);
		   //Insert a record into maintenance table once a request created	
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(addmaintrecordquery);
			preStatement1.setString(1, aptUser.getApartmentID());
			preStatement1.setString(2, aptUser.getUnitNo());
			preStatement1.setInt(3, requestNo);
			preStatement1.executeUpdate();
		
			PreparedStatement preStatement2 = (PreparedStatement) connection.prepareStatement(selectquery);
			preStatement2.setInt(1, requestNo);
			ResultSet rs1 = preStatement2.executeQuery();
			
			if(rs1.next()){
				maintrequest.setRequestNo(requestNo);;
				maintrequest.setRequestDate(rs1.getDate(2));
				maintrequest.setProblemTypeNo(rs.getInt(3));
				maintrequest.setProblemDescriptin(rs.getString(4));
				maintrequest.setAptUser(aptuser);
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		super.closeConnection(connection);
		return maintrequest;			
		
	}
	
	
	public Schedule scheduleMaintenance(int maintenanceNo, Date sdate, Staff staff ){
		
		int scheduleNo = 0;
		Schedule schedule = new Schedule();
		String addquery = "INSERT INTO schedule(`ScheduleDate`,`StaffID`) VALUES(?,?);";
		String selectquery = "Select * From schedule where `ScheduleNo` = ?";
		String updatemaintrecordquery = "update Maintenance set `ScheduleNo` = ? where maintenanceNo = ?;";
				//"Select * From schedule where `ScheduleNo` = ?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();

			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery);
			preStatement.setDate(1, sdate);
			preStatement.setInt(2, staff.getStaffID());

			preStatement.executeUpdate();
			
			//get the auto generated RequestNo first and then get all the schedule info
			ResultSet rs = preStatement.getGeneratedKeys();
			scheduleNo = rs.getInt(1);
			
			//Update scheduleNo into maintenance table once schedule a maintenance
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(updatemaintrecordquery);
			preStatement1.setInt(1, scheduleNo);
			preStatement1.setInt(2, maintenanceNo);
			preStatement1.executeUpdate();
		
			PreparedStatement preStatement2 = (PreparedStatement) connection.prepareStatement(selectquery);
			preStatement2.setInt(1, scheduleNo);
			ResultSet rs1 = preStatement2.executeQuery();
			
			if(rs1.next()){
				schedule.setScheduleNo(scheduleNo);
				schedule.setScheduleDate(rs.getDate(2));
				schedule.setStaff(staff);
				
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		super.closeConnection(connection);
		return schedule;		
			
	}

	public Set<MaintenanceRequest> listMaintRequests(){
		Set<MaintenanceRequest> requests = new HashSet<MaintenanceRequest>();
		String getquery = "SELECT * FROM `MaintenanceRequest`;";
		String getuserquery = "SELECT * FROM `AptUser` WHERE UserName =? ;";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
			ResultSet rs = preStatement.executeQuery();
			requests.clear();

			while (rs.next()) {
				MaintenanceRequest request = new MaintenanceRequest();
	            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
	            	AptUser aptuser = new AptUser();
	            	
	            	//aptuser.setUserName(rs.getString(5));
	            	//stmt = connection.createStatement();
	    			
	            	request.setRequestNo(rs.getInt(1));			
	            	request.setRequestDate(rs.getDate(2));	
	            	request.setProblemTypeNo(rs.getInt(3));
	            	request.setProblemDescriptin(rs.getString(4));
	    		
	            //Get username from request table and then go to aptuser table to get other information	
	            	PreparedStatement preStatement2 = (PreparedStatement) connection.prepareStatement(getuserquery);
	    			preStatement.setString(1, rs.getString(5));
	    			ResultSet rs2 = preStatement2.executeQuery();
	    		//set aptuser with the specific username	
	    			aptuser.setUserName(rs2.getString(1);
	    			aptuser.setPhoneNo(rs2.getString(2));
	    			aptuser.setUnitNo(rs2.getString(3));
	    			aptuser.setApartmentID(rs2.getString(4));
	    		//set the attribute AptUser of request	
	    			request.setAptUser(aptuser);
	    			
	    			requests.add(request);
	            }
	        }

			stmt.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		super.closeConnection(connection);

		return requests;	
		
		
	}
	
	public Set<Maintenance> listMaintenance(String apartmentID){
		Set<Maintenance> maintenances = new HashSet<Maintenance>();
		String getquery = "SELECT(`MaintenanceNo`, `ApartmentID`,`UnitNo`,`RequestDate`,`ProblemType`, `Lname`,`Fname`,`FinishedDate`)" 
                          + "FROM maintenance, maintenancerequest, `schedule`, maintenanceorder, staff, facilityproblem"
                          + "WHERE maintenance.RequestNo=maintenancerequest.RequestNo"
                          + "AND maintenance.ScheduleNo=`schedule`.ScheduleNo"
                          + "AND maintenance.ProblemTypeNo=facilityproblem.ProblemTypeNo"
                          + "AND `schedule`.StaffID = staff.StaffID"
                          + "AND maintenance.OrderNo = maintenanceorder.OrderNo"
                          + "AND ApartmentID=?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
			preStatement.setString(1, apartmentID);
			ResultSet rs = preStatement.executeQuery();
			maintenances.clear();

			while (rs.next()) {
				Maintenance maintenance = new Maintenance();
	            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
	           	            	
	                maintenance.setMaintenanceNo(rs.getInt(1));			
	            	maintenance.setApartmentID(rs.getString(2));
	            	maintenance.setUnitNo(rs.getString(3));
	            	maintenance.setRequestdate(rs.getDate(4));
	            	maintenance.setProblemType(rs.getString(5));
	            	maintenance.setMaintenanceTechnician(rs.getString(6)+ rs.getString(7));
	            	maintenance.setFinisheddate(rs.getDate(8));
	                    	
     	           	maintenances.add(maintenance);
	            }
	        }
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		super.closeConnection(connection);
		return maintenances;	
		
	}

	/**
	 * Rank the problem reported from all requests in database and store problems and the times they're reported into a hashmap
	 * @return
	 */
	 public HashMap<String,Integer> listFacilityProblems() {
		 
		 HashMap<String,Integer> problemrank = new HashMap<>();
		 String getquery = "SELECT ProblemType£¬COUNT(maintenancerequest.`ProblemTypeNo`) FROM `maintenancerequest`,facilityproblem"
				          + "WHERE maintenancerequest.ProblemTypeNo=facilityproblem.ProblemTypeNo GROUP BY maintenancerequest.ProblemTypeNo;";
		 Connection connection = super.getConnection();
			Statement stmt = null;
			
			try {
				stmt = connection.createStatement();
				PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
				ResultSet rs = preStatement.executeQuery();
				

				while (rs.next()) {
					    for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {		            	
		            	problemrank.put(rs.getString(1), rs.getInt(2));	  			
		            	
		            }
		        }

				stmt.close();
				rs.close();

			} catch (SQLException e) {
				System.out.println(e.toString());
			}

			super.closeConnection(connection);

			return problemrank;	
	 }
	
	 public object calcMaintenanceCostForFacility(){
		 
		 
	 }
}
