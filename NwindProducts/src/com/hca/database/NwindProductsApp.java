package com.hca.database;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NwindProductsApp {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// Load the mysql JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// display error if user doesn't enter 2 command line args (username and
		// password)
		if (args.length != 2) {
			System.out.println("Must include username and password on command line.");
			System.exit(1);
		}

		// get the username and password from the command line args
		String username = args[0];
		String password = args[1];
		
		Scanner scan = new Scanner(System.in);
		
		// ask the user what the max amount they will pay for a product is
		System.out.print("What is the max you are willing to pay for a product? ");
		float maxPrice = scan.nextFloat();

		// display username and password for proof of concept ONLY
		// System.out.printf("Username %s, Password %s\n", username, password);

		// create a connection to our database w/ the needed credentials
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/northwind", username,
				password);

		// create a statement that can execute the query we give it
		PreparedStatement statement = connection.prepareStatement(
				"SELECT productid, productname, unitprice, unitsinstock, unitprice*unitsinstock AS value FROM products WHERE unitprice <= ?");
		
		// fill in the parameter
		statement.setFloat(1, maxPrice);

		// execute the query and hold the results
		ResultSet results = statement.executeQuery();

		// print headings
		System.out.printf("%-2s  %-33s  %10s  %14s   %17s\n", "ID", "Product Name", "Price", "Units in Stock", "Cost of Inventory");
		System.out.printf("%-2s  %-33s  %10s  %14s   %17s\n", "--", "------------", "-----", "--------------", "-----------------");

		// loop thru the results as long as next() returns true .. i.e. there are
		// results left to examine
		while (results.next()) {
			System.out.printf("%2d  %-33s  %10.2f  %14d   %17.2f\n", results.getInt("productid"), results.getString("productname"),
					results.getFloat("unitprice"), results.getInt("unitsinstock"), results.getFloat("value"));
		}

//		if (results.next()) {
//			System.out.println("Your matches are: \n");
//
//			do  {
//				System.out.printf("%2d  %-33s  %10.2f  %14d\n", results.getInt(1), results.getString(2),
//						results.getFloat(3), results.getInt(4));
//			} while (results.next());
//		} else {
//			System.out.println("No matches!");
//		}

		results.close();
		statement.close();
		connection.close();

	}

}
