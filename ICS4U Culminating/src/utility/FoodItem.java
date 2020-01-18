package utility;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FoodItem {
	private StringProperty groupName;
	private SimpleStringProperty name, expiryDate;
	private FoodGroup group;
	private IntegerProperty quantity, stock;
	private DoubleProperty cost;
	
	/**Creates a FoodItem with a name, expiry date, and FoodGroup, Lists the quantity to stock numbers.
	 * @param item_name - The name of the FoodItem.
	 * @param item_expiry - The expiry date of the FoodItem.
	 * @param group - The FoodGroup of the FoodItem.
	 * @param item_quantity - The quantity of products of this FoodItem currently in stock.
	 * @param item_stock - The expected number of products of this FoodItem to be in stock.*/
	public FoodItem(String item_name, String expiry_date, FoodGroup group, int item_quantity, int item_stock) {
		name = new SimpleStringProperty();
		name.set(item_name);
		
		expiryDate = new SimpleStringProperty();
		expiryDate.set(expiry_date);
		
		this.group = group;
		groupName = new SimpleStringProperty();
		groupName.set(group.getGroupName());
		
		quantity = new SimpleIntegerProperty();
		quantity.set(item_quantity);
		
		stock = new SimpleIntegerProperty();
		stock.set(item_stock);
		
		cost = new SimpleDoubleProperty();
		cost.set(0);
	}
	
	/**Gets the name of the FoodItem.
	 * @return String*/
	public String getName() {
		return name.get();
	}
	
	/**Sets the name of the FoodItem.
	 * @param item_name - String*/
	public void setName(String item_name) {
		name.set(item_name);
	}
	
	/**Gets the String version of the LocalDate for the expiry of this FoodItem.
	 * @return LocalDate*/
	public String getExpiryDate() {
		return expiryDate.get();
	}
	
	public LocalDate getExpiryAsDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		return LocalDate.parse(expiryDate.get(), formatter);
	}
	
	/**Sets the expiry date of this FoodItem.
	 * @param item_expiry - LocalDate*/
	public void setExpiryDate(String expiry_date) {
		this.expiryDate.set(expiry_date);
	}
	
	/**Gets the FoodGroup of this FoodItem.
	 * @return FoodGroup*/
	public FoodGroup getGroup() {
		return group;
	}
	
	/**Sets the FoodGroup of this FoodItem. The FoodGroup currently has non named Supplier.
	 * @param name - String*/
	public void setGroup(String name) {
		group = new FoodGroup(name);
	}
	/**Sets the FoodGroup of this FoodItem.
	 * @param name - String
	 * @param supplier - Supplier*/
	public void setGroup(String name, Supplier supplier) {
		group = new FoodGroup(name, supplier);
	}
	
	public StringProperty groupNameProperty() {
		return groupName;
	}
	
	/**Gets the current quantity of this FoodItem.
	 * @return quantity*/
	public int getQuantity() {
		return quantity.get();
	}
	
	/**Sets the current quantity of this FoodItem.
	 * @param item_quantity - Number*/
	public void setQuantity(Number item_quantity) {
		quantity.set(item_quantity.intValue());
	}
	
	/**Gets the quantity property for this FoodItem object.
	 * @return IntegerProperty*/
	public IntegerProperty quantityProperty() {
		return quantity;
	}
	
	/**Gets the expected stock of this FoodItem
	 * @return int*/
	public int getStock() {
		return stock.get();
	}
	
	/**Sets the expected stock of this FoodItem.
	 * @param item_stock - Number*/
	public void setStock(Number item_stock) {
		stock.set(item_stock.intValue());
	}
	
	/**Gets the stock property for this FoodItem object.
	 * @return IntegerProperty*/
	public IntegerProperty stockProperty() {
		return stock;
	}
	
	public DoubleProperty costProperty() {
		return cost;
	}
	
	/**Checks if the current quantity of this FoodItem is below its expected stock.
	 * @return boolean*/
	public boolean isBelowExpected() {
		if (quantity.get() < stock.get()) {
			return true;
		}
		return false;
	}
	
	/**Computes the cost for one standard purchase of this FoodItem.
	 * @return double*/
	public double computeSTDCost() {
		Supplier supplier = group.getSupplier();
		double expenses = supplier.getFlatFee() + supplier.getStdQty();
		cost.set(expenses);
		return expenses;
	}
}
