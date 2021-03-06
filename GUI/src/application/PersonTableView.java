package application;

import java.text.NumberFormat;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.event.EventHandler;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ChoiceBox;

public class PersonTableView extends Application {

	Rectangle2D screen;
	VBox pane;
	Scene scene;
	TableView<Person> table;
	ContextMenu tableRC;	//table right-click
	
	private final NumberFormat nf = new DecimalFormat("#.##");
	
	@Override
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		pane = new VBox();
		pane.setSpacing(5);
		pane.setPadding(new Insets(10, 0, 0, 10));
		
		scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		
		table = new TableView<>();
		ObservableList<Person> people = FXCollections.observableArrayList();
		people.addAll(new Person("A", "D", "12/12/2002", 2), new Person("B", "E", "12/12/2002", 3), 
				new Person("C", "F", "12/12/2002", 4.098));
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
		/*age.setCellFactory(tc -> new TableCell<Person, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value,  empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});*/
		
		age.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		age.setOnEditCommit(new EventHandler<CellEditEvent<Person, Number>>() {
			@Override
			public void handle(CellEditEvent<Person, Number> e) {
				if (e.getNewValue() instanceof Number) {
					((Person) e.getTableView().getItems().get(
							e.getTablePosition().getRow())).setAge(e.getNewValue());
					table.refresh();
				}
			}
		});
		
		FilteredList<Person> person_list = new FilteredList<>(people, p -> true);
		table.setItems(person_list);
		
		//Will add in age search and general number search later
		ChoiceBox<String> choices = new ChoiceBox<>();
		choices.getItems().addAll("First Name", "Last Name");
		choices.setValue("First Name");
		
		LimitedTextField field = new LimitedTextField();
		field.setPromptText("Search table");
		field.setOnKeyReleased(event -> {
			switch (choices.getValue()) {
			
				case "First Name":
					field.setAsAlphaOnly();
					person_list.setPredicate(p -> 
					p.getFirstName().toLowerCase().contains(field.getText().toLowerCase().trim()));
					break;
				case "Last Name":
					field.setAsAlphaOnly();
					person_list.setPredicate(p -> 
					p.getLastName().toLowerCase().contains(field.getText().toLowerCase().trim()));
					break;
				/*case "Age":
					field.setAsNumericOnly();
					person_list.setPredicate(p ->
					p.getAge() == Double.parseDouble(field.getText().trim()));
					break;*/
			}
		});
		
		choices.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				field.setText("");
				person_list.setPredicate(null);
			}
		});
		
		HBox box = new HBox(choices, field);
		box.setAlignment(Pos.CENTER);
	
		table.getColumns().add(firstName);
		table.getColumns().add(lastName);
		table.getColumns().add(birth);
		table.getColumns().add(age);
		
		setTableEditable();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		pane.getChildren().addAll(table, box);
		
		tableRC = new ContextMenu();
		tableRC.setAutoHide(true);
		MenuItem addRow = new MenuItem("Add Row");
                addRow.setOnAction(event -> {
                    addRow(stage);
                });
                        
                
		MenuItem deleteRow = new MenuItem("Delete Row");
		tableRC.getItems().addAll(addRow, deleteRow);
		
		table.setOnContextMenuRequested(event -> {
			tableRC.show(table, event.getScreenX(), event.getScreenY());
		});
		
		stage.setScene(scene);
		stage.setTitle("Simple Table Test");
		stage.show();
	}
	
	@SuppressWarnings("unchecked")
	private void setTableEditable() {
		//Make table editable by external user input using TableView Selection Model
		table.setEditable(true);
		table.getSelectionModel().cellSelectionEnabledProperty().set(true);
		
		//Lambda event to capture event and process (filter), then consume (terminate) it
		table.setOnKeyPressed(event -> {
			if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
				final TablePosition<Person, ?> focusedCell = table.focusModelProperty().get().focusedCellProperty().get();
				table.edit(focusedCell.getRow(), focusedCell.getTableColumn());
			} else if (event.getCode() == KeyCode.TAB || event.getCode() == KeyCode.RIGHT) {
				table.getSelectionModel().selectNext();
				event.consume();
			} else if (event.getCode() == KeyCode.LEFT) {
				selectPrevious();
				event.consume();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void selectPrevious() {
		if (table.getSelectionModel().isCellSelectionEnabled()) {
			TablePosition<Person, ?> position = table.getFocusModel().getFocusedCell();
			
			if (position.getColumn() - 1 >= 0) {
				table.getSelectionModel().select(position.getRow(), getPrevColumn(position.getTableColumn(), -1));
			} else if (position.getRow() < table.getItems().size()) {
				table.getSelectionModel().select(position.getRow() - 1,
						table.getVisibleLeafColumn(table.getVisibleLeafColumns().size()));
			}
		} else {
			int focus = table.getFocusModel().getFocusedIndex();
			
			if (focus == -1) {
				table.getSelectionModel().select(table.getItems().size() - 1);
			} else if (focus > 0) {
				table.getSelectionModel().select(focus - 1);
			}
		}
	}
	
	private TableColumn<Person, ?> getPrevColumn(final TableColumn<Person, ?> column, int offset) {
		int column_index = table.getVisibleLeafIndex(column);
		return table.getVisibleLeafColumn(column_index + offset);
	}
	
	public void refresh() {
		ObservableList<Person> copyOf = FXCollections.observableArrayList();
		
		for (Person p : table.getItems()) {
			copyOf.add(p);
		}
		
		table.getItems().removeAll(table.getItems());
		
		for (Person p : copyOf) {
			table.getItems().add(p);
		}
		table.refresh();
	}
	
	//Open up textmodal for person, create new Person object, add to observablearraylist, refresh table
	//boolean returns since need to check if inputs are valid (e.g. Date, Names, Age, etc.)
	//will use try-catch 
	private void addRow(Stage stage) {
                TextDialog dialog = new TextDialog(stage);
		
		LimitedTextField age = new LimitedTextField();
		age.setMaxLength(3);
		age.setAsNumericOnly();
		dialog.setHeaderContent("Add New Row: Person");
		
		
                
                LimitedTextField firstName = new LimitedTextField();
                firstName.setAsAlphaOnly();
		
                LimitedTextField lastName = new LimitedTextField();
                lastName.setAsAlphaOnly();
                
		dialog.addOpenedPair(new Label("First Name: "), true, firstName, false);
		dialog.addOpenedPair(new Label("Last Name: "), true, lastName, false);
		dialog.addOpenedPair(new Label("Age: "), true, age, true);
		dialog.addDateBox(new Label("Date of Birth"));
                dialog.primeButtons();
                
                dialog.display();
                
                if(dialog.isSubmitPressed) {
                    ArrayList<HashMap<TextField, String>> resp = dialog.getResponses();
                    String fName = resp.get(0).get(dialog.getFields().get(0));
                    String lName = resp.get(1).get(dialog.getFields().get(1));
                    int month = dialog.getDateBox().getMonthBox().getValue();
                    int day = dialog.getDateBox().getDayBox().getValue();
                    int year = dialog.getDateBox().getYearBox().getValue();
                    
                    String dob = Integer.toString(month) + "/" + Integer.toString(day) + "/" +
                    Integer.toString(year); 
                     
                    double nAge = Double.valueOf(resp.get(2).get(dialog.getFields().get(2)));
                    
                    Person person = new Person(fName, lName, dob, nAge);
                    table.getItems().add(person);
                    refresh();
                }
	}
	
	/*private boolean deleteRow() {
	
	}*/
	
	public static void main(String[] args) {
		launch(args);
	}

}