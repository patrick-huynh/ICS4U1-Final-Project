package application;

import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuButtonTester extends Application {

	Pane pane;
	Scene scene;
	
	@Override
	public void start(Stage stage) throws URISyntaxException {
		pane = new Pane();
		scene = new Scene(pane, 500, 500);
		
		ImageView image = new ImageView(new Image(getClass().getResource(
				"/IMAGES/hamburger.png").toURI().toString()));
		MenuButton button = new MenuButton("");
		
		image.setFitHeight(25);
		image.setFitWidth(25);
		button.setGraphic(image);
		
		//Loads recent.txt to the tables
		MenuItem load = new MenuItem("Load");
		//File transfer protocol
		MenuItem save = new MenuItem("Save");
		MenuItem reload = new MenuItem("Reload");
		MenuItem retrieve = new MenuItem("Retrieve");
		
		
		button.getItems().addAll(load, save, reload, retrieve);
		pane.getChildren().add(button);
		
		stage.setScene(scene);
		stage.setTitle("MenuButtonTester");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
