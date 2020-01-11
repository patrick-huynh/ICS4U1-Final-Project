package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Senior extends Person {

    private final SimpleStringProperty date_of_entry;
    private final SimpleLongProperty home_id, chequing_acc;
    private SimpleDoubleProperty care_hours;	//number of hours of care to receive per week
    //Maybe add bank account number,

    /**
     * Constructor for class Senior.
     *
     * @param doe - The date of entry of the Senior object into the retirement
     * residence.
     * @param hID - The ID of the Senior object while in the retirement
     * residence
     * @param insurance - The Old Age Security (OAS) number of the Senior object
     */
    public Senior(String fname, String lname, String dob, int age, double height, double weight, String gender, String doe,
            long hID, long chequing) {
        super(fname, lname, dob, age, height, weight, gender);

        date_of_entry = new SimpleStringProperty();
        date_of_entry.set(doe);

        home_id = new SimpleLongProperty();
        home_id.set(hID);

        chequing_acc = new SimpleLongProperty();
        chequing_acc.set(chequing);

        care_hours = new SimpleDoubleProperty();
        care_hours.set(0);
    }

    /**
     * Gets the date of entry of the Senior into the residence.
     *
     * @return long - The value of the property date_of_entry.
     */
    public String getDOE() {
        return date_of_entry.get();
    }

    /**
     * @return long - ID of the Senior.
     */
    public long getHomeID() {
        return home_id.get();
    }

    /**
     * @return long - The chequing account number of the Senior.
     */
    public long getAccountNumber() {
        return chequing_acc.get();
    }

    /**
     * @return double - The hours cared for the Senior.
     */
    public double getHours() {
        return care_hours.get();
    }

    /**
     * @param date - The date of entry of the Senior.
     */
    public void setDOE(String date) {
        date_of_entry.set(date);
    }

    /**
     * @param hID - The ID of the Senior.
     */
    public void setHomeID(long hID) {
        home_id.set(hID);
    }

    /**
     * @param chequing - The chequing account number of the Senior.
     */
    public void setAccountNumber(long chequing) {
        chequing_acc.set(chequing);
    }

    /**
     * @param hours - The number of hours the Senior is cared for.
     */
    public void setHours(double hours) {
        care_hours.set(hours);
    }

    /**
     * This method resets the number of hours cared for the citizen to 0.
     */
    public void resetHours() {
        care_hours.set(0);
    }

    public String toString() {
        return super.toString() + "\n"
                + "Date of Entry: " + getDOE() + "\n"
                + "Home ID Number: " + getHomeID() + "\n"
                + "Chequing Account: " + getAccountNumber() + "\n"
                + "Hours Cared: " + getHours();

        //SETTERS REQUIRED; FIELDS CANNOT BE FINAL
    }
}
