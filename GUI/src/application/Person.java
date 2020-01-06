package application;

import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

//Taken from the Oracle Documentation

public class Person {
	private StringProperty firstName, lastName, birthDate;
	private DoubleProperty age;

	public Person(String first, String last, String birth, double age) {
		firstName = new SimpleStringProperty();
		firstName.set(first);
		
		lastName = new SimpleStringProperty();
		lastName.set(last);
		
		birthDate = new SimpleStringProperty();
		birthDate.set(birth);
		
		this.age = new SimpleDoubleProperty();
		this.age.set(age);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName(String str) {
		firstName.set(str);
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public void setLastName(String str) {
		lastName.set(str);
	}
	
	public String getBirthDate() {
		return birthDate.get();
	}
	
	public void setBirthDate(String str) {
		birthDate.set(str);
	}
	
	public double getAge() {
		return age.get();
	}
	
	public void setAge(Number val) {
		age.set(val.doubleValue());
	}
	
	public DoubleProperty ageProperty() {
		return this.age;
	}
}
