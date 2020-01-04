package application;

import java.text.NumberFormat;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.event.EventHandler;


//SAMPLE CLASS, with no css or fxml. No styling or layout has been applied; only tested for functionality.
/*Things to fix:
 * Cell Wrapping: When a value is entered and does not fit in the cell, how will the table handle this?
 * Age Column: edit commits do not work, since right-end parameterized object is Number and not String
 * cancenlEdit not yet implemented
 * Method of creating tables is the same, only setOnEditCommit and property factories vary. Needs FXML and CSS
 * editable, feature-rich tables: design, good spacing, adjustable and adaptable. 
 * Still working on textmodal
 * */

public class PersonTableView extends Application {

	Rectangle2D screen;
	Pane pane;
	Scene scene;
	TableView<Person> table;
	
	private final NumberFormat nf = new DecimalFormat("#.##");
	
	@Override
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		pane = new Pane();
		scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		
		table = new TableView<>();
		ObservableList<Person> people = FXCollections.observableArrayList();
		people.addAll(new Person("A", "A", "12/12/2002", 2), new Person("B", "B", "12/12/2002", 3), 
				new Person("C", "C", "12/12/2002", 4.098));
		table.setItems(people);
		
		TableColumn<Person, String> firstName = new TableColumn<>("First Name");
		firstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		firstName.setCellFactory(TextFieldTableCell.forTableColumn());
		firstName.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> e) {
				((Person) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setFirstName(e.getNewValue());
				table.refresh();
			}
		});
		
		TableColumn<Person, String> lastName = new TableColumn<>("Last Name");
		lastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		lastName.setCellFactory(TextFieldTableCell.forTableColumn());
		lastName.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>(){
			@Override
			public void handle(CellEditEvent<Person, String> e) {
				((Person) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setLastName(e.getNewValue());
				table.refresh();
			}
		}); 
		
		TableColumn<Person, String> birth = new TableColumn<>("Date of Birth");
		birth.setCellValueFactory(new PropertyValueFactory<Person, String>("birthDate"));
		birth.setCellFactory(TextFieldTableCell.forTableColumn());
		birth.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> e) {
				((Person) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setBirthDate(e.getNewValue());
				table.refresh();
			}
		});
		
		TableColumn<Person, Number> age = new TableColumn<>("Age");
		age.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
		age.setCellFactory(tc -> new TableCell<Person, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value,  empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});
		
		/*age.setOnEditCommit(new EventHandler<CellEditEvent<Person, Number>>() {
			@Override
			public void handle(CellEditEvent<Person, Number> e) {
				((Person) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setAge(e.getNewValue());
			}
		});*/
		
		table.getColumns().add(firstName);
		table.getColumns().add(lastName);
		table.getColumns().add(birth);
		table.getColumns().add(age);
		
		setTableEditable();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		pane.getChildren().add(table);
		
		stage.setScene(scene);
		stage.setTitle("Simple Table Test");
		stage.show();
	}
	
	@SuppressWarnings("unchecked")
	private void setTableEditable() {
		table.setEditable(true);
		table.getSelectionModel().cellSelectionEnabledProperty().set(true);
		
		table.setOnKeyPressed(event -> {
			if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
				final TablePosition<Person, ?> focusedCell = table.focusModelProperty().get().focusedCellProperty().get();
				table.edit(focusedCell.getRow(), focusedCell.getTableColumn());
			} else if (event.getCode() == KeyCode.TAB || event.getCode() == KeyCode.RIGHT) {
				table.getSelectionModel().selectNext();
				event.consume();
			} else if (event.getCode() == KeyCode.LEFT) {
				table.getSelectionModel().selectPrevious();
				event.consume();
			}
		});
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
