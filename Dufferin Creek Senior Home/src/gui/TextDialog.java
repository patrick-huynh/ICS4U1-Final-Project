package gui;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

//INCOMPLETE: WILL MAKE WITH STYLING

public class TextDialog {
	
        public boolean isSubmitPressed = false;
	private int row = 0;
	
	private ArrayList<Label> label_container;
	private ArrayList<TextField> field_container;
        //used to map TextField controls with their respective texts
        private ArrayList<HashMap<TextField, String>> responses;
	
	private Stage parent, modal;
	private Scene main;
	private GridPane frame;
	private final Label header_content;
	private final Button cancel, confirm, submit;
        private final DateBox box;
        private ChoiceBox<Integer> numberBox;
	
	/**Creates a custom TextDialog with an empty header label and two buttons.
	 * @param parent - The owner Stage of this TextDialog.*/
	public TextDialog(Stage parent) {
		this.parent = parent;
		
                /*Instatiate ArrayLists*/
		label_container = new ArrayList<>();
		field_container = new ArrayList<>();
                responses = new ArrayList<>();
		
                /**Setup the GridPane parent layout for the modal*/
		frame = new GridPane();
		frame.setVgap(10);
		frame.setHgap(10);
		frame.setPadding(new Insets(20, 150, 10, 10));
		
                /**Add the header content (empty default). Increment the current row to adjust
                * the layout space and accommodate for more nodes.*/
		header_content = new Label();
		frame.add(header_content, 0, 0);
		row++;
		
                /**Setup the buttons without priming (cancel, confirm, submit)*/
		cancel = new Button("Cancel");
                frame.add(cancel, 2, 1);
                  
                confirm = new Button("Confirm");
                frame.add(confirm, 3, 1);
                
                
		submit = new Button("Submit");
               	frame.add(submit, 4, 1);
                submit.setDisable(true);
		
                /**Setup the DateBox and modality of the stage*/
                box = new DateBox();
                
		main = new Scene(frame);
		
		modal = new Stage(StageStyle.UTILITY);
		modal.initModality(Modality.WINDOW_MODAL);
		modal.initOwner(parent);
		modal.setScene(main);
	}
	
        /**Creates a TextDialog with a blank header content.
         * @param parent - The parent Stage of this modal.
         * @param header_content - The header label of this modal.*/
	public TextDialog(Stage parent, String header_content) {
		this(parent);
		this.header_content.setText(header_content);
	}
	
        /**Validates the input fields and the DateBox in the modal. If cancel is pressed, 
         * the modal is closed; if confirm is pressed, all of the fields are checked for empty 
         * literals or null values. If it is confirmed that all of the fields have valid input and
         * are not empty, the submit button is enabled and the information can be processed when
         * clicked.
         */
	public void primeButtons() {
		cancel.setOnAction(e -> {
                    modal.close();
		});
		
		confirm.setOnAction(e -> {
             boolean allFields = false;
             boolean dates = false;
			
			for (TextField contained_field : field_container) {
				if(contained_field != null &&
						!contained_field.getText().trim().isEmpty()) {
					allFields = true;
				}
			}
                  
			
                        if(box.getMonthBox().getValue() != null && box.getDayBox().getValue() 
                                != null && box.getYearBox().getValue() != null) {
                            dates = true;
                        }
			
			if (allFields && dates) {
				submit.setDisable(false);
			} else {
				
				//add warning that some required fields are missing
			}
		});
		
		//collect all information from TextFields in the ArrayList
		submit.setOnAction(e -> {
                    for (int i = 0; i < field_container.size(); i++) {
                        if (field_container.get(i) != null) {
                        	HashMap<TextField, String> pair = new HashMap<>();
                        	pair.put(field_container.get(i), 
                                        field_container.get(i).getText());
                        	responses.add(pair);
			}
                    }
                    isSubmitPressed = true;
                    modal.close();
		});
	}
	
	/**Sets the current row index of the buttons using RowConstraints property of GridPane
         by adding to the row total.*/
	private void updateAdd() {
            GridPane.setConstraints(cancel, 2, row + 1);
            GridPane.setConstraints(confirm, 3, row + 1);
            GridPane.setConstraints(submit, 4, row + 1);
	}
	
        /**Sets the current row index of the buttons using RowConstraints property of GridPane
         by subtracting to the row total.*/
        private void updateSubtract() {
            GridPane.setConstraints(cancel, 2, row - 1);
            GridPane.setConstraints(confirm, 3, row - 1);
            GridPane.setConstraints(submit, 4, row - 1);
        }
	
