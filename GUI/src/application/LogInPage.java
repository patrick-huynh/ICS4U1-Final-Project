/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author 343426938
 */
public class LogInPage extends Application {



    Stage window;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Log In");
        primaryStage.setResizable(false);
        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setMaximized(false);
        });
        
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(8);
        grid.setHgap(10);
        
        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Username:");
        nameLabel.setId("bold-label");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Input
        TextField nameInput = new TextField();
        nameInput.setPrefWidth(100);
        nameInput.setPromptText("username");
        //String username = nameInput.getText();
        GridPane.setConstraints(nameInput, 1, 0);

        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);
        
        Label incorrect = new Label("");
        incorrect.setId("incorrect");
        GridPane.setConstraints(incorrect, 1, 5);
        
        //Password Input
        TextField passInput = new TextField();
        passInput.setPrefWidth(200);
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);
        
        //Login button
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 3);

        passInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) 
            {
                if(keyEvent.getCode() == KeyCode.ENTER)
                {
                    if(nameInput.getText().equals("admin") && passInput.getText().equals("admin")){
                    incorrect.setText("");
                    window.hide();
                    MainMenu menu = new MainMenu();
                    try {
						menu.start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					};
					
    
                } else {
                    incorrect.setText("*Incorret Username/Password");
                    
                }
                }
            }   
    });
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
         
            @Override
            public void handle(ActionEvent e) {
                if(nameInput.getText().equals("admin") && passInput.getText().equals("admin")){
                    incorrect.setText("");
                    window.hide();
                    MainMenu menu = new MainMenu();
                    try {
						menu.start(primaryStage);
					} catch (Exception f) {
						f.printStackTrace();
					};
    
                } else {
                    incorrect.setText("*Incorret Username/Password");
                    
                }
            }
            
        }
        );
    
        
        //Sign up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.getStyleClass().add("button-blue");
        GridPane.setConstraints(signUpButton, 1, 4);


        //Add everything to grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, signUpButton, incorrect);

        Scene scene = new Scene(grid, 380, 200);
        scene.getStylesheets().add("LogInPage.css");
        
        
        window.setScene(scene);
        window.show();

            
}
    
    
}