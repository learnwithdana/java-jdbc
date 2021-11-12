package com.hca.nwind.datalayer;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.hca.nwind.models.Category;
import com.hca.nwind.models.Product;

import com.mysql.cj.jdbc.MysqlDataSource;

public class NwindDataManager {

	private MysqlDataSource dataSource;

	public NwindDataManager() {
		dataSource = new MysqlDataSource();

		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("northwind");
		dataSource.setUser("root");
		dataSource.setPassword("password");
	}

	public List<Product> getAllProducts() {
		ArrayList<Product> list = new ArrayList<Product>();

		try (
				Connection connection = dataSource.getConnection();
				PreparedStatement stmtProducts = connection.prepareStatement(
						"SELECT * FROM Products ORDER BY ProductName");
				ResultSet results = stmtProducts.executeQuery();
		) {
			
			while (results.next()) {
				Product product = new Product(results.getInt("ProductId"), results.getString("ProductName"),
						results.getDouble("UnitPrice"), results.getInt("UnitsInStock"), null);

				list.add(product);
			}
		} 
		catch (SQLException e) {
			System.out.println("***ERROR [NwindDataManager:getAllProducts] -- unable to fetch products");
		}

		return list;
	}

	public List<Product> getProductsByCategory(int categoryId) {
		ArrayList<Product> list = new ArrayList<Product>();

		try (
				Connection connection = dataSource.getConnection();
				PreparedStatement stmtProducts = connection.prepareStatement(
						"SELECT * FROM Products WHERE CategoryId = ? ORDER BY ProductName");
		) {
			stmtProducts.setInt(1, categoryId);
			
			try(
				ResultSet results = stmtProducts.executeQuery();
			) {
				while (results.next()) {
					Product product = new Product(results.getInt("ProductId"), results.getString("ProductName"),
							results.getDouble("UnitPrice"), results.getInt("UnitsInStock"), null);
	
					list.add(product);
				}				
			}
		} 
		catch (SQLException e) {
			System.out.println("***ERROR [getProductsByCategory] -- unable to fetch products for category " + categoryId);
		}

		return list;
	}

	public List<Category> getAllCategories() {
		ArrayList<Category> list = new ArrayList<Category>();

		try (
				Connection connection = dataSource.getConnection();
				PreparedStatement stmtCategories = connection.prepareStatement(
						"SELECT * FROM Categories ORDER BY CategoryId");
				ResultSet results = stmtCategories.executeQuery();
		) {
			while (results.next()) {
				Category category = new Category(results.getInt("CategoryId"), results.getString("CategoryName"), results.getString("Description"));
				list.add(category);
			}
		} 
		catch (SQLException e) {
			System.out.println("***ERROR [NwindDataManager:getAllCategories] -- unable to fetch categories");
		}
		return list;
	}

}
