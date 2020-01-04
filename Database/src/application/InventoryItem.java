package application;

import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {

	//Declare simplestringproperty class-only visible fields with final modifier
	private final SimpleStringProperty ingredient;
	private final SimpleStringProperty rMealID;
	private final SimpleStringProperty quantity;

	/*Omit default no-args constructor; each InventoryItem has an associated quantity and ingredient referenced to the
	String properties that define the class object state*/
    InventoryItem(String nIngredient, String nRMealID, String nQuantity) {
    	this.ingredient = new SimpleStringProperty(nIngredient);
    	this.rMealID = new SimpleStringProperty(nRMealID);
    	this.quantity = new SimpleStringProperty(nQuantity);
    }
    
    /*Getters and setters for class properties; cooperate with tableview instantiation. All getters abd setters
     *are made public so that class-accessible fields are open to the world*/
     
    public String getIngredient() {
    	return ingredient.get();
    }
    
    public void setIngredient(String nIngredient) {
    	ingredient.set(nIngredient);
    }
    
    public String getRMealID() {
    	return rMealID.get();
    }
    
    public void setRMealID(String nRMealID) {
    	rMealID.set(nRMealID);
    }
    
    public String getQuantity() {
    	return quantity.get();
    }
    
    public void setQuantity(String nQuantity) {
    	quantity.set(nQuantity);
    }
    
}