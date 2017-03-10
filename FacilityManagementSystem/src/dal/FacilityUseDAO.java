package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.ArrayList;


import model.facilityuse.AptUser;
import model.facilityuse.FacilityUse;
import model.facility.Unit;
import model.facility.Inspection;
import model.facilitymaitenance.MaintenanceRequest;

import java.util.List;


/*public boolean inInUseDuringInterval
 *public void calcUsagerate()
 *public void vacateFacility()
 *public void assignFacility()
 *public void listactualusage()
 *public <inspections> listinspections()
 */

public class FacilityUseDAO extends DBoperate
{
		
	public FacilityUseDAO()
	{
		super();
	}
   /**
    * Check if is a certain unit is in use or not
    * @param apartmentID
    * @param UnitNo
    * @return
    */
	public String isInUseDuringInterval(String apartmentID, String UnitNo)
	{
		String inUse = "Rented";
		String getquery = "SELECT UnitStatus FROM Unit WHERE apartmentID=? AND UnitNo=? ";
		Connection connection = super.getConnection();
		Statement stmt = null;
		String isinuse = "is available" ; 

		try 
		{
			stmt = connection.createStatement(); //step to execute our query and get a resultSetObj
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);//getting result
			preStatement.setString(1, apartmentID);
			preStatement.setString(2, UnitNo);
			
			ResultSet rs = preStatement.executeQuery();
			
			if(rs.next()){
				String result = rs.getString(1);
				
				if (result.contentEquals(inUse))
				{
					isinuse = "is in use";	
					
				}
			}

			stmt.close();
		}
		 catch (SQLException e) 
		{
			 System.out.println(e.toString());
		
		}
		super.closeConnection(connection);
		return isinuse; 
	}

	/**
	 * List all the units that rented out
	 * @param apartmentID
	 * @return
	 */
	public List<Unit> listActualUsage(String apartmentID)
	{
		List <Unit> usageList = new ArrayList <Unit>();
		String listQuery = "SELECT UnitNo FROM unit WHERE UnitStatus = Rented AND apartmentID =?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(listQuery);
			preStatement.setString(1,apartmentID);
			
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next())
			{
				Unit listUse = new Unit();
				listUse.setUnitNo(rs.getString(1));
				usageList.add(listUse);	
			}
			stmt.close();
			rs.close();
		}
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
		super.closeConnection(connection);

		return usageList;	
	}
	
	/**
	 * Mark a unit as available
	 * @param apartmentID
	 * @param unitNo
	 * @return
	 */
	public String vacateFacility(String apartmentID, String unitNo)
	{
		
		String vacateQuery = "UPDATE unit SET UnitStatus =? WHERE apartmentID =? AND unitNo = ?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(vacateQuery);
			preStatement.setString(1,"Available");
			preStatement.setString(2,apartmentID);
			preStatement.setString(3,unitNo);
			preStatement.executeUpdate();
			stmt.close();
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
		super.closeConnection(connection);

		return apartmentID + " " + unitNo+" is Available!";

	}
	
	/**
	 * Assign a unit to a user
	 * @param apartmentID
	 * @param unitNo
	 * @param useApt
	 * @return
	 */
	public String assignFacility(String apartmentID, String unitNo, AptUser useApt)
	{
		String assignQuery = "INSERT INTO AptUser (`UserName`,`PhoneNo`,`UnitNo`,`ApartmentID`) VALUES(?,?,?,?);";
		String updateQuery = "update unit set `unitstatus` = ? where apartmentID = ? and unitNo = ?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(assignQuery);
			preStatement.setString(1,useApt.getUserName());
			preStatement.setString(2,useApt.getPhoneNo());
			preStatement.setString(3,useApt.getUnitNo());
			preStatement.setString(4,useApt.getApartmentID());
			preStatement.executeUpdate();
			
			PreparedStatement preStatement2 = (PreparedStatement) connection.prepareStatement(updateQuery);
			preStatement2.setString(1,"Rented");
			preStatement2.setString(2,apartmentID);
			preStatement2.setString(3,unitNo);
			preStatement2.executeUpdate();
			
			stmt.close(); 
		}
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
		
		super.closeConnection(connection);
		
		return "Apartment " + apartmentID + " " + unitNo + " is assigned to user " +  useApt.getUserName();

		
	}
	
	/**
	 * List all the inspections for a apartment
	 * @param apartmentID
	 * @return
	 */
	public List<Inspection> listInspections(String apartmentID)
	{
		List <Inspection> inspectList = new ArrayList <Inspection>();
		String listQuery = "SELECT InspectionID FROM Inspection WHERE apartmentID =?";
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(listQuery);
			preStatement.setString(1,apartmentID);
			
			ResultSet rs = preStatement.executeQuery();
			int parseInt = 0;
			while(rs.next())
			{
				Inspection fetchInspect = new Inspection(); //to return a list of inspections
				parseInt =Integer.parseInt(rs.getString(1));
				
				fetchInspect.setinspectionID(parseInt);
				inspectList.add(fetchInspect);	//add output to new list
			}
			stmt.close();
			rs.close();
		}
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
		super.closeConnection(connection);

		return inspectList;
		
	}

	/**
	 * Get the number of units that rented out
	 * @param apartmentID
	 * @return
	 */
	
	public float getUsedCount(String apartmentID)
	{
		String countunitQuery = "SELECT COUNT(UnitNo) FROM unit WHERE UnitStatus = 'Rented' AND apartmentID =? ";
		int usedcount = 0;
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(countunitQuery);
			preStatement.setString(1,apartmentID);
			ResultSet rs = preStatement.executeQuery(); 
		
			if(rs.next() )
			{
				usedcount = rs.getInt(1);
		
			}
		
			stmt.close(); 
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
		super.closeConnection(connection);
		
		return (float)usedcount;
	}
	
	/**
	 * Get the capacity of an apartment
	 * @param apartmentID
	 * @return
	 */
	public float getCapacity(String apartmentID)
	{
		String getcapQuery = "SELECT capatity FROM facilitydetail WHERE apartmentID =?";
		int cap = 0;
		
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
				
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(getcapQuery);
			preStatement1.setString(1,apartmentID);
			ResultSet rs1 = preStatement1.executeQuery();			
		
			if(rs1.next() )
			{
				cap  = rs1.getInt(1);			
			}
			
			stmt.close(); 						
		}
		
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
		super.closeConnection(connection);
		
		return (float)cap;
	}
	

}
