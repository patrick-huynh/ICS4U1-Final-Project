/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
import utility.SuiteModule;

import java.io.FileInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author 343426938
 */
public class MainMenu extends Application {



    Stage window;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Menu");
        primaryStage.setResizable(false);
        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setMaximized(false);
        });
        
        //If you need any help: http://tutorials.jenkov.com/javafx/gridpane.html
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        //Add Logo
        Image logo = new Image("fullLogoWhite.png");
        ImageView logoImage = new ImageView(logo);
        logoImage.setFitHeight(180);
        logoImage.setPreserveRatio(true);
        
        Button logOut = new Button("Logout");
        logOut.getStyleClass().add("logout");
        logOut.setOnAction(actionEvent ->  {
            primaryStage.hide();
            LogInPage logIn = new LogInPage();
            try {
				logIn.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			};
        });
        
        BorderPane pane = new BorderPane();
        pane.setRight(logOut);
        pane.setCenter(logoImage);
        GridPane.setConstraints(pane, 0, 0);
        
        
        
        
        //Senior Button
        Button suiteBtn = new Button("Suite");
        suiteBtn.setPrefSize(190, 50);
        suiteBtn.getStyleClass().add("button-blue");
        GridPane.setConstraints(suiteBtn, 0,1);
        
        suiteBtn.setOnAction(actionEvent ->  {
            primaryStage.hide();
            SuiteModule suiteModule = new SuiteModule();
            suiteModule.start(primaryStage);
        });
        
        //Inventory Button
        Button inventoryBtn = new Button("Inventory");
        inventoryBtn.setPrefSize(190, 50);
        inventoryBtn.getStyleClass().add("button-blue");
        GridPane.setConstraints(inventoryBtn, 1, 1);
        inventoryBtn.setOnAction(actionEvent ->  {
            primaryStage.hide();
            NewPersonTable inventoryTable = new NewPersonTable();
            inventoryTable.start(primaryStage);
        });
        
        //Add everything to grid
        grid.add(pane, 0, 0, 2, 1);
        grid.getChildren().addAll(suiteBtn, inventoryBtn);

        Scene scene = new Scene(grid, 400, 250);
        scene.getStylesheets().add("Viper.css");
        
        
        window.setScene(scene);
        window.show();
        
        
        
            
}
    
    
}
