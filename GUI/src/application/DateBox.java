package application;

import javafx.scene.control.ChoiceBox;

public final class DateBox {
	
	public static final int DAY_MAX = 31;
	public static final int MONTH_MAX = 12;
	
	private final ChoiceBox<Integer> month;
	private final ChoiceBox<Integer> day;
	private final ChoiceBox<Integer> year;
	
	public DateBox() {
		month = new ChoiceBox<>();
		
		for (int i = 1; i <= MONTH_MAX; i++) {
			month.getItems().add(i);
		}
		
		day = new ChoiceBox<>();
		
		for (int i = 1; i <= DAY_MAX; i++) {
			day.getItems().add(i);
		}
		
		year = new ChoiceBox<>();
	}
	
	public ChoiceBox<Integer> getMonthBox() {
		return month;
	}
	
	public ChoiceBox<Integer> getDayBox() {
		return day;
	}
	
	public ChoiceBox<Integer> getYearBox() {
		return year;
        }
	
	//Getters should only be run when ChoiceBox is non-null (selection has been made)
	public int getMonth() {
		return month.getValue();
	}
	
	public int getDay() {
		return day.getValue();
	}
	
	public int getYear() {
		return year.getValue();
	}
        
        public void setYearConstrains(int lower, int upper) {
            for (int i = lower; i < upper; i++) {
                year.getItems().add(i);
            }
        }
}
