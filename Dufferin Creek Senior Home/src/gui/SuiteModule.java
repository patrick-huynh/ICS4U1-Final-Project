package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import java.io.File;
import java.text.NumberFormat;
import java.text.DecimalFormat;

import utility.Senior;
import utility.Activity;
import utility.Caregiver;
import utility.Suite;
import utility.TransferProtocol;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.stage.Screen;

import javafx.geometry.Rectangle2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class SuiteModule extends Application {

	final NumberFormat nf = new DecimalFormat("####");
	Random rand = new Random();
	
	Rectangle2D screen;
	Scene scene;
	TabPane main;
	Tab tab_senior, tab_caregiver, tab_suite;
	VBox box_senior, box_caregiver, box_suite;
	HBox div_senior, divSearch_senior, div_caregiver, divSearch_caregiver, div_suite;
	
	MenuButton file_senior, file_caregiver, table_options;
	MenuItem save_senior, load_senior, reload_senior, save_caregiver, load_caregiver, reload_caregiver,
	save_suite, load_suite, option_add, option_remove;
    Label filePrompt_senior, filePrompt_caregiver, filePrompt_suite;
	ContextMenu ctx_senior, ctx_caregiver;
	MenuItem add_senior, add_caregiver, delete_senior, delete_caregiver;
	
	TableView<Activity> table_activities;
	ObservableList<Activity> list_activities;
	FilteredList<Activity> filtered_activities;
	SortedList<Activity> sorted_activities;
	
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
	
	ChoiceBox<Long> choices_senior, choices_caregiver;
	
	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		main = new TabPane();
		//Home Button created to each tab will lead to the MainMenu
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
		
		//SENIOR TABLE SETUP
		table_senior = new TableView<>();
		table_senior.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table_senior.setEditable(true);
        table_senior.setPlaceholder(new Label("Right-click to add a senior or load data into the table."));
		list_senior = FXCollections.observableArrayList();
		
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
			if (e.getNewValue() != null && e.getNewValue() instanceof Number) {
				((Senior) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setAge(e.getNewValue());
			}
		});
		
		TableColumn<Senior, Number> roomid_senior = new TableColumn<>("Room ID");
		roomid_senior.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		roomid_senior.setCellFactory(tc -> new TableCell<Senior, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value, empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});

		TableColumn<Senior, Number> homeid_senior = new TableColumn<>("Home ID");
		homeid_senior.setCellValueFactory(new PropertyValueFactory<>("hID"));
		homeid_senior.setCellFactory(tc -> new TableCell<Senior, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value, empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});
                
		table_senior.getColumns().addAll(fName_senior, lName_senior, dob_senior, age_senior, roomid_senior,
				homeid_senior);
		
		
		//SENIOR FILE MENUBUTTON SETUP
		filePrompt_senior = new Label();
		file_senior = new MenuButton("File");
		save_senior = new MenuItem("Save");
		save_senior.setOnAction(e -> {
			TransferProtocol.saveSenior(new File("recent_senior.txt"), new File("previous_senior.txt"), list_senior);
            filePrompt_senior.setText(" Senior Data Saved.");
		});
		
		load_senior = new MenuItem("Load");
		load_senior.setOnAction(e -> {
			TransferProtocol.loadSenior(new File("recent_senior.txt"), list_senior, choices_senior);
            filePrompt_senior.setText(" Senior Data Loaded.");
		});
		
		reload_senior = new MenuItem("Reload");
		reload_senior.setOnAction(e -> {
			TransferProtocol.reloadSenior(new File("recent_senior.txt"), new File("previous_senior.txt"), 
					list_senior, choices_senior);
			filePrompt_senior.setText(" Senior Data Reloaded.");
		});
		
		file_senior.getItems().addAll(save_senior, load_senior, reload_senior);
		
		div_senior = new HBox();
		div_senior.getChildren().addAll(file_senior, filePrompt_senior, home_senior);
		
		
		//SENIOR CONTEXTMENU SETUP
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
		
		
		//SENIOR SEARCH BAR: FILTERED AND SORTED LISTS
		filtered_senior = new FilteredList<>(list_senior, p -> true);
		TextField field_senior = new TextField();
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
				} else if (senior.getDOB().contains(lowerCaseFilter)) {
					return true;
				}
				
				return false;
			});
		});
		
		sorted_senior = new SortedList<>(filtered_senior);
		sorted_senior.comparatorProperty().bind(table_senior.comparatorProperty());
		table_senior.setItems(sorted_senior);
		
		divSearch_senior = new HBox();
		divSearch_senior.getChildren().add(field_senior);
		divSearch_senior.setAlignment(Pos.CENTER_LEFT);
		
		//SENIOR COST BOX
		choices_senior = new ChoiceBox<>();
        TextField costField_senior = new TextField();
        costField_senior.setDisable(true);
        Button compute_senior = new Button("Compute Monthly Cost");
        compute_senior.setOnAction(e -> {
        	if (choices_senior.getValue() != null && choices_senior.getItems().indexOf(choices_senior.getValue()) >= 0) {
            	Senior ofInterest = list_senior.get(choices_senior.getItems().indexOf(choices_senior.getValue()));
            	costField_senior.setText(Double.toString(ofInterest.getMonthlyPayment()));
        	}

        });

		//ACTIVITIES TABLE/GENERATOR
		table_activities = new TableView<>();
		table_activities.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		list_activities = FXCollections.observableArrayList();
		list_activities.addAll(Activity.randomGen(8));
		
		Label activity_label = new Label("Activities");
		activity_label.setId("activityLabel");
		
		TableColumn<Activity, String> name_activity = new TableColumn<>("Name");
		name_activity.setCellValueFactory(new PropertyValueFactory<>("name"));
		name_activity.setCellFactory(TextFieldTableCell.forTableColumn());
		TableColumn<Activity, String> startTime_activity = new TableColumn<>("Start Time");
		startTime_activity.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		startTime_activity.setCellFactory(TextFieldTableCell.forTableColumn());
		TableColumn<Activity, String> endTime_activity = new TableColumn<>("End Time");
		endTime_activity.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		endTime_activity.setCellFactory(TextFieldTableCell.forTableColumn());
		TableColumn<Activity, Boolean> type_activity = new TableColumn<>("Outdoor");
		type_activity.setCellValueFactory(cellData -> cellData.getValue().outdoorProperty());
		
		table_activities.getColumns().addAll(name_activity, startTime_activity, endTime_activity, type_activity);
		table_activities.setItems(list_activities);
        
        HBox divCost_senior = new HBox();
        divCost_senior.getChildren().addAll(choices_senior, compute_senior, costField_senior);
		

		VBox activityGenerator = new VBox();
		activityGenerator.setPrefWidth(1000);
		activityGenerator.getChildren().addAll(activity_label, table_activities);
		VBox box_divSearchCost = new VBox();
		box_divSearchCost.setPrefWidth(400);
		box_divSearchCost.setSpacing(15);
		box_divSearchCost.getChildren().addAll(divSearch_senior, divCost_senior);

		HBox box = new HBox(25);
		box.getChildren().addAll(box_divSearchCost, activityGenerator);
		
		box_senior = new VBox();
		box_senior.setSpacing(5);
		box_senior.setPadding(new Insets(10, 0, 0, 10));
		box_senior.getChildren().addAll(div_senior, table_senior, box);
		tab_senior.setContent(box_senior);
		main.getTabs().add(tab_senior);
		

		
		//CAREGIVER MODULE START
		tab_caregiver = new Tab("Caregiver");
		tab_caregiver.setClosable(false);
		
		//CAREGIVER TABLE SETUP
		table_caregiver = new TableView<>();
		table_caregiver.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table_caregiver.setEditable(true);
        table_caregiver.setPlaceholder(new Label("Right-click to add a caregiver or load data into the table."));
        list_caregiver = FXCollections.observableArrayList();
		
		//CAREGIVER COLUMNS SETUP
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
			if (e.getNewValue() != null && e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setAge(e.getNewValue());
			}
		});
		
		TableColumn<Caregiver, Number> roomid_caregiver = new TableColumn<>("Room ID");
		roomid_caregiver.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		roomid_caregiver.setCellFactory(tc -> new TableCell<Caregiver, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value, empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});
		
		TableColumn<Caregiver, Number> empNum_caregiver = new TableColumn<>("Employee Number");
		empNum_caregiver.setCellValueFactory(new PropertyValueFactory<>("empNum"));
		empNum_caregiver.setCellFactory(tc -> new TableCell<Caregiver, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value, empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});
		
		TableColumn<Caregiver, Number> hours_caregiver = new TableColumn<>("Weekly Hours");
		hours_caregiver.setCellValueFactory(new PropertyValueFactory<>("hours"));
		hours_caregiver.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		hours_caregiver.setOnEditCommit(e -> {
			if (e.getNewValue() != null && e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setHours(e.getNewValue());
			}
		});
		
		TableColumn<Caregiver, Number> wage_caregiver = new TableColumn<>("Hourly Wage");
		wage_caregiver.setCellValueFactory(new PropertyValueFactory<>("wage"));
		wage_caregiver.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		wage_caregiver.setOnEditCommit(e -> {
			if (e.getNewValue() != null && e.getNewValue() instanceof Number) {
				((Caregiver) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setWage(e.getNewValue());
			}
		});
		
		table_caregiver.getColumns().addAll(fName_caregiver, lName_caregiver, dob_caregiver, age_caregiver,
				roomid_caregiver, empNum_caregiver, hours_caregiver, wage_caregiver);
        
		//CAREGIVER FILE MENUBUTTONS SETUP
        filePrompt_caregiver = new Label();
		file_caregiver = new MenuButton("File");
        
        save_caregiver = new MenuItem("Save");
        save_caregiver.setOnAction(e -> {
        	TransferProtocol.saveCaregiver(new File("recent_caregiver.txt"), new File("previous_caregiver.txt"), 
        			list_caregiver);
            filePrompt_caregiver.setText(" Caregiver Data Saved.");
        });

        load_caregiver = new MenuItem("Load");
        load_caregiver.setOnAction(e -> {
        	TransferProtocol.loadCaregiver(new File("recent_caregiver.txt"), list_caregiver, choices_caregiver);
            filePrompt_caregiver.setText(" Caregiver Data Loaded.");
        });
        
        reload_caregiver = new MenuItem("Reload");
        reload_caregiver.setOnAction(e -> {
        	TransferProtocol.reloadCaregiver(new File("recent_caregiver.txt"), new File("previous_caregiver.txt"), 
        			list_caregiver, choices_caregiver);
        	filePrompt_caregiver.setText(" Caregiver Data Reloaded.");
        });

        file_caregiver.getItems().addAll(save_caregiver, load_caregiver, reload_caregiver);		
		
		div_caregiver = new HBox();
		div_caregiver.getChildren().addAll(file_caregiver, filePrompt_caregiver, home_caregiver);
		
		//CAREGIVER CONTEXTMENU SETUP
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
		
		
		//CAREGIVER SEARCH BAR: FILTERED LIST AND SORTED LIST
		filtered_caregiver = new FilteredList<>(list_caregiver, p -> true);
		TextField field_caregiver = new TextField();
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
				} else if (caregiver.getDOB().contains(lowerCaseFilter)) {
					return true;
				}
				
				return false;
			});
		});
		
		sorted_caregiver = new SortedList<>(filtered_caregiver);
		sorted_caregiver.comparatorProperty().bind(table_caregiver.comparatorProperty());
		table_caregiver.setItems(sorted_caregiver);
		
		divSearch_caregiver = new HBox();
		divSearch_caregiver.getChildren().add(field_caregiver);
		divSearch_caregiver.setAlignment(Pos.CENTER);
		
		//CAREGIVER COST BOX
		choices_caregiver = new ChoiceBox<>();
		ChoiceBox<String> payChoices = new ChoiceBox<>();
		payChoices.getItems().addAll("Weekly", "Monthly", "Yearly");
		payChoices.setValue("Yearly");
		
		TextField costField_caregiver = new TextField();
		costField_caregiver.setDisable(true);
		Button compute_caregiver = new Button("Compute");
		compute_caregiver.setOnAction(e -> {
			if (choices_caregiver.getValue() != null) {
				Caregiver ofInterest = list_caregiver.get(choices_caregiver.getItems().indexOf(
						choices_caregiver.getValue()));
				
				switch (payChoices.getValue()) {
				case "Weekly":
					ofInterest.calculateWeeklyPay();
					costField_caregiver.setText(Double.toString(Math.round(ofInterest.getWeeklyPay() * 100.0) / 100.0));
					break;
				case "Monthly":
					ofInterest.calculateMonthlyPay();
					costField_caregiver.setText(Double.toString(Math.round(ofInterest.getMonthlyPay() * 100.0) / 100.0));
					break;
				case "Yearly":
					ofInterest.calculateAnnualPay();
					costField_caregiver.setText(Double.toString(Math.round(ofInterest.getAnnualPay() * 100.0) / 100.0));
					break;
			}
			}
		});
		
		HBox divCost_caregiver = new HBox();
		divCost_caregiver.getChildren().addAll(choices_caregiver, payChoices, compute_caregiver, costField_caregiver);
		
		box_caregiver = new VBox();
		box_caregiver.setSpacing(5);
		box_caregiver.setPadding(new Insets(10, 0, 0, 10));
		box_caregiver.getChildren().addAll(div_caregiver, table_caregiver, divSearch_caregiver, divCost_caregiver);
		tab_caregiver.setContent(box_caregiver);
		main.getTabs().add(tab_caregiver);
		
		//SUITE MODULE START
		table_suite = new TableView<>();
		table_suite.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		list_suite = FXCollections.observableArrayList();
		buildSuite();
		
		// SUITE COLUMNS
		TableColumn<Suite, Number> number_suite = new TableColumn<>("Suite Number");
		number_suite.setCellValueFactory(new PropertyValueFactory<>("suiteNumber"));
		number_suite.setCellFactory(tc -> new TableCell<Suite, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value, empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});
		
		TableColumn<Suite, String> style_suite = new TableColumn<>("Suite Style");
		style_suite.setCellValueFactory(new PropertyValueFactory<>("styleName"));
		
		TableColumn<Suite, Number> numOccupants = new TableColumn<>("Current Number of Occupants");
		numOccupants.setCellValueFactory(cellData -> cellData.getValue().numberOfOccupantsProperty());
		numOccupants.setCellFactory(tc -> new TableCell<Suite, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value, empty);
				if (value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});
		
		TableColumn<Suite, Boolean> presiderColumn = new TableColumn<>("Room Has A Presider");
		presiderColumn.setCellValueFactory(cellData -> cellData.getValue().presiderIsInProperty());

		table_suite.getColumns().addAll(number_suite, style_suite, numOccupants, presiderColumn);
		table_suite.setItems(list_suite);
		
		//SUITE TABLE OPTIONS
		filePrompt_suite = new Label();
		table_options = new MenuButton("Table Options");
		
		save_suite = new MenuItem("Save");
		save_suite.setOnAction(e -> {
			TransferProtocol.saveSuite(new File("recent_suite.txt"), new File("previous_caregiver.txt"), rooms);
			filePrompt_suite.setText(" Suite data saved.");
		});
		
		option_add = new MenuItem("Add Person");
		option_add.setOnAction(e -> {
			addRowSuite(stage, true);
		});
		
		option_remove = new MenuItem("Remove Person");
		option_remove.setOnAction(e -> {
			deleteRowSuite(stage);
		});
		
		table_options.getItems().addAll(save_suite, option_add, option_remove);
		
		div_suite = new HBox();
		div_suite.getChildren().addAll(table_options, home_suite, filePrompt_suite);
		
		box_suite = new VBox();
		box_suite.setSpacing(5);
		box_suite.setPadding(new Insets(10, 0, 0, 10));
		box_suite.getChildren().addAll(div_suite, table_suite);
		
		tab_suite = new Tab("Suite");
		tab_suite.setClosable(false);
		tab_suite.setContent(box_suite);
		main.getTabs().add(tab_suite);
		
		scene = new Scene(main, screen.getWidth(), screen.getHeight());
        scene.getStylesheets().add("Viper.css");

		stage.setTitle("Suite Module");
		stage.setScene(scene);
		stage.show();
	}
	
	//PRIVATE METHODS TO SUPPORT THE GUI
	private void addRowSenior(Stage stage) {
		TextDialog dialog_senior = new TextDialog(stage);
		dialog_senior.setWindowTitle("Add Senior");
		dialog_senior.setHeaderContent("Add a Senior to the table.");
		
		LimitedTextField fNameField = new LimitedTextField();
		fNameField.setAsAlphaOnly();
		dialog_senior.addOpenedPair(new Label("First Name"), true, fNameField, false);
		
		LimitedTextField lNameField = new LimitedTextField();
		lNameField.setAsAlphaOnly();
		dialog_senior.addOpenedPair(new Label("Last Name"), true, lNameField, false);
		
		LimitedTextField ageField = new LimitedTextField();
		ageField.setAsNumericOnly();
		ageField.setMaxLength(3);
		dialog_senior.addOpenedPair(new Label("Age"), true, ageField, true);
		dialog_senior.addDateBox(new Label("Date of Birth"));
		
		LimitedTextField roomidField = new LimitedTextField();
		roomidField.setAsNumericOnly();
		roomidField.setMaxLength(2);
		dialog_senior.addOpenedPair(new Label("Room ID"), true, roomidField, false);
		roomidField.setPromptText("1 - 12 ONLY");
		
		LimitedTextField hoursField = new LimitedTextField();
		hoursField.setAsNumericOnly();
		hoursField.setMaxLength(2);
		dialog_senior.addOpenedPair(new Label("Hours Per Day"), true, hoursField, false);
		
		LimitedTextField homeidField = new LimitedTextField();
        homeidField.setAsNumericOnly();
        homeidField.setMaxLength(4);
        dialog_senior.addOpenedPair(new Label("Home ID"), true, homeidField, false);
        
        dialog_senior.primeButtons();
        dialog_senior.display();
        
        if (dialog_senior.isSubmitPressed) {
            ArrayList<HashMap<TextField, String>> resp = dialog_senior.getResponses();
            String fNameP = resp.get(0).get(dialog_senior.getFields().get(0));
            String lNameP = resp.get(1).get(dialog_senior.getFields().get(1));
            int ageP = Integer.parseInt(resp.get(2).get(dialog_senior.getFields().get(2)));
            
            int dayP = dialog_senior.getDateBox().getDayBox().getValue();
            String dayPString = Integer.toString(dayP);
            
            int monthP = dialog_senior.getDateBox().getMonthBox().getValue();
            String monthPString = Integer.toString(monthP);
            
            int yearP = dialog_senior.getDateBox().getYearBox().getValue();
            String yearPString = Integer.toString(yearP);
            
            String dobP = dayPString + "/" + monthPString
                    + "/" + yearPString;
            
            int roomidP = Integer.parseInt(resp.get(3).get(dialog_senior.getFields().get(3)));
            double hourP = Double.parseDouble(resp.get(4).get(dialog_senior.getFields().get(4)));
            long homeidP = Long.parseLong(resp.get(5).get(dialog_senior.getFields().get(5)));
            
            Senior senior = new Senior(fNameP, lNameP, dobP, ageP, roomidP, hourP, homeidP);
            
            list_senior.add(senior);
            TransferProtocol.updateChoicesSenior(choices_senior, senior, true);
            table_senior.refresh();        	
        }
	}
	
	private void deleteRowSenior() {
		Senior removed = table_senior.getSelectionModel().getSelectedItem();
		if (list_senior.size() > 0 && removed != null) {
			list_senior.remove(removed);
			TransferProtocol.updateChoicesSenior(choices_senior, removed, false);
			filePrompt_senior.setText(" Senior deleted.");
		} else {
			filePrompt_senior.setText(" No Senior selected in the table.");
		}
	}
	
	private void addRowCaregiver(Stage stage) {
		TextDialog dialog_caregiver = new TextDialog(stage);
		dialog_caregiver.setWindowTitle("Add Caregiver");
		dialog_caregiver.setHeaderContent("Add a Caregiver to the table.");
		
		LimitedTextField fNameField = new LimitedTextField();
		fNameField.setAsAlphaOnly();
		dialog_caregiver.addOpenedPair(new Label("First Name"), true, fNameField, false);
		
		LimitedTextField lNameField = new LimitedTextField();
		lNameField.setAsAlphaOnly();
		dialog_caregiver.addOpenedPair(new Label("Last Name"), true, lNameField, false);
		
		LimitedTextField ageField = new LimitedTextField();
		ageField.setAsNumericOnly();
		ageField.setMaxLength(3);
		dialog_caregiver.addOpenedPair(new Label("Age"), true, ageField, true);
		dialog_caregiver.addDateBox(new Label("Date of Birth"));
		
		LimitedTextField roomidField = new LimitedTextField();
		roomidField.setAsNumericOnly();
		roomidField.setMaxLength(2);
		dialog_caregiver.addOpenedPair(new Label("Room ID"), true, roomidField, false);
		roomidField.setPromptText("1 - 12 ONLY");
		
		LimitedTextField empNumField = new LimitedTextField();
		empNumField.setAsNumericOnly();
		empNumField.setMaxLength(4);
		dialog_caregiver.addOpenedPair(new Label("Employee Number"), true, empNumField, false);
		
		LimitedTextField hoursField = new LimitedTextField();
		hoursField.setAsNumericOnly();
		hoursField.setMaxLength(3);
		dialog_caregiver.addOpenedPair(new Label("Hours Per Week"), true, hoursField, false);
		
		LimitedTextField wageField = new LimitedTextField();
		wageField.setAsNumericOnly();
		wageField.setMaxLength(2);
		dialog_caregiver.addOpenedPair(new Label("Hourly Wage"), true, wageField, false);
		
		dialog_caregiver.primeButtons();
		dialog_caregiver.display();
		
		if (dialog_caregiver.isSubmitPressed) {
			ArrayList<HashMap<TextField, String>> resp = dialog_caregiver.getResponses();
  
            String fNameP = resp.get(0).get(dialog_caregiver.getFields().get(0));
            String lNameP = resp.get(1).get(dialog_caregiver.getFields().get(1));
            int ageP = Integer.parseInt(resp.get(2).get(dialog_caregiver.getFields().get(2)));
            
            int dayP = dialog_caregiver.getDateBox().getDayBox().getValue();
            String dayPString = Integer.toString(dayP);
            
            int monthP = dialog_caregiver.getDateBox().getMonthBox().getValue();
            String monthPString = Integer.toString(monthP);
            
            int yearP = dialog_caregiver.getDateBox().getYearBox().getValue();
            String yearPString = Integer.toString(yearP);
            
            String dobP = dayPString + "/" + monthPString
                    + "/" + yearPString;
            
            int roomidP = Integer.parseInt(resp.get(3).get(dialog_caregiver.getFields().get(3)));
            long empNumP = Long.parseLong(resp.get(4).get(dialog_caregiver.getFields().get(4)));
            double hourP = Double.parseDouble(resp.get(5).get(dialog_caregiver.getFields().get(5)));
            double wageP = Double.parseDouble(resp.get(6).get(dialog_caregiver.getFields().get(6)));
            
            Caregiver caregiver = new Caregiver(fNameP, lNameP, dobP, ageP, roomidP, empNumP, hourP, wageP);
            
            list_caregiver.add(caregiver);
            TransferProtocol.updateChoicesCaregiver(choices_caregiver, caregiver, true);
            table_caregiver.refresh();
		}
	}
	
	private void deleteRowCaregiver() {
		Caregiver removed = table_caregiver.getSelectionModel().getSelectedItem();
		if (list_caregiver.size() > 0 && removed != null) {
			list_caregiver.remove(removed);
			TransferProtocol.updateChoicesCaregiver(choices_caregiver, removed, false);
			filePrompt_caregiver.setText(" Caregiver deleted.");
		} else {
			filePrompt_caregiver.setText(" No Caregiver selected in the table.");
		}
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
    
    private void addRowSuite(Stage stage, boolean add) {
    	String choice = "";
    	
    	List<String> choices = new ArrayList<>();
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
                
    		seniorSuite_dialog.addDateBox(new Label("Date of Entry: "));
    		seniorSuite_dialog.getDateBox().setYearConstraints(2020, 2035);
    		
    		seniorSuite_dialog.primeButtons();
    		seniorSuite_dialog.display();
    		
    		if (seniorSuite_dialog.isSubmitPressed && list_senior.size() > 0) {
    			ArrayList<HashMap<TextField, String>> resp = seniorSuite_dialog.getResponses();
    			int roomNum = Integer.parseInt(resp.get(0).get(seniorSuite_dialog.getFields().get(0)));
    			long homeid = Long.parseLong(resp.get(1).get(seniorSuite_dialog.getFields().get(1)));
    			
    			Senior searched = Senior.searchHomeID(homeid, list_senior, 0, list_senior.size());
    			
    			if (searched != null && roomNum > 0 && roomNum <= 12 && !rooms[roomNum - 1].occupantExists(searched)
    					&& !searched.isInside()) {
    				rooms[roomNum - 1].addOccupant(searched);
    				filePrompt_suite.setText(" Senior added to the complex.");
    			} else {
    				filePrompt_suite.setText(" Senior not found or senior is already in this suite or another suite.");
    			}
    		} else {
    			filePrompt_suite.setText(" No matching senior, room number, or home ID.");
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
    		caregiver_dialog.addOpenedPair(new Label("Employee Number: "), true, empNum_caregiver, false);
    		
    		caregiver_dialog.addDateBox(new Label("Date Assigned: "));
    		caregiver_dialog.getDateBox().setYearConstraints(2020, 2035);
    		
    		caregiver_dialog.primeButtons();
    		caregiver_dialog.display();
    		
    		if (caregiver_dialog.isSubmitPressed && list_caregiver.size() > 0) {
    			ArrayList<HashMap<TextField, String>> resp = caregiver_dialog.getResponses();
    			int roomNum = Integer.parseInt(resp.get(0).get(caregiver_dialog.getFields().get(0)));
    			long empnum = Long.parseLong(resp.get(1).get(caregiver_dialog.getFields().get(1)));
    			
    			Caregiver searched = Caregiver.searchEmpNum(empnum, list_caregiver, 0, list_caregiver.size());
    			
    			if (searched != null && roomNum > 0 && roomNum <= 12 && !rooms[roomNum - 1].presiderExists(searched)
    					&& !searched.isAssigned()) {
    				rooms[roomNum - 1].addPresider(searched);
    				filePrompt_suite.setText(" Caregiver assigned.");
                } else {
                	filePrompt_suite.setText(" Caregiver not found or caregiver is already assigned to this suite or "
                			+ "another suite.");
                }
    		} else {
    			filePrompt_suite.setText(" No matching caregiver, room number, or employee number.");
    		}
    	}
    }
    
    private void deleteRowSuite(Stage stage) {
    	String choice = "";
    	
    	List<String> choices = new ArrayList<>();
    	choices.add("Senior");
    	choices.add("Caregiver");
    	
    	ChoiceDialog<String> choice_dialog = new ChoiceDialog<>("Senior", choices);
    	choice_dialog.setTitle("Remove Person");
    	choice_dialog.setHeaderText("Remove a person from the Suite.");
    	choice_dialog.setContentText("Choose type: ");
    	
    	Optional<String> result = choice_dialog.showAndWait();
    	
    	if (result.isPresent()) {
    		choice = result.get();
    	}
    	
    	if (choice.equals("Senior")) {
    		TextDialog deleteSenior = new TextDialog(stage);
    		deleteSenior.setWindowTitle("Delete Senior");
    		deleteSenior.setHeaderContent("Remove a senior from the suite.");
    		
    		LimitedTextField roomNumber_senior = new LimitedTextField();
    		roomNumber_senior.setAsNumericOnly();
    		roomNumber_senior.setMaxLength(2);
    		deleteSenior.addOpenedPair(new Label("Room Number: "), true, roomNumber_senior, false);
 
    		LimitedTextField homeid_senior = new LimitedTextField();
    		homeid_senior.setAsNumericOnly();
    		homeid_senior.setMaxLength(4);
    		deleteSenior.addOpenedPair(new Label("Home ID: "), true, homeid_senior, false);
                
    		deleteSenior.addDateBox(new Label("Date of Leave: "));
    		deleteSenior.getDateBox().setYearConstraints(2020, 2035);
    		
    		deleteSenior.primeButtons();
    		deleteSenior.display();
    		
    		if (deleteSenior.isSubmitPressed && list_senior.size() > 0) {
    			ArrayList<HashMap<TextField, String>> resp = deleteSenior.getResponses();
    			int roomNum = Integer.parseInt(resp.get(0).get(deleteSenior.getFields().get(0)));
    			long homeid = Long.parseLong(resp.get(1).get(deleteSenior.getFields().get(1)));
    			
    			Senior searched = Senior.searchHomeID(homeid, list_senior, 0, list_senior.size());
    			
    			if (searched != null && roomNum >= 0 && roomNum < 12 && rooms[roomNum - 1].occupantExists(searched)
    					&& searched.isInside()) {
    				rooms[roomNum - 1].removeOccupant(searched.getHID());
    				filePrompt_suite.setText(" Senior removed from the complex.");
    			} else {
    				filePrompt_suite.setText(" Senior not found. Please add the Senior in the Senior tab first.");
    			}
    		} else {
    			filePrompt_suite.setText(" No Seniors in the table, room number does not exist, or occupant "
    					+ "is not in this suite.");
    		}
    		
    	} else {
    		TextDialog caregiver_dialog = new TextDialog(stage);
    		caregiver_dialog.setHeaderContent("Remove a Caregiver from the Suite.");
    		caregiver_dialog.setWindowTitle("Delete Caregiver");
    		
    		LimitedTextField roomNumber_caregiver = new LimitedTextField();
    		roomNumber_caregiver.setAsNumericOnly();
    		roomNumber_caregiver.setMaxLength(2);
    		caregiver_dialog.addOpenedPair(new Label("Room Number: "), true, roomNumber_caregiver, false);
    		
    		LimitedTextField empNum_caregiver = new LimitedTextField();
    		empNum_caregiver.setAsNumericOnly();
    		empNum_caregiver.setMaxLength(4);
    		caregiver_dialog.addOpenedPair(new Label("Employee Number: "), true, empNum_caregiver, false);
    		
    		caregiver_dialog.addDateBox(new Label("Date Removed: "));
    		caregiver_dialog.getDateBox().setYearConstraints(2020, 2035);
    		
    		caregiver_dialog.primeButtons();
    		caregiver_dialog.display();
    		
    		if (caregiver_dialog.isSubmitPressed && list_caregiver.size() > 0) {
    			ArrayList<HashMap<TextField, String>> resp = caregiver_dialog.getResponses();
    			int roomNum = Integer.parseInt(resp.get(0).get(caregiver_dialog.getFields().get(0)));
    			long empnum = Long.parseLong(resp.get(1).get(caregiver_dialog.getFields().get(1)));
    			
    			Caregiver searched = Caregiver.searchEmpNum(empnum, list_caregiver, 0, list_caregiver.size());
    			
    			if (searched != null && roomNum > 0 && roomNum <= 12 && rooms[roomNum - 1].presiderExists(searched)
    					&& searched.isAssigned()) {
    				rooms[roomNum - 1].removePresider();
    				filePrompt_suite.setText(" Caregiver removed from the complex.");
                } else {
                	filePrompt_suite.setText(" Caregiver not found or caregiver is not assigned to this suite.");
                }
    		} else {
    			filePrompt_suite.setText(" No Caregivers in the table, room number does not exist, or presider "
    					+ "does not already exist.");
    		}
    	}
    }
    
	public static void main(String[] args) {
		launch(args);
	}

}
