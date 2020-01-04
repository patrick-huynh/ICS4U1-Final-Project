package application;

import javafx.beans.property.SimpleStringProperty;

public class Meal {
	private final SimpleStringProperty mealID;
	private final SimpleStringProperty recipe;
	private final SimpleStringProperty category;
	private final SimpleStringProperty portion;
	private final SimpleStringProperty basePrice;
	
	Meal(String nMealID, String nRecipe, String nCategory, String nPortion, String nPrice) {
		this.mealID = new SimpleStringProperty(nMealID);
		this.recipe = new SimpleStringProperty(nRecipe);
		this.category = new SimpleStringProperty(nCategory);
		this.portion = new SimpleStringProperty(nPortion);
		this.basePrice = new SimpleStringProperty(nPrice);
	}
	
	public String getMealID() {
		return mealID.get();
	}
	
	public void setMealID(String nMealID) {
		mealID.set(nMealID);
	}
	
	public String getRecipe() {
		return recipe.get();
	}
	
	public void setRecipe(String nRecipe) {
		recipe.set(nRecipe);
	}
	
	public String getCategory() {
		return category.get();
	}
	
	public void setCategory(String nCategory) {
		category.set(nCategory);
	}
	
	public String getPortion() {
		return portion.get();
	}
	
	public void setPortion(String nPortion) {
		portion.set(nPortion);
	}
	
	public String getBasePrice() {
		return basePrice.get();
	}
	
	public void setBasePrice(String nBasePrice) {
		basePrice.set(nBasePrice);
	}
}
