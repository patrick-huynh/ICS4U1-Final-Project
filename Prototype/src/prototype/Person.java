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
    public Person (String fname,String  lname,String  phone, String gender, String dob, int age) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.address = address;
        
    }
    
}
