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
public class Patient extends Person{
  protected long PatientID;
  protected String status;
  public Patient(String firstName, String lastName, String phone, String gender, String dob, int age, String address, String status, long PatientID){
    super(firstName, lastName, phone, gender, dob, age, address);
    this.PatientID = PatientID;
    this.status = status;
  }
  
  
}
