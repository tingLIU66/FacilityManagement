package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBoperate {
	
	public static final String URL = "jdbc:mysql://localhost:3306/facilitymanagementsystem";
	//public static final String USERNAME = "root";
	//public static final String PASSWORD = "123456";
	public static final String USERNAME = "DBuser";
	public static final String PASSWORD = "DBkaimen";

		public Connection getConnection() {
			try {
				//System.out.println("Loading JDBC driver...");
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBC driver successfully loaded!");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

			Connection connection = null;
			//Statement stmt = null;

			try {
				System.out.println("Connecting to the MySQL database...");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);	
				//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facilitymanagementsystem", "DBuser", "DBkaimen");	
				System.out.println("MySQL Database connected!");
			}
		    catch (SQLException e) {
			System.out.println(e.toString());
		    }
			return connection;
		 }
		
		public void closeConnection(Connection connection){ 
				//System.out.println("Closing the connection.");
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException ignore) {
					}
				}
				
			
		
		
		
		
		
		}
	

	
	

}
