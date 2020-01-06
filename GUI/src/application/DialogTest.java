package application;

import java.net.URISyntaxException;
import java.util.Optional;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Node;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;

public class DialogTest extends Application {

	Scene scene;
	
	@Override
	public void start(Stage stage) throws URISyntaxException {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("TextField Modal Test");
		dialog.setHeaderText("Person Data");
		
		//dialog.setGraphic(new ImageView(new Image(getClass().getResource("/IMAGES/person.png").toURI().toString())));
		ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(submit, ButtonType.CANCEL);
		
		//if submit was clicked, get values from textfield
		//catch exceptions
		
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		TextField first = new TextField();
		TextField last = new TextField();
		TextField dob = new TextField("mm/dd/yyy");
		TextField age = new TextField();
		
		grid.add(new Label("h"), 0, 0);
		grid.add(new Label("First Name"), 0, 0);
		grid.add(first, 1, 0);
		
		grid.add(new Label("Last Name"), 0, 1);
		grid.add(last, 1, 1);
		
		grid.add(new Label("Birth Date"), 0, 2);
		grid.add(dob, 1, 2);
		
		grid.add(new Label("Age"), 0, 3);
		grid.add(age, 1, 3);
	
		dialog.getDialogPane().setContent(grid);
		
		Button real_submit = new Button("Submit");
		grid.add(real_submit, 4, 4);
		grid.getChildren().remove(real_submit);
		
		Button button = new Button("Oepn up custom dialog.");
		button.setOnAction(event -> {
			dialog.showAndWait();
		});
		
		Pane pane = new Pane();
		pane.getChildren().add(button);
		scene = new Scene(pane, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Custom Dialog Test");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
