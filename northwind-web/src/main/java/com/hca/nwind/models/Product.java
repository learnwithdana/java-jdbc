package com.hca.nwind.models;

public class Product {
	private int productId;
	private String productName;
	private double unitPrice;
	private int unitsInStock;
	private Category category; 
	// we could have encapsulated more Product table columns
	// if you had wanted to work this weekend
	
	public Product(int productId, String productName, double unitPrice, int unitsInStock, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return String.format("Product [productId=%s, productName=%s, unitPrice=%s, unitsInStock=%s, category=%s]",
				productId, productName, unitPrice, unitsInStock, category);
	}
	
}
