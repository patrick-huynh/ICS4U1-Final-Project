package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Person {

    private final SimpleStringProperty fname, lname, dob;
    private final SimpleIntegerProperty age, roomID;
    
    /**
     * Constructor for abstract class Person.
     *
     * @param fname - The first name of the Person object.
     * @param lname - The last name of the Person object.
     * @param dob - The date of birth of the Person object.
     * @param age - The age of the Person object.
     * @param height - The height of the Person object, in cm.
     * @param weight - The weight of the Person object, in kg.
     */
    public Person(String fname, String lname, String dob, int age, int roomID) {
        this.fname = new SimpleStringProperty();
        this.lname = new SimpleStringProperty();
        this.dob = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();
        this.roomID = new SimpleIntegerProperty();

        this.fname.set(fname);
        this.lname.set(lname);
        this.dob.set(dob);
        this.age.set(age);
        this.roomID.set(roomID);
    }

    /**
     * Gets the first name of this Person object.
     * @return String 
     */
    public String getFirstName() {
        return fname.get();
    }

    /**
     * Gets the last name of this Person object.
     * @return String 
     */
    public String getLastName() {
        return lname.get();
    }

    /**
     * Gets the full name of this Person object.
     * @return String
     */
    public String getFullName() {
        return fname.get() + " " + lname.get();
    }

    /**
     * Gets the age of this Person object.
     * @return double 
     */
    public int getAge() {
        return age.get();
    }

    /**
     * Sets the age of this Person object.
     * @param age
     */
    public void setAge(int age) {
        this.age.set(age);
    }
    
    /**
     * Gets the room ID of this Person object.
     * @return int*/
    public int getRoomID() {
    	return roomID.get();
    }
    
    /**
     * Sets the room ID of this Person object.
     * @param roomID*/
    public void setRoomID(Number roomID) {
    	this.roomID.set(roomID.intValue());
    }

    @Override
    /**Returns a String representation of this Person object.
     * @return String*/
    public String toString() {
        return "Name: " + getFullName() + "\n"
        		+ "Date of Birth: " + dob.get() + "\n"
                + "Age: " + getAge() + "\n"
                + "Room ID: " + getRoomID() + "\n";
    }
}
