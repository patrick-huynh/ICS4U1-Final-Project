package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Senior extends Person {
	
	private final SimpleStringProperty date_of_entry;
	private final SimpleLongProperty home_id, insurance_number;
	private SimpleDoubleProperty care_hours;	//number of hours of care to receive per week
	//Maybe add bank account number, 
	
	/**Constructor for class Senior.
	 * @param doe - The date of entry of the Senior object into the retirement residence.
	 * @param hID - The ID of the Senior object while in the reitrement residence
	 * @param insurance - The Old Age Security (OAS) number of the Senior object*/
	public Senior(String fname, String lname, String dob, double age, double height, double weight, String doe,
			long hID, long insurance) {
		super(fname, lname, dob, age, height, weight);
		
		date_of_entry = new SimpleStringProperty();
		date_of_entry.set(doe);
		
		home_id = new SimpleLongProperty();
		home_id.set(hID);
		
		insurance_number = new SimpleLongProperty();
		insurance_number.set(insurance);
		
		care_hours = new SimpleDoubleProperty();
		care_hours.set(0);
	}
	
	/**Gets the date of entry of the Senior into the residence.
	 * @return long - The value of the property date_of_entry.*/
	public String getDateOfEntry() {
		return date_of_entry.get();
	}
	
	/***/
	public long getHomeID() {
		return home_id.get();
	}
	
	/***/
	public long getInsuranceNumber() {
		return insurance_number.get();
	}
	
	/***/
	public double getHours() {
		return care_hours.get();
	}
	
	/***/
	public void setHours(double hours) {
		care_hours.set(hours);
	}
	
	//SETTERS REQUIRED; FIELDS CANNOT BE FINAL
}
