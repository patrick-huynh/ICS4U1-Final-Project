package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TextModalTest extends Application {

	Rectangle2D screen;
	Pane pane;
	Scene scene;
	
	@Override
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		pane = new Pane();
		scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		stage.setScene(scene);
		
		Button btn = new Button("Click to display modal window!");
		
		TextModal mod = new TextModal(stage);
		mod.addLabel(new Label("This won't work"), false);
		mod.addField(new TextField());
		mod.setWindowTitle("Test Window");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				mod.display();
			}
		});
		
		pane.getChildren().add(btn);
		
		stage.setTitle("TextModal Tester");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
