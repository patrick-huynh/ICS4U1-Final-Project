package utility;

import java.time.Duration;
import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Activity {

    private SimpleStringProperty name;
    private SimpleBooleanProperty outdoor;
    private LocalTime startTime, endTime;

    private Caregiver coordinator;
    private Senior[] participants;

    /**
     * 
     * @param aname - The name of the activity.
     * @param aoutdoor - Whether the activity is hosted outdoors, otherwise indoors.
     * @param astartTime - The start time of the activity.
     * @param aendTime - The end time of the activity.
     * @param acoordinator  - The Caregiver that is coordinating the activity.
     * @param aparticipants  - The Seniors that are participating in the activity.
     */
    public Activity(String aname, boolean aoutdoor, LocalTime astartTime, LocalTime aendTime, Caregiver acoordinator, Senior aparticipants[]) {
        name = new SimpleStringProperty();
        name.set(aname);

        outdoor = new SimpleBooleanProperty();
        outdoor.set(aoutdoor);

        startTime = astartTime;
        endTime = aendTime;
        coordinator = acoordinator;
        participants = aparticipants;

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
     * @param participants - The array of Senior that are all participating in this activity.
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
}
