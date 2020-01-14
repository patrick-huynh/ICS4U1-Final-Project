package application;

import java.text.NumberFormat;

import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.Scene;


import javafx.geometry.Insets;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Label;

public class TableRadioTest extends Application {

	Scene scene;
	TableView<Person> table;
	TableView<Person> cart;
	ObservableList<Person> people;
	ObservableList<Person> added;
	
	@Override
	public void start(Stage stage) {
		VBox box = new VBox();
		box.setSpacing(5);
		box.setPadding(new Insets(10, 0, 0, 10));
		scene = new Scene(box, 1000, 600);
		
		table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		people = FXCollections.observableArrayList();
		people.addAll(new Person("A", "", "", 40), new Person("B", "", "", 50), new Person("C", "", "", 34));
		table.setItems(people);
		
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
		
		cart = new TableView<>();
		cart.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		added = FXCollections.observableArrayList();
		cart.setItems(added);
		
		ScrollPane window = new ScrollPane();
		
		ChoiceBox<String> choices = new ChoiceBox<>();
		for (int i = 0; i < people.size(); i++) {
			choices.getItems().add(people.get(i).getFirstName());
		}
		
		final NumberFormat nf = new DecimalFormat("##.##");
		
		TableColumn<Person, String> name_col = new TableColumn<>();
		name_col.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		
		TableColumn<Person, Number> age_col = new TableColumn<>();
		age_col.setCellValueFactory(new PropertyValueFactory<Person, Number>("age"));
		age_col.setCellFactory(tc -> new TableCell<Person, Number>() {
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
		
		cart.getColumns().add(name_col);
		cart.getColumns().add(age_col);
		Label costLabel = new Label("0.0");
		
		ContextMenu ctx = new ContextMenu();
		MenuItem menu = new MenuItem("Delete Row");
		cart.setOnContextMenuRequested(event -> {
			ctx.show(cart, event.getScreenX(), event.getScreenY());
		});
		
		ctx.getItems().add(menu);
		menu.setOnAction(event -> {
			added.removeAll(cart.getSelectionModel().getSelectedItems());
			cart.refresh();
		});
		
				
		Button addToCart = new Button("Add To Cart");
		addToCart.setOnAction(event -> {
			added.add(people.get(choices.getItems().indexOf(choices.getValue())));
			cart.refresh();
		});
		
		Button compute = new Button("Calculate Cost");
		compute.setOnAction(event -> {
			double age_sum = 0.0;
			
			for (Person p : added) {
				age_sum += p.getAge();
			}
			
			costLabel.setText(Double.toString(age_sum));
		});
		
		window.setContent(cart);
		
		HBox div = new HBox();
		VBox div_child = new VBox();
		div_child.getChildren().addAll(choices, addToCart, compute, costLabel);
		div.getChildren().addAll(window, div_child);
		
		
		box.getChildren().addAll(table, div);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
