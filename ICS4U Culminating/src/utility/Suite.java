package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Suite {

    /**
     * Enum-type with typesafe constants for suite style. SINGLE: One bedroom
     * DOUBLE: Two bedrooms SINGLE_KITCHEN: One bedroom with a kitchen
     * DOUBLE_KITCHEN: Two bedrooms with a kitchen
     */
    public enum Type {
        SINGLE(2, "SINGLE"), DOUBLE(4, "DOUBLE"), SINGLE_KITCHEN(3, "SINGLE_KITCHEN"),
        DOUBLE_KITCHEN(5, "DOUBLE_KITCHEN");

        private int maximum;
        private String typeName;

        /**
         * Constructor for enum-type Type.
         *
         * @param maximum - The maximum number of occupants permitted in the
         * suite.
         * @param typeName - The name of the suite style.
         */
        private Type(int maximum, String typeName) {
            this.maximum = maximum;
            this.typeName = typeName;
        }

        /**
         * Gets the value of the enum property maximum.
         *
         * @return int - The maximum number of occupants permitted in the suite.
         */
        public int getMaximum() {
            return maximum;
        }

        /**
         * Gets the type name of this Type.
         */
        public String getTypeName() {
            return typeName;
        }
    }

    private final SimpleIntegerProperty suiteNumber;
    private final SimpleDoubleProperty monthlyCost;	//amount it costs to house each occupant per month
    private final Type suiteStyle;
    private final SimpleStringProperty styleName;
    private Caregiver presider;
    private Senior[] occupants;

    /**
     * Constructor for class Suite. Initializes an empty room with no presider.
     *
     * @param suiteNumber - The suite number for bookkeeping purposes.
     * @param monthlyCost - The monthly cost of living and maintenance of the
     * suite.
     * @param suiteStyle - The Type of the suite.
     */
    public Suite(int suiteNumber, int monthlyCost, Type suiteStyle) {
        this.suiteNumber = new SimpleIntegerProperty();
        this.suiteNumber.set(suiteNumber);

        this.monthlyCost = new SimpleDoubleProperty();
        this.monthlyCost.set(monthlyCost);

        this.suiteStyle = suiteStyle;
        styleName = new SimpleStringProperty();
        styleName.set(suiteStyle.getTypeName());

        occupants = new Senior[0];
        presider = null;
    }

    /**
     * Gets the identification number of the suite.
     *
     * @return int - The suite number.
     */
    public int getSuiteNumber() {
        return suiteNumber.get();
    }

    /**
     * Sets the identification number of the suite.
     *
     * @param suiteNumber
     */
    public void setSuiteNumber(Number suiteNumber) {
        this.suiteNumber.set(suiteNumber.intValue());
    }

    /**
     * Gets the configuration of the suite.
     *
     * @return Type - The suite style.
     */
    public Type getSuiteStyle() {
        return suiteStyle;
    }

    public String getStyleName() {
        return styleName.get();
    }

    public void setStyleName(String styleName) {
        this.styleName.set(styleName);
    }

    public double getMonthlyCost() {
        return monthlyCost.get();
    }

    public void setMonthlyCost(Number monthlyCost) {
        this.monthlyCost.set(monthlyCost.doubleValue());
    }

    /**
     * Gets the presiding Caregiver of the suite.
     *
     * @return presider - The presider responsible for the Seniors in the suite.
     */
    public Caregiver getPresider() {
        return presider;
    }

    /**
     * Sets the presiding Caregiver of the suite.
     *
     * @param presider - The Caregiver assigned to the suite.
     */
    public void setPresider(Caregiver presider) {
        this.presider = presider;
    }

    /**
     * Adds an occupant to the suite.
     */
    public boolean addOccupant(String fname, String lname, String dob, int age, int roomID,
            String doe, long hID) {
        if (this.suiteStyle.getMaximum() > occupants.length) {
            Senior[] temp = occupants.clone();
            occupants = new Senior[temp.length + 1];

            for (int i = 0; i < temp.length; i++) {
                occupants[i] = temp[i];
            }

            occupants[occupants.length - 1] = new Senior(fname, lname, dob, age, roomID, doe, hID);
            return true;

        } else {
            //write to a label or trigger a system alert stating that number of occupants exceeds
            /*e.g. if in our runnable class there is a Label called occupant_error, here we would write
			 * occupant_error.setText("The number of occupants exceeds ... etc.")*/
            return false;
        }
    }

    /**
     * 
     */
    public boolean addOccupants(Senior... seniors) {
        if (this.suiteStyle.getMaximum() >= occupants.length + seniors.length) {
            Senior[] temp = occupants.clone();
            occupants = new Senior[temp.length + seniors.length];

            for (int i = 0; i < temp.length; i++) {
                occupants[i] = temp[i];
            }

            for (int i = 0; i < seniors.length; i++) {
                occupants[temp.length + i] = seniors[i];
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     */
    public boolean removeOccupant(long home_id) {
        for (int i = 0; i < occupants.length; i++) {
            if (occupants[i] != null && occupants[i].getHID() == home_id) {
                occupants[i] = null;
                return true;
            }
        }
        return false;
    }

    public double computeWeeklyEarnings() {
        return presider.getWage() * presider.getHours();
    }

    public double computeMonthlyEarnings() {
        return computeWeeklyEarnings() * 4;
    }

    public double computeYearlyEarnings() {
        return computeMonthlyEarnings() * 12;
    }

    public double computeYearlyCost() {
        return monthlyCost.get() * occupants.length * 12;
    }
}
