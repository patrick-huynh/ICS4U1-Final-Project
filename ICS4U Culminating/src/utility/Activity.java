package utility;

import java.time.Duration;
import java.time.*;
import java.time.format.*;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.text.SimpleDateFormat;
import static java.time.temporal.ChronoField.MINUTE_OF_DAY;

public class Activity {

    private SimpleStringProperty name;
    private SimpleBooleanProperty outdoor;
    private LocalTime startTime, endTime;
    private Caregiver coordinator;
    private Senior[] participants;

    /**
     *
     * @param aname - The name of the activity.
     * @param aoutdoor - Whether the activity is hosted outdoors, otherwise
     * indoors.
     * @param astartTime - The start time of the activity.
     * @param aendTime - The end time of the activity.
     */
    public Activity(String aname, boolean aoutdoor, LocalTime astartTime, LocalTime aendTime) {
        name = new SimpleStringProperty();
        name.set(aname);

        outdoor = new SimpleBooleanProperty();
        outdoor.set(aoutdoor);

        startTime = astartTime;
        endTime = aendTime;

    }

    /**
     * @return String - Name of the activity.
     */
    public String getName() {
        return name.get();
    }

    /**
     * @return String - Start time of the activity (24-hour clock.
     */
    public String getStartTime() {
        return startTime.toString();
    }

    /**
     * @return String - End time of the activity (24-hour clock).
     */
    public String getEndTime() {
        return endTime.toString();

    }

    /**
     * Takes the difference between the endTime and the startTime in order to
     * determine the duration.
     *
     * @return double - How long the activity will take in hours.
     */
    public double getDuration() {
        Duration duration = Duration.between(endTime, startTime);
        return duration.toHours();
    }

    /**
     * @return Caregiver - The coordinator of the activity, usually a caregiver.
     */
    public Caregiver getCoordinator() {
        return coordinator;

    }

    /**
     * @return Senior[] - The seniors participating in the activity.
     */
    public Senior[] getParticipants() {
        return participants;
    }

    /**
     * @param aname - The name of the activity
     */
    public void setName(String aname) {
        name.set(aname);
    }

    /**
     * @param start - Start time of the activity.
     */
    public void setStartTime(LocalTime start) {
        startTime = start;
    }

    /**
     * @param end - End time of the activity
     */
    public void setEndTime(LocalTime end) {
        endTime = end;
    }

    /**
     * @param coordinator - The caregiver that is hosting the activity.
     */
    public void setCoordinator(Caregiver coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * @param participants - The array of Senior that are all participating in
     * this activity.
     */
    public void setParticipants(Senior[] participants) {
        this.participants = participants;
    }

    /**
     * @return boolean - Returns true if the activity is hosted outdoors.
     */
    public boolean isOutDoor() {
        return outdoor.get();
    }

    /**
     * This method sorts the list of activities by time (sorts from most closest
     * to most furthest.)
     *
     * @param activities - Array of unsorted activities.
     */
    public static void sortTime(Activity[] activities) {
        for (int i = 0; i < activities.length; i++) {
            Activity holder = activities[i];
            int j = i - 1;
            while ((j >= 0) && activities[j].startTime.get(MINUTE_OF_DAY) > holder.startTime.get(MINUTE_OF_DAY)) {
                activities[j + 1] = activities[j];
                j--;
            }
            activities[j + 1] = holder;
        }
    }

    public static void sortAlpha(Activity[] activities) {
        for (int i = 0; i < activities.length; i++) {
            Activity holder = activities[i];
            int j = i - 1;
            while ((j >= 0) && activities[j].getName().charAt(0) > holder.getName().charAt(0)) {
                activities[j + 1] = activities[j];
                j--;
            }
            activities[j + 1] = holder;
        }
    }

    /**
     * Searches for an activity within an array of activity by the name. If no
     * activity is found with that name, will return null.
     *
     * @param name - Name of the activity.
     * @param activities - Array of activities.
     * @return
     */
    public static Activity findByName(String name, Activity[] activities) {
        for (int i = 0; i < activities.length; i++) {
            if (name.equalsIgnoreCase(activities[i].getName())) {
                return activities[i];
            }
        }
        return null;
    }

    public String toString() {
        String type;
        if (isOutDoor()) {
            type = "Outdoor";
        } else {
            type = "Indoor";
        }

        return "Name: " + getName() + "\n"
                + "Start Time: " + getStartTime() + "\n"
                + "End Time: " + getEndTime() + "\n"
                + "Duration: " + getDuration() + "\n"
                + "Type: " + type + "\n"
                + "Coordinator: " + coordinator.getFullName();

    }

}
