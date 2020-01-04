package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.control.TabPane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.text.Font;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NewDatabaseCopy extends Application {
	//Named-constant int field for number of contained tables
	static final int MAX_SIZE = 5;
	
	//Named-constant int fields for portion-specific cost raises
	static final double SMALL_RAISE = 0.05;
	static final double MEDIUM_RAISE = 0.10;
	static final double LARGE_RAISE = 0.15;
	
	//Initialize int fields for ingredient count
	static int chickenC, turkeyC, potatoC, noodlesC, tortillaC, cheeseC, veggieC, fruitC, eggC = 0;
	
	//static double filed to represent running total for database transactions
	static double total = 0.00;
	
	//static lists of type tableview and label to condense setting of respective table properties
	static List<TableView<?>> database = new ArrayList<TableView<?>>();
	static List<Label> headers = new ArrayList<Label>();
	
	//CUSTOMER INFORMATION (ID, Buying Branch, PIN)
	/*List-initialize 2D backend string array for client information. Create supporting selection model as an observable 
	 * array list for table view control. Instantiate table view element with parameterization object type of Customer.*/
	static String[][] baseOne = {{"1","Toronto 1","12345678"}, {"2","Hamilton 1","6669699"}, 
			{"3","London 2","55566677"}, {"4","Ottawa 2","85694721"}, {"5","Ottawa 2","36925814"}, 
			{"6","Tri-Cities 2","78529634"}, {"7","Tri-Cities 1","11111111"}, {"8","Ottawa 1","87654321"}, 
			{"9","London 1","84673591"}, {"10","Toronto 2","65372198"}};
	static final ObservableList<Customer> customerBase = FXCollections.observableArrayList();
	static TableView<Customer> customers = new TableView<>();
	
	//MEAL INFORMATION (Recipe, Category, Portion, Base Price)
	static String[][] baseTwo = {{"1","Mashed Potato Turkey Pot Pie","Ambitious","SML","15.05"}, 
			{"2","Chicken Souvlaki Noodles","Ambitious","SML","12.50"}, {"3","Chicken Teriyaki Wraps","Ambitious","SML","10.00"}, 
			{"4","Eggplant Salad","Ambitious","SML","2.50"}, {"5","Chicken and Mushroom","Soups","SML","5.00"}, 
			{"6","Chicken Noodle","Soups","SML","5.00"}, {"7","Macaroni Fruit Salad","Salads","SML","8.50"}, 
			{"8","Avocado Chicken Salad","Salads","SML","10.00"}, {"9","Cannoli Cheesecake Dessert","Desserts","SML","2.99"}, 
			{"10","Pumpkin Cobbler Dessert","Desserts","SML","4.60"}};
	static final ObservableList<Meal> mealBase = FXCollections.observableArrayList();
	static TableView<Meal> meals = new TableView<>();
	
	//INVENTORY INFORMATION (Ingredient, Quantity)
	static String[][] baseThree = {{"Skinless Boneless Chicken Breast","2 3 5 6 8","50"}, {"Turkey","1","50"}, 
		{"Potato","1 2 3 4","50"}, {"Medium Noodles","1 2 3","30"}, {"Corn Tortilla","1 2 3","30"}, 
		{"Cheese (Misc.)","9 10","30"}, {"Vegetables (Misc.)","4 7 8","30"}, {"Fruits (Misc.)","4 7 8","30"}, 
			{"Eggs","9 10","30"}};
	static final ObservableList<InventoryItem> inventoryBase = FXCollections.observableArrayList();
	static TableView<InventoryItem> inventory = new TableView<>();
	//Temporary inventory for reloading purposes
	static String[][] temp = Arrays.copyOf(baseThree, baseThree.length);
	
	//SUPPLIER INFORMATION (ID, Name, Product, Phone Number)
	static String[][] baseFour = {{"1", "Good Chicken", "2 3 5 6 8", "416-999-8888"}, 
			{"2", "Nice Turkey", "1", "416-754-7545"}, {"3", "Gold Potato", "1 2 3 4", "416-647-9111"}, 
			{"4", "NooNoos", "1 2 3", "416-899-5621"}, {"5", "Tortillas Inc.", "1 2 3", "416-923-1256"}, 
			{"6", "ACheesyName", "9 10", "416-216-6669"}, {"7", "Fat Veggies", "4 7 8", "416-585-6321"}, 
			{"8", "Fat Fruits", "4 7 8", "416-222-3333"}, {"9", "Eggcellence", "9 10", "416-859-1598"}};
	static final ObservableList<Supplier> supplierBase = FXCollections.observableArrayList();
	static TableView<Supplier> suppliers = new TableView<>();
	
	//TRANSACTIONS AND PURCHASES (Customer ID, Purchase, Quantity, Portions)
	static String[][] baseFive = {{"", "", "", ""}, {"", "", "", ""}, 
			{"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, 
			{"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, 
			{"", "", "", ""}, {"", "", "", ""}};
	static final ObservableList<Transaction> transactionBase = FXCollections.observableArrayList();
	static TableView<Transaction> transactions = new TableView<>();
	
	//ASSEMBLY METHODS for constructing observableLists
	//Utility method for assembling observableList of type customer from 2D string array (translation protocol).
	static void buildClient(String[][] base) {
		for (int i = 0; i < base.length; i++) {
			Customer customer = new Customer(base[i][0], base[i][1], base[i][2]);
			customerBase.add(customer);
		}
		customers.setItems(customerBase);
	}
	
	//Meal Builder
	static void buildMeal(String[][] base) {
		for (int j = 0; j < base.length; j++) {
			Meal meal = new Meal(base[j][0], base[j][1], base[j][2], base[j][3], base[j][4]);
			mealBase.add(meal);
		}
		meals.setItems(mealBase);
	}
	
	//InventoryItem builder
	static void buildInventory(String[][] base) {
		for (int k = 0; k < base.length; k++) {
			InventoryItem item = new InventoryItem(base[k][0], base[k][1], base[k][2]);
			inventoryBase.add(item);
		}
		inventory.setItems(inventoryBase);
	}
	
	//Supplier Builder
	static void buildSupplier(String[][] base) {
		for (int i = 0; i < base.length; i++) {
			Supplier supplier = new Supplier(base[i][0], base[i][1], base[i][2], base[i][3]);
			supplierBase.add(supplier);
		}
		suppliers.setItems(supplierBase);
	}
	
	
	//Transaction Builder
	static void buildTransaction(String[][] base) {
		for (int j = 0; j < base.length; j++) {
			Transaction transaction = new Transaction(base[j][0], base[j][1], base[j][2], base[j][3]);
			transactionBase.add(transaction);
		}
		transactions.setItems(transactionBase);
	}
	
	//Searching the meal database for base price
	static double searchForPrice(String mealID) {
		for (int i = 0; i < baseTwo.length; i++) {
			if(!baseTwo[i][0].equalsIgnoreCase(mealID)) {
				continue;
			}
			return Double.parseDouble(baseTwo[i][4]);
		}
		return 0.00;
	}
	
	//Cost Calculations
	static void calculate() {
		int sCount = 0;	//Set a;; counts for portion sizes to 0
		int mCount = 0;
		int lCount = 0;
		for (int i = 0; i < baseFive.length; i++) {
			if ((baseFive[i][0] == "" || baseFive[i][1] == "" || baseFive[i][2] == "" || baseFive[i][3] == "") ||
					(Integer.parseInt(baseFive[i][2]) != baseFive[i][3].length())) {
				continue;	//skip current iteration if array contains empty fields or if quantity entered does not equal
							//number of portions specified
			}
			double price = searchForPrice(baseFive[i][1]);	//invoke inventory searching methods to apply deductions
			deduct(baseFive[i][1], baseFive[i][2]);
			double quantity = Double.parseDouble(baseFive[i][2]);
			char[] sizes = baseFive[i][3].toCharArray();
			
			//Create a char[] instance on runtime and increment portion size counts as necessary
			for (int k = 0; k < sizes.length; k++) {
				if(sizes[k] == 'S') sCount++;
				else if (sizes[k] == 'M') mCount++;
				else lCount++;
			}
			//calculate cost and display in transaction label
			total += quantity * (price + price * (sCount * SMALL_RAISE + mCount * MEDIUM_RAISE + lCount * LARGE_RAISE));
			total = Math.round(total * 100.0) / 100.0;
		}
	}
	
	static void callSupplier(String mealID) {
		for (int i = 0; i < baseFour.length; i++) {
			if (baseFour[i][2].equalsIgnoreCase(mealID)) {
				baseFour[i][0] = baseFour[i][0].concat(" CALL NOW");
			}
		}
		supplierBase.removeAll(supplierBase);
		buildSupplier(baseFour);
		suppliers.refresh();
	}
	
	//Minus from quantity if item is purchased. Use single-if conditions to avoid escaping possibilities
	static void deduct(String mealID, String qty) {
		for (int i = 0; i < baseThree.length; i++) {
			for (int j = 0; j < baseThree[i][1].split(" ").length; j++) {
				if (baseThree[i][1].split(" ")[j].equals(mealID)) {
					baseThree[i][2] = Integer.toString(Integer.parseInt(baseThree[i][2]) - Integer.parseInt(qty));
					
					if (Integer.parseInt(baseThree[i][2]) <= 0) {
						callSupplier(baseThree[i][1]);
					}
				}
			}
			//At the end of operation, refresh observable list backing tableview model to enable updated status
			inventoryBase.removeAll(inventoryBase);
			buildInventory(baseThree);
			inventory.refresh();
			//Upon refresh operation, return quantity to normal (reload)
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		//Get the visual bounds of the current screen
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		
		//Add all tables to the database list of type tableview
		Collections.addAll(database, customers, meals, inventory, suppliers, transactions);
		for (int i = 0; i < MAX_SIZE; i++) {
			database.get(i).setEditable(true);
			database.get(i).setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
		
		//Instantiate new TabPane and add to the scene graph
		TabPane pane = new TabPane();
		Scene scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		
		//Instantiate label headings for each tab and accompanying tableview control
		final Label label1 = new Label("Customers");
		final Label label2 = new Label("Meals");
		final Label label3 = new Label("Inventory");
		final Label label4 = new Label("Suppliers");
		final Label label5 = new Label("Transactions");
		Label costL = new Label(Double.toString(total));
		Collections.addAll(headers, label1, label2, label3, label4, label5);
		for (int i = 0; i < MAX_SIZE; i++) {
			headers.get(i).setFont(new Font("Arial", 20));
		}
		
		//Build all observableLists by invoking build methods
		buildClient(baseOne);
		buildMeal(baseTwo);
		buildInventory(baseThree);
		buildSupplier(baseFour);
		buildTransaction(baseFive);
		
		//COLUMNS FOR TABLEVIEW CUSTOMERS
        TableColumn<Customer, String> customerName = new TableColumn<>("Customer ID");	//Object Parameterization
        customerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));	//get name property of customer class
        customerName.setCellFactory(TextFieldTableCell.forTableColumn());	//enable cell editing by populating with cell textfields
        customerName.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {	//save changes
        	@Override
        	public void handle(CellEditEvent<Customer, String> event) {
        		((Customer) event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(event.getNewValue());
        		//append returned object by event after consumption to array as a string by calling .toString()
        		baseOne[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Customer, String> buyingBranch = new TableColumn<>("Buying Branch");
        buyingBranch.setCellValueFactory(new PropertyValueFactory<Customer, String>("buyingBranch"));
        buyingBranch.setCellFactory(TextFieldTableCell.forTableColumn());
        buyingBranch.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
        	@Override
        	public void handle(CellEditEvent<Customer, String> event) {
        		((Customer) event.getTableView().getItems().get(
        				event.getTablePosition().getRow())).setBuyingBranch(event.getNewValue());
        		baseOne[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Customer, String> pin = new TableColumn<>("PIN");
        pin.setCellValueFactory(new PropertyValueFactory<Customer, String>("pin"));
        pin.setCellFactory(TextFieldTableCell.forTableColumn());
        pin.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
        	@Override
        	public void handle(CellEditEvent<Customer, String> event) {
        		((Customer) event.getTableView().getItems().get(
        				event.getTablePosition().getRow())).setPin(event.getNewValue()); 
        		baseOne[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        customers.getColumns().addAll(customerName, buyingBranch, pin);
		
		//COLUMNS FOR MEAL 
        TableColumn<Meal, String> mealID = new TableColumn<>("Meal ID");
        mealID.setCellValueFactory(new PropertyValueFactory<Meal, String>("mealID"));
        mealID.setCellFactory(TextFieldTableCell.forTableColumn());
        mealID.setOnEditCommit(new EventHandler<CellEditEvent<Meal, String>>() {
        	@Override
        	public void handle(CellEditEvent<Meal, String> event) {
        		((Meal) event.getTableView().getItems().get(event.getTablePosition().getRow())).setRecipe(event.getNewValue());
        		baseTwo[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Meal, String> recipe = new TableColumn<>("Recipe");
        recipe.setCellValueFactory(new PropertyValueFactory<Meal, String>("recipe"));
        recipe.setCellFactory(TextFieldTableCell.forTableColumn());
        recipe.setOnEditCommit(new EventHandler<CellEditEvent<Meal, String>>() {
        	@Override
        	public void handle(CellEditEvent<Meal, String> event) {
        		((Meal) event.getTableView().getItems().get(event.getTablePosition().getRow())).setRecipe(event.getNewValue());
        		baseTwo[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Meal, String> category = new TableColumn<>("Category");
        category.setCellValueFactory(new PropertyValueFactory<Meal, String>("category"));
        category.setCellFactory(TextFieldTableCell.forTableColumn());
        category.setOnEditCommit(new EventHandler<CellEditEvent<Meal, String>>() {
        	@Override
        	public void handle(CellEditEvent<Meal, String> event) {
        		((Meal) event.getTableView().getItems().get(
        				event.getTablePosition().getRow())).setCategory(event.getNewValue());
        		baseTwo[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Meal, String> portion = new TableColumn<>("Portion");
        portion.setCellValueFactory(new PropertyValueFactory<Meal, String>("portion"));
        portion.setCellFactory(TextFieldTableCell.forTableColumn());
        portion.setOnEditCommit(new EventHandler<CellEditEvent<Meal, String>>() {
        	@Override
        	public void handle(CellEditEvent<Meal, String> event) {
        		((Meal) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPortion(event.getNewValue());
        		baseTwo[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Meal, String> basePrice = new TableColumn<>("Base Price");
        basePrice.setCellValueFactory(new PropertyValueFactory<Meal, String>("basePrice"));
        basePrice.setCellFactory(TextFieldTableCell.forTableColumn());
        basePrice.setOnEditCommit(new EventHandler<CellEditEvent<Meal, String>>() {
        	@Override
        	public void handle(CellEditEvent<Meal, String> event) {
        		((Meal) event.getTableView().getItems().get(
        				event.getTablePosition().getRow())).setBasePrice(event.getNewValue());
        		baseTwo[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        meals.getColumns().addAll(mealID, recipe, category, portion, basePrice);
        
        //COLUMNS FOR INVENTORY
        TableColumn<InventoryItem, String> ingredient = new TableColumn<>("Ingredient (Bag, Roll, or Box)");
        ingredient.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("ingredient"));
        ingredient.setCellFactory(TextFieldTableCell.forTableColumn());
        ingredient.setOnEditCommit(new EventHandler<CellEditEvent<InventoryItem, String>>() {
        	@Override
        		public void handle(CellEditEvent<InventoryItem, String> event) {
        			((InventoryItem) event.getTableView().getItems().get(
        				event.getTablePosition().getRow())).setIngredient(event.getNewValue());
        			baseThree[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        		}
        });
        
        TableColumn<InventoryItem, String> rMealID = new TableColumn<>("Meal ID");
        rMealID.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("rMealID"));
        rMealID.setCellFactory(TextFieldTableCell.forTableColumn());
        rMealID.setOnEditCommit(new EventHandler<CellEditEvent<InventoryItem, String>>() {
        	@Override
        		public void handle(CellEditEvent<InventoryItem, String> event) {
        			((InventoryItem) event.getTableView().getItems().get(
        				event.getTablePosition().getRow())).setRMealID(event.getNewValue());
        			baseThree[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        		}
        });
        
        TableColumn<InventoryItem, String> quantity = new TableColumn<>("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("quantity"));
        quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setOnEditCommit(new EventHandler<CellEditEvent<InventoryItem, String>>() {
        	@Override
        		public void handle(CellEditEvent<InventoryItem, String> event) {
        			((InventoryItem) event.getTableView().getItems().get(
        				event.getTablePosition().getRow())).setQuantity(event.getNewValue());
        			baseThree[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        		}
        });
        inventory.getColumns().addAll(ingredient, rMealID, quantity);
        
        //COLUMNS FOR SUPPLIERS
        TableColumn<Supplier, String> id = new TableColumn<>("Supplier ID");
        id.setCellValueFactory(new PropertyValueFactory<Supplier, String>("ID"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(new EventHandler<CellEditEvent<Supplier, String>>() {
        	@Override
        	public void handle(CellEditEvent<Supplier, String> event) {
        		((Supplier) event.getTableView().getItems().get(event.getTablePosition().getRow())).setID(event.getNewValue());
        		baseFour[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Supplier, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<CellEditEvent<Supplier, String>>() {
        	@Override
        	public void handle(CellEditEvent<Supplier, String> event) {
        		((Supplier) event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(event.getNewValue());
        		baseFour[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Supplier, String> product = new TableColumn<>("Product");
        product.setCellValueFactory(new PropertyValueFactory<Supplier, String>("product"));
        product.setCellFactory(TextFieldTableCell.forTableColumn());
        product.setOnEditCommit(new EventHandler<CellEditEvent<Supplier, String>>() {
        	@Override
        	public void handle(CellEditEvent<Supplier, String> event) {
        		((Supplier) event.getTableView().getItems().get(event.getTablePosition().getRow())).setProduct(event.getNewValue());
        		baseFour[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Supplier, String> phone = new TableColumn<>("Phone Number");
        phone.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setOnEditCommit(new EventHandler<CellEditEvent<Supplier, String>>() {
        	@Override
        	public void handle(CellEditEvent<Supplier, String> event) {
        		((Supplier) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPhone(event.getNewValue());
        		baseFour[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        suppliers.getColumns().addAll(id, name, product, phone);
        
        //COLUMNS FOR TRANSACTIONS
        TableColumn<Transaction, String> nameID = new TableColumn<>("Customer ID");
        nameID.setCellValueFactory(new PropertyValueFactory<Transaction, String>("nameID"));
        nameID.setCellFactory(TextFieldTableCell.forTableColumn());
        nameID.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() {
        	@Override
        	public void handle(CellEditEvent<Transaction, String> event) {
        		((Transaction) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNameID(event.getNewValue());
        		baseFive[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Transaction, String> purchase = new TableColumn<>("Purchase");
        purchase.setCellValueFactory(new PropertyValueFactory<Transaction, String>("purchase"));
        purchase.setCellFactory(TextFieldTableCell.forTableColumn());
        purchase.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() {
        	@Override
        	public void handle(CellEditEvent<Transaction, String> event) {
        		((Transaction) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPurchase(event.getNewValue());
        		baseFive[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Transaction, String> quantityT = new TableColumn<>("Quantity");
        quantityT.setCellValueFactory(new PropertyValueFactory<Transaction, String>("quantityT"));
        quantityT.setCellFactory(TextFieldTableCell.forTableColumn());
        quantityT.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() {
        	@Override
        	public void handle(CellEditEvent<Transaction, String> event) {
        		((Transaction) event.getTableView().getItems().get(event.getTablePosition().getRow())).setQuantityT(event.getNewValue());
        		baseFive[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        
        TableColumn<Transaction, String> portions = new TableColumn<>("Portions");
        portions.setCellValueFactory(new PropertyValueFactory<Transaction, String>("portions"));
        portions.setCellFactory(TextFieldTableCell.forTableColumn());
        portions.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() {
        	@Override
        	public void handle(CellEditEvent<Transaction, String> event) {
        		((Transaction) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPortions(event.getNewValue());
        		baseFive[event.getTablePosition().getRow()][event.getTablePosition().getColumn()] = event.getNewValue().toString();
        	}
        });
        transactions.getColumns().addAll(nameID, purchase, quantityT, portions);
        
        //Button to invoke to calculate cost
        Button calculator = new Button("Update");
        calculator.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		calculate();
        		costL.setText(Double.toString(total));
        		total = 0;
        	}
        });
        
        for (int i = 0; i < MAX_SIZE; i++) {
        	for (int j = 0; j < database.get(i).getColumns().size(); j++) {
        		database.get(i).getColumns().get(j).setMinWidth(250);
        	}
        	
        	VBox nVbox = new VBox();
        	nVbox.setSpacing(5);
        	nVbox.setPadding(new Insets(10, 0, 0, 10));
        	nVbox.getChildren().addAll(headers.get(i), database.get(i), costL, calculator);
        	
        	Group nGroup = new Group();
        	nGroup.getChildren().addAll(nVbox);
        	Tab tab = new Tab(headers.get(i).getText());
        	tab.setContent(nGroup);
        	pane.getTabs().add(tab);
        }
        
		//Set stage title, scene graph, and show (write to GUI handler)
		stage.setTitle("Krenchel's Database");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
