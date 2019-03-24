package com.zoho.beans;

public class SaleItems {
	private String Item;

	private int Quantity_Sold;

	private int Price_Product;

	public String getItem() {
		return Item;
	}

	public void setItem(String Item) {
		this.Item = Item;
	}

	public int getQuantity_Sold() {
		return Quantity_Sold;
	}

	public void setQuantity_Sold(int Quantity_Sold) {
		this.Quantity_Sold = Quantity_Sold;
	}

	public int getPrice_Product() {
		return Price_Product;
	}

	public void setPrice_Product(int Price_Product) {
		this.Price_Product = Price_Product;
	}

	@Override
	public String toString() {
		return "SaleItems [Item=" + Item + ", Price_Product=" + Price_Product + ", Quantity_Sold=" + Quantity_Sold + "]";
	}
}
