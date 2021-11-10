package com.hca.database;

import java.sql.*;

public class BasicQueryApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		
		// Load the mysql JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// display error if user doesn't enter 2 command line args (username and password)
		if (args.length != 2) {
			System.out.println("Must include username and password on command line.");
			System.exit(1);
		}
		
		// get the username and password from the command line args
		String username = args[0];
		String password = args[1];
		
		
		// display username and password for proof of concept ONLY
		// System.out.printf("Username %s, Password %s\n", username, password);
		
		// create a connection to our database w/ the needed credentials
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sakila", username, password);
		
		// create a statement that can execute the query we give it
		PreparedStatement statement = connection.prepareStatement("SELECT category_id, name FROM category");
		
		// execute the query and hold the results
		ResultSet results = statement.executeQuery();
		
		// loop thru the results as long as next() returns true .. i.e. there are results left to examine
		while (results.next()) {
			System.out.printf("Category ID = %d Name = %s\n", results.getInt(1), results.getString(2));	
		}
		
		results.close();
		statement.close();
		connection.close();
		
	}

}
