package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public Schedule scheduleMaintenance(){
		return null;
		
		
	}
	
}
