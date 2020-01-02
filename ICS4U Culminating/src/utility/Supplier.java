package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Supplier {
	private SimpleStringProperty name, address, phone;
	private final SimpleDoubleProperty ship_rate, std_cost, std_qty, flat_fee;
	
	/**Creates a new Supplier object with known contact information and applicable standard rates.
	 * @param sName - The name of the Supplier object.
	 * @param sAddress - The named location of the Supplier object (# street direction postal)
	 * @param sPhone - The phone number of the Supplier object (416-###-####)
	 * @param rate - The standard shipping rate for the Supplier.
	 * @param cost - The standard cost per standard purchase of the Supplier.
	 * @param quantity - The standard quantity of FoodItem objects in a standard purchase.
	 * @param flat_fee - The base fee due when a standard purchase is made.*/
	public Supplier(String sName, String sAddress, String sPhone, double rate, double cost, double quantity, 
			double fee) {
		name = new SimpleStringProperty();
		name.set(sName);
		
		address = new SimpleStringProperty();
		address.set(sAddress);
		
		phone = new SimpleStringProperty();
		phone.set(sPhone);
		
		ship_rate = new SimpleDoubleProperty();
		ship_rate.set(rate);
		
		std_cost = new SimpleDoubleProperty();
		std_cost.set(cost);
		
		std_qty = new SimpleDoubleProperty();
		std_qty.set(quantity);
		
		flat_fee = new SimpleDoubleProperty();
		flat_fee.set(fee);
	}
	
	/***/
	public String getSupplierName() {
		return name.get();
	}
	
	public void setSupplierName(String sName) {
		name.set(sName);
	}
	
	public String getAddress() {
		return address.get();
	}
	
	
}
