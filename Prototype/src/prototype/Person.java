/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

/**
 *
 * @author 325254100
 */
public abstract class Person {
    private String fname, lname, phone, gender, dob, address;
    private int age;
    
    /*Constructor for class Person.
    /*@param fname - The first name of the Person.
    /*@param lname - The last name of the Person.
    /*@param phone - The phone number of the Person.
    /*@param gender - The gender of the Person.
    /*@param dob - The date of birth (mm/dd/yyyy) of the Person.
    /*@param address - The address of the Person. */
    public Person (String fname, String  lname, String  phone, String gender, String dob, int age, String address) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.address = address;
    }
    
}
