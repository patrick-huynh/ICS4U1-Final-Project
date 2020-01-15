package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleDoubleProperty;

public class Inventory {

    private SimpleDoubleProperty month_budget;
    private FoodItem[] inventory;
    private int size;
    private LocalDateTime orderTimestamp;

    /**
     * Creates an empty Inventory object.
     */
    public Inventory() {
        inventory = new FoodItem[0];
        size = 0;
    }

    /**
     * Creates an empty Inventory object with a monthly-budget.
     *
     * @param month_budget The monthly budget allocated to dining and food services.
     */
    public Inventory(double month_budget) {
        this();
        this.month_budget.set(month_budget);
    }

    /**
     * Gets the size of the inventory.
     *
     * @return int
     */
    public int getSize() {
        return size;
    }

    private boolean itemExists(String item_name) {
        for (int i = 0; i < size; i++) {
            if (inventory[i].getName().equalsIgnoreCase(item_name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a FoodItem to the Inventory.
     *
     * @param item_name - The name of the FoodItem.
     * @param item-expiry - The expiry date of the FoodItem.
     * @param group - The name of the FoodGroup for the FoodItem.
     * @param item_quantity - The current quantity for the FoodItem.
     * @param item_stock - The expected stock of the FoodItem in the Inventory.
     * @return boolean
     */
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

    /**
     * Removes a FoodItem from the Inventory.
     *
     * @param item_name - The name of the FoodItem to be removed.
     */
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

    /**
     * Checks the Inventory and returns a FoodItem array containing all FoodItem
     * objects with quantity less than expected stock.
     *
     * @return FoodItem[]
     */
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

    /**
     * Checks the Inventory and returns a FoodItem array containing FoodItem
     * objects to expire at the given date.
     */
    public FoodItem[] toExpire(LocalDate time) {
        FoodItem[] to_expire = new FoodItem[0];

        for (int i = 0; i < size; i++) {
            if (inventory[i] != null && inventory[i].getExpiryDate().equals(time)) {
                FoodItem[] copy = to_expire.clone();

                for (int j = 0; j < copy.length; j++) {
                    to_expire[j] = copy[j];
                }

                to_expire[to_expire.length - 1] = inventory[i];
            }
        }
        return to_expire;
    }

    /**
     * Gets the FoodItem in the inventory that has the greatest current stock.
     *
     * @return FoodItem
     */
    public FoodItem hasMostInStock() {
        FoodItem mostInStock = inventory[0];

        for (int i = 1; i < size; i++) {
            if (inventory[i] != null && inventory[i].getQuantity() > mostInStock.getQuantity()) {
                mostInStock = inventory[i];
            }
        }

        return mostInStock;
    }

    /**
     * Gets the FoodItem that has the highest cost.
     *
     * @return FoodItem
     */
    public FoodItem isMostExpensive() {
        FoodItem mostExpensive = inventory[0];

        for (int i = 0; i < size; i++) {
            if (inventory[i] != null && inventory[i].computeSTDCost() > mostExpensive.computeSTDCost()) {
                mostExpensive = inventory[i];
            }
        }

        return mostExpensive;
    }

    /**
     * Checks the Inventory and returns a FoodItem array containing FoodItem
     * objects that are instances of the specified FoodGroup.
     */
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
     * Computes the mean cost for all the FoodItem objects in the Inventory to
     * two decimal places.
     *
     * @return double
     */
    public double computeMeanCost() {
        double mean_cost = 0.00d;

        for (int i = 0; i < size; i++) {
            mean_cost += inventory[i].computeSTDCost();
        }

        return Math.round(mean_cost / size * 100.0) / 100.0;
    }

    /**
     * Returns the timestamp as a String.
     *
     * @return String
     */
    public String getTimeAsString() {
        return orderTimestamp.toString();
    }

    /**
     * Gets the timestamp as a LocalDateTime object.
     *
     * @return LocalDateTime
     */
    public LocalDateTime getTime() {
        return orderTimestamp;
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

    /*public boolean order() {
	 * cost box with radio buttons
	 * check all checked, map each choice with a food item
	 * get food item, get supplier, computeSTD cost
	 * add to running total
	 * compare total to month budget
	 * if less, allow
	 * ask to remove so that less
		sets the order time stamp as well
	}*/
}
