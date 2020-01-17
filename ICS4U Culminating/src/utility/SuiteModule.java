package utility;

import application.MainMenu;
import utility.Senior;
import utility.Caregiver;
import utility.Suite;

import application.TextDialog;
import application.LimitedTextField;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.util.converter.NumberStringConverter;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;

import javafx.geometry.Rectangle2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class SuiteModule extends Application {

	Rectangle2D screen;
	Scene scene;
	TabPane main;
	Tab tab_senior, tab_caregiver, tab_suite;
	VBox box_senior, box_caregiver, box_suite;
	HBox div_senior, div_caregiver, div_suite, divSearch_senior, divSearch_caregiver, divCost_senior, divSalary_caregiver;
	ChoiceBox<Long> choices_senior, choices_caregiver;
	TextField field_senior, field_caregiver;
	MenuButton file_senior, file_caregiver, table_options;
	MenuItem save_senior, load_senior, save_caregiver, load_caregiver, option_add, option_remove, option_meal;
    Label fileError_senior, fileError_caregiver;
	ContextMenu ctx_senior, ctx_caregiver;
	MenuItem add_senior, add_caregiver, delete_senior, delete_caregiver;
	
	TableView<Senior> table_senior;
	ObservableList<Senior> list_senior;
	FilteredList<Senior> filtered_senior;
	SortedList<Senior> sorted_senior;
	
	TableView<Caregiver> table_caregiver;
	ObservableList<Caregiver> list_caregiver;
	FilteredList<Caregiver> filtered_caregiver;
	SortedList<Caregiver> sorted_caregiver;
	
	final Suite[] rooms = new Suite[12];
	TableView<Suite> table_suite;
	ObservableList<Suite> list_suite;
	Button home_senior, home_caregiver, home_suite;
	
	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		main = new TabPane();
		
		home_senior = new Button("Home");
		home_senior.setOnAction(actionEvent ->  {
            stage.hide();
            MainMenu mainMenu = new MainMenu();
            try {
				mainMenu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		home_caregiver = new Button("Home");
		home_caregiver.setOnAction(actionEvent ->  {
            stage.hide();
            MainMenu mainMenu = new MainMenu();
            try {
				mainMenu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		home_suite = new Button("Home");
		home_suite.setOnAction(actionEvent ->  {
            stage.hide();
            MainMenu mainMenu = new MainMenu();
            try {
				mainMenu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		//SENIOR MODULE START
		tab_senior = new Tab("Senior");
		tab_senior.setClosable(false);
		table_senior = new TableView<>();
		table_senior.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                table_senior.setEditable(true);
		list_senior = FXCollections.observableArrayList();
		
		//SENIOR COLUMNS
		TableColumn<Senior, String> fName_senior = new TableColumn<>("First Name");
		fName_senior.setCellValueFactory(new PropertyValueFactory<>("fName"));
		fName_senior.setCellFactory(TextFieldTableCell.forTableColumn());
		fName_senior.setOnEditCommit(e -> {
			((Senior) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setFName(e.getNewValue());
		});	
		
		TableColumn<Senior, String> lName_senior = new TableColumn<>("Last Name");
		lName_senior.setCellValueFactory(new PropertyValueFactory<>("lName"));
		lName_senior.setCellFactory(TextFieldTableCell.forTableColumn());
		lName_senior.setOnEditCommit(e -> {
			((Senior) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setLName(e.getNewValue());
		});
		
		TableColumn<Senior, String> dob_senior = new TableColumn<>("Date of Birth");
		dob_senior.setCellValueFactory(new PropertyValueFactory<>("DOB"));
		dob_senior.setCellFactory(TextFieldTableCell.forTableColumn());
		dob_senior.setOnEditCommit(e -> {
			((Senior) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setDOB(e.getNewValue());
		});
		
		TableColumn<Senior, Number> age_senior = new TableColumn<>("Age");
		age_senior.setCellValueFactory(new PropertyValueFactory<>("age"));
		age_senior.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		age_senior.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Senior) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setAge(e.getNewValue());
			}
		});
		
		TableColumn<Senior, Number> roomid_senior = new TableColumn<>("Room ID");
		roomid_senior.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		roomid_senior.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		roomid_senior.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Senior) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setRoomID(e.getNewValue());
			}
		});
		
		TableColumn<Senior, Number> homeid_senior = new TableColumn<>("Home ID");
		homeid_senior.setCellValueFactory(new PropertyValueFactory<>("hID"));
		homeid_senior.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		homeid_senior.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Senior) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setHID(e.getNewValue());
			}
		});
		
		table_senior.getColumns().addAll(fName_senior, lName_senior, dob_senior, age_senior, roomid_senior,
				homeid_senior);
		
		//SENIOR SEARCH BAR: FILTERED AND SORTED LISTS
		filtered_senior = new FilteredList<>(list_senior, p -> true);
		field_senior = new TextField();
		field_senior.setPromptText("Search here");
		
		field_senior.textProperty().addListener((observable, oldValue, newValue) -> {
			filtered_senior.setPredicate(senior -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase().trim();
				
				if (senior.getFName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (senior.getLName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} 
				
				return false;
			});
		});
		
		sorted_senior = new SortedList<>(filtered_senior);
		sorted_senior.comparatorProperty().bind(table_senior.comparatorProperty());
		table_senior.setItems(sorted_senior);
		
		//SENIOR CONTEXTMENU
		ctx_senior = new ContextMenu();
		ctx_senior.setAutoHide(true);
		
		add_senior = new MenuItem("Add Senior");
		add_senior.setOnAction(e -> {
			addRowSenior(stage);
		});
		
		delete_senior = new MenuItem("Delete Senior");
		delete_senior.setOnAction(e -> {
			deleteRowSenior();
		});
		
		ctx_senior.getItems().addAll(add_senior, delete_senior);
		table_senior.setOnContextMenuRequested(e -> {
			ctx_senior.show(table_senior, e.getScreenX(), e.getScreenY());
		});
		
		//SENIOR FILE I/O MENUBUTTON
		fileError_senior = new Label("");
		file_senior = new MenuButton("File");
		save_senior = new MenuItem("Save");
		save_senior.setOnAction(e -> {
			saveSenior(new File("recent_senior.txt"), new File("previous_senior.txt"));
                        fileError_senior.setText(" Senior Data Saved.");
		});
		
		load_senior = new MenuItem("Load");
		load_senior.setOnAction(e -> {
			loadSenior(new File("recent_senior.txt"));
                        fileError_senior.setText(" Senior Data Loaded");
		});
		
		file_senior.getItems().addAll(save_senior, load_senior);
		
        //SENIOR COST BOX
		choices_senior = new ChoiceBox<>();
        TextField costField_senior = new TextField();
        costField_senior.setDisable(true);
        Button compute_senior = new Button("Compute Monthly Cost");
        compute_senior.setOnAction(e -> {
        	Senior ofInterest = list_senior.get(choices_senior.getItems().indexOf(choices_senior.getValue()));
        	costField_senior.setText(Double.toString(ofInterest.getMonthlyPayment()));

        });
        
        divCost_senior = new HBox();
        divCost_senior.getChildren().addAll(choices_senior, compute_senior, costField_senior);
        
                
        //SENIOR ACTIVITY GENERATOR
             
                
		div_senior = new HBox();
		div_senior.getChildren().addAll(file_senior, fileError_senior, home_senior);
		divSearch_senior = new HBox();
		divSearch_senior.getChildren().add(field_senior);
		divSearch_senior.setAlignment(Pos.CENTER);
		
		
		box_senior = new VBox();
		box_senior.setSpacing(5);
		box_senior.setPadding(new Insets(10, 0, 0, 10));
		box_senior.getChildren().addAll(div_senior, table_senior, divSearch_senior, divCost_senior);
		tab_senior.setContent(box_senior);
		
		
		
		
		//CAREGIVER MODULE START
		tab_caregiver = new Tab("Caregiver");
		tab_caregiver.setClosable(false);
		table_caregiver = new TableView<>();
		table_caregiver.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		list_caregiver = FXCollections.observableArrayList();
		
		//CAREGIVER TABLE COLUMNS
		TableColumn<Caregiver, String> fName_caregiver = new TableColumn<>("First Name");
		fName_caregiver.setCellValueFactory(new PropertyValueFactory<>("fName"));
		fName_caregiver.setCellFactory(TextFieldTableCell.forTableColumn());
		fName_caregiver.setOnEditCommit(e -> {
			((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setFName(e.getNewValue());
		});	
		
		TableColumn<Caregiver, String> lName_caregiver = new TableColumn<>("Last Name");
		lName_caregiver.setCellValueFactory(new PropertyValueFactory<>("lName"));
		lName_caregiver.setCellFactory(TextFieldTableCell.forTableColumn());
		lName_caregiver.setOnEditCommit(e -> {
			((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setLName(e.getNewValue());
		});	
		
		TableColumn<Caregiver, String> dob_caregiver = new TableColumn<>("Date of Birth");
		dob_caregiver.setCellValueFactory(new PropertyValueFactory<>("DOB"));
		dob_caregiver.setCellFactory(TextFieldTableCell.forTableColumn());
		dob_caregiver.setOnEditCommit(e -> {
			((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setDOB(e.getNewValue());
		});	
		
		TableColumn<Caregiver, Number> age_caregiver = new TableColumn<>("Age");
		age_caregiver.setCellValueFactory(new PropertyValueFactory<>("age"));
		age_caregiver.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		age_caregiver.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setAge(e.getNewValue());
			}
		});
		
		TableColumn<Caregiver, Number> roomid_caregiver = new TableColumn<>("Room ID");
		roomid_caregiver.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		roomid_caregiver.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		roomid_caregiver.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setRoomID(e.getNewValue());
			}
		});
		
		TableColumn<Caregiver, Number> empNum_caregiver = new TableColumn<>("Employee Number");
		empNum_caregiver.setCellValueFactory(new PropertyValueFactory<>("empNum"));
		empNum_caregiver.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		empNum_caregiver.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setEmpNum(e.getNewValue());
			}
		});
		
		TableColumn<Caregiver, Number> hours_caregiver = new TableColumn<>("Weekly Hours");
		hours_caregiver.setCellValueFactory(new PropertyValueFactory<>("hours"));
		hours_caregiver.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		hours_caregiver.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setHours(e.getNewValue());
			}
		});
		
		TableColumn<Caregiver, Number> wage_caregiver = new TableColumn<>("Hourly Wage");
		wage_caregiver.setCellValueFactory(new PropertyValueFactory<>("wage"));
		wage_caregiver.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		wage_caregiver.setOnEditCommit(e -> {
			if (e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setWage(e.getNewValue());
			}
		});
		
		table_caregiver.getColumns().addAll(fName_caregiver, lName_caregiver, dob_caregiver, age_caregiver,
				roomid_caregiver, empNum_caregiver, hours_caregiver, wage_caregiver);
		
		//CAREGIVER SEARCH BAR: FILTERED AND SORTED LISTS
		filtered_caregiver = new FilteredList<>(list_caregiver, p -> true);
		field_caregiver = new TextField();
		field_caregiver.setPromptText("Search here");
		
		field_caregiver.textProperty().addListener((observable, oldValue, newValue) -> {
			filtered_caregiver.setPredicate(caregiver -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase().trim();
				
				if (caregiver.getFName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (caregiver.getLName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} 
				
				return false;
			});
		});
		
		sorted_caregiver = new SortedList<>(filtered_caregiver);
		sorted_caregiver.comparatorProperty().bind(table_caregiver.comparatorProperty());
		table_caregiver.setItems(sorted_caregiver);
		
		//CAREGIVER CONTEXTMENUS
		ctx_caregiver = new ContextMenu();
		ctx_caregiver.setAutoHide(true);
		
		add_caregiver = new MenuItem("Add Caregiver");
		add_caregiver.setOnAction(e -> {
			addRowCaregiver(stage);
		});
		
		delete_caregiver = new MenuItem("Delete Caregiver");
		delete_caregiver.setOnAction(e -> {
			deleteRowCaregiver();
		});
		
		ctx_caregiver.getItems().addAll(add_caregiver, delete_caregiver);
		table_caregiver.setOnContextMenuRequested(e -> {
			ctx_caregiver.show(table_caregiver, e.getScreenX(), e.getScreenY());
		});
		
		
		file_caregiver = new MenuButton("File");
                fileError_caregiver = new Label("");
                
		save_caregiver = new MenuItem("Save");
		save_caregiver.setOnAction(e -> {
			saveCaregiver(new File("recent_caregiver.txt"), new File("previous_caregiver.txt"));
                        fileError_caregiver.setText(" Caregiver Data Saved");
		});
		
		load_caregiver = new MenuItem("Load");
		load_caregiver.setOnAction(e -> {
			loadCaregiver(new File("recent_caregiver.txt"));
                        fileError_caregiver.setText(" Caregiver Data Loaded");
		});
		
		file_caregiver.getItems().addAll(save_caregiver, load_caregiver);
		
		//ACTIVITY SALARY BOX
		choices_caregiver = new ChoiceBox<>();
		TextField costField_caregiver = new TextField();
		costField_caregiver.setDisable(true);
		Button compute_caregiver = new Button("Compute Yearly Salary");
		compute_caregiver.setOnAction(e -> {
			Caregiver ofInterest = list_caregiver.get(choices_caregiver.getItems().indexOf(choices_caregiver.getValue()));
			costField_caregiver.setText(Double.toString(ofInterest.getAnnualPay()));
		});
		
		
		divSalary_caregiver = new HBox();
		divSalary_caregiver.getChildren().addAll(choices_caregiver, compute_caregiver, costField_caregiver);
		
		divSearch_caregiver = new HBox();
		divSearch_caregiver.getChildren().add(field_caregiver);
		divSearch_caregiver.setAlignment(Pos.CENTER);
		
		div_caregiver = new HBox();
		div_caregiver.getChildren().addAll(file_caregiver, fileError_caregiver, home_caregiver);
		
		box_caregiver = new VBox();
		box_caregiver.setSpacing(5);
		box_caregiver.setPadding(new Insets(10, 0, 0, 10));
		box_caregiver.getChildren().addAll(div_caregiver, table_caregiver, divSearch_caregiver, divSalary_caregiver);
		tab_caregiver.setContent(box_caregiver);
		
		//SUITE MODULE SETUP
		table_suite = new TableView<>();
		table_suite.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		list_suite = FXCollections.observableArrayList();
		buildSuite();
		
		// SUITE COLUMNS
		TableColumn<Suite, Number> number_suite = new TableColumn<>("Suite Number");
		number_suite.setCellValueFactory(new PropertyValueFactory<>("suiteNumber"));
		number_suite.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		TableColumn<Suite, String> style_suite = new TableColumn<>("Suite Style");
		style_suite.setCellValueFactory(new PropertyValueFactory<>("styleName"));
		style_suite.setCellFactory(TextFieldTableCell.forTableColumn());

		table_suite.getColumns().addAll(number_suite, style_suite);
		table_suite.setItems(list_suite);
		
		//SUITE TABLE OPTIONS
		table_options = new MenuButton("Table Options");
		option_add = new MenuItem("Add Person");
		option_add.setOnAction(e -> {
			addRowSuite(stage);
		});
		
		option_remove = new MenuItem("Remove Person");
		option_remove.setOnAction(e -> {
			removeRowSuite(stage);
		});
		
		option_meal = new MenuItem("Cart Meal");
		option_meal.setOnAction(e -> {
			cartMeal();
		});
		
		table_options.getItems().addAll(option_add, option_remove, option_meal);
		
		HBox h_box_suite = new HBox();
		h_box_suite.setSpacing(5);
		h_box_suite.getChildren().addAll(table_options, home_suite);
		
		box_suite = new VBox();
		box_suite.setSpacing(5);
		box_suite.setPadding(new Insets(10, 0, 0, 10));
		box_suite.getChildren().addAll(h_box_suite, table_suite);
		
		tab_suite = new Tab("Suite");
		tab_suite.setClosable(false);
		tab_suite.setContent(box_suite);
		
		main.getTabs().addAll(tab_senior, tab_caregiver, tab_suite);
		scene = new Scene(main, screen.getWidth(), screen.getHeight());
		stage.setTitle("Suite Module");
        scene.getStylesheets().add("Viper.css");
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

        //PRIVATE METHODS FOR CONTEXTMENUS IN THE SENIOR AND CAREGIVER MODULES
	
		private void updateChoicesSenior(Senior senior, boolean add) {
			if (add) {
				choices_senior.getItems().add(senior.getHID());
			} else {
				choices_senior.getItems().remove(senior.getHID());
			}
		}
		
		private void updateChoicesCaregiver(Caregiver caregiver, boolean add) {
			if (add) {
				choices_caregiver.getItems().add(caregiver.getEmpNum());
			} else {
				choices_caregiver.getItems().remove(caregiver.getEmpNum());			
			}
		}
	
        private void addRowSenior(Stage stage) {
            TextDialog dialog_senior = new TextDialog(stage);
            dialog_senior.setHeaderContent("Add a Senior to the Table");
            dialog_senior.setWindowTitle("Add Row");
            
            LimitedTextField fNameField_senior = new LimitedTextField();
            fNameField_senior.setAsAlphaOnly();
            dialog_senior.addOpenedPair(new Label("First Name"), true, fNameField_senior, false);
            
            LimitedTextField lNameField_senior = new LimitedTextField();
            lNameField_senior.setAsAlphaOnly();
            dialog_senior.addOpenedPair(new Label("Last Name"), true, lNameField_senior, false);
            
            LimitedTextField ageField_senior = new LimitedTextField();
            ageField_senior.setAsNumericOnly();
            ageField_senior.setMaxLength(3);
            dialog_senior.addOpenedPair(new Label("Age"), true, ageField_senior, true);
            
            dialog_senior.addDateBox(new Label("Date of Birth"));
            
            LimitedTextField roomidField_senior = new LimitedTextField();
            roomidField_senior.setAsNumericOnly();
            roomidField_senior.setMaxLength(2);
            dialog_senior.addOpenedPair(new Label("Room ID"), true, roomidField_senior, false);
            
            LimitedTextField hourField_senior = new LimitedTextField();
            hourField_senior.setAsNumericOnly();
            hourField_senior.setMaxLength(2);
            dialog_senior.addOpenedPair(new Label("Care Hours Per Day"), true, hourField_senior, false);
            
            LimitedTextField homeidField_senior = new LimitedTextField();
            homeidField_senior.setAsNumericOnly();
            homeidField_senior.setMaxLength(4);
            dialog_senior.addOpenedPair(new Label("Home ID"), true, homeidField_senior, false);
            
            dialog_senior.primeButtons();
            dialog_senior.display();
            
            if (dialog_senior.isSubmitPressed) {
                ArrayList<HashMap<TextField, String>> resp = dialog_senior.getResponses();
                String fName_resp = resp.get(0).get(dialog_senior.getFields().get(0));
                String lName_resp = resp.get(1).get(dialog_senior.getFields().get(1));
                int age_resp = Integer.parseInt(resp.get(2).get(dialog_senior.getFields().get(2)));
                
                int month_resp = dialog_senior.getDateBox().getMonthBox().getValue();
                int day_resp = dialog_senior.getDateBox().getDayBox().getValue();
                int year_resp = dialog_senior.getDateBox().getYearBox().getValue();
                
                String dob_resp = Integer.toString(month_resp) + "/" + Integer.toString(day_resp)
                        + "/" + Integer.toString(year_resp);
                
                int roomid_resp = Integer.parseInt(resp.get(3).get(dialog_senior.getFields().get(3)));
                double hour_resp = Double.parseDouble(resp.get(4).get(dialog_senior.getFields().get(4)));
                long homeid_resp = Long.parseLong(resp.get(5).get(dialog_senior.getFields().get(5)));
                
                Senior add_senior = new Senior(fName_resp, lName_resp, dob_resp, age_resp, roomid_resp, 
                		hour_resp, homeid_resp);
                
                list_senior.add(add_senior);
                updateChoicesSenior(add_senior, true);
                
                table_senior.refresh();
            }
        }
        
        private void deleteRowSenior() {
        	Senior removed = table_senior.getSelectionModel().getSelectedItem();
            list_senior.removeAll(removed);
            updateChoicesSenior(removed, false);
            table_senior.refresh();
           
        }
        
        private void addRowCaregiver(Stage stage) {
            TextDialog dialog_caregiver = new TextDialog(stage);
            dialog_caregiver.setHeaderContent("Add a Caregiver to the Table");
            dialog_caregiver.setWindowTitle("Add Row");
            
            LimitedTextField fNameField_caregiver = new LimitedTextField();
            fNameField_caregiver.setAsAlphaOnly();
            dialog_caregiver.addOpenedPair(new Label("First Name"), true, fNameField_caregiver, false);
            
            LimitedTextField lNameField_caregiver = new LimitedTextField();
            lNameField_caregiver.setAsAlphaOnly();
            dialog_caregiver.addOpenedPair(new Label("Last Name"), true, lNameField_caregiver, false);
            
            LimitedTextField ageField_caregiver = new LimitedTextField();
            ageField_caregiver.setAsNumericOnly();
            ageField_caregiver.setMaxLength(3);
            dialog_caregiver.addOpenedPair(new Label("Age"), true, ageField_caregiver, true);
            
            dialog_caregiver.addDateBox(new Label("Date of Birth"));
            
            LimitedTextField roomidField_caregiver = new LimitedTextField();
            roomidField_caregiver.setAsNumericOnly();
            roomidField_caregiver.setMaxLength(2);
            dialog_caregiver.addOpenedPair(new Label("Room ID"), true, roomidField_caregiver, false);
            
            LimitedTextField empNumField_caregiver = new LimitedTextField();
            empNumField_caregiver.setAsNumericOnly();
            empNumField_caregiver.setMaxLength(4);
            dialog_caregiver.addOpenedPair(new Label("Employee #"), true, empNumField_caregiver, false);
            
            LimitedTextField hoursField_caregiver = new LimitedTextField();
            hoursField_caregiver.setAsNumericOnly();
            hoursField_caregiver.setMaxLength(3);
            dialog_caregiver.addOpenedPair(new Label("Weekly Hours"), true, hoursField_caregiver, false);
            
            LimitedTextField wageField_caregiver = new LimitedTextField();
            wageField_caregiver.setAsNumericOnly();
            wageField_caregiver.setMaxLength(3);
            dialog_caregiver.addOpenedPair(new Label("Hourly Wage"), true, wageField_caregiver, false);
            
            dialog_caregiver.primeButtons();
            dialog_caregiver.display();
            
            if (dialog_caregiver.isSubmitPressed) {
                ArrayList<HashMap<TextField, String>> resp = dialog_caregiver.getResponses();
                String fName_resp = resp.get(0).get(dialog_caregiver.getFields().get(0));
                String lName_resp = resp.get(1).get(dialog_caregiver.getFields().get(1));
                int age_resp = Integer.parseInt(resp.get(2).get(dialog_caregiver.getFields().get(2)));
                
                int month_resp = dialog_caregiver.getDateBox().getMonthBox().getValue();
                int day_resp = dialog_caregiver.getDateBox().getDayBox().getValue();
                int year_resp = dialog_caregiver.getDateBox().getYearBox().getValue();
                
                String dob_resp = Integer.toString(month_resp) + "/" + Integer.toString(day_resp)
                        + "/" + Integer.toString(year_resp);
                
                int roomid_resp = Integer.parseInt(resp.get(3).get(dialog_caregiver.getFields().get(3)));
                long empNum_resp = Long.parseLong(resp.get(4).get(dialog_caregiver.getFields().get(4)));
                double hours_resp = Double.parseDouble(resp.get(5).get(dialog_caregiver.getFields().get(5)));
                double wage_resp = Double.parseDouble(resp.get(6).get(dialog_caregiver.getFields().get(6)));
              
                Caregiver add_caregiver = new Caregiver(fName_resp, lName_resp, dob_resp, age_resp,
                        roomid_resp, empNum_resp, hours_resp, wage_resp);
                
                list_caregiver.add(add_caregiver);
                updateChoicesCaregiver(add_caregiver, true);
                table_caregiver.refresh();
            }
        }
        
        private void deleteRowCaregiver() {
        	Caregiver removed = table_caregiver.getSelectionModel().getSelectedItem();
            list_caregiver.removeAll(removed);
            updateChoicesCaregiver(removed, false);
            table_caregiver.refresh();
        }
        
        private void buildSuite() {
        	for (int i = 0; i < 3; i++) {
        		rooms[i] = new Suite(i + 1, Suite.Type.SINGLE);
        	}
        	
        	for (int i = 3; i < 6; i++) {
        		rooms[i] = new Suite(i + 1, Suite.Type.DOUBLE);
        	}
        	
        	for (int i = 6; i < 9; i++) {
        		rooms[i] = new Suite(i + 1, Suite.Type.SINGLE_KITCHEN);
        	}
        	
        	for (int i = 9; i < 12; i++) {
        		rooms[i] = new Suite(i + 1, Suite.Type.DOUBLE_KITCHEN);
        	}
        	
        	for (int i = 0; i < rooms.length; i++) {
        		list_suite.add(rooms[i]);
        	}
        }
        
        private void addRowSuite(Stage stage) {
        	//ChoiceDialog
        	String choice = "";
        	
        	List<String> choices = new ArrayList<String>();
        	choices.add("Senior");
        	choices.add("Caregiver");
        	
        	ChoiceDialog<String> choice_dialog = new ChoiceDialog<>("Senior", choices);
        	choice_dialog.setTitle("Add Person");
        	choice_dialog.setHeaderText("Add a person to the Suite.");
        	choice_dialog.setContentText("Choose type: ");
        	
        	Optional<String> result = choice_dialog.showAndWait();
        	if(result.isPresent()) {
        		choice = result.get();
        	}
        	
        	if (choice.equals("Senior")) {
        		TextDialog seniorSuite_dialog = new TextDialog(stage);
        		seniorSuite_dialog.setHeaderContent("Add a Senior to a Suite.");
        		seniorSuite_dialog.setWindowTitle("Add Senior");
        		
        		LimitedTextField roomNumber_senior = new LimitedTextField();
        		roomNumber_senior.setAsNumericOnly();
        		roomNumber_senior.setMaxLength(2);
        		seniorSuite_dialog.addOpenedPair(new Label("Room Number: "), true, roomNumber_senior, false);
        		
        		LimitedTextField homeid_senior = new LimitedTextField();
        		homeid_senior.setAsNumericOnly();
        		homeid_senior.setMaxLength(4);
        		seniorSuite_dialog.addOpenedPair(new Label("Home ID: "), true, homeid_senior, false);
        		
        		LimitedTextField currentYear_senior = new LimitedTextField();
        		currentYear_senior.setAsNumericOnly();
        		currentYear_senior.setMaxLength(4);
        		seniorSuite_dialog.addOpenedPair(new Label("Current Year: "), true, currentYear_senior, true);
        		
        		seniorSuite_dialog.addDateBox(new Label("Date Added: "));
        		
        		seniorSuite_dialog.primeButtons();
        		seniorSuite_dialog.display();
        		
        		if (seniorSuite_dialog.isSubmitPressed) {
        			ArrayList<HashMap<TextField, String>> resp = seniorSuite_dialog.getResponses();
        			int roomNum = Integer.parseInt(resp.get(0).get(seniorSuite_dialog.getFields().get(0)));
        			long homeid = Long.parseLong(resp.get(1).get(seniorSuite_dialog.getFields().get(1)));
        			
        			Senior searched = Senior.searchHomeID(homeid, list_senior, 0, list_senior.size());
        			
        			if (searched != null) {
        				rooms[roomNum - 1].addOccupant(searched);
        			}
        		}
        	} else {
        		TextDialog caregiver_dialog = new TextDialog(stage);
        		caregiver_dialog.setHeaderContent("Add a Caregiver to the Suite.");
        		caregiver_dialog.setWindowTitle("Add Caregiver");
        		
        		LimitedTextField roomNumber_caregiver = new LimitedTextField();
        		roomNumber_caregiver.setAsNumericOnly();
        		roomNumber_caregiver.setMaxLength(2);
        		caregiver_dialog.addOpenedPair(new Label("Room Number: "), true, roomNumber_caregiver, false);
        		
        		LimitedTextField empNum_caregiver = new LimitedTextField();
        		empNum_caregiver.setAsNumericOnly();
        		empNum_caregiver.setMaxLength(4);
        		caregiver_dialog.addOpenedPair(new Label("Home ID: "), true, empNum_caregiver, false);
        		
        		LimitedTextField currentYear_senior = new LimitedTextField();
        		currentYear_senior.setAsNumericOnly();
        		currentYear_senior.setMaxLength(4);
        		caregiver_dialog.addOpenedPair(new Label("Current Year: "), true, currentYear_senior, true);
        		
        		caregiver_dialog.addDateBox(new Label("Date Added: "));
        		caregiver_dialog.primeButtons();
        		caregiver_dialog.display();
        		
        		if (caregiver_dialog.isSubmitPressed) {
        			ArrayList<HashMap<TextField, String>> resp = caregiver_dialog.getResponses();
        			int roomNum = Integer.parseInt(resp.get(0).get(caregiver_dialog.getFields().get(0)));
        			long empnum = Long.parseLong(resp.get(1).get(caregiver_dialog.getFields().get(1)));
        			
        			
        			
        			if (searched != null) {
        				rooms[roomNum - 1].addOccupant(searched);
        			}
        		}
        	}
        }
        
        private void removeRowSuite(Stage stage) {
        	//ChoiceDialog
        	String choice = "";
        	
        	List<String> choices = new ArrayList<String>();
        	choices.add("Senior");
        	choices.add("Caregiver");
        	
        	ChoiceDialog<String> choice_dialog = new ChoiceDialog<>("Senior", choices);
        	choice_dialog.setTitle("Remove Person");
        	choice_dialog.setHeaderText("Remove a person from the Suite.");
        	choice_dialog.setContentText("Choose type: ");
        	
        	Optional<String> result = choice_dialog.showAndWait();
        	if(result.isPresent()) {
        		choice = result.get();
        	}
        	
        	if (choice.equals("Senior")) {
        		
        	}
        }
        
        private void cartMeal() {
        	
        }
        
        //FILE I/O FOR THE SENIOR AND CAREGIVER MODULES
        
        private void copy(File source, File target) {
            try(FileInputStream in = new FileInputStream(source);
                FileOutputStream out = new FileOutputStream(target);
                FileChannel from = in.getChannel();
                FileChannel to = out.getChannel();
                    ){
                
                to.transferFrom(from, 0, from.size());
                
            } catch (IOException e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            } catch (Exception e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        private void saveSenior(File source, File target) {
            copy(source, target);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));
                    ) {
                for (Senior senior : list_senior) {
                    if (senior != null) {
                        writer.write(senior.getFName());
                        writer.write(System.getProperty("line.separator"));
                        writer.write(senior.getLName());
                        writer.write(System.getProperty("line.separator"));
                        writer.write(senior.getDOB());
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Integer.toString(senior.getAge()));
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Integer.toString(senior.getRoomID()));
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Double.toString(senior.getHours()));
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Long.toString(senior.getHID()));
                        writer.write(System.getProperty("line.separator"));
                    }
                }
            } catch (IOException e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            } catch (Exception e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            }
                
        }
        
        private void loadSenior(File source) {
            list_senior.removeAll(list_senior);
            
            try (BufferedReader reader = new BufferedReader(new FileReader(source));
                    ) {
                String line;
                
                while ((line = reader.readLine()) != null) {
                    list_senior.add(new Senior(line, reader.readLine(), reader.readLine(), 
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), 
                    Double.parseDouble(reader.readLine()), Long.parseLong(reader.readLine())));
                }
                
            } catch (IOException e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            } catch (Exception e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        private void saveCaregiver(File source, File target) {
                        copy(source, target);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));
                    ) {
                for (Caregiver caregiver : list_caregiver) {
                    if (caregiver != null) {
                        writer.write(caregiver.getFName());
                        writer.write(System.getProperty("line.separator"));
                        writer.write(caregiver.getLName());
                        writer.write(System.getProperty("line.separator"));
                        writer.write(caregiver.getDOB());
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Integer.toString(caregiver.getAge()));
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Integer.toString(caregiver.getRoomID()));
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Long.toString(caregiver.getEmpNum()));
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Double.toString(caregiver.getHours()));
                        writer.write(System.getProperty("line.separator"));
                        writer.write(Double.toString(caregiver.getWage()));
                        writer.write(System.getProperty("line.separator"));
                    }
                }
            } catch (IOException e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            } catch (Exception e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        private void loadCaregiver(File source) {
            list_caregiver.removeAll(list_caregiver);
            
            try (BufferedReader reader = new BufferedReader(new FileReader(source));
                    ) {
                String line;
                
                while ((line = reader.readLine()) != null) {
                    list_caregiver.add(new Caregiver(line, reader.readLine(), reader.readLine(),
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
                    Long.parseLong(reader.readLine()), Double.parseDouble(reader.readLine()),
                    Double.parseDouble(reader.readLine())));
                }
                
            } catch (IOException e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            } catch (Exception e) {
                Logger.getLogger(SuiteModule.class.getName()).log(Level.SEVERE, null, e);
            }
        }
}
