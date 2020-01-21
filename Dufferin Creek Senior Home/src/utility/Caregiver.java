package utility;

import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;

public class Caregiver extends Person {

    private final SimpleLongProperty empNum;
    private final SimpleDoubleProperty wage, hours, weekly_pay, monthly_pay, annual_pay;
    private boolean assigned;
    
    /**
     * Constructor for class Caregiver.
     * @param empNum - The employee number.
	 * @param hours - The hours worked per week.
	 * @param wage - The hourly wage.	
     */
    public Caregiver(String fname, String lname, String DOB, int age, int roomID,
            long empNum, double hours, double wage) {
        super(fname, lname, DOB, age, roomID);

        this.empNum = new SimpleLongProperty();
        this.empNum.set(empNum);

        this.hours = new SimpleDoubleProperty();
        this.hours.set(hours);

        this.wage = new SimpleDoubleProperty();
        this.wage.set(wage);
        
        weekly_pay = new SimpleDoubleProperty();
        weekly_pay.set(0);

        monthly_pay = new SimpleDoubleProperty();

        annual_pay = new SimpleDoubleProperty();
        assigned = false;
    }

    /**Gets the employee number of this Caregiver.
     * @return long*/
    public long getEmpNum() {
        return empNum.get();
    }

    /**Sets the employee number of this Caregiver.
     * @param empNum*/
    public void setEmpNum(Number empNum) {
        this.empNum.set(empNum.longValue());
    }
    
    /**Checks whether or not a Caregiver is already assigned to a Suite.
     * @return boolean*/
    public boolean isAssigned() {
    	return assigned;
    }
    
    /**Sets the value of the assigned property.
     * @param assigned - The resident state of the Caregiver in the complex.*/
    public void setAssigned(boolean assigned) {
    	this.assigned = assigned;
    }
    
    /**Gets the number of weekly hours worked by this Caregiver.
     * @return double*/
    public double getHours() {
    	return hours.get();
    }
    
    /**Sets the number of weekly hours worked by this Caregiver.
     * @param hours*/
    public void setHours(Number hours) {
    	this.hours.set(hours.doubleValue());
    }

    /**Gets the hourly wage of this Caregiver.
     * @return double*/
    public double getWage() {
    	return wage.get();
    }
    
    /**Sets the hourly wage of this Caregiver.
     * @param wage*/
    public void setWage(Number wage) {
    	this.wage.set(wage.doubleValue());
    }
    
    /**
     * This method calculates the weekly pay for the Caregiver.
     */
    public void calculateWeeklyPay() {
    	weekly_pay.set(wage.get()*hours.get()*0.735);
    }
    
    /**@return double - The weekly pay*/
    public double getWeeklyPay() {
    	return weekly_pay.get();
    }
    
    /**Computes the monthly pay for the Caregiver.*/
    public void calculateMonthlyPay() {
    	monthly_pay.set(wage.get()*hours.get()*0.735*4.34);
    }
    
    /**@return double - The monthly pay*/
    public double getMonthlyPay() {
    	return monthly_pay.get();
    }
    
    /**Computes the annual pay for the Caregiver.*/
    public void calculateAnnualPay() {
    	annual_pay.set(wage.get()*hours.get()*0.735*4.34*12.0);
    }
    
    /**@return double - The annual pay.*/
    public double getAnnualPay() {
    	return annual_pay.get();
    }
    
    
    /**
     * Sorts the caregiver by employee number.
     * @param c - The list of caregivers to sort.
     */
    public static void sortEmpNum(List<Caregiver> c){
        for (int i = 0; i < c.size(); i++) {
    		Caregiver holder = c.get(i);
    		int j = i - 1;
    		while (j >- 0 && c.get(j).getEmpNum() > holder.getEmpNum()) {
    			c.set(j + 1, c.get(j));
    			j--;
    		}
    		c.set(j + 1, holder);
    	}
    }
    
    /**
     * Searches for Caregiver depending on EmpNum using Binary search (recursion).
     * @param empNum - The employee number to search for.
     * @param c - The list of Caregivers.
     * @param l - The left most index of the array.
     * @param r - The right most index of the array.
     * @return Caregiver - The caregiver with the searched employee number.
     */
    public static Caregiver searchEmpNum(long empNum, List<Caregiver> c, int l, int r) {
    	Caregiver.sortEmpNum(c);
        l = 0;
        r = c.size() - 1;
        
        while (l<=r) {
            int mid = (l+r)/2;
            if (empNum == c.get(mid).getEmpNum()) {
                return c.get(mid);
            } else if (empNum > c.get(mid).getEmpNum()) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            
        }
        return null;
             
    }
    
            @Override
    /**Returns a String representation of this Caregiver.
     * @return String*/
    public String toString() {
        return super.toString() + "\n"
                + "Employee Number: " + getEmpNum() + "\n"
                + "Hours Per Week: " + getHours() + "\n"
                + "Wage: " + getWage() + "\n";
    }
}
