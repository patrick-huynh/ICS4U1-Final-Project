package utility;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;

public class Caregiver extends Person {

    private final SimpleLongProperty empNum;
    private final SimpleDoubleProperty wage, hours, weekly_pay, monthly_pay, annual_pay;
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
    
    public void calculateWeeklyPay() {
    	weekly_pay.set(wage.get()*hours.get()*0.735);
    }
    public Double getWeeklyPay() {
    	return weekly_pay.get();
    }
    public void calculateMonthlyPay() {
    	monthly_pay.set(wage.get()*hours.get()*0.735*4.34);
    }
    public Double getMonthlyPay() {
    	return monthly_pay.get();
    }
    public void calculateAnnualPay() {
    	annual_pay.set(wage.get()*hours.get()*0.735*4.34*12.0);
    }
    public Double getAnnualPay() {
    	return annual_pay.get();
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
