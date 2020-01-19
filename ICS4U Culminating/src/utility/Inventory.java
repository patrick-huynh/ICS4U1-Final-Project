package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleDoubleProperty;

public class Inventory {
	private SimpleDoubleProperty month_budget; 
	private FoodItem[] inventory;
	private int size;
	private LocalDateTime orderTimestamp;
	
	/**Creates an empty Inventory object.*/
	public Inventory() {
		inventory = new FoodItem[0];
		size = 0;
		month_budget = new SimpleDoubleProperty();
		month_budget.set(20000);
		
		orderTimestamp = LocalDateTime.now();
	}
	
	public static void clear() {
		
	}
	
	/**Gets the size of the inventory.
	 * @return int*/
	public int getSize() {
		return size;
	}
	
	public double getMonthBudget() {
		return month_budget.get();
	}
	
	public void setMonthBudget(double budget) {
		month_budget.set(budget);
	}
	
	private boolean itemExists(String item_name) {
		for (int i = 0; i < size; i++) {
			if (inventory[i].getName().equalsIgnoreCase(item_name)) {
				return true;
			}
		}
		return false;
	}
	
	/**Adds a FoodItem to the Inventory.
	 * @param item_name - The name of the FoodItem.
	 * @param item-expiry - The expiry date of the FoodItem.
	 * @param group - The name of the FoodGroup for the FoodItem.
	 * @param item_quantity - The current quantity for the FoodItem.
	 * @param item_stock - The expected stock of the FoodItem in the Inventory.
	 * @return boolean*/
	public boolean addItem(String item_name, String item_expiry, FoodGroup group, int item_quantity, int item_stock) {
		if (!itemExists(item_name)) {
			FoodItem[] copy = inventory.clone();
			inventory = new FoodItem[copy.length + 1];
			
			for (int i = 0; i < copy.length; i++) {
				inventory[i] = copy[i];
			}
			
			inventory[inventory.length - 1] = new FoodItem(item_name, item_expiry, group, item_quantity, item_stock);
			size++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addItem(FoodItem item) {
		if(!itemExists(item.getName())) {
			FoodItem[] copy = inventory.clone();
			inventory = new FoodItem[copy.length + 1];
			
			for (int i = 0; i < copy.length; i++) {
				inventory[i] = copy[i];
			}
			
			inventory[inventory.length - 1] = item;
			size++;
			return true;
		} else {
			return false;
		}
	}
	
	/**Removes a FoodItem from the Inventory.
	 * @param item_name - The name of the FoodItem to be removed.*/
	public boolean removeItem(String item_name) {
		for (int i = 0; i < size; i++) {
			if (inventory[i].getName().equalsIgnoreCase(item_name)) {
				inventory[i] = null;
				size--;
				return true;
			}
		}
		//write to label that no such with item name exists
		return false;
	}
	
	/**Checks the Inventory and returns a FoodItem array containing all FoodItem objects with quantity less than expected
	 * stock.
	 * @return FoodItem[]*/
	public FoodItem[] belowExpected() {
		FoodItem[] below_stock = new FoodItem[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].isBelowExpected()) {
				FoodItem[] copy = below_stock.clone();
				below_stock = new FoodItem[copy.length + 1];
				
				for (int j = 0; j < copy.length; j++) {
					below_stock[j] = copy[j];
				}
				
				below_stock[below_stock.length - 1] = inventory[i];
			}
		}
		
		return below_stock;
	}
	
	/**Checks the Inventory and returns a FoodItem array containing FoodItem objects to expire at the given date.*/
	public FoodItem[] toExpire() {
		FoodItem[] to_expire = new FoodItem[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].getExpiryAsDate().compareTo(LocalDate.now()) <= 7 && 
					inventory[i].getExpiryAsDate().compareTo(LocalDate.now()) > 0) {
				FoodItem[] copy = to_expire.clone();
				to_expire = new FoodItem[copy.length + 1];
				
				for (int j = 0; j < copy.length; j++) {
					to_expire[j] = copy[j];
				}
				
				to_expire[to_expire.length - 1] = inventory[i];
			}
		}
		return to_expire;
	}
	
	public FoodItem[] expired() {
		FoodItem[] expired = new FoodItem[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].getExpiryAsDate().compareTo(LocalDate.now()) < 0) {
				FoodItem[] copy = expired.clone();
				expired = new FoodItem[copy.length + 1];
				
				for (int j = 0; j < copy.length; j++) {
					expired[j] = copy[j];
				}
				
				expired[expired.length - 1] = inventory[i];
			}
		}
		return expired;
	}
	
	/**Checks the Inventory and returns a FoodItem array containing FoodItem objects that are instances of the 
	 * specified FoodGroup.*/
	public FoodItem[] getAllInstancesOf(FoodGroup group) {
		FoodItem[] instances = new FoodItem[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].getGroup().getGroupName().equalsIgnoreCase(group.getGroupName())) {
				FoodItem[] copy = instances.clone();
				
				for (int j = 0; j < copy.length; j++) {
					instances[j] = copy[j];
				}
				
				instances[instances.length - 1] = inventory[i];
			}
		}
		
		return instances;
	}

	   /**
     * 
     * @param index - The index in the FoodItem array.
     * @return FoodItem - The food item at the corresponding index.
     */
    public FoodItem from(int index) {
        if (index < size) {
            return inventory [index];
        }
        return null;
    }
	
	/**Returns the timestamp as a String.
	 * @return String*/
	public String getTimeAsString() {
		return orderTimestamp.toString();
	}
	
	/**Gets the timestamp as a LocalDateTime object.
	 * @return LocalDateTime*/
	public LocalDateTime getTime() {
		return orderTimestamp;
	}
	
	public void order(double expenses) {
		month_budget.set(month_budget.get() - expenses);
		orderTimestamp = LocalDateTime.now();
	}
}
