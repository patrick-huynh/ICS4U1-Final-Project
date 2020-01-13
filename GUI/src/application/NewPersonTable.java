package application;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.HostServices;

import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.stage.Screen;

import javafx.geometry.Rectangle2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NewPersonTable extends Application {

	Rectangle2D screen;
	Scene scene;
	VBox box;
	HBox div;
	TableView<Person> table;
	ObservableList<Person> people;
	FilteredList<Person> filtered_list;
	SortedList<Person> sorted_list;
	
	ContextMenu tablectx;
	
	@Override
	public void start(Stage stage) {
		//SCENE SETUP
		screen = Screen.getPrimary().getVisualBounds();
		box = new VBox();
		box.setSpacing(5);
		box.setPadding(new Insets(10, 0, 0, 10));
		scene = new Scene(box, screen.getWidth(), screen.getHeight());
		
		File rec = new File("recent.txt");
		File pre = new File("previous.txt");
		
		//MENUBAR SETUP	
		MenuItem save = new MenuItem("Save");
		save.setOnAction(event -> {
			save(rec, pre);
		});
		
		MenuItem load = new MenuItem("Load");
		load.setOnAction(event -> {
			load(rec);
		});
		
		MenuItem reload = new MenuItem("Reload");
		reload.setOnAction(event -> {
			reload(rec, pre);
		});
		
		MenuItem retrieve = new MenuItem("Retrieve");
	
		MenuButton button = new MenuButton("");
		
		try {
			ImageView image = new ImageView(new Image(getClass().getResource(
					"/IMAGES/hamburger.png").toURI().toString()));
			image.setFitHeight(25);
			image.setFitWidth(25);
			button.setGraphic(image);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		button.getItems().addAll(save, load, reload);
		
		
		//TABLE SETUP
		table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		setTableEditable();
		
		people = FXCollections.observableArrayList();
		/*people.addAll(new Person("A", "D", "12/12/2002", 2), new Person("B", "E", "12/12/2002", 3), 
				new Person("C", "F", "12/12/2002", 4.098));*/
		
		//TABLE COLUMNS
		TableColumn<Person, String> firstName = new TableColumn<>("First Name");
		firstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		firstName.setCellFactory(TextFieldTableCell.forTableColumn());
		firstName.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> e) {
				((Person) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setFirstName(e.getNewValue());
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
			}
		});
		
		TableColumn<Person, Number> age = new TableColumn<>("Age");
		age.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
		age.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		age.setOnEditCommit(new EventHandler<CellEditEvent<Person, Number>>() {
			@Override
			public void handle(CellEditEvent<Person, Number> e) {
				if (e.getNewValue() instanceof Number) {
					((Person) e.getTableView().getItems().get(
							e.getTablePosition().getRow())).setAge(e.getNewValue());
				}
			}
		});
		
		table.getColumns().add(firstName);
		table.getColumns().add(lastName);
		table.getColumns().add(birth);
		table.getColumns().add(age);
		
		//SEARCH BAR AND TRANSFER FROM OBSERVABLEARRAYLIST TO SORTEDLIST WRAPPING FILTEREDLIST
		filtered_list = new FilteredList<>(people, p-> true);
		TextField field = new TextField();
		field.setPromptText("Search here");
		
		field.textProperty().addListener((observable, oldValue, newValue) -> {
			filtered_list.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase().trim();
				
				if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (person.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				
				return false;
			});
		});
		
		sorted_list = new SortedList<>(filtered_list);
		sorted_list.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sorted_list);
		
		
		//HBOX AND VBOX SETUP
		HBox div = new HBox(field);
		div.setAlignment(Pos.CENTER);
		
		box.getChildren().addAll(table, div, button);
		
		//CONTEXTMENU SETUP
		tablectx = new ContextMenu();
		tablectx.setAutoHide(true);
		
		MenuItem addRow = new MenuItem("Add Row");
		addRow.setOnAction(event -> {
			addRow(stage);
		});
		
		MenuItem addDefaultRow = new MenuItem("Add Default Row");
		addDefaultRow.setOnAction(event -> {
			addDefaultRow();
		});
		
		MenuItem deleteRow = new MenuItem("Delete Row");
		deleteRow.setOnAction(event -> {
			deleteRow();
		});
		
		MenuItem deleteLastRow = new MenuItem("Delete Last Row");
		deleteLastRow.setOnAction(event -> {
			deleteLastRow();
		});
		
		tablectx.getItems().addAll(addRow, addDefaultRow, deleteRow, deleteLastRow);
		table.setOnContextMenuRequested(event -> {
			tablectx.show(table, event.getScreenX(), event.getScreenY());
		});
		
		//STAGE SETUP
		stage.setScene(scene);
		stage.setTitle("Simple Table Test");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	//Utility Methods for the Table and the Modal
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
            people.add(person);
        }
	}
	
	private void addDefaultRow() {
		people.add(people.size(), new Person("", " ", "", 0.0));
	}
	
	private void deleteLastRow() {
		if (people.size() > 0) {
			people.remove(people.size() - 1);
			table.refresh();
		}
	//if obslist does not refresh, can create a custom repopulation method
	}
	
	private void deleteRow() {
		people.removeAll(
				table.getSelectionModel().getSelectedItems());
		table.refresh();
	}
	
	
	private void copy(File source, File destination) {
		
		try (FileInputStream in = new FileInputStream(source);
			 FileOutputStream out = new FileOutputStream(destination);
			 FileChannel from = in.getChannel();
		     FileChannel to = out.getChannel();
			 ) {
			
			to.transferFrom(from, 0, from.size());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void swap(File source, File target) {
		File temp = new File("temp.txt");
		copy(target, temp);
		copy(source, target);
		copy(temp, source);
	}
	
	private void save(File source, File destination) {
		copy(source, destination);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));
				) {
			for (Person person : people) {
				if (person != null) {
					writer.write(person.getFirstName());
					writer.write(System.getProperty("line.separator"));
					writer.write(person.getLastName());
					writer.write(System.getProperty("line.separator"));
					writer.write(person.getBirthDate());
					writer.write(System.getProperty("line.separator"));
					writer.write(String.valueOf(person.getAge()));
					writer.write(System.getProperty("line.separator"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load(File source) {
		people.removeAll(people);
		
		try (BufferedReader reader = new BufferedReader(new FileReader(source));
				) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				people.add(new Person(line, reader.readLine(), reader.readLine(), Double.parseDouble(reader.readLine())));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void reload(File source, File target) {
		people.removeAll(people);
		swap(source, target);
		load(source);
	}
}
