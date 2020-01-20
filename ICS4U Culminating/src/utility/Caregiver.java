package utility;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Suite {
	
	/**Enum-type with typesafe constants for suite style.
	 * SINGLE: One bedroom
	 * DOUBLE: Two bedrooms
	 * SINGLE_KITCHEN: One bedroom with a kitchen
	 * DOUBLE_KITCHEN: Two bedrooms with a kitchen*/
	public enum Type {
		SINGLE(2, "SINGLE", 1500, 15), DOUBLE(4, "DOUBLE", 3000, 30), SINGLE_KITCHEN(3, "SINGLE KITCHEN", 3000, 30), 
		DOUBLE_KITCHEN(5, "DOUBLE KITCHEN", 5000, 50);
		
		private int maximum;
		private String typeName;
		private double monthlyCost, feeMultiple;
		
		/**Constructor for enum-type Type.
		 * @param maximum - The maximum number of occupants permitted in the suite.
		 * @param typeName - The name of the suite style.*/
		private Type(int maximum, String typeName, double monthlyCost, double feeMultiple) {
			this.maximum = maximum;
			this.typeName = typeName;
			this.monthlyCost = monthlyCost;
			this.feeMultiple = feeMultiple;
		}
		
		/**Gets the value of the enum property maximum.
		 * @return int - The maximum number of occupants permitted in the suite.*/
		public int getMaximum() {
			return maximum;
		}
		
		/**Gets the type name of this Type.*/
		public String getTypeName() {
			return typeName;
		}
		
		public double getMonthlyCost() {
			return monthlyCost;
		}
		
		public double getFeeMultiple() {
			return feeMultiple;
		}
	}
	
	private IntegerProperty numberOfOccupants;
	private final SimpleIntegerProperty suiteNumber;
	private final Type suiteStyle;
	private final SimpleStringProperty styleName;
	private Caregiver presider;
	private Senior[] occupants;
	
	/**Constructor for class Suite. Initializes an empty room with no presider.
	 * @param suiteNumber - The suite number for bookkeeping purposes.
	 * @param monthlyCost - The monthly cost of living and maintenance of the suite.
	 * @param suiteStyle - The Type of the suite.*/
	public Suite(int suiteNumber, Type suiteStyle) {
		this.suiteNumber = new SimpleIntegerProperty();
		this.suiteNumber.set(suiteNumber);
		
		this.suiteStyle = suiteStyle;
		styleName = new SimpleStringProperty();
		styleName.set(suiteStyle.getTypeName());
		
		occupants = new Senior[0];
                numberOfOccupants = new SimpleIntegerProperty();
                numberOfOccupants.set(0);
		presider = null;
	}
	
	public IntegerProperty numberOfOccupantsProperty() {
		return numberOfOccupants;
	}
	
	/**Gets the identification number of the suite.
	 * @return int - The suite number.*/
	public int getSuiteNumber() {
		return suiteNumber.get();
	}
	
	/**Sets the identification number of the suite.
	 * @param suiteNumber*/
	public void setSuiteNumber(Number suiteNumber) {
		this.suiteNumber.set(suiteNumber.intValue());
	}
	
	/**Gets the configuration of the suite.
	 * @return Type - The suite style.*/
	public Type getSuiteStyle() {
		return suiteStyle;
	}
	
	public String getStyleName() {
		return styleName.get();
	}
	
	public void setStyleName(String styleName) {
		this.styleName.set(styleName);
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
		presider.assigned = true;
	}
	
	public boolean occupantExists(Senior senior) {
		for (int i = 0; i < occupants.length; i++) {
			if (occupants[i].getHID() == senior.getHID()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presiderExists(Caregiver caregiver) {
		if (this.getPresider() != null) {
			return true;
		}
		return false;
	}

	/**Adds an occupant to the suite.*/
	public boolean addOccupant(String fname, String lname, String dob, int age, int roomID,
			double hours, long hID) {
		if (this.suiteStyle.getMaximum() > occupants.length) {
			Senior[] temp = occupants.clone();
			occupants = new Senior[temp.length + 1];
			
			for (int i = 0; i < temp.length; i++) {
				occupants[i] = temp[i];
			}
			
			Senior senior = new Senior(fname, lname, dob, age, roomID, hours, hID);
			occupants[occupants.length - 1] = senior;
            numberOfOccupants.set(numberOfOccupants.get() + 1);
            senior.inside = true;
            
			System.out.println("Senior added successfully.");
			return true;
			
		} else { 
			System.out.println("Senior add failed. This suite has reached maximum occupants.");
			return false;
		}
	}
	
	public boolean addOccupant(Senior senior) {
		if (this.suiteStyle.getMaximum() > occupants.length) {
			Senior[] temp = occupants.clone();
			occupants = new Senior[temp.length + 1];
			
			for (int i = 0; i < temp.length; i++) {
				occupants[i] = temp[i];
			}
			
			occupants[occupants.length - 1] = senior;
                        numberOfOccupants.set(numberOfOccupants.get() + 1);
			System.out.println("Senior added successfully.");
			return true;
		} else {
			System.out.println("Senior add failed. This suite has reached maximum occupants.");
			return false;
		}
	}
	
	/***/
	public boolean addOccupants(Senior...seniors) {
		if (this.suiteStyle.getMaximum() >= occupants.length + seniors.length) {
			Senior[] temp = occupants.clone();
			occupants = new Senior[temp.length + seniors.length];
			
			for (int i = 0; i < temp.length; i++) {
				occupants[i] = temp[i];
			}
			
			for (int i = 0; i < seniors.length; i++) {
				occupants[temp.length + i] = seniors[i];
			}
			
                        numberOfOccupants.set(numberOfOccupants.get() + seniors.length);
			return true;
		}  else {
			return false;
		}
	}
	
	/***/
	public boolean removeOccupant(long home_id) {
		for (int i = 0; i < occupants.length; i++) {
			if (occupants[i] != null && occupants[i].getHID() == home_id) {
				occupants[i] = null;
                numberOfOccupants.set(numberOfOccupants.get() - 1);
				return true;
			}
		}
		return false;
	}
	
	public boolean homeIDExists(long homeid) {
		if (occupants.length > 0 && Senior.searchHomeID(homeid, occupants, 0, occupants.length) != null) {
			return true;
		}
		return false;
	}
	
	public boolean empNumExists(long empNum) {
		if (presider.getEmpNum() == empNum) {
			return true;
		} 
		return false;
	}
}
