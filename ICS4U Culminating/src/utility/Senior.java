package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Senior extends Person {

    private final SimpleStringProperty DOE;
    private final SimpleLongProperty hID, chequing;
    private SimpleDoubleProperty careHours;	//number of hours of care to receive per week
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

        DOE = new SimpleStringProperty();
        DOE.set(doe);

        this.hID = new SimpleLongProperty();
        this.hID.set(hID);

        this.chequing = new SimpleLongProperty();
        this.chequing.set(chequing);

        careHours = new SimpleDoubleProperty();
        careHours.set(0);
    }

    /**
     * Gets the date of entry of the Senior into the residence.
     *
     * @return long - The value of the property DOE.
     */
    public String getDOE() {
        return DOE.get();
    }

    /**
     * @return long - ID of the Senior.
     */
    public long getHomeID() {
        return hID.get();
    }

    /**
     * @return long - The chequing account number of the Senior.
     */
    public long getAccountNumber() {
        return chequing.get();
    }

    /**
     * @return double - The hours cared for the Senior.
     */
    public double getHours() {
        return careHours.get();
    }

    /**
     * @param date - The date of entry of the Senior.
     */
    public void setDOE(String date) {
        DOE.set(date);
    }

    /**
     * @param hID - The ID of the Senior.
     */
    public void setHomeID(long hID) {
        this.hID.set(hID);
    }

    /**
     * @param chequing - The chequing account number of the Senior.
     */
    public void setAccountNumber(long chequing) {
        this.chequing.set(chequing);
    }

    /**
     * @param hours - The number of hours the Senior is cared for.
     */
    public void setHours(double hours) {
        careHours.set(hours);
    }

    /**
     * This method resets the number of hours cared for the citizen to 0.
     */
    public void resetHours() {
        careHours.set(0);
    }
    /**
     * 
     * @param s - The array of seniors. 
     */
    public static void sortNum(Senior [] s) {
        for (int i = 0; i < s.length; i++) {
            Senior holder = s[i];
            int j = i - 1;
            while (j >= 0 && s[j].getHomeID() > holder.getHomeID()) {
                s[j + 1] = s[j];
                j--;
            }
            s[j + 1] = holder;
        }
    }
    
   /**
    * 
    * @param ID - The home ID you want to search for.
    * @param s - The array of seniors.
    * @param l - The farthest left of the senior array (usually 0).
    * @param r - The farthest right of the senior array (usually length of array).
    * @return Senior - The senior with the corresponding home ID.
    */
    public static Senior searchHomeID(long ID, Senior [] s, int l, int r){
        Senior.sortNum(s);
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (s[mid].getHomeID() == ID) {
                return s[mid];  
            }

            if (s[mid].getHomeID() > ID ) {
                return searchHomeID(ID,s, l,mid-1 );
            }


            return searchHomeID(ID, s,mid + 1, r);
        }

        return null;
        
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
