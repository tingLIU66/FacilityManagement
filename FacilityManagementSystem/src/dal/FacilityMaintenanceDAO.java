package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();

			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery);
			preStatement.setInt(1, problemtypeNo);
			preStatement.setString(2, pdescription);
			preStatement.setString(3, aptuser.getUserName());

			preStatement.executeUpdate();
			
			//get the auto generated RequestNo first and then get all the maintenancerequest info
			ResultSet rs = preStatement.getGeneratedKeys();
			requestNo = rs.getInt(1);
		
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(selectquery);
			preStatement1.setInt(1, requestNo);
			ResultSet rs1 = preStatement1.executeQuery();
			
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
	
	public Schedule scheduleMaintenance(Date sdate, MaintenanceRequest maintrequest, Staff staff ){
				
		int scheduleNo = 0;
		Schedule schedule = new Schedule();
		String addquery = "INSERT INTO schedule(`RequestNo`,`ScheduleDate`,`StaffID`) VALUES(?,?,?);";
		String selectquery = "Select * From schedule where `ScheduleNo` = ?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();

			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery);
			preStatement.setInt(1, maintrequest.getRequestNo());
			preStatement.setDate(2, sdate);
			preStatement.setInt(3, staff.getStaffID());

			preStatement.executeUpdate();
			
			//get the auto generated RequestNo first and then get all the schedule info
			ResultSet rs = preStatement.getGeneratedKeys();
			scheduleNo = rs.getInt(1);
		
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(selectquery);
			preStatement1.setInt(1, scheduleNo);
			ResultSet rs1 = preStatement1.executeQuery();
			
			if(rs1.next()){
				schedule.setScheduleNo(scheduleNo);
				schedule.setMaintenanceRequest(maintrequest);
				schedule.setScheduleDate(rs.getDate(3));
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
	
	public Set<Maintenance> listMaintenance(String ApartmentID){
		return null;
		
	}
	
}
