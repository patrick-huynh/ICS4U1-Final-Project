
import application.Person;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 325254100
 */
public class SaveLoadModules {

    //Senior class
    private void save(File source, File destination) {
        copy(source, destination);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));) {
            for (Senior seniors : allSenior) {
                if (seniors != null) {
                    writer.write(seniors.getFirstName());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(seniors.getLastName());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(seniors.getBirthDate());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(seniors.getAge()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(seniors.getAge()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(seniors.getHeight()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(seniors.getWeight()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(seniors.getGender());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(seniors.getDOE());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(seniors.getHomeID()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(seniors.getAccountNumber()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Caregiver class
    private void save(File source, File destination) {
        copy(source, destination);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));) {
            for (Caregiver caregivers : allCaregiver) {
                if (caregivers != null) {
                    writer.write(caregivers.getFirstName());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(caregivers.getLastName());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(caregivers.getBirthDate());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(caregivers.getAge()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(caregivers.getAge()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(caregivers.getHeight()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(caregivers.getWeight()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(caregivers.getGender());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(caregivers.getEmp()));
                    writer.write(System.getProperty("line.separator"));
                    writer.write(caregivers.getType());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(caregivers.getTotalHoursWorked()));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Activity class
    private void save(File source, File destination) {
        copy(source, destination);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));) {
            for (Activity activities : allActivities) {
                if (activities != null) {
                    writer.write(activities.Name());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(activities.getStartTime());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(activities.getEndTime());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(String.valueOf(activities.getDuration()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
