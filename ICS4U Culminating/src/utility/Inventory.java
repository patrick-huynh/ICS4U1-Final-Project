package utility;

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
	
	public boolean itemExists(String item_name) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].getItemName().equalsIgnoreCase(item_name)) {
				return true;
			}
		}
		return false;
	}
	
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
			//write to label that item already exists in inventory
			return false;
		}
	}
	
	public boolean removeItem(String item_name) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].getItemName().equalsIgnoreCase(item_name)) {
				inventory[i] = null;
				size--;
				return true;
			}
		}
		//write to label that no such with item name exists
		return false;
	}
	
	/*public FoodItem[] belowExpected() {
		FoodItem[] below_stock = new FoodItem[0];
		
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getItemQuantity() < inventory[i].getItemStock()) {
		
			}
		}
	}*/
}
