package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Person {
	private final SimpleStringProperty fname, lname, dob;
	private SimpleDoubleProperty age, height, weight;
        private SimpleStringProperty gender;
	
	/**Constructor for abstract class Person.
	 * @param nfname - The first name of the Person object.
	 * @param nlname - The last name of the Person object.
	 * @param ndob - The date of birth of the Person object.
	 * @param nage - The age of the Person object.
	 * @param height - The height of the Person object, in cm.
	 * @param weight - The weight of the Person object, in kg.*/
	public Person(String nfname, String nlname, String ndob, double nage, double nheight, double nweight, String ngender) {
		fname = new SimpleStringProperty();
		lname = new SimpleStringProperty();
		dob = new SimpleStringProperty();
		
		fname.set(nfname);
		lname.set(nlname);
		dob.set(ndob);
		age.set(nage);
		height.set(nheight);
		weight.set(nweight);
                gender.set(ngender);
	}
	
	/**Gets the value of the SimpleStringProperty fname.
	 * @return String - The wrapped String object representing the first name of the Person object.*/
	public String getFirstName() {
		return fname.get();
	}
	
	/**Gets the value of the SimpleStringProperty lname.
	 * @return String - The wrapped String object representing the last name of the Person object.*/
	public String getLastName() {
		return lname.get();
	}
	
	/**Gets the full name of the Person object.
	 * @return String - A String object concatenating the wrapped String objects for fnmae and lname.*/
	public String getFullName() {
		return fname.get() + " " + lname.get();
	}
	
	/**Gets the value of the SimpleDoubleProperty age.
	 * @return double - The wrapped double type representing the age of the Person object.*/
	public double getAge() {
		return age.get();
	}
	
	/**Sets the value of the SimpleDoubleProperty age.
	 * @param val - The new age of the Person object.*/
	public void setAge(double val) {
		age.set(val);
	}
	
	/**Gets the value of the SimpleDoubleProperty height.
	 * @return double - The wrapped double type representing the height of the Person object.*/
	public double getHeight() {
		return height.get();
	}
	
	/**Sets the value of the SimpleDoubleProperty height.
	 * @param val - THe new height of the Person object.*/
	public void setHeight(double val) {
		height.set(val);
	}
	
	/**Gets the value of the SimpleDoubleProperty weight.
	 * @return double - The wrapped double type representing the weight of the Person object.*/
	public double getWeight() {
		return weight.get();
	}
        
        /**
         * 
         * @return The gender of the person.
         */
        public String getGender(){
            return gender.get();
        }
	
	/**Sets the value of the SimpleDoubleProperty weight.
	 * @param val - The new weight of the Person object.*/
	public void setWeight(double val) {
		weight.set(val);
	}
        /**
         * 
         * @param g - The gender of the person.
         */
        public void setGender(String g) {
            gender.set(g);
        }
}
