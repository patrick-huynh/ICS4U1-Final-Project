package fileio;

import javafx.application.Application;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

import javafx.scene.layout.Pane;

public class HyperLinkTester extends Application {

	@Override
	public void start(Stage stage) {
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 500, 500);
	
		Hyperlink link = new Hyperlink();
		link.setText("Open link to Google.");
		
		link.setOnAction(event -> {
			if(Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.google.com"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
			
		});
		pane.getChildren().add(link);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
