package utility;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Suite {
	
	/**Enum-type with typesafe constants for suite style.
	 * SINGLE: One bedroom
	 * DOUBLE: Two bedrooms
	 * SINGLE_KITCHEN: One bedroom with a kitchen
	 * DOUBLE_KITCHEN: Two bedrooms with a kitchen*/
	public enum Type {
		SINGLE(2), DOUBLE(4), SINGLE_KITCHEN(3), DOUBLE_KITCHEN(5);
		
		private int maximum;
		
		/**Constructor for enum-type Type.
		 * @param maximum - The maximum number of occupants permitted in the suite.*/
		private Type(int maximum) {
			this.maximum = maximum;
		}
		
		/**Gets the value of the enum property maximum.
		 * @return int - The maximum number of occupants permitted in the suite.*/
		public int getMaxCapacity() {
			return maximum;
		}
	}
	
	private final SimpleIntegerProperty suite_number;
	private final SimpleDoubleProperty monthly_cost;	//amount it costs to house each occupant per month
	private final Type suite_style;
	private Caregiver presider;
	private Senior[] occupants;
	
	/**Constructor for class Suite. Initializes an empty room with no presider.
	 * @param number - The suite number for bookkeeping purposes.
	 * @param cost - The monthly cost of living and maintenance of the suite.
	 * @param suite_style - The Type of the suite.*/
	public Suite(int number, int cost, Type suite_style) {
		suite_number = new SimpleIntegerProperty();
		suite_number.set(number);
		
		monthly_cost = new SimpleDoubleProperty();
		monthly_cost.set(cost);
		
		this.suite_style = suite_style;
		occupants = new Senior[0];
		presider = null;
	}
	
	/**Gets the identification number of the suite.
	 * @return int - The suite number.*/
	public int getSuiteNumber() {
		return suite_number.get();
	}
	
	/**Gets the configuration of the suite.
	 * @return Type - The suite style.*/
	public Type getType() {
		return suite_style;
	}
	
	/**Gets the presiding Caregiver of the suite.
	 * @return presider - The presider responsible for the Seniors in the suite.*/
	public Caregiver getPresider() {
		return presider;
	}
	
	/**Sets the presiding Caregiver of the suite.
	 * @param presider - The Caregiver assigned to the suite.*/
	public void setPresider(Caregiver presider) {
		this.presider = presider;
	}

	/**Adds an occupant to the suite.*/
	public boolean addOccupant(String fname, String lname, String dob, double age, double height, double weight, 
			String doe, long home_id, long insurance_number) {
		if (this.suite_style.getMaxCapacity() > occupants.length) {
			Senior[] temp = occupants.clone();
			occupants = new Senior[temp.length + 1];
			
			for (int i = 0; i < temp.length; i++) {
				occupants[i] = temp[i];
			}
			
			occupants[occupants.length - 1] = new Senior(fname, lname, dob, age, height, weight, doe, home_id, 
					insurance_number);		
			return true;
			
		} else { 
			//write to a label or trigger a system alert stating that number of occupants exceeds
			/*e.g. if in our runnable class there is a Label called occupant_error, here we would write
			 * occupant_error.setText("The number of occupants exceeds ... etc.")*/
			return false;
		}
	}
	
	/***/
	public boolean addOccupants(Senior...seniors) {
		if (this.suite_style.getMaxCapacity() >= occupants.length + seniors.length) {
			Senior[] temp = occupants.clone();
			occupants = new Senior[temp.length + seniors.length];
			
			for (int i = 0; i < temp.length; i++) {
				occupants[i] = temp[i];
			}
			
			for (int i = 0; i < seniors.length; i++) {
				occupants[temp.length + i] = seniors[i];
			}
			
			return true;
		}  else {
			//write to a label or trigger a system alert stating that number of occupants exceeds
			return false;
		}
	}
	
	/***/
	public boolean removeOccupant(long home_id) {
		for (int i = 0; i < occupants.length; i++) {
			if (occupants[i] != null && occupants[i].getHomeID() == home_id) {
				occupants[i] = null;
				return true;
			}
		}
		//communicate to a label or alert that no such occupant with the home id exists
		return false;
	}
	
	//Yearly cost with no aid or benefits applied. Is equal to the GROSS cost for ALL seniors
	public double computeGrossYearlyCost() {
		return monthly_cost.get() * occupants.length * 12;
	}
}
