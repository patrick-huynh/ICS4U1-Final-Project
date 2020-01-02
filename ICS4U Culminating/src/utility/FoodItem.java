package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class FoodItem {
	private SimpleStringProperty name, expiry_date; // change exp_date to DateTimeFormatter
	private FoodGroup group;
	private SimpleIntegerProperty quantity, stock;
	
	public FoodItem(String item_name, String item_expiry, FoodGroup group, int item_quantity, int item_stock) {
		name = new SimpleStringProperty();
		name.set(item_name);
		
		expiry_date = new SimpleStringProperty();
		expiry_date.set(item_expiry);
		
		this.group = group;
		
		quantity = new SimpleIntegerProperty();
		quantity.set(item_quantity);
		
		stock = new SimpleIntegerProperty();
		stock.set(item_stock);
	}
	
	public String getItemName() {
		return name.get();
	}
	
	public void setItemName(String item_name) {
		name.set(item_name);
	}
	
	public String getExpiryDate() {
		return expiry_date.get();
	}
	
	/*public boolean setExpiryDate(String item_expiry) {
		//Make DateTimeFormatter
	}*/
	
	public FoodGroup getFoodGroup() {
		return group;
	}
	
	public void setFoodGroup(String name, Supplier supplier) {
		group = new FoodGroup(name, supplier);
	}
	
	public int getItemQuantity() {
		return quantity.get();
	}
	
	public void setItemQuantity(int item_quantity) {
		quantity.set(item_quantity);
	}
	
	public int getItemStock() {
		return stock.get();
	}
	
	public void setItemStock(int item_stock) {
		stock.set(item_stock);
	}
}
