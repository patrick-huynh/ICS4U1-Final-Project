package application;

import java.util.logging.Level;
import java.util.logging.Logger;
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
		
		DateBox date = new DateBox();
		//date.setYearConstraints(1925, 1955);
		
		dialog.addOpenedPair(new Label("First Name: "), true, new TextField(), false);
		dialog.addOpenedPair(new Label("Last Name: "), true, new TextField(), false);
		dialog.addOpenedPair(new Label("Age: "), true, age, true);
		dialog.addDateBox(new Label("Date of Birth"));
		
		Button button = new Button("Click to open modal.");
                button.setId("button");
		button.setOnAction(event -> {
                    dialog.display();
		});
                //home button
		Button home = new Button("Home");
                home.setId("button");
                home.setOnAction(event->{
                    dialog.hide();
                    MainMenu returnToMenu = new MainMenu();
                    try {
                        returnToMenu.start(stage);
                    } catch (Exception ex) {
                        Logger.getLogger(TextDialogTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
		dialog.primeButtons();
		
		Pane pane = new Pane();
		pane.getChildren().addAll(button, home);
                
                
		Scene scene = new Scene(pane, 500, 500);
                scene.getStylesheets().add("Viper.css");
                
		stage.setScene(scene);
		stage.setTitle("TextDialogTest");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
