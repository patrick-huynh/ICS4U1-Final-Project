package utility;

import javafx.beans.property.SimpleStringProperty;

public class FoodGroup {
	private SimpleStringProperty name;
	private Supplier supplier;
	
	/**Creates a new FoodGroup object with a known name and no assigned Supplier.
	 * @param group - The name of this FoodGroup.*/
	public FoodGroup(String group) {
		name = new SimpleStringProperty();
		name.set(group);
	}
	
	/**Creates a new FoodGroup object with a known group name and Supplier.
	 * @param group - The name of the FoodGroup.
	 * @param supplier - The Supplier distributing items from this FoodGroup.*/
	public FoodGroup(String group, Supplier supplier) {
		this(group);
		this.supplier = supplier;
	}
	
	/**Gets the name of the FoodGroup.
	 * @return String - The value of the property name*/
	public String getGroupName() {
		return name.get();
	}
	
	/**Sets the name of the FoodGroup. Used to assist the formation of a PropertyCellValue Factory.
	 *@param group - The value the property name will take on.*/
	public void setGroupName(String group) {
		name.set(group);
	}
	
	/**Gets the Supplier object for this FoodGroup.
	 * @return Supplier - The value of the property supplier.*/
	public Supplier getSupplier() {
		return supplier;
	}
	
	/**Sets the Supplier for this FoodGroup.
	 * @param supplier - The value the property supplier will take on.*/
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@Override
	/**Returns a String representation of this FoodGroup.
	 * @return String*/
	public String toString() {
		return "Food Group Name: " + name.get() + "\n"
				+ supplier.toString();
	}
}
