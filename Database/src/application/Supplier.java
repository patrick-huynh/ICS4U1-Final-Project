package application;

import javafx.beans.property.SimpleStringProperty;

public class Supplier {
	private final SimpleStringProperty ID;
	private final SimpleStringProperty name;
	private final SimpleStringProperty product;
	private final SimpleStringProperty phone;
	
	Supplier(String nID, String nName, String nProduct, String nPhone) {
		this.ID = new SimpleStringProperty(nID);
		this.name = new SimpleStringProperty(nName);
		this.product = new SimpleStringProperty(nProduct);
		this.phone = new SimpleStringProperty(nPhone);
	}
	
	public String getID() {
		return ID.get();
	}
	
	public void setID(String nID) {
		ID.set(nID);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String nName) {
		name.set(nName);
	}
	
	public String getProduct() {
		return product.get();
	}
	
	public void setProduct(String nProduct) {
		product.set(nProduct);
	}
	
	public String getPhone() {
		return phone.get();
	}
	
	public void setPhone(String nPhone) {
		phone.set(nPhone);
	}
	
}
