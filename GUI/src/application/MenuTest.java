
/**
 * Menu Creation Testing
 * @since 2019-12-17 
 * */

package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Rectangle2D;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ContextMenu;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

public class MenuTest extends Application {

	Rectangle2D screen;
	Pane pane;
	VBox vbox;
	Scene scene;
	MenuBar menu_bar;
	ContextMenu ctxmenu;
	
	@Override
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		
		menu_bar = new MenuBar();
		Menu file_menu = new Menu("File");
		MenuItem load = new MenuItem("Load");
		MenuItem save = new MenuItem("Save");
		MenuItem revert = new MenuItem("Revert");
		MenuItem retrieve = new MenuItem("Retrieve");
		file_menu.getItems().addAll(load, save, revert, retrieve);
		
		Menu table_menu = new Menu("Table");
		MenuItem add_emp = new MenuItem("Add Employee");
		MenuItem delete_emp = new MenuItem("Remove Employee");
		table_menu.getItems().addAll(add_emp, delete_emp);
		
		add_emp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				TextModal mo = new TextModal(stage);
				mo.addLabel(new Label("This is required"), true);
				mo.addField(new TextField("Please fill in."));
				mo.display();
			}
		});
		
		Menu help_menu = new Menu("Help");
		//Add hyperlink here to support web features
		MenuItem wiki = new MenuItem("Git Wiki");
		MenuItem tips = new MenuItem("Tips");
		help_menu.getItems().addAll(wiki, tips);
		
		wiki.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		menu_bar.getMenus().addAll(file_menu, table_menu, help_menu);
		
		vbox = new VBox();
		vbox.getChildren().add(menu_bar);
		pane = new Pane();
		pane.getChildren().add(vbox);
		scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		
		ctxmenu = new ContextMenu();
		Menu file_cxtmenu = new Menu("File");
		MenuItem load_cxtmenu = new MenuItem("Load");
		MenuItem save_cxtmenu = new MenuItem("Save");
		MenuItem revert_cxtmenu = new MenuItem("Revert");
		MenuItem reload_cxtmenu = new MenuItem("Reload");
		
		file_cxtmenu.getItems().addAll(load_cxtmenu, save_cxtmenu, revert_cxtmenu, reload_cxtmenu);
		
		ctxmenu.getItems().add(file_cxtmenu);
		
		pane.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
			ctxmenu.show(pane, event.getScreenX(), event.getScreenY());
			event.consume();
		});
		
		pane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			ctxmenu.hide();
		});
		
		

		
		stage.setScene(scene);
		stage.setTitle("Menu Test");
		stage.show();
	}
	
	//feature: add modal-dragging
	private void addemp_modal(Stage parent) {
		final Stage modal = new Stage(StageStyle.UTILITY);
		modal.initModality(Modality.WINDOW_MODAL);
		modal.initOwner(parent);
		
		HBox modal_pane = new HBox();
		Label label_empNumber = new Label("Employee Number");
		TextField text_empNumber = new TextField();
		Label label_firstName = new Label("First Name");
		TextField text_firstName = new TextField();
		
		modal_pane.getChildren().addAll(label_empNumber, text_empNumber, label_firstName, text_firstName);
		
		Scene modal_scene = new Scene(modal_pane, parent.getScene().getWidth() / 2.0, 
				parent.getScene().getHeight() / 2.0);
		
		modal.setScene(modal_scene);
		modal.setTitle("Add Employee");
		modal.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
