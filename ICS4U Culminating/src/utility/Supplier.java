package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Supplier {
	private SimpleStringProperty name, address, phone;
	private final SimpleDoubleProperty std_cost, flat_fee;
	private SimpleIntegerProperty std_qty;
	
	/**Creates a new Supplier object with known contact information and applicable standard rates.
	 * @param sName - The name of the Supplier object.
	 * @param sAddress - The named location of the Supplier object (# street direction postal)
	 * @param sPhone - The phone number of the Supplier object (416-###-####)
	 * @param rate - The standard shipping rate for the Supplier.
	 * @param cost - The standard cost per standard purchase of the Supplier.
	 * @param quantity - The standard quantity of FoodItem objects in a standard purchase.
	 * @param fee - The base fee due when a standard purchase is made.*/
	public Supplier(String sName, String sAddress, String sPhone, double cost, int quantity, 
			double fee) {
		name = new SimpleStringProperty();
		name.set(sName);
		
		address = new SimpleStringProperty();
		address.set(sAddress);
		
		phone = new SimpleStringProperty();
		phone.set(sPhone);
		
		std_cost = new SimpleDoubleProperty();
		std_cost.set(cost);
		
		std_qty = new SimpleIntegerProperty();
		std_qty.set(quantity);
		
		flat_fee = new SimpleDoubleProperty();
		flat_fee.set(fee);
	}
	
	/**Gets the name of this Supplier.
	 * @return String*/
	public String getSupplierName() {
		return name.get();
	}
	
	/**Sets the name of this Supplier.
	 * @param sName - String*/
	public void setSupplierName(String sName) {
		name.set(sName);
	}
	
	/**Gets the address of this Supplier.
	 * @return String*/
	public String getAddress() {
		return address.get();
	}
	
	/**Sets the address of this Supplier.
	 * @param sAddress - String*/
	public void setAddress(String sAddress) {
		address.set(sAddress);
	}
	
	/**Gets the phone number of this Supplier.
	 * @return String*/
	public String getPhoneNumber() {
		return phone.get();
	}
	
	/**Sets the phone number of this Supplier.
	 * @param sPhone - String*/
	public void setPhoneNumber(String sPhone) {
		phone.set(sPhone);
	}
	
	public double getSTDCost() {
		return std_cost.get();
	}
	
	public int getSTDQty() {
		return std_qty.get();
	}
	
	public double getFee() {
		return flat_fee.get();
	}
	
}
