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
import model.facilitymaitenance.Cost;
import model.facilitymaitenance.FacilityProblem;
import model.facilitymaitenance.Maintenance;
import model.facilitymaitenance.MaintenanceOrder;
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

	/**
	 * 
	 * @param problemtypeNo
	 * @param pdescription
	 * @param aptuser
	 * @return
	 */
	public MaintenanceRequest makeFacilityMaintRequest(int problemtypeNo, String pdescription, String username){
		
		int requestNo = 0;
		MaintenanceRequest maintrequest = null;
		String addquery = "INSERT INTO maintenancerequest(`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES(?,?,?);";
		String selectquery = "SELECT `RequestNo`,a.`UserName`, `PhoneNo`,`UnitNo`,`ApartmentID`, f.`ProblemTypeNo`,`ProblemType`,`ProblemDescription`,`RequestDate`"
							+ " FROM `maintenancerequest` AS m,aptuser AS a,facilityproblem AS f"
							+ " WHERE m.ProblemTypeNo = f.ProblemTypeNo"
							+ " AND m.UserName = a.UserName"
							+ " AND m.RequestNo = ?";
		String addmaintrecordquery = "INSERT INTO maintenance(`RequestNo`) VALUES(?);";
		
		//"INSERT INTO maintenance(`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES(?,?,?);";

		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
		
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery, Statement.RETURN_GENERATED_KEYS);
			preStatement.setInt(1, problemtypeNo);
			preStatement.setString(2, pdescription);
			preStatement.setString(3, username);			

			preStatement.executeUpdate();
			
			//get the auto generated RequestNo first and then get all the maintenancerequest info
			ResultSet rs = preStatement.getGeneratedKeys();
			if(rs.next()){
			requestNo = rs.getInt(1);
			}
			
			System.out.println("id:"+requestNo);
		   //Insert a record into maintenance table once a request created	
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(addmaintrecordquery);
			preStatement1.setInt(1, requestNo);
						
			preStatement1.executeUpdate();
		
			PreparedStatement preStatement2 = (PreparedStatement) connection.prepareStatement(selectquery);
			preStatement2.setInt(1, requestNo);
			ResultSet rs1 = preStatement2.executeQuery();
			
			if(rs1.next()){
				maintrequest = new MaintenanceRequest(requestNo, new AptUser(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)),
														new FacilityProblem(rs1.getInt(6),rs1.getString(7)), rs1.getString(8),rs1.getDate(9));
				//maintrequest.setRequestNo(requestNo);
				//maintrequest.setAptUser(new AptUser(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)));
				//maintrequest.setFacilityProblem(new FacilityProblem(rs1.getInt(6),rs1.getString(7)));
				//maintrequest.setProblemDescriptin(rs1.getString(8));
				//maintrequest.setRequestDate(rs1.getDate(9));
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		super.closeConnection(connection);
		return maintrequest;			
		
	}
	
	/**
	 * 
	 * @param maintenanceNo
	 * @param sdate
	 * @param staff
	 * @return
	 */
	public Schedule scheduleMaintenance(int maintenanceNo, Date sdate, Staff staff ){
		
		int scheduleNo = 0;
		Schedule schedule = null;
		String addquery = "INSERT INTO schedule(`ScheduleDate`,`StaffID`) VALUES(?,?);";
		String selectquery = "Select * From schedule where `ScheduleNo` = ?";
		String updatemaintrecordquery = "update Maintenance set `ScheduleNo` = ? where maintenanceNo = ?;";
				//"Select * From schedule where `ScheduleNo` = ?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();

			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery,Statement.RETURN_GENERATED_KEYS);
			preStatement.setDate(1, sdate);
			preStatement.setInt(2, staff.getstaffID());

			preStatement.executeUpdate();
			
			//get the auto generated RequestNo first and then get all the schedule info
			ResultSet rs = preStatement.getGeneratedKeys();
			if(rs.next()){
			scheduleNo = rs.getInt(1);
			}
			//Update scheduleNo into maintenance table once schedule a maintenance
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(updatemaintrecordquery);
			preStatement1.setInt(1, scheduleNo);
			preStatement1.setInt(2, maintenanceNo);
			preStatement1.executeUpdate();
		
			PreparedStatement preStatement2 = (PreparedStatement) connection.prepareStatement(selectquery);
			preStatement2.setInt(1, scheduleNo);
			ResultSet rs1 = preStatement2.executeQuery();
			
			if(rs1.next()){
				schedule = new Schedule(scheduleNo,rs1.getDate(2),staff);
				//schedule.setScheduleNo(scheduleNo);
				//schedule.setScheduleDate(rs1.getDate(2));
				//schedule.setStaff(staff);
				
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		super.closeConnection(connection);
		return schedule;		
			
	}

	/**
	 * 
	 * @return
	 */
	public Set<MaintenanceRequest> listMaintRequests(){
		Set<MaintenanceRequest> requests = new HashSet<MaintenanceRequest>();
		String getquery = "SELECT `RequestNo`,a.`UserName`, `PhoneNo`,`UnitNo`,`ApartmentID`, f.`ProblemTypeNo`,`ProblemType`,`ProblemDescription`,`RequestDate`"
						+ " FROM `maintenancerequest` AS m,aptuser AS a,facilityproblem AS f"
						+ " WHERE m.ProblemTypeNo = f.ProblemTypeNo"
						+ " AND m.UserName = a.UserName";						

		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
			ResultSet rs1 = preStatement.executeQuery();
			requests.clear();

			while (rs1.next()) {
				MaintenanceRequest request = new MaintenanceRequest(rs1.getInt(1),new AptUser(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)),
						                                            new FacilityProblem(rs1.getInt(6),rs1.getString(7)),rs1.getString(8),rs1.getDate(9));          
	            	
				//request.setRequestNo(rs1.getInt(1));
				//request.setAptUser(new AptUser(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)));
				//request.setFacilityProblem(new FacilityProblem(rs1.getInt(6),rs1.getString(7)));
				//request.setProblemDescriptin(rs1.getString(8));
				//request.setRequestDate(rs1.getDate(9));
	    			
	    		requests.add(request);
	            }
	        

			stmt.close();
			rs1.close();

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		super.closeConnection(connection);

		return requests;	
		
		
	}
	
	/**
	 * 
	 * @param apartmentID
	 * @return
	 */
	public Set<Maintenance> listMaintenance(String apartmentID){
		Set<Maintenance> maintenances = new HashSet<Maintenance>();
	
		String getquery = "SELECT `MaintenanceNo`, mr.`RequestNo`, mr.`UserName`,`PhoneNo`,`UnitNo`,`ApartmentID`, mr.`ProblemTypeNo`,`ProblemType`,`ProblemDescription`, `RequestDate`,"
						 + "s.`ScheduleNo`,`ScheduleDate`, s.`StaffID`,`Fname`,`Lname`,`SpecialtyDescription`,"
						 + "mo.`OrderNo`,`OrderDate`,`OrderStatus`,`FinishedDate`, `LaborCost`,`MaterialCost`"
						 + " FROM maintenance AS m, maintenancerequest AS mr, `schedule` AS s, maintenanceorder AS mo, staff AS sf, facilityproblem AS f, aptuser AS a, cost AS c"
						 + " WHERE m.RequestNo = mr.RequestNo AND m.ScheduleNo = s.ScheduleNo"
						 + " AND mr.ProblemTypeNo = f.ProblemTypeNo AND s.StaffID = sf.StaffID"
						 + " AND m.OrderNo = mo.OrderNo AND mr.UserName = a.UserName"
						 + " AND mo.CostNo = c.CostNo AND ApartmentID= ?";
		
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
			preStatement.setString(1, apartmentID);
			ResultSet rs1 = preStatement.executeQuery();
			maintenances.clear();

			while (rs1.next()) {
				Maintenance maintenance = new Maintenance();
				
				     	            	
	                maintenance.setMaintenanceNo(rs1.getInt(1));
	                maintenance.setMaintenanceRequest(new MaintenanceRequest(rs1.getInt(2),new AptUser(rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6)),
                                                                             new FacilityProblem(rs1.getInt(7),rs1.getString(8)),rs1.getString(9),rs1.getDate(10)));
	                maintenance.setSchedule(new Schedule(rs1.getInt(11),rs1.getDate(12),new Staff(rs1.getInt(13),rs1.getString(14),rs1.getString(15), rs1.getString(16))));
	                maintenance.setMaintenanceOrder(new MaintenanceOrder(rs1.getInt(17), rs1.getDate(18),rs1.getString(19),rs1.getDate(20), new Cost(rs1.getFloat(22),rs1.getFloat(22))));
	                
	                
	            	//maintenance.setApartmentID(rs.getString(2));
	            	//maintenance.setUnitNo(rs.getString(3));
	            	//maintenance.setRequestdate(rs.getDate(4));
	            	//maintenance.setProblemType(rs.getString(5));
	            	//maintenance.setMaintenanceTechnician(rs.getString(6)+ rs.getString(7));
	            	//maintenance.setFinisheddate(rs.getDate(8));
	                    	
     	           	maintenances.add(maintenance);
	            
	        }
			stmt.close();
			rs1.close();

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
	 public HashMap<String,Integer> listFacilityProblems(String apartmentID) {
		 
		 HashMap<String,Integer> problemrank = new HashMap<>();
		 String getquery = "SELECT ProblemType, COUNT(m.ProblemTypeNo) "
		 					+ "FROM maintenancerequest AS m, facilityproblem AS f, aptuser AS a "
		 					+ "WHERE m.ProblemTypeNo = f.ProblemTypeNo "
		 					+ "AND m.UserName = a.UserName "
		 					+ "AND apartmentID = ?"
		 					+ "GROUP BY ProblemType";
		 Connection connection = super.getConnection();
			Statement stmt = null;
			
			try {
				stmt = connection.createStatement();
				PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
				preStatement.setString(1, apartmentID);
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

	 /**
	  * 
	  * @param apartmentID
	  * @return
	  */
	 public float getProblemcount(String apartmentID){
		 float problemcount = 0;
		 String getnumber = "SELECT COUNT(`MaintenanceNo`)"
				 			+ " FROM `maintenanceorder`AS mo, maintenance AS m, maintenancerequest AS mr, aptuser AS a"
				 			+ " WHERE YEAR(`FinishedDate`) = 2016"
				 			+ " AND `OrderStatus`= 'Finished'"
				 			+ " AND m.OrderNo = mo.OrderNo"
				 			+ " AND m.RequestNo = mr.RequestNo"
				 			+ " AND mr.UserName = a.UserName"
				 			+ " AND apartmentID = ?";
		 
		 Connection connection = super.getConnection();
			Statement stmt = null;
			
			try {
				stmt = connection.createStatement();
				PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getnumber);
				preStatement.setString(1, apartmentID);
				ResultSet rs = preStatement.executeQuery();
				

				if (rs.next()) {
					    problemcount = rs.getFloat(1);			            	
		            }
		     
				stmt.close();
				rs.close();

			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			super.closeConnection(connection);
		 
		return problemcount;
	 
	 }


	
	 public Cost calcMaintenanceCostForFacility(String apartmentID){
		 Cost cost = null;
		 
		 String getcostquery = "SELECT SUM(`LaborCost`),SUM(`MaterialCost`)"
		 						+ " FROM maintenance AS m, maintenanceorder AS mo,cost AS c, maintenancerequest AS mr, aptuser AS a"
		 						+ " WHERE m.OrderNo = mo.OrderNo AND mo.CostNo = c.CostNo"
		 						+ " AND mr.UserName = a.UserName AND mr.RequestNo = m.RequestNo"
		 						+ " AND YEAR(FinishedDate) = 2016"
		 						+ " AND apartmentID =?";
		 
		 Connection connection = super.getConnection();
			Statement stmt = null;
			
			try {
				stmt = connection.createStatement();
				PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getcostquery);
				preStatement.setString(1, apartmentID);
				ResultSet rs = preStatement.executeQuery();
				

				if (rs.next()) {
					cost = new Cost(rs.getFloat(1),rs.getFloat(2));
					   //cost.setLaborCost(rs.getFloat(1));  
					   //cost.setMaterialCost(rs.getFloat(2));
					  // cost.setTotal();
		            }
		     
				stmt.close();
				rs.close();

			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			super.closeConnection(connection);
		 
		 return cost;
		 
		 
	 }
}
