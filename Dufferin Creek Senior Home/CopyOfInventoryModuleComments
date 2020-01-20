package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import java.io.File;
import java.text.NumberFormat;
import java.text.DecimalFormat;

import utility.FoodGroup;
import utility.FoodItem;
import utility.Inventory;
import utility.Supplier;
import utility.TransferProtocol;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;

import javafx.geometry.Rectangle2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class InventoryModule extends Application {

	final NumberFormat nf = new DecimalFormat("##.##");
	
	Rectangle2D screen;
	Scene scene;
	TabPane main;
	Tab tab_inventory, tab_supplier;
	VBox box_inventory, box_supplier, divCost_inventory;
	HBox div_inventory, divSearch_inventory;
	
	ContextMenu ctx_inventory, ctx_cart;
	MenuButton fileMenu_inventory;
	MenuItem save_inventory, load_inventory, reload_inventory, addRow_inventory, deleteRow_inventory;
	Label filePrompt_inventory, budget;
	
	TableView<FoodItem> table_inventory;
	Inventory inventory;
	ObservableList<FoodItem> list_inventory, choiceList;
	FilteredList<FoodItem> filtered_inventory;
	SortedList<FoodItem> sorted_inventory, choiceSorted;
	
	TableView<Supplier> table_supplier;
	ObservableList<Supplier> list_supplier;
	
	TableView<FoodItem> cart;
	ObservableList<FoodItem> added;
	TextField costField_inventory;
	
	ChoiceBox<String> choices_inventory, filterBox_inventory;
	
	Button home_inventory, home_suppliers;
	
	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		
		//Gets the visual bounds of the user's screen.
		screen = Screen.getPrimary().getVisualBounds();
		main = new TabPane();
		
		/*Adds the home buttons to the Supplier and Inventory tabs.*/
		home_inventory = new Button("Home");
		home_inventory.setOnAction(actionEvent ->  {
        	    stage.hide();
	            MainMenu mainMenu = new MainMenu();
            		try {
				mainMenu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	});
		home_suppliers = new Button("Home");
		home_suppliers.setOnAction(actionEvent ->  {
            		stage.hide();
            		MainMenu mainMenu = new MainMenu();
            		try {
				mainMenu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	});
		
		//INVENTORY MODULE START
		tab_inventory = new Tab("Inventory");
		tab_inventory.setClosable(false);
		
		//INVENTORY TABLE SETUP
		
		/*Creates the inventory table, sets it editable, and adds a placeholder and column resize policy. The ObservableList
		backing this table is set to an ObservableArrayList.*/
		inventory = new Inventory();
		table_inventory = new TableView<>();
		table_inventory.setEditable(true);
		table_inventory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table_inventory.setPlaceholder(new Label("Right-click to add an item or load data into the table."));
		list_inventory = FXCollections.observableArrayList();
		
		//INVENTORY COLUMNS SETUP
		
		/*The typical format for a String parameterized TableColumn object. TableColumns take the properties from the class specified
		in the diamond operator and displays as the second type listed. 
		The method .setCellFactory defines what to display in the column. Either a lambda expression or a new PropertyValueFactory,
		which is also parameterized, are used to locate (by reflection) the property of the first type in the diamond operator 
		corresponding to the named property in its constructor. 
		The method .setCellFactory defines how to display the data. TextFieldTableCell puts a TextField into each table cell of the 
		TableColumn, allowing it to be directly edited via key and mouse input. 
		The method .setOnEditCommit processes the value of the commit event (after ENTER is pressed) by setting the value of the 
		property in that class to the input value in the cell, while displaying it in the table.
		*/
		
		TableColumn<FoodItem, String> name_inventory = new TableColumn<>("Item Name");
		name_inventory.setCellValueFactory(new PropertyValueFactory<>("name"));
		name_inventory.setCellFactory(TextFieldTableCell.forTableColumn());
		name_inventory.setOnEditCommit(e -> {
			((FoodItem) e.getTableView().getItems().get(
						e.getTablePosition().getRow())).setName(e.getNewValue());
			inventory.from(e.getTablePosition().getRow()).setName(e.getNewValue());
			choices_inventory.getItems().set(e.getTablePosition().getRow(), 
					e.getNewValue());
		});
		
		TableColumn<FoodItem, String> expiry_inventory = new TableColumn<>("Expiry Date");
		expiry_inventory.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
		
		/*An example of the lambda expression in .setCellFactory. In lieu of a PropertyValueFactory,
		each of the individual cells is linked explicitly to a property in the first-order class in the diamond operator.
		*/
		TableColumn<FoodItem, String> groupName_inventory = new TableColumn<>("Group Name");
		groupName_inventory.setCellValueFactory(cellData -> cellData.getValue().groupNameProperty());
		groupName_inventory.setCellFactory(TextFieldTableCell.forTableColumn());
		groupName_inventory.setOnEditCommit(e -> {
			((FoodItem) e.getTableView().getItems().get(
					e.getTablePosition().getRow())).setGroupName(e.getNewValue());
			inventory.from(e.getTablePosition().getRow()).setGroupName(e.getNewValue());
		});
		
		/*An example of a display type change for a Number field. Instead of using a TextFieldTableCell, Number fields
		that do not need editing can set a Cell Factory that updates formatting using a NumberFormat object 
		directly in the TableCell.
		*/
		TableColumn<FoodItem, Number> qty_inventory = new TableColumn<>("Current Quantity");
		qty_inventory.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
		qty_inventory.setCellFactory(tc -> new TableCell<FoodItem, Number>() {
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
		
		TableColumn<FoodItem, Number> stock_inventory = new TableColumn<>("Expected Stock");
		stock_inventory.setCellValueFactory(cellData -> cellData.getValue().stockProperty());
		stock_inventory.setCellFactory(tc -> new TableCell<FoodItem, Number>() {
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
		
		/*Adds the TableColumn objects to the inventory table.*/
		table_inventory.getColumns().addAll(name_inventory, expiry_inventory, groupName_inventory, 
				qty_inventory, stock_inventory);
		
		//INVENTORY FILE MENUBUTTON SETUP
		filePrompt_inventory = new Label();
		
		/*A MenuButton is a control used in the scene graph to display a dropdown of MenuItem objects. The save method
		for the inventory from class TransferProtocol is used to create version history and data transfer to a supported
		external file with a .txt extension.*/
		
		fileMenu_inventory = new MenuButton("File");
		save_inventory = new MenuItem("Save");
		save_inventory.setOnAction(e -> {
			TransferProtocol.saveInventory(new File("recent_inventory.txt"), new File ("previous_inventory.txt"), 
					list_inventory);
			filePrompt_inventory.setText(" Inventory Data Saved");
		});
		
		/*Setting a node on action means to fire a series of key processes when an action event is received.*/
		load_inventory = new MenuItem("Load");
		load_inventory.setOnAction(e -> {
			TransferProtocol.loadInventory(new File("recent_inventory.txt"), inventory, list_inventory, choices_inventory);
			filePrompt_inventory.setText(" Inventory Data Loaded");
		});
		
		reload_inventory = new MenuItem("Reload");
		reload_inventory.setOnAction(e -> {
			TransferProtocol.reloadInventory(new File("recent_inventory.txt"), new File("previous_inventory.txt"), 
					inventory, list_inventory, choices_inventory);
			filePrompt_inventory.setText(" Inventory Data Reloaded");
		});
		
		/*Sets the Hgrow property of the containing HBox to separate the cost label from the file menu and file prompt label.
		The Region between the file menu and file prompt and the cost label has a grow priority of ALWAYS.*/
		budget = new Label(Double.toString(inventory.getMonthBudget()));
		Region budgetRegion = new Region();
		HBox.setHgrow(budgetRegion, Priority.ALWAYS);
		
		fileMenu_inventory.getItems().addAll(save_inventory, load_inventory, reload_inventory);
		div_inventory = new HBox();
		div_inventory.getChildren().addAll(fileMenu_inventory, home_inventory, filePrompt_inventory, budgetRegion, budget);
		
		//INVENTORY CONTEXTMENU SETUP
		
		/**A ContextMenu is a menu that appears on right-click on the node. Adding a row or deleting a row is by selected item
		in the table.*/
		ctx_inventory = new ContextMenu();
		addRow_inventory = new MenuItem("Add Food Item");
		addRow_inventory.setOnAction(e -> {
			addRowInventory(stage);
		});
		
		deleteRow_inventory = new MenuItem("Delete Food Item");
		deleteRow_inventory.setOnAction(e -> {
			deleteRowInventory();
		});
		
		ctx_inventory.getItems().addAll(addRow_inventory, deleteRow_inventory);
		table_inventory.setOnContextMenuRequested(e -> {
			ctx_inventory.show(table_inventory, e.getScreenX(), e.getScreenY());
		});
		
		
		//INVENTORY SEARCH BAR SETUP
		
		/*The FilteredList that takes from the ObservableListArray constantly updating is used to create a predicate, 
		which in turn returns a list of FoodItem objects that correspond to the search request.*/
		filtered_inventory = new FilteredList<>(list_inventory, p -> true);
		TextField search_inventory = new TextField();
		search_inventory.setPromptText("Search the inventory");
		
		/**A listener is added to the text property of the TextField representing the search bar. This listener 
		observes whether key input event has occurred; if so, the oldValue (empty) is change to a newValue (the new String).
		This newValue is used with the predicate of the FilteredList to determine if a specific lookup property of the
		FoodItem matches the filter (text in the TextField). */
		search_inventory.textProperty().addListener((observable, oldValue, newValue) -> {
			filtered_inventory.setPredicate(item -> {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase().trim();
				
				if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (item.getExpiryDate().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (item.getGroupName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				
				return false;	
			});
		});
		
		/**A SortedList is used to sort the FilteredList; as such, the updated display in the TableView is sorted 
		by the lookup property (either alphabetically or numerically).*/
		sorted_inventory = new SortedList<>(filtered_inventory);
		sorted_inventory.comparatorProperty().bind(table_inventory.comparatorProperty());
		table_inventory.setItems(sorted_inventory);
		
		//FILTER BOX INVENTORY SETUP
		choiceList = FXCollections.observableArrayList();
		
		/**A String ChoiceBox is a control used in the scene graph to display a dropdown list of static choices.*/
		filterBox_inventory = new ChoiceBox<>();
		filterBox_inventory.getItems().addAll("Restore", "Below Expected", "To Expire", "Expired");
		filterBox_inventory.setValue("Restore");
		
		/*The selected index of the selection model of the ChoiceBox is listened to; depending on the index, 
		a particular method is run.
		Case 0 sets the table back to the initial view of the inventory table.
		Case 1 sets the list to contain all items below expected stock.
		Case 2 sets the list to contain all items about to expire.
		Case 3 sets the list to contain all items that are expired.
		*/
		filterBox_inventory.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)
				-> {
					switch ((Integer) newValue) {
						case 0:
							table_inventory.setItems(sorted_inventory);
							choiceList.removeAll(choiceList);
							break;
						case 1:
							buildChoiceList(inventory.belowExpected());
							break;
						case 2:
							buildChoiceList(inventory.toExpire());
							break;
						case 3:
							buildChoiceList(inventory.expired());
							break;
					}
				});
		
		//The method .setAlignment() uses Pos (geometry class) to set the alignment of all of the children of the HBox.
		divSearch_inventory = new HBox();
		divSearch_inventory.getChildren().addAll(search_inventory, filterBox_inventory);
		divSearch_inventory.setAlignment(Pos.CENTER);
		
		//INVENTORY COST BOX SETUP
		ScrollPane window = new ScrollPane();
		
		Button order = new Button("ORDER");
		order.setDisable(true);
		costField_inventory = new TextField();
		costField_inventory.setDisable(true);
		costField_inventory.setText("0.00");
		
		/*A new TableView is created and is put into a ScrollPane (window) effectively resizing it and making it 
		uneditable. The listener is added to the ObservableList that supports the table model and checks if its size is 
		greater than 0; if it is, the ORDER button is set enabled so that the user can proceed to checkout.
		*/
		cart = new TableView<>();
		cart.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		added = FXCollections.observableArrayList();
		added.addListener((ListChangeListener<FoodItem>) c -> {
			order.setDisable(c.getList().size() < 1);
		});
		cart.setItems(added);
		
		//CART TABLE SETUP
		TableColumn<FoodItem, String> name_cart = new TableColumn<>("Name");
		name_cart.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<FoodItem, Number> cost_cart = new TableColumn<>("Cost");
		cost_cart.setCellValueFactory(cellData -> cellData.getValue().costProperty());
		cost_cart.setCellFactory(tc -> new TableCell<FoodItem, Number>() {
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
		
		TableColumn<FoodItem, Number> quantity_cart = new TableColumn<>("Quantity");
		quantity_cart.setCellValueFactory(cellData -> cellData.getValue().batchAddProperty());
		quantity_cart.setCellFactory(tc -> new TableCell<FoodItem, Number>() {
			@Override
			protected void updateItem(Number value, boolean empty) {
				super.updateItem(value,  empty);
				if(value == null || empty) {
					setText("");
				} else {
					setText(nf.format(value));
				}
			}
		});
		
		cart.getColumns().addAll(name_cart, cost_cart, quantity_cart);
		
		/*ContextMenu for the add to cart table. It only allows deleting. When a FoodItem is removed from the cart 
		the cost of the items in the cart is updated accordingly.*/
		ctx_cart = new ContextMenu();
		ctx_cart.setAutoHide(true);
		
		MenuItem deleteRow_cart = new MenuItem("Remove From Cart");
		deleteRow_cart.setOnAction(e -> {
			FoodItem removed = cart.getSelectionModel().getSelectedItem();
			if (added.size() > 0 && removed != null) {
				added.remove(removed);
				costField_inventory.setText(Double.toString(Double.parseDouble(costField_inventory.getText()) - 
						removed.getCost()));
			} else {
				filePrompt_inventory.setText(" No item selected in cart.");
			}
		});
		ctx_cart.getItems().add(deleteRow_cart);
		
		cart.setOnContextMenuRequested(e -> {
			ctx_cart.show(cart, e.getScreenX(), e.getScreenY());
		});
		
		//SETUP OF THE SCROLLPANE
		window.setContent(cart);
		window.setMinViewportHeight(cart.getHeight());
		window.setFitToWidth(true);
		window.setPrefViewportWidth(screen.getWidth() / 2.0);
		window.setPadding(new Insets(10));
		
		HBox window_hbox = new HBox();
		window_hbox.getChildren().addAll(window);
		window_hbox.setPadding(new Insets(30));
		window_hbox.setAlignment(Pos.CENTER);
			
		/*The ADD TO CART button adds a currently existing item in the inventory table to the cart table. Upon adding the item,
		the total cost of the items in the cart is updated.*/
		choices_inventory = new ChoiceBox<>();
		Button addToCart = new Button("Add To Cart");
		addToCart.setOnAction(e -> {
			if (choices_inventory.getValue() != null) {
				FoodItem toAdd = list_inventory.get(choices_inventory.getItems().indexOf(choices_inventory.getValue()));
				toAdd.computeSTDCost();
				added.add(toAdd);
				costField_inventory.setText(Double.toString(Double.parseDouble(costField_inventory.getText()) + 
						toAdd.getCost()));
				cart.refresh();
			}
		});
		
		order.setOnAction(e -> {
			orderInventory();
		});
		
		HBox div_costButtons = new HBox();
		div_costButtons.getChildren().addAll(choices_inventory, addToCart, costField_inventory, order);
		
		divCost_inventory = new VBox();
		divCost_inventory.getChildren().addAll(window_hbox);
		
		/*Scene graph setup (tab hierarchy)*/
		box_inventory = new VBox();
		box_inventory.setPadding(new Insets(10, 0, 0, 10));
		box_inventory.setSpacing(5);
		box_inventory.getChildren().addAll(div_inventory, table_inventory, divSearch_inventory, div_costButtons,
				divCost_inventory);
		tab_inventory.setContent(box_inventory);
		
		
		
		//SUPPLIER MODULE START
		tab_supplier = new Tab("Supplier");
		tab_supplier.setClosable(false);
		
		//SUPPLIER TABLE SETUP
		table_supplier = new TableView<>();
		table_supplier.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		list_supplier = FXCollections.observableArrayList();
		TransferProtocol.loadSupplier(new File("suppliers.txt"), list_supplier);
		table_supplier.setItems(list_supplier);
		
		TableColumn<Supplier, String> name_supplier = new TableColumn<>("Name");
		name_supplier.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Supplier, String> address_supplier = new TableColumn<>("Address");
		address_supplier.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		TableColumn<Supplier, String> phone_supplier = new TableColumn<>("Phone Number");
		phone_supplier.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		TableColumn<Supplier, Number> cost_supplier = new TableColumn<>("Cost");
		cost_supplier.setCellValueFactory(cellData -> cellData.getValue().stdCostProperty());
		cost_supplier.setCellFactory(tc -> new TableCell<Supplier, Number>() {
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
		
		TableColumn<Supplier, Number> quantity_supplier = new TableColumn<>("Quantity");
		quantity_supplier.setCellValueFactory(cellData -> cellData.getValue().stdQtyProperty());
		quantity_supplier.setCellFactory(tc -> new TableCell<Supplier, Number>() {
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
		
		TableColumn<Supplier, Number> fee_supplier = new TableColumn<>("Base Charge");
		fee_supplier.setCellValueFactory(cellData -> cellData.getValue().flatFeeProperty());
		fee_supplier.setCellFactory(tc -> new TableCell<Supplier, Number>() {
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
		
		table_supplier.getColumns().addAll(name_supplier, address_supplier, phone_supplier, cost_supplier, 
				quantity_supplier, fee_supplier);
		
		box_supplier = new VBox();
		box_supplier.setPadding(new Insets(10, 0, 0, 10));
		box_supplier.setSpacing(5);
		box_supplier.getChildren().addAll(home_suppliers, table_supplier);
		
		tab_supplier.setContent(box_supplier);
		main.getTabs().addAll(tab_inventory, tab_supplier);
		
		scene = new Scene(main, screen.getWidth(), screen.getHeight());
		
		stage.setScene(scene);
		stage.setTitle("Inventory Module");
		stage.show();
	}
	
	//PRIVATE METHODS TO SUPPORT THE GUI
	
	//METHODS FOR CONTEXTMENU INVENTORY
	
	/**Adds a row to the inventory table.
	@param stage*/
	private void addRowInventory(Stage stage) {
		/*Creates a TextDialog for the inventory (requests all necessary parameters to create a FoodItem
		The LimitedTextField objects get input
		The DateBox is a series of ChoiceBox objects for day, month, and year
		*/
		TextDialog dialog_inventory = new TextDialog(stage);
		dialog_inventory.setWindowTitle("Add Food Item");
		dialog_inventory.setHeaderContent("Add a Food Item to the table.");
		
		LimitedTextField nameField = new LimitedTextField();
		nameField.setAsAlphaOnly();
		dialog_inventory.addOpenedPair(new Label("Name: "), true, nameField, false);
		
		dialog_inventory.addDateBox(new Label("Expiry Date: "));
		dialog_inventory.getDateBox().setYearConstraints(2020, 2035);
		
		LimitedTextField groupField = new LimitedTextField();
		groupField.setAsAlphaOnly();
		dialog_inventory.addOpenedPair(new Label("Group Name: "), true, groupField, false);
		
		LimitedTextField qtyField = new LimitedTextField();
		qtyField.setAsNumericOnly();
		qtyField.setMaxLength(5);
		dialog_inventory.addOpenedPair(new Label("Current Quantity"), true, qtyField, false);
		
		LimitedTextField stockField = new LimitedTextField();
		stockField.setAsNumericOnly();
		stockField.setMaxLength(5);
		dialog_inventory.addOpenedPair(new Label("Expected Stock: "), true, stockField, false);
		
		dialog_inventory.addNumberChoiceBox(new Label("Supplier ID: "), 1, 4);
		
		/*The method .primeButtons() activates the buttons for validation; .display() shows the dialog and waits until a response.*/
		dialog_inventory.primeButtons();
		dialog_inventory.display();
		
		/**If SUBMIT is pressed, an ArrayList representing the responses from the TextDialog is taken, a new FoodItem is added
		to the table, to the inventory, and to the inventory ChoiceBox.
		*/
		if (dialog_inventory.isSubmitPressed) {
			ArrayList<HashMap<TextField, String>> resp = dialog_inventory.getResponses();
			String nameP = resp.get(0).get(dialog_inventory.getFields().get(0));
			
			int day = dialog_inventory.getDateBox().getDayBox().getValue();
			String dayString = Integer.toString(day);
			
			int month = dialog_inventory.getDateBox().getMonthBox().getValue();
			String monthString = Integer.toString(month);
			
			int year = dialog_inventory.getDateBox().getYearBox().getValue();
			String yearString = Integer.toString(year);
			
			String expiryP = dayString + "/" + monthString + "/" + yearString;
			
			String groupP = resp.get(1).get(dialog_inventory.getFields().get(1));
			
			int qtyP = Integer.parseInt(resp.get(2).get(dialog_inventory.getFields().get(2)));
			int stockP = Integer.parseInt(resp.get(3).get(dialog_inventory.getFields().get(3)));
			
			Supplier supplierP = list_supplier.get(dialog_inventory.getNumberBox().getValue() - 1);
			
			FoodItem toAdd = new FoodItem(nameP, expiryP, new FoodGroup(groupP, supplierP), qtyP, stockP);
			inventory.addItem(nameP, expiryP, new FoodGroup(groupP, supplierP), qtyP, stockP);
			list_inventory.add(toAdd);
			TransferProtocol.updateChoicesInventory(choices_inventory, toAdd, true);
			
			table_inventory.refresh();
			filePrompt_inventory.setText(" Item added.");
		}
	}
	
	/**Deletes a Row in the inventory via a the selection model of the TableView.*/
	public void deleteRowInventory() {
		FoodItem removed = table_inventory.getSelectionModel().getSelectedItem();
		if (list_inventory.size() > 0 && removed != null) {
			list_inventory.remove(removed);
			TransferProtocol.updateChoicesInventory(choices_inventory, removed, false);
			filePrompt_inventory.setText(" Item deleted.");
		} else {
			filePrompt_inventory.setText(" No item selected in table.");
		}
	}
	
	/**Orders all of the items in the cart table. Creates an Error alert if the expenses exceed the monthly budget.
	Otherwise, a subtraction is made to the monthly budget, and quantities are increased.
	*/
	private void orderInventory() {
		if (Double.parseDouble(costField_inventory.getText()) > inventory.getMonthBudget()) {
			//Error Dialog
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Order Denied");
			error.setHeaderText("Expenses exceed the monthly budget.");
			error.setContentText("Please remove some items from cart.");
			
			error.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Payment Order");
			alert.setHeaderText("Payment Due to Suppliers: $" + costField_inventory.getText());
			alert.setContentText("Click OK to proceed. Cancel to review your order.");
			
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				Alert checkout = new Alert(AlertType.INFORMATION);
				checkout.setTitle("Checkout");
				checkout.setHeaderText(null);
				checkout.setContentText("Suppliers have been contacted and your standardized requests are being processed. "
						+ "Please wait for the items to arrive.");
				checkout.showAndWait();
				inventory.order(Double.parseDouble(costField_inventory.getText()), added, list_inventory);
				budget.setText(Double.toString(Double.parseDouble(budget.getText()) - Double.parseDouble(
						costField_inventory.getText())));
				
			} else {
				alert.close();
			}
		}
	}
	
	/**Creates the ChoiceBox for the cart list. 
	@param items - an array of FoodItem objects (taken from class Inventory)*/
	private void buildChoiceList(FoodItem[] items) {
		choiceList.removeAll(choiceList);
		for (FoodItem item : items) {
			choiceList.add(item);
		}
		table_inventory.setItems(choiceList);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
