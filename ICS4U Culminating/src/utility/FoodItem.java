package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.time.LocalDate;

public class FoodItem {
	private SimpleStringProperty name; // change exp_date to DateTimeFormatter
	private LocalDate expiry_date;
	private FoodGroup group;
	private SimpleIntegerProperty quantity, stock;
	
	/**Creates a FoodItem with a name, expiry date, and FoodGroup, Lists the quantity to stock numbers.
	 * @param item_name - The name of the FoodItem.
	 * @param item_expiry - The expiry date of the FoodItem.
	 * @param group - The FoodGroup of the FoodItem.
	 * @param item_quantity - The quantity of products of this FoodItem currently in stock.
	 * @param item_stock - The expected number of products of this FoodItem to be in stock.*/
	public FoodItem(String item_name, LocalDate expiry_date, FoodGroup group, int item_quantity, int item_stock) {
		name = new SimpleStringProperty();
		name.set(item_name);
		
		this.expiry_date = expiry_date;
		
		this.group = group;
		
		quantity = new SimpleIntegerProperty();
		quantity.set(item_quantity);
		
		stock = new SimpleIntegerProperty();
		stock.set(item_stock);
	}
	
	/**Gets the name of the FoodItem.
	 * @return String*/
	public String getItemName() {
		return name.get();
	}
	
	/**Sets the name of the FoodItem.
	 * @param item_name - String*/
	public void setItemName(String item_name) {
		name.set(item_name);
	}
	
	/**Gets the String version of the LocalDate for the expiry of this FoodItem.
	 * @return LocalDate*/
	public LocalDate getExpiryDate() {
		return expiry_date;
	}
	
	/**Sets the expiry date of this FoodItem.
	 * @param item_expiry - LocalDate*/
	public void setExpiryDate(LocalDate expiry_date) {
		this.expiry_date = expiry_date;
	}
	
	/**Gets the FoodGroup of this FoodItem.
	 * @return FoodGroup*/
	public FoodGroup getFoodGroup() {
		return group;
	}
	
	/**Sets the FoodGroup of this FoodItem.
	 * @param name - String
	 * @param supplier - Supplier*/
	public void setFoodGroup(String name, Supplier supplier) {
		group = new FoodGroup(name, supplier);
	}
	
	/**Gets the current quantity of this FoodItem.
	 * @return quantity*/
	public int getQuantity() {
		return quantity.get();
	}
	
	/**Sets the current quantity of this FoodItem.
	 * @param item_quantity - int*/
	public void setQuantity(int item_quantity) {
		quantity.set(item_quantity);
	}
	
	/**Gets the expected stock of this FoodItem
	 * @return int*/
	public int getStock() {
		return stock.get();
	}
	
	/**Sets the expected stock of this FoodItem.
	 * @param item_stock - int*/
	public void setStock(int item_stock) {
		stock.set(item_stock);
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
		return supplier.getFee() + supplier.getSTDQty() * supplier.getSTDCost();
	}
}
