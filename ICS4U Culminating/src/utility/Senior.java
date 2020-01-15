package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Senior extends Person {

    private final SimpleStringProperty DOE;
    private final SimpleLongProperty hID;
    private SimpleDoubleProperty hours;	

    /**
     * Constructor for class Senior.
     * @param doe - The date of entry of the Senior object into the retirement residence.
     * @param hID - The ID of the Senior object while in the retirement residence.
     */
    public Senior(String fname, String lname, String DOB, int age, int roomID, String DOE,
            long hID) {
        super(fname, lname, DOB, age, roomID);

        this.DOE = new SimpleStringProperty();
        this.DOE.set(DOE);

        this.hID = new SimpleLongProperty();
        this.hID.set(hID);

        hours = new SimpleDoubleProperty();
        hours.set(0);
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
     * @param date - The date of entry of the Senior.
     */
    public void setDOE(String DOE) {
        this.DOE.set(DOE);
    }
    
    /**
     * @return long - ID of the Senior.
     */
    public long getHID() {
        return hID.get();
    }
    
    /**
     * @param hID - The ID of the Senior.
     */
    public void setHID(Number hID) {
        this.hID.set(hID.longValue());
    }

    /**
     * @return double - The hours cared for the Senior.
     */
    public double getHours() {
        return hours.get();
    }

    /**
     * @param hours - The number of hours the Senior is cared for.
     */
    public void setHours(Number hours) {
        this.hours.set(hours.doubleValue());
    }

    /**
     * This method resets the number of hours cared for the citizen to 0.
     */
    public void resetHours() {
        hours.set(0);
    }
    /**
     * 
     * @param s - The array of seniors. 
     */
    public static void sortHomeID(Senior[] s) {
        for (int i = 0; i < s.length; i++) {
            Senior holder = s[i];
            int j = i - 1;
            while (j >= 0 && s[j].getHID() > holder.getHID()) {
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
        Senior.sortHomeID(s);
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (s[mid].getHID() == ID) {
                return s[mid];  
            }

            if (s[mid].getHID() > ID ) {
                return searchHomeID(ID,s, l,mid-1 );
            }


            return searchHomeID(ID, s,mid + 1, r);
        }

        return null;
        
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Date of Entry: " + getDOE() + "\n"
                + "Home ID Number: " + getHID() + "\n"
                + "Hours Cared: " + getHours();
    }
}
