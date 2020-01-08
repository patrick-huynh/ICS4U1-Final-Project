package utility;

import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Activity {
	private SimpleStringProperty name;
	private SimpleBooleanProperty outdoor;
        private LocalTime startTime, endTime;
	
	private Caregiver coordinator;
	private Senior[] participants;
	
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
         * 
         * @return 
         */
        public String getName(){
            return name.get();
        }
        
        public String getStartTime() {
            return startTime.toString();
        }
        
        public String getEndTime(){
            return endTime.toString();
            
        }
        
        /*public String getDuration(){
            //START HERE
        }*/
        
        
}
