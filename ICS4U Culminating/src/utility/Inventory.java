package utility;

import java.time.LocalDate;

public class Inventory {
	private FoodItem[] inventory;
	private int size;
	
	public Inventory() {
		inventory = new FoodItem[0];
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	private boolean itemExists(String item_name) {
		for (int i = 0; i < size; i++) {
			if (inventory[i].getItemName().equalsIgnoreCase(item_name)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addItem(String item_name, LocalDate item_expiry, FoodGroup group, int item_quantity, int item_stock) {
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
			//write to label that item already exists in inventory
			return false;
		}
	}
	
	public boolean removeItem(String item_name) {
		for (int i = 0; i < size; i++) {
			if (inventory[i].getItemName().equalsIgnoreCase(item_name)) {
				inventory[i] = null;
				size--;
				return true;
			}
		}
		//write to label that no such with item name exists
		return false;
	}
	
	public FoodItem[] belowExpected() {
		FoodItem[] below_stock = new FoodItem[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].isBelowExpected()) {
				FoodItem[] copy = below_stock.clone();
				
				for (int j = 0; j < copy.length; j++) {
					below_stock[j] = copy[j];
				}
				
				below_stock[below_stock.length - 1] = inventory[i];
			}
		}
		
		return below_stock;
	}
	
	public FoodItem[] toExpire() {
		FoodItem[] to_expire = new FoodItem[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].getExpiryDate().equals(LocalDate.now())) {
				FoodItem[] copy = to_expire.clone();
				
				for (int j = 0; j < copy.length; j++) {
					to_expire[j] = copy[j];
				}
				
				to_expire[to_expire.length - 1] = inventory[i];
			}
		}
		return to_expire;
	}
	
	public FoodItem hasMostInStock() {
		FoodItem mostInStock = inventory[0];
		
		for (int i = 1; i < size; i++) {
			if (inventory[i] != null && inventory[i].getQuantity() > mostInStock.getQuantity()) {
				mostInStock = inventory[i];
			}
		}
		
		return mostInStock;
	}
	
	public FoodItem isMostExpensive() {
		FoodItem mostExpensive = inventory[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].computeSTDCost() > mostExpensive.computeSTDCost()) {
				mostExpensive = inventory[i];
			}
		}	
		
		return mostExpensive;
	}
	
	public FoodItem[] getAllInstancesOf(FoodGroup group) {
		FoodItem[] instances = new FoodItem[0];
		
		for (int i = 0; i < size; i++) {
			if (inventory[i] != null && inventory[i].getFoodGroup().getGroupName().equalsIgnoreCase(group.getGroupName())) {
				FoodItem[] copy = instances.clone();
				
				for (int j = 0; j < copy.length; j++) {
					instances[j] = copy[j];
				}
				
				instances[instances.length - 1] = inventory[i];
			}
		}
		
		return instances;
	}
	
	public double computeMeanCost() {
		double mean_cost = 0.00d;
		
		for (int i = 0; i < size; i++) {
			mean_cost += inventory[i].computeSTDCost();
		}
		
		return mean_cost / size; 
	}
}
