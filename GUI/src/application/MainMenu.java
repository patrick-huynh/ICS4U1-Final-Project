/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


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
        
        Image logo = new Image("fullLogoWhite.png");
        ImageView logoImage = new ImageView(logo);
        logoImage.setFitHeight(180);
        logoImage.setPreserveRatio(true);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(logoImage);
        //pane.setCenter(name);
        GridPane.setConstraints(pane, 0, 0);
        
        
        Button seniorBtn = new Button("Seniors");
        seniorBtn.setPrefSize(190, 50);
        seniorBtn.getStyleClass().add("button-blue");
        GridPane.setConstraints(seniorBtn, 0,1);
        
        seniorBtn.setOnAction(actionEvent ->  {
            primaryStage.hide();
            TextDialogTest testdialog = new TextDialogTest();
            testdialog.start(primaryStage);
        });
        
        Button inventoryBtn = new Button("Inventory");
        inventoryBtn.setPrefSize(190, 50);
        inventoryBtn.getStyleClass().add("button-blue");
        GridPane.setConstraints(inventoryBtn, 0, 2);
        
        Button caregiverBtn = new Button("Caregiver");
        caregiverBtn.setPrefSize(190, 50);
        caregiverBtn.getStyleClass().add("button-blue");
        GridPane.setConstraints(caregiverBtn, 1, 1);
        
        Button suite = new Button("Suite");
        suite.setPrefSize(190, 50);
        suite.getStyleClass().add("button-blue");
        GridPane.setConstraints(suite, 1, 2);
        
        //Add everything to grid
        grid.add(pane, 0, 0, 2, 1);
        grid.getChildren().addAll(seniorBtn, inventoryBtn, caregiverBtn, suite);

        Scene scene = new Scene(grid, 400, 250);
        scene.getStylesheets().add("Viper.css");
        
        
        window.setScene(scene);
        window.show();
        
        
        
            
}
    
    
}