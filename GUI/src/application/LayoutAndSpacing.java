package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LayoutAndSpacing extends Application {

	Scene scene;
	VBox pane;
	Rectangle2D screen;
	
	@Override
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		
		pane = new VBox();
		scene = new Scene(pane, screen.getWidth() / 2, screen.getHeight() / 2);
		pane.setSpacing(10);
		pane.setPadding(new Insets(50, scene.getWidth() / 2.0, scene.getWidth()/2.0, 50));
		
		Label label = new Label("Hello World!");
		TextField field = new TextField("Say something!");
		
		Label label2 = new Label("I'm surprised this trash works!");
		TextField field2 = new TextField("Are you surprised?");
		
		pane.getChildren().addAll(label, field, label2, field2);
		
		stage.setScene(scene);
		stage.setTitle("Layout and Spacing Test");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
