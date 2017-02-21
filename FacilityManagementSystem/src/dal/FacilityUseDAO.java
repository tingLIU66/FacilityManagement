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
	//public boolean inInUseDuringInterval
	
	public FacilityUseDAO()
	{
		super();
	}

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
				//System.out.println(result);
				if (result.contentEquals(inUse))
				{
					isinuse = "is in use";	
					//System.out.println(isinuse);
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
	
	public void assignFacility(String apartmentID, String unitNo, AptUser useApt)
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
		
		System.out.println("Apartment " + apartmentID + " " + unitNo + " is assigned to user " +  useApt.getUserName());

		
	}
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

	
	
	public void calcUsagerate(String apartmentID)
	{
		String countunitQuery = "SELECT COUNT(UnitNo) FROM unit WHERE UnitStatus = 'Rented' AND apartmentID =? ";
		String getcapQuery = "SELECT capatity FROM facilitydetail WHERE apartmentID =?";
		int usecount = 0;
		int cap = 0;
		float useRate = 0;
		Connection connection = super.getConnection();
		Statement stmt = null;
		
		try 
		{
			stmt = connection.createStatement();
			PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(countunitQuery);
			preStatement.setString(1,apartmentID);
			ResultSet rs = preStatement.executeQuery(); 
			
			
			PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(getcapQuery);
			preStatement1.setString(1,apartmentID);
			ResultSet rs1 = preStatement1.executeQuery();
			
			//int parseInt1 = 0;
			if(rs.next() )
			{
				usecount = rs.getInt(1);
			
			}
			//int parseInt2 = 0;
			if(rs1.next() )
			{
				cap  = rs1.getInt(1);
			
			}
			
			useRate = (float) usecount/(float) cap;
			System.out.println(usecount + " " + cap + " " +useRate );
			stmt.close(); 
			System.out.println("Usage Rate is " + useRate*100 + "%");
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.toString());
		}
		super.closeConnection(connection);
		
		
	}
	

}
