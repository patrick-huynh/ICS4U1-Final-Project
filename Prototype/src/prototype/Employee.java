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
public class Employee extends Person{
    protected String title;
    private long empNum;
    
    public Employee(String fName, String lName, long empNum, String title){
        super(fName, lName);
        this.empNum = empNum;
        this.title = title;
    }
}
