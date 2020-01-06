package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;

public class LimitedTextFieldTest extends Application {

	@Override
	public void start(Stage stage) {
		LimitedTextField field = new LimitedTextField();
		field.setMaxLength(8);
		field.setAsNumericOnly();
		
		field.setOnAction(event -> {
			field.replaceText(0, field.getText().length(), field.getText());
		});
		
		Pane pane = new Pane();
		pane.getChildren().add(field);
		
		Scene scene = new Scene(pane, 500, 500);
		stage.setScene(scene);
		stage.setTitle("LimitedStringTestField Test");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
