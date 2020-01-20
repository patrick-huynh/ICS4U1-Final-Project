package utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Person {

    private final SimpleStringProperty fName, lName, DOB;
    private final SimpleIntegerProperty age, roomID;

    /**
     * Constructor for abstract class Person.
     *
     * @param fname - The first name of the Person object.
     * @param lname - The last name of the Person object.
     * @param dob - The date of birth of the Person object.
     * @param age - The age of the Person object.
     * @param roomID - The room ID of this Person.
     */
    public Person(String fName, String lName, String DOB, int age, int roomID) {
        this.fName = new SimpleStringProperty();
        this.lName = new SimpleStringProperty();
        this.DOB = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();
        this.roomID = new SimpleIntegerProperty();

        this.fName.set(fName);
        this.lName.set(lName);
        this.DOB.set(DOB);
        this.age.set(age);
        this.roomID.set(roomID);
    }

    /**
     * Gets the first name of this Person object.
     *
     * @return String
     */
    public String getFName() {
        return fName.get();
    }

    public void setFName(String fName) {
        this.fName.set(fName);
    }

    /**
     * Gets the last name of this Person object.
     *
     * @return String
     */
    public String getLName() {
        return lName.get();
    }

    public void setLName(String lName) {
        this.lName.set(lName);
    }

    /**
     * Gets the full name of this Person object.
     *
     * @return String
     */
    public String getFullName() {
        return fName.get() + " " + lName.get();
    }

    /**
     * Gets the date of birth of this Person.
     *
     * @return String
     */
    public String getDOB() {
        return DOB.get();
    }

    /**
     * Sets the date of birth of this Person.
     *
     * @param DOB
     */
    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }

    /**
     * Gets the age of this Person object.
     *
     * @return double
     */
    public int getAge() {
        return age.get();
    }

    /**
     * Sets the age of this Person object.
     *
     * @param age
     */
    public void setAge(Number age) {
        this.age.set(age.intValue());
    }

    /**
     * Gets the room ID of this Person object.
     *
     * @return int
     */
    public int getRoomID() {
        return roomID.get();
    }

    /**
     * Sets the room ID of this Person object.
     *
     * @param roomID
     */
    public void setRoomID(Number roomID) {
        this.roomID.set(roomID.intValue());
    }

    @Override
    /**
     * Returns a String representation of this Person object.
     *
     * @return String
     */
    public String toString() {
        return "Name: " + getFullName() + "\n"
                + "Date of Birth: " + getDOB() + "\n"
                + "Age: " + getAge() + "\n"
                + "Room ID: " + getRoomID() + "\n";
    }
}