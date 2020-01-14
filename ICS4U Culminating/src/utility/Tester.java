/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.time.LocalTime;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_DAY;

/**
 *
 * @author 325254100
 */
public class Tester {

    public static void main(String[] args) {
        Senior s1 = new Senior("Patrick", "Huynh", "27/09/2002", 1, 170, 165, "M", "01/09/2020", 416906L, 123456L);
        Caregiver c1 = new Caregiver("Patrick", "Huynh", "27/09/2002", 5, 456, 789, "M",
                5465L, "Night", 89, s1);

        c1.setTimeClockIn(1200);
        c1.setTimeClockOut(1400);
        System.out.println(c1.getTotalHoursWorked());

        Caregiver c2 = new Caregiver("Henchel", "Santillan", "09", 89, 178, 165, "M",
                132456L, "Day", 45, s1);
        Senior [] s11 = {s1};
        Activity soccer = new Activity("Soccer", true, LocalTime.parse("23:00"), LocalTime.parse("11:30"),c2, s11);
        Activity basketball = new Activity("Basketball", false, LocalTime.parse("10:30"), LocalTime.parse("14:00"),c2, s11);
        Activity lacrosse = new Activity("Lacrosse", true, LocalTime.parse("19:00"), LocalTime.parse("20:00"),c2, s11);
        
        Activity [] all = {lacrosse, basketball,soccer};
        //Activity.sortTime(all);
        Activity.sortAlpha(all);
        /*for (int i = 0; i < all.length; i++) {
            System.out.println(all[i]);
        }
        */
        System.out.println(Activity.findByName("soccer", all));
        
        
        
        
        


    }

}
