package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.facility.Apartment;
import model.facility.FacilityDetail;



public class FacilityDAO extends DBoperate{
	
	public FacilityDAO() {
		super();
	}
	
	Set<Apartment> apartments = new HashSet<Apartment>();

/**
 * List all the facilities under management
 * @return Set<Apartment>
 */
public Set<Apartment> listFacilities() {
	String getquery = "SELECT * FROM `apartment`;";
	Connection connection = super.getConnection();
	Statement stmt = null;
	
	try {
		stmt = connection.createStatement();
		PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
		ResultSet rs = preStatement.executeQuery();
		apartments.clear();

		while (rs.next()) {
			Apartment apartment = new Apartment();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
            	apartment.setApartmentID(rs.getString(1));			
    			apartment.setName(rs.getString(2));			
    			
    			apartments.add(apartment);
            }
        }

		stmt.close();
		rs.close();

	} catch (SQLException e) {
		System.out.println(e.toString());
	}

	super.closeConnection(connection);

	return apartments;	
		
	}

public FacilityDetail getFacilityInformation(String apartmentID) {
	FacilityDetail fdetail = new FacilityDetail();
	String getquery = "SELECT * FROM facilitydetail WHERE `ApartmentID`=?";
	Connection connection = super.getConnection();
	Statement stmt = null;
	try {
		stmt = connection.createStatement();

		PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(getquery);
		preStatement.setString(1, apartmentID);
		ResultSet rs = preStatement.executeQuery();
		
		if(rs.next()){
			fdetail.setAdress(rs.getString(1));
			fdetail.setZipcode(rs.getString(2));
			fdetail.setAge(rs.getInt(3));
			fdetail.setCapacity(rs.getInt(4));			
			fdetail.setParking(rs.getString(5));	
		}
		
		stmt.close();
		
	} catch (SQLException e) {
		System.out.println(e.toString());
	}

	super.closeConnection(connection);
	
	return fdetail;
}

/**
 * Display how many units are available in a specific apartment
 * @param apartmentID
 * @return
 */
public int requestAvailableCapacity(String apartmentID) {
    int availablecapacity = 0; 
	String requestquery = "SELECT COUNT(`UnitStatus`) FROM unit WHERE `UnitStatus`='Available' AND `ApartmentID`=?";
	Connection connection = super.getConnection();
	Statement stmt = null;
	try {
		stmt = connection.createStatement();

		PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(requestquery);
		preStatement.setString(1, apartmentID);
		ResultSet rs = preStatement.executeQuery();
		
		if(rs.next()){
			
			availablecapacity = rs.getInt(1);			
		}
		
		stmt.close();
		
	} catch (SQLException e) {
		System.out.println(e.toString());
	}

	super.closeConnection(connection);
	return availablecapacity;
   
  }

/**
 * Add detail information to a specific apartment
 * @param apartmentID
 * @param facilitydetail
 * @return
 */
public String addFacilityDetail(String apartmentID, FacilityDetail facilitydetail) {
	
	String addquery = "INSERT INTO FacilityDetail(`Address`,`Zipcode`,`Age`,`Capatity`,`Parking`,`ApartmentID`) VALUES('?,?,?,?,?,?');";
	Connection connection = super.getConnection();
	Statement stmt = null;
	
	try {
		stmt = connection.createStatement();

		PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery);
		preStatement.setString(1, facilitydetail.getAddress());
		preStatement.setString(2, facilitydetail.getZipcode());	
		preStatement.setInt(3, facilitydetail.getAge());
		preStatement.setInt(4, facilitydetail.getCapacity());
		preStatement.setString(5, facilitydetail.getParking());
		preStatement.setString(6, apartmentID);

		preStatement.executeUpdate();	
		stmt.close();
		
	} catch (SQLException e) {
		System.out.println(e.toString());
	}

	super.closeConnection(connection);
	
	return "Adding facilityDetail successfully!";
	
}

/**
 * Add a given new Facility(Apartment)
 * @param apartmentID
 * @param name
 * @return Apartment
 */
public Apartment addNewFacility(String apartmentID, String name){
	
	Apartment apartment = new Apartment();
	String addquery = "INSERT INTO `Apartment` (`ApartmentID`, `Name`) VALUES (?,?);";
	String selectquery = "Select * From apartment where apartmentID = ?";
	Connection connection = super.getConnection();
	Statement stmt = null;
	
	try {
		stmt = connection.createStatement();

		PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(addquery);
		preStatement.setString(1, apartmentID);
		preStatement.setString(2, name);		

		preStatement.executeUpdate();
	
		PreparedStatement preStatement1 = (PreparedStatement) connection.prepareStatement(selectquery);
		preStatement1.setString(1, apartmentID);
		ResultSet rs1 = preStatement1.executeQuery();
		
		if(rs1.next()){
		apartment.setApartmentID(apartmentID);
		apartment.setName(rs1.getString(2));			
		}
		
		stmt.close();
		
	} catch (SQLException e) {
		System.out.println(e.toString());
	}

	super.closeConnection(connection);
	return apartment;
}

/**
 * Delete a specific apartment
 * @param apartmentID
 * @return
 */
public String removeFacility(String apartmentID){
	String deletequery = "DELETE FROM apartment WHERE apartmentID =?";
	Connection connection = super.getConnection();
	Statement stmt = null;

	try {
		stmt = connection.createStatement();
		PreparedStatement preStatement = (PreparedStatement) connection.prepareStatement(deletequery);
		preStatement.setString(1, apartmentID);
		preStatement.executeUpdate();

		stmt.close();
		

	} catch (SQLException e) {
		System.out.println(e.toString());
	}

	super.closeConnection(connection);
	
	return apartmentID + " is deleted from the database!";
}
}
