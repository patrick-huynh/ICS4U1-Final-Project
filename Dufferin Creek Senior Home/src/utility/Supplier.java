package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Supplier {
	private SimpleStringProperty name, address, phone;
	private final DoubleProperty stdCost, flatFee;
	private IntegerProperty stdQty;
	
	/**Creates a new Supplier object with known contact information and applicable standard rates.
	 * @param sName - The name of the Supplier object.
	 * @param sAddress - The named location of the Supplier object (# street direction postal)
	 * @param sPhone - The phone number of the Supplier object (416-###-####)
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
		
		stdCost = new SimpleDoubleProperty();
		stdCost.set(cost);
		
		stdQty = new SimpleIntegerProperty();
		stdQty.set(quantity);
		
		flatFee = new SimpleDoubleProperty();
		flatFee.set(fee);
	}
	
	/**Gets the name of this Supplier.
	 * @return String*/
	public String getName() {
		return name.get();
	}
	
	/**Sets the name of this Supplier.
	 * @param sName - String*/
	public void setName(String sName) {
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
	public String getPhone() {
		return phone.get();
	}
	
	/**Sets the phone number of this Supplier.
	 * @param sPhone - String*/
	public void setPhone(String sPhone) {
		phone.set(sPhone);
	}
	
	/**Gets the price set by this Supplier for one standard purchase of their items.
	 * @return double*/
	public double getStdCost() {
		return stdCost.get();
	}
	
	/**
	* Gets the stdCostProperty.
	*@return DoubleProperty
	*/
	public DoubleProperty stdCostProperty() {
		return stdCost;
	}
	
	/**
	* Gets flatFeeProperty.
	* @return DoubleProperty
	*/
	public DoubleProperty flatFeeProperty() {
		return flatFee;
	}
	
	
	/**
	* Gets stdQtyProperty.
	*@return IntegerProperty
	*/
	public IntegerProperty stdQtyProperty() {
		return stdQty;
	}
	
	/**Gets the quantity of items this Supplier packs in one standard purchase.
	 * @return int*/
	public int getStdQty() {
		return stdQty.get();
	}
	
	/**Gets the flat fee issued by this Supplier for all purchases.
	 * @return double*/
	public double getFlatFee() {
		return flatFee.get();
	}
	
	@Override
	/**Returns a String representation of this Supplier object. Overrides java.lang.Object.toString().
	 * @return String*/
	public String toString() {
		return "Supplier Name: " + name.get()
			+ "\nAddress: " + address.get() 
			+ "\nPhone: " + phone.get()
			+ "\nStandard Quantity: " + stdQty.get() 
			+ "\nStandard Cost: " + stdCost.get() 
			+ "\nFlat Fee: " + flatFee.get();
	}
}
