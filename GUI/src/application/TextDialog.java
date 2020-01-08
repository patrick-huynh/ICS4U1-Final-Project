package application;

import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.geometry.Insets;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.ColumnConstraints;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

//INCOMPLETE: WILL MAKE WITH STYLING

public class TextDialog {
	
	private int row = 0;
	
	private ArrayList<Label> label_container;
	private ArrayList<TextField> field_container;
	
	private Stage parent, modal;
	private Scene main;
	private GridPane frame;
	private final Label header_content;
	private final Button cancel, submit;
	
	/**Creates a custom TextDialog with an empty header label and two buttons.
	 * @param parent - The owner Stage of this TextDialog.*/
	public TextDialog(Stage parent) {
		this.parent = parent;
		
		label_container = new ArrayList<>();
		field_container = new ArrayList<>();
		
		frame = new GridPane();
		frame.setVgap(10);
		frame.setHgap(10);
		frame.setPadding(new Insets(20, 150, 10, 10));
		
		header_content = new Label();
		frame.add(header_content, 0, 0);
		row++;
		
		cancel = new Button("Cancel");
		submit = new Button("Submit");
		
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
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				modal.hide();
			}
		});
		
		//collect all information from TextFields in the ArrayList
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < field_container.size(); i++) {
					if (field_container.get(i) != null) {
						
					}
				}
			}
		});
	}
	
	//Set Row indices using RowConstraints
	private void updateButtonPositions() {
		
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
	public void addOpenedPair(Label label, boolean req, TextField field) {
		if (req) {
			label.setText(label.getText().concat("*"));
			label.setTextFill(Color.RED);
		}
		
		frame.add(label, 0, row);
		frame.add(field, 1, row);
		row++;
		
		label_container.add(label);
		field_container.add(field);
	}
	
	public void removeOpenedPair(Label label, TextField field) {
		frame.getChildren().removeAll(label, field);
		label_container.remove(label);
		field_container.remove(field);
		row--;
	}
	
	public void addDateBox(Label label, boolean req, DateBox box) {
		if (req) {
			label.setText(label.getText().concat("*"));
			label.setTextFill(Color.RED);
		}		
		
		frame.add(label, 1, row);
		frame.add(box.getMonthBox(), 2, row);
		frame.add(box.getDayBox(), 3, row);
		frame.add(box.getYearBox(), 4, row);
		row++;
		
		label_container.add(label);
	}
	
	public void display() {
		modal.showAndWait();
	}
	
	public ArrayList<Label> getLabels() {
		return label_container;
	}
	
	public Label getLabelAt(int index) {
		if (index > 0 && index < label_container.size()) {
			return label_container.get(index);
		}
		return null;
	}
	
	public Label getLabelAt(String text) {
		for (Label contained_label : label_container) {
			if (contained_label.getText().equals(text)) {
				return contained_label;
			}
		}
		return null;
	}
	
	public ArrayList<TextField> getFields() {
		return field_container;
	}
	
	public TextField getFieldAt(int index) {
		if (index > 0 && index < field_container.size()) {
			return field_container.get(index);
		}
		return null;
	}
}
