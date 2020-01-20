package utility;

import javafx.beans.property.SimpleLongProperty;

import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;

public class Senior extends Person {

    private final SimpleLongProperty hID;
    private SimpleDoubleProperty hours, monthlyPayment;	
    public boolean inside;
    
    /**
     * Constructor for Senior class.
     * @param roomID - The room ID that the senior is staying in.
     * @param hours - The number of care hours the Senior requires on a daily basis.
     * @param hID - The ID of the Senior object while in the retirement residence.
     */
    public Senior(String fname, String lname, String DOB, int age, int roomID, 
            double hours, long hID) {
        super(fname, lname, DOB, age, roomID);

        this.hID = new SimpleLongProperty();
        this.hID.set(hID);

        this.hours = new SimpleDoubleProperty();
        this.hours.set(0);
        
        monthlyPayment = new SimpleDoubleProperty();
        
        if (roomID > 0 && roomID <= 3) {
        	monthlyPayment.set(Suite.Type.SINGLE.getFeeMultiple() * hours * 30 + 
        				Suite.Type.SINGLE.getMonthlyCost());
        } else if (roomID > 3 && roomID <= 6) {
        	monthlyPayment.set(Suite.Type.DOUBLE.getFeeMultiple() * hours * 30 +
        			Suite.Type.DOUBLE.getMonthlyCost());
        } else if (roomID > 6 && roomID <= 9) {
        	monthlyPayment.set(Suite.Type.SINGLE_KITCHEN.getFeeMultiple() * hours * 30 +
        			Suite.Type.SINGLE_KITCHEN.getMonthlyCost());
        } else if (roomID > 9 && roomID <= 12){
        	monthlyPayment.set(Suite.Type.DOUBLE_KITCHEN.getFeeMultiple() * hours * 30 +
        			Suite.Type.DOUBLE_KITCHEN.getMonthlyCost());
        } else {
            System.out.println("Room ID does not exist.");
        }
        
        inside = false;
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
    
    public double getMonthlyPayment() {
    	return monthlyPayment.get();
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
    
    public static void sortHomeID(List<Senior> s) {
    	for (int i = 0; i < s.size(); i++) {
    		Senior holder = s.get(i);
    		int j = i - 1;
    		while (j >- 0 && s.get(j).getHID() > holder.getHID()) {
    			s.set(j + 1, s.get(j));
    			j--;
    		}
    		s.set(j + 1, holder);
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
    
    public static Senior searchHomeID(long ID, List<Senior> s, int l, int r) {
    	Senior.sortHomeID(s);
    	if (r >= l) {
    		int mid = l + (r - l) / 2;
    		
    		if (s.get(mid).getHID() == ID) {
    			return s.get(mid);
    		}
    		
    		if (s.get(mid).getHID() > ID) {
    			return searchHomeID(ID, s, l, mid - 1);
    		}
    		return searchHomeID(ID, s, mid + 1, r);
    	}
    	return null;
    }

    @Override
    /**Returns a String representation of this Senior.
     * @return String*/
    public String toString() {
        return super.toString() + "\n"
                + "Home ID Number: " + getHID() + "\n"
                + "Hours Cared: " + getHours();
    }
}
