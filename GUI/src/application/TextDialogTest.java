package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class TextDialogTest extends Application {

	@Override
	public void start(Stage stage) {
		TextDialog dialog = new TextDialog(stage);
		
		LimitedTextField age = new LimitedTextField();
		age.setMaxLength(3);
		age.setAsNumericOnly();
		dialog.setHeaderContent("Add New Row: Person");
		
		dialog.addOpenedPair(new Label("First Name: "), true, new TextField());
		dialog.addOpenedPair(new Label("Age: "), true, age);
		
		Button button = new Button("Click to open modal.");
		button.setOnAction(event -> {
			dialog.display();
		});
		
		Pane pane = new Pane();
		pane.getChildren().add(button);
		
		Scene scene = new Scene(pane, 500, 500);
		stage.setScene(scene);
		stage.setTitle("TextDialogTest");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
