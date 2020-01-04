package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.geometry.Rectangle2D;

import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Cell;

public class TableViewTest extends Application {
	
	Rectangle2D screen;
	Pane pane;
	TableView<FullTimeStaff> table;
	Scene scene;
	
	@Override
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		pane = new Pane();
		
		table = new TableView<>();
		TableColumn<FullTimeStaff, String> fname = new TableColumn<>("First Name");
		TableColumn<FullTimeStaff, String> lname = new TableColumn<>("Last Name");
		
		table.getColumns().addAll(fname, lname);
		
		pane.getChildren().add(table);
		scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		
		stage.setScene(scene);
		stage.setTitle("TableView Test");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}