package utility;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Suite {
	
	/**Enum-type with typesafe constants for suite style.
	 * SINGLE: One bedroom
	 * DOUBLE: Two bedrooms
	 * SINGLE_KITCHEN: One bedroom with a kitchen
	 * DOUBLE_KITCHEN: Two bedrooms with a kitchen*/
	public enum Type {
		SINGLE(2, "SINGLE", 1500, 7.50), DOUBLE(4, "DOUBLE", 3000, 15.00), SINGLE_KITCHEN(3, "SINGLE KITCHEN", 3000, 25.00), 
		DOUBLE_KITCHEN(5, "DOUBLE KITCHEN", 5000, 30.00);
		
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
		
		/**Gets the monthly cost of this Suite based on Type.*/
		public double getMonthlyCost() {
			return monthlyCost;
		}
		
		/**Gets the fee multiple (head count hourly) for the Suite.*/
		public double getFeeMultiple() {
			return feeMultiple;
		}
	}
	
	private BooleanProperty presiderIsIn;
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
		
		presiderIsIn = new SimpleBooleanProperty();
		presiderIsIn.set(false);
	}
	
	/**Returns the integer property for the number of occupants in this Suite.
	 * @return IntegerProperty*/
	public IntegerProperty numberOfOccupantsProperty() {
		return numberOfOccupants;
	}
	
	/**Gets the number of occupants currently in the Suite.
	 * @return int*/
	public int getNumberOfOccupants() {
		return numberOfOccupants.get();
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
	
	/**Gets the Type of this Suite based on its style name.
	 * @param name - The name of the style.*/
	public static Type getTypeByName(String name) {
		if (name.equals("SINGLE")) {
			return Type.SINGLE;
		} else if (name.equals("DOUBLE")) {
			return Type.DOUBLE;
		} else if (name.equals("SINGLE KITCHEN")) {
			return Type.SINGLE_KITCHEN;
		} else if (name.equals("DOUBLE KITCHEN")) {
			return Type.DOUBLE_KITCHEN;
		}
		return null;
	}
	
	/**Gets the style name.
	 * @return String*/
	public String getStyleName() {
		return styleName.get();
	}
	
	/**Sets the style name.
	 * @param styleName - The style name of the Suite.*/
	public void setStyleName(String styleName) {
		this.styleName.set(styleName);
	}
	
	/**Gets the presiding Caregiver of the suite.
	 * @return presider - The presider responsible for the Seniors in the suite.*/
	public Caregiver getPresider() {
		return presider;
	}
	
	/**Sets the presider of this Suite (for null carryover) */
	public void setPresider(Caregiver presider) {
		this.presider = presider;
	}
	
	/**Sets the presiding Caregiver of the suite, and sets the Caregiver to status assigned and the suite to 
	 * covered (non-null instances).
	 * @param presider - The Caregiver assigned to the suite.*/
	public void addPresider(Caregiver presider) {
		this.presider = presider;
		this.presider.setAssigned(true);
		presiderIsIn.set(true);
	}
	
	/**Removes the presiding Caregiver from this Suite.*/
	public void removePresider() {
		this.presider.setAssigned(false);
		this.presider = null;
		presiderIsIn.set(false);
	}
	
	/**Determines whether this Suite has a presider or not.
	 * @return boolean*/
	public boolean getPresiderIsIn() {
		return presiderIsIn.get();
	}
	
	/**Sets whether a presider is covering this Suite or not.*/
	public void setPresiderIsIn(boolean presiderIsIn) {
		this.presiderIsIn.set(presiderIsIn);
	}
	
	/**Returns the value of the boolean property for presider resident state.
	 * @return BooleanProperty*/
	public BooleanProperty presiderIsInProperty() {
		return presiderIsIn;
	}
	
	/**Checks whether the Senior object exists in the Suite.
	 * @return boolean*/
	public boolean occupantExists(Senior senior) {
		for (int i = 0; i < occupants.length; i++) {
			if (occupants[i].getHID() == senior.getHID()) {
				return true;
			}
		}
		return false;
	}
	
	/**Checks whether a Caregiver is assigned to this room or not.
	 * @return boolean*/
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
            senior.setInside(true);
            
			System.out.println("Senior add attempted.");
			return true;
			
		} else { 
			System.out.println("Senior add failed. This suite has reached maximum occupants.");
			return false;
		}
	}
	
	/**
	* Adds an occupant.
	* @param senior - The senior to add.
	* @return boolean - true if everything is exceuted succesfully.
	*/
	public boolean addOccupant(Senior senior) {
		if (this.suiteStyle.getMaximum() > occupants.length) {
			Senior[] temp = occupants.clone();
			occupants = new Senior[temp.length + 1];
			
			for (int i = 0; i < temp.length; i++) {
				occupants[i] = temp[i];
			}
			
			occupants[occupants.length - 1] = senior;
                        numberOfOccupants.set(numberOfOccupants.get() + 1);
			System.out.println("Senior add attempted.");
			senior.setInside(true);
			return true;
		} else {
			System.out.println("Senior add failed. This suite has reached maximum occupants.");
			return false;
		}
	}
	
	/**
	* Adds occupants.
	*@param seniors - The seniors to add.
	*@return boolean - true if everything is executed correctly.
	*/
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
	
	/**
	*Removes occupants.
	*@param home_id - The home ID of the occupant to remove.
	*@return boolean - true if everything is executed correctly.
	*/
	public boolean removeOccupant(long home_id) {
		for (int i = 0; i < occupants.length; i++) {
			if (occupants[i] != null && occupants[i].getHID() == home_id) {
				occupants[i].setInside(false);
				occupants[i] = null;
                numberOfOccupants.set(numberOfOccupants.get() - 1);
				return true;
			}
		}
		return false;
	}
	
	/**
	*Checks if the home ID exists in the suite.
	*@param homeid - The home ID of a senior.
	*@return boolean - true if it does exist.
	*/
	public boolean homeIDExists(long homeid) {
		if (occupants.length > 0 && Senior.searchHomeID(homeid, occupants, 0, occupants.length) != null) {
			return true;
		}
		return false;
	}
	
	/**Gets the occupants list for this Suite.*/
	public Senior[] getOccupants() {
		return occupants;
	}
}
