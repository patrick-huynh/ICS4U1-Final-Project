package application;

public abstract class Employee {
	protected long employee_number;
	protected String first_name, last_name, title;
	
	public Employee(long employee_number, String first_name, String last_name, String title) {
		this.employee_number = employee_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Employee Number: " + employee_number 
			+  "\nFirst Name: " + first_name 
			+ "\nLast Name: " + last_name
			+ "\nTitle: " + title;
	}
}
