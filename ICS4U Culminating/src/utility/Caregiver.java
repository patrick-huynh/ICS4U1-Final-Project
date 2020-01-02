package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;

public class Caregiver extends Person {

	private SimpleStringProperty title;
	private final SimpleLongProperty employee_number;
	private boolean status; //supervisory status
	
	public Caregiver(String fname, String lname, String dob, double age, double height, double weight,
			long empnum) {
		super(fname, lname, dob, age, height, weight);
		employee_number = new SimpleLongProperty();
		employee_number.set(empnum);
	}
}
