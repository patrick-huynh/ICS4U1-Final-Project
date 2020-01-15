package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class Caregiver extends Person {

    private SimpleStringProperty type;
    private final SimpleLongProperty empNum;
    private SimpleIntegerProperty timeClockIn;
    private SimpleIntegerProperty timeClockOut;
    private SimpleIntegerProperty totalHoursWorked;
    private final SimpleDoubleProperty WAGE;
    private Senior assignedSenior;

    /**
     *
     * @param empnum - The employee number.
     * @param ntype - The type of employee (i.e. either day or night).
     * @param ntimeClockIn - The time the caregiver clocks in.
     * @param ntimeClockOut - The time the caregiver clocks out.
     * @param ntotalHoursWorked - The total hours worked.
     * @param assignedSenior - The assigned senior to the care giver.
     */
    public Caregiver(String fname, String lname, String dob, int age, double height, double weight, String gender,
            long empnum, String ntype, int ntimeClockIn, int ntimeClockOut, int ntotalHoursWorked, Senior assignedSenior) {
        super(fname, lname, dob, age, height, weight, gender);

        type = new SimpleStringProperty();
        type.set(ntype);

        empNum = new SimpleLongProperty();
        empNum.set(empnum);

        timeClockIn = new SimpleIntegerProperty();
        timeClockIn.set(ntimeClockIn);

        timeClockOut = new SimpleIntegerProperty();
        timeClockOut.set(ntimeClockOut);

        totalHoursWorked = new SimpleIntegerProperty();
        totalHoursWorked.set(ntotalHoursWorked);

        WAGE = new SimpleDoubleProperty();
        WAGE.set(14);
    }

    public Caregiver(String fname, String lname, String dob, int age, double height, double weight, String gender,
            long empnum, String ntype, int ntotalHoursWorked, Senior assignedSenior) {
        super(fname, lname, dob, age, height, weight, gender);

        type = new SimpleStringProperty();
        type.set(ntype);

        empNum = new SimpleLongProperty();
        empNum.set(empnum);

        timeClockIn = new SimpleIntegerProperty();
        timeClockIn.set(0);

        timeClockOut = new SimpleIntegerProperty();
        timeClockOut.set(0);
        
        totalHoursWorked = new SimpleIntegerProperty();
        totalHoursWorked.set(ntotalHoursWorked);

        WAGE = new SimpleDoubleProperty();
        WAGE.set(14);
    }

    public long getEmp() {
        return empNum.get();
    }

    /**
     *
     * @return String - The type of caregiver (i.e. day or night)
     */
    public String getType() {
        return type.get();
    }

    /**
     *
     * @return int - The time clocked in (represented in 24hr. format)
     */
    public int getTimeClockIn() {
        return timeClockIn.get();
    }

    /**
     *
     * @return int - The time clocked out.
     */
    public int getTimeClockOut() {
        return timeClockOut.get();
    }

    /**
     *
     * @return int - The total hours worked for the duration of the payroll.
     */
    public int getTotalHoursWorked() {
        return totalHoursWorked.get();
    }

    /**
     *
     * @return Senior - The senior that is assigned to the caregiver.
     */
    public Senior getAssigned() {
        return assignedSenior;
    }

    public void setEmp(long empNum) {
        this.empNum.set(empNum);
    }

    /**
     *
     * @param ntype - The type of caregiver.
     */
    public void setType(String ntype) {
        type.set(ntype);
    }

    /**
     *
     * @param time - The time clocked in.
     */
    public void setTimeClockIn(int time) {
        timeClockIn.set(time);
    }

    /**
     *
     * @param time - The time clocked out.
     */
    public void setTimeClockOut(int time) {
        timeClockOut.set(time);
    }

    /**
     *
     * @param time - The total hours worked.
     */
    public void setTotalHoursWorked(int time) {
        totalHoursWorked.set(time);
    }

    /**
     *
     * @param name - The senior that is assigned to the caregiver.
     */
    public boolean setAssigned(Senior name) {

        if (assignedSenior.equals(name)) {
            return false;
        }
        assignedSenior = name;
        return true;
    }

    /**
     *
     * @param name - The caregiver you want to calculate the pay for.
     * @return double - The pay in CAD.
     */
    public double calculatePay(Caregiver name) {
        return name.getTotalHoursWorked() * WAGE.get();
    }

    /**
     * Adds the number of hours for the day to the total number of hours for the
     * duration of the payroll.
     *
     * @param name The caregiver.
     */
    public void addHours(Caregiver name) {
        int hours = name.getTimeClockOut() - name.getTimeClockIn();
        name.totalHoursWorked.set(name.totalHoursWorked.get() + hours);
    }

    public String toString() {
        return super.toString() + "\n"
                + "Employee Number: " + getEmp() + "\n"
                + "Type (Day/Night): " + getType() + "\n"
                + "Total Hours Worked: " + getTotalHoursWorked() + "\n"
                + "Assigned Seniors: " + getAssigned().getFullName();

    }
}
