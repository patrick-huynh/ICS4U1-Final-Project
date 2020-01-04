package application;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
	static SimpleStringProperty[] properties = new SimpleStringProperty[3];
	
	private final SimpleStringProperty ID;
	private final SimpleStringProperty buyingBranch;
	private final SimpleStringProperty pin;
	
	Customer(String nName, String nBuyingBranch, String nPin) {
		this.ID = new SimpleStringProperty(nName);
		this.buyingBranch = new SimpleStringProperty(nBuyingBranch);
		this.pin = new SimpleStringProperty(nPin);
	}
	
	public void addProperties() {
		properties[0] = ID;
		properties[1] = buyingBranch;
		properties[2] = pin;
	}
	
	public String getName() {
		return ID.get();
	}
	
	public void setName(String nName) {
		ID.set(nName);
	}
	
	public String getBuyingBranch() {
		return buyingBranch.get();
	}
	
	public void setBuyingBranch(String nBranch) {
		buyingBranch.set(nBranch);
	}
	
	public String getPin() {
		return pin.get();
	}
	
	public void setPin(String nPin) {
		pin.set(nPin);
	}
	
}
