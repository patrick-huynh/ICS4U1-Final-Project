package gui;

import javafx.scene.control.ChoiceBox;

public final class DateBox {
	
	public static final int DAY_MAX = 31;
	public static final int MONTH_MAX = 12;
	
	private final ChoiceBox<Integer> month;
	private final ChoiceBox<Integer> day;
	private final ChoiceBox<Integer> year;
	
	/**Creates a DateBox with filled in day and month ChoiceBox objects and an empty year ChoiceBox.*/
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
	
	/**Gets the ChoiceBox for month in the DateBox.
	@return ChoiceBox<Integer>*/
	public ChoiceBox<Integer> getMonthBox() {
		return month;
	}
	
	/**Gets the ChoiceBox for day in the DateBox.
	@return ChoiceBox<Integer>*/
	public ChoiceBox<Integer> getDayBox() {
		return day;
	}
	
	/**Gets the ChoiceBox for year in the DateBox.
	@return ChoiceBox<integer>*/
	public ChoiceBox<Integer> getYearBox() {
		return year;
        }
	
	
	/**Gets the current value being displayed in the month ChoiceBox.
	@return int*/
	public int getMonth() {
		return month.getValue();
	}
	
	/**Gets the current value being displayed in the day ChoiceBox.
	@return int*/
	public int getDay() {
		return day.getValue();
	}
	
	/**Gets the current value being displayed in the year ChoiceBox.
	@return int*/
	public int getYear() {
		return year.getValue();
	}
        
	/**Sets the upper and lower year limits for the ChoiceBox for the year.
	@param lower - The lower limit.
	@param upper - The upper limit.*/
        public void setYearConstraints(int lower, int upper) {
            for (int i = lower; i <= upper; i++) {
                year.getItems().add(i);
            }
        }
}