        /**Gets the parent of this modal.
         @return Stage*/
	public Stage getParent() {
		return parent;
	}
	
        /**Sets the window title of this modal.
         @param title - The String name of the popup stage.*/
	public void setWindowTitle(String title) {
		modal.setTitle(title);
	}
	
        /**Sets the content of the label representing the header of this modal.
         @param content - The String content to populate the belonging label.*/
	public void setHeaderContent(String content) {
		header_content.setText(content);
	}
	
	/**Adds a label and a textfield pair to the modal.
         @param label - The label providing information about the expected input.
         @param req - A boolean flag that determines whether to colour and symbolize the label
         to communicate to the user that this field is required.
         @param field - The textfield that accepts user input.
         @param isAgeField - A boolean flag that indicates whether or not this field will take an age value.*/
	public void addOpenedPair(Label label, boolean req, TextField field, boolean isAgeField) {
		Label warning = new Label();
		
                /**If the field is required, set the label text to a red fill and concatenate an 
                 * asterisk to the end of the string.*/
		if (req) {
			label.setText(label.getText().concat("*"));
			label.setTextFill(Color.RED);
                        
		/**Using focus property of the textfield and listens to in and out of focus.
                 If the field is empty, a warning message is displayed next to the field.
                 If the field is an age field, the year choicebox will display current year minus age.*/	
			field.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                        if (isAgeField && !field.getText().trim().isEmpty()) {
                                            int birth_year = 2020 - Integer.parseInt(field.getText());
                                            box.getYearBox().getItems().clear();
                                            box.getYearBox().getItems().add(birth_year);
                                        } 
					if (!newPropertyValue && field.getText().trim().isEmpty()) {
						warning.setText("NEEDED");
						warning.setTextFill(Color.RED);
					} else {
						warning.setText("");
					}
				}
			});
		}
		
                /*Updates the stage modal layout.*/
		frame.add(label, 0, row);
		frame.add(field, 1, row);
		frame.add(warning, 2, row);
		row++;
                updateAdd();
		
		label_container.add(label);
		field_container.add(field);
	}
	
        /**Removes a label and textfield pair from this modal.
         @param label - The label to remove.
         @param field - The textfield to remove.*/
	public void removeOpenedPair(Label label, TextField field) {
		frame.getChildren().removeAll(label, field);
		label_container.remove(label);
		field_container.remove(field);
		row--;
                updateSubtract();
	}

        /**Adds a DateBox object to the modal.
         @param label - The label accompanying this DateBox object.*/
	public void addDateBox(Label label) {
		frame.add(label, 1, row);
		frame.add(box.getMonthBox(), 2, row);
		frame.add(box.getDayBox(), 3, row);
		frame.add(box.getYearBox(), 4, row);
		row++;
                updateAdd();
		
		label_container.add(label);
	}
        
        /**Gets the DateBox in this modal.
         @return DateBox*/
        public DateBox getDateBox() {
            return box;
        }
        
    /**Adds a standalone, single choicebox control to the modal in the next available row.
     @param label - The label for this number choicebox.
     @param lower - The lower limit of this range.
     @param upper - The upper limit of this range.*/
    public void addNumberChoiceBox(Label label, int lower, int upper) {
    	numberBox = new ChoiceBox<>();
    	
    	for (int i = lower; i <= upper; i++) {
    		numberBox.getItems().add(i);
    	}
    	
    	frame.add(label, 1, row);
    	frame.add(numberBox, 2, row);
    	row++;
    	updateAdd();
    	
    	label_container.add(label);
    }
    
    /**Gets the number choicebox of this modal.
     @return ChoiceBox*/
    public ChoiceBox<Integer> getNumberBox() {
    	return numberBox;
    }
        
    /**Displays the modal via a show and wait request.*/
	public void display() {
		modal.showAndWait();
	}
	
        /**Gets a list of labels in this modal.
         @return ArrayList*/
	public ArrayList<Label> getLabels() {
		return label_container;
	}
	
        /**Gets a list of fields in this modal.
         @return ArrayList*/
	public ArrayList<TextField> getFields() {
		return field_container;
	}
        
        /**Gets the list of responses in this modal using a textfield to string mapping.
         @return ArrayList*/
        public ArrayList<HashMap<TextField, String>> getResponses() {
            return responses;
        }
}
