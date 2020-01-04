package application;

public class FullTimeStaff extends Employee {
	
	protected final double SICK_DAYS = 20.0d;
	
	private double salary;
	private double sick_taken;
	
	public FullTimeStaff(long employee_number, String first_name, String last_name, 
			String title, double salary) {
		super(employee_number, first_name, last_name, title);
		this.salary = salary;
		sick_taken = 0;
	}
	
	public double getSickDaysTaken() {
		return sick_taken;
	}
	
	public boolean addSickDays(double amount) {
		if ((sick_taken + amount) > SICK_DAYS) {
			return false;
		} else {
			sick_taken += amount;
			return true;
		}
	}
	
	public double getSalary() {
		return salary;
	}
	
}
