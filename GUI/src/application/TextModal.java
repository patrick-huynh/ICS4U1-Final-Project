package application;

import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.geometry.Insets;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TextModal {
	private ArrayList<Label> label_container;
	private ArrayList<TextField> field_container;
	
	private Stage parent, modal;
	private Scene main;
	private VBox vbox;
	
	/**Constructor for class TextModal
	 * @param parent - The Stage owner of this TextModal. */
	public TextModal(Stage parent) {
		this.parent = parent;
		label_container = new ArrayList<>();
		field_container = new ArrayList<>();
		
		vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(50, parent.getScene().getWidth() / 2.0, parent.getScene().getHeight() / 2.0, 50));
		
		main = new Scene(vbox, parent.getScene().getWidth() / 2.0, parent.getScene().getHeight() / 2.0);
		
		modal = new Stage(StageStyle.UTILITY);
		modal.initModality(Modality.WINDOW_MODAL);
		modal.initOwner(parent);
		modal.setScene(main);
	}
	
	/**Sets the title of the Stage modal.
	 * @param title - The String representing the window title.*/
	public void setWindowTitle(String title) {
		modal.setTitle(title);
	}
	
	/**Adds a Label object to the modal. This method works on a first-in display basis.
	 * @param label - The Label object to show
	 * @param req - A flag determining if the complementary TextField to follow (if any) is required or not.*/
	public void addLabel(Label label, boolean req) {
		if (req) {
			label.setText(label.getText().concat("*"));
			label.setTextFill(Color.RED);
		}
		
		label_container.add(label);
		vbox.getChildren().add(label);
	}
	
	/**Adds a TextField object to the modal. This method works on a first-in display basis.
	 * @param field - The TextField object to show*/
	public void addField(TextField field) {
		field_container.add(field);
		vbox.getChildren().add(field);
	}

	/**Adds a Label object with its complementary TextField object to the modal.
	 * @param label - {@link #addLabel(Label, boolean)}
	 * @param req - {@link #addLabel(Label, boolean)}
	 * @param field - {@link #addField(TextField)} */
	public void addSection(Label label, boolean req, TextField field) {
		addLabel(label, req);
		addField(field);
	}
	
	/**Displays the modal by calling Stage.showAndWait().*/
	public void display() {
		modal.showAndWait();
	}
	
	/**Gets the parent Stage of this TextModal.*/
	public Stage getParentStage() {
		return parent;
	}
	
	/**Gets the ArrayList containing the Label objects of this TextModal.*/
	public ArrayList<Label> getLabels() {
		return label_container;
	}
	
	/**Gets the ArrayList containing the TextField objects of this TextModal*/
	public ArrayList<TextField> getFields() {
		return field_container;
	}
	
}