package com.hca.database;

import java.sql.*;
import java.util.Scanner;

public class BasicJoinApp {

	public static void main(String[] args) {

		// display error if user doesn't enter 2 command line args (username and
		// password)
		if (args.length != 2) {
			System.out.println("Must include username and password on command line.");
			System.exit(1);
		}

		// get the username and password from the command line args
		String username = args[0];
		String password = args[1];

		try {
			// load the mysql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("You didn't get the MySQL driver loaded!");
			e1.printStackTrace();
			System.exit(1);
		}
		

		// move the scope of the connection object up here so that we can use the
		// same connection across two queries below
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username,
				password);) {

			// QUERY 1 - get employees
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT employeeid, firstname FROM employees ORDER BY firstname");
					ResultSet results = statement.executeQuery();) {

				System.out.println("Our employees are: ");

				while (results.next()) {
					System.out.printf("Name = %s  Id = %d\n", results.getString(2), results.getInt(1));
				}
			}

			// ask which employee you want to see the orders for
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the name of the employee you want to see the orders for? ");
			String name = scanner.nextLine();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT orderid, shipname ");
			sql.append("FROM orders ");
			sql.append("JOIN employees ");
			sql.append("ON employees.employeeid = orders.employeeid ");
			sql.append("WHERE firstname = ? ");
			sql.append("ORDER BY orderid");

//			String sql = "SELECT orderid, shipname FROM orders JOIN employees ON employees.employeeid = orders.employeeid WHERE firstname = ? ORDER BY orderid";
			//
//			String sql = "SELECT orderid, shipname FROM orders " +
//			             "JOIN employees ON employees.employeeid = orders.employeeid " +
//					     "WHERE firstname = ? ORDER BY orderid";

			// QUERY 2 - Get the orders for a specific employee
			try (PreparedStatement statement = connection.prepareStatement(sql.toString());) {
				statement.setString(1, name.trim());

				try (ResultSet results = statement.executeQuery();) {
					System.out.println(name + "sold the following: ");

					while (results.next()) {
						System.out.printf("OrderID = %d  Ship To = %s\n", results.getInt(1), results.getString(2));
					}
				}

			}
		} catch (SQLException e) {
			System.out.println("Something is wrong with our conversation with MySQL!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Uh oh -- something bad happened that we weren't expecting!");
			e.printStackTrace();
		}
	}

}
