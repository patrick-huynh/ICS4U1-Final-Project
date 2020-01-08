package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.control.ChoiceBox;

public class DateBoxTester extends Application {
	
	@Override
	public void start(Stage stage) {
		ChoiceBox<Integer> box = new ChoiceBox<>();
		for (int i = 1; i <= 31; i++) {
			box.getItems().add(i);
		}
		
		Pane pane = new Pane();
		pane.getChildren().add(box);
		
		Scene scene = new Scene(pane, 500, 500);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
