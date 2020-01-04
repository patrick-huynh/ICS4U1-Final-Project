package application;

public class PartTimeStaff extends Employee {
	
	private double work_hours, hourly_rate;
	
	public PartTimeStaff(long employee_number, String first_name, String last_name, 
			String title, double work_hours, double hourly_rate) {
		super(employee_number, first_name, last_name, title);
		this.work_hours = work_hours;
		this.hourly_rate = hourly_rate;
	}
	
	
}
