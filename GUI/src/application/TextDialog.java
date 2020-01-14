package application;

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
	
	/**Creates a custom TextDialog with an empty header label and two buttons.
	 * @param parent - The owner Stage of this TextDialog.*/
	public TextDialog(Stage parent) {
		this.parent = parent;
		
		label_container = new ArrayList<>();
		field_container = new ArrayList<>();
                responses = new ArrayList<>();
		
		frame = new GridPane();
		frame.setVgap(10);
		frame.setHgap(10);
		frame.setPadding(new Insets(20, 150, 10, 10));
		
		header_content = new Label();
		frame.add(header_content, 0, 0);
		row++;
		
		cancel = new Button("Cancel");
                frame.add(cancel, 2, 1);
                  
        confirm = new Button("Confirm");
        frame.add(confirm, 3, 1);
                
                
		submit = new Button("Submit");
               	frame.add(submit, 4, 1);
                submit.setDisable(true);
		
                box = new DateBox();
                
		main = new Scene(frame);
		
		modal = new Stage(StageStyle.UTILITY);
		modal.initModality(Modality.WINDOW_MODAL);
		modal.initOwner(parent);
		modal.setScene(main);
	}
	
	public TextDialog(Stage parent, String header_content) {
		this(parent);
		this.header_content.setText(header_content);
	}
	
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
	
	//Set Row indices using RowConstraints
	private void updateAdd() {
            GridPane.setConstraints(cancel, 2, row + 1);
            GridPane.setConstraints(confirm, 3, row + 1);
            GridPane.setConstraints(submit, 4, row + 1);
	}
	
        private void updateSubtract() {
            GridPane.setConstraints(cancel, 2, row - 1);
            GridPane.setConstraints(confirm, 3, row - 1);
            GridPane.setConstraints(submit, 4, row - 1);
        }
	
	public Stage getParent() {
		return parent;
	}
	
	public void setWindowTitle(String title) {
		modal.setTitle(title);
	}
	
	public void setHeaderContent(String content) {
		header_content.setText(content);
	}
	
	//Automatically add a 0-width label beside each row, which can be used to send error messages about input
	//also, disable submit button until all fields parsed have correct input
	public void addOpenedPair(Label label, boolean req, TextField field, boolean isAgeField) {
		Label warning = new Label();
		
		if (req) {
			label.setText(label.getText().concat("*"));
			label.setTextFill(Color.RED);
			
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
		
		frame.add(label, 0, row);
		frame.add(field, 1, row);
		frame.add(warning, 2, row);
		row++;
                updateAdd();
		
		label_container.add(label);
		field_container.add(field);
	}
	
	public void removeOpenedPair(Label label, TextField field) {
		frame.getChildren().removeAll(label, field);
		label_container.remove(label);
		field_container.remove(field);
		row--;
                updateSubtract();
	}

	public void addDateBox(Label label) {
		frame.add(label, 1, row);
		frame.add(box.getMonthBox(), 2, row);
		frame.add(box.getDayBox(), 3, row);
		frame.add(box.getYearBox(), 4, row);
		row++;
                updateAdd();
		
		label_container.add(label);
	}
        
        public DateBox getDateBox() {
            return box;
        }
        
	public void display() {
		modal.showAndWait();
	}
	public void hide(){
            	modal.hide();
        }
	public ArrayList<Label> getLabels() {
		return label_container;
	}
	
	public ArrayList<TextField> getFields() {
		return field_container;
	}
        
        public ArrayList<HashMap<TextField, String>> getResponses() {
            return responses;
        }
}
