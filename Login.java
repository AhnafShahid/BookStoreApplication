package Finalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.io.*;
import java.text.ParseException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.WindowEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.HPos;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class Login extends Application {
  
    
    @Override //Override the start method in the Application class
    public void start(Stage primaryStage) {
        //primaryStage refers to the only stage (i.e. window) that
        //we care about in this app.
        //Create a scene and place it in the stage.
        //Note that call to createFirstGridPane creates a GridPane 
        //inside that scene.
        primaryStage.setScene(Login2.create1(primaryStage));
        primaryStage.show(); 
        
        //handle the ActionEvent when okButton is clicked
        /**login.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                   System.out.println("login was clicked"); 
                    if(usertext.getText().equals("admin") && passtext.getText().equals("admin")){
                        System.out.println("Owner");
                        primaryStage.setScene(new Scene(createSecondGridPane(), 500, 500));
                    }
                    else{
                        System.out.println("Customer");
                        Customer c = new Customer(usertext.getText(), passtext.getText());
                        primaryStage.setScene(c.create(primaryStage));
                       
                        
                    }
                   //Create a scene and place it in the stage.
                   //(the call to createSecondGridPane creates a GridPane 
                   //inside that scene)
                 // primaryStage.setScene(new Scene(createSecondGridPane(), 250, 250));
                }
            }    
        );
        
        Logout.setOnAction(
        new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(new Scene(createFirstGridPane(), 500, 500));
                usertext.clear();
                passtext.clear();
            }
        }
        
        );
        
       
        //handle the ActionEvent when the [Enter] key is hit by user
     /**   usertext.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    TextField tf = (TextField)e.getSource();
                    //System.out.println("You typed: " + tf.getText()); 
                    if(tf.getText().equals("owner")){
                        System.out.println("Owner");
                    }
                    else{
                        System.out.println("Customer");
                    }
                }
            } 
        );
        
        passtext.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        TextField p = (TextField)e.getSource();
                        System.out.println("You typed: " +p.getText());
                        if(p.getText().equals("owner")){
                            System.out.println("Owner");
                        }
                        else{
                            System.out.println("Customer");
                        }
                    }
                }); **/
        
        //called when the "close" icon [x] at the window's top-right is clicked
        primaryStage.setOnCloseRequest(
            new EventHandler<WindowEvent>() {
                 @Override
                 public void handle(WindowEvent e) {
                     System.out.println("window (i.e stage) is closing");
                }  
            }
        );
    }
    
    /**
    
    public GridPane createFirstGridPane() {
        GridPane gp1 = new GridPane();
        gp1.setAlignment(Pos.CENTER); 
        //add a Button in the GridPane at column 0 and row 0
        gp1.add(login, 0, 4);
        gp1.add(user, 0, 1);
        gp1.add(pass, 0, 2);
        //add a TextField in the GridPane at column 0 and row 1
        gp1.add(usertext, 2, 1);
        gp1.add(passtext, 2, 2);
        gp1.add(welcome, 0,0);
        return gp1;
    }
    
   
   
    
    public GridPane createSecondGridPane(){
        GridPane gp2 = new GridPane();
        gp2.setAlignment(Pos.CENTER);
        gp2.add(Books, 0,0 );
        gp2.add(Customer, 0, 2);
        gp2.add(Logout, 0,4);
        return gp2;
    }
    
    
    public static Scene create(Stage stage){
        Button login = new Button("Login");
    Label user = new Label("Username");
    Label pass = new Label("Password");
    Label welcome = new Label("Welcome to the Bookstore App");
    
    
    Button Books = new Button("Books");
    Button Customer = new Button("Customers");
    Button Logout = new Button("Logout");
    
    
    
    TextField usertext = new TextField();
    PasswordField passtext = new PasswordField(); 
        
        
        Books.setPrefWidth(100);
        Customer.setPrefWidth(100);
        Logout.setPrefWidth(100);
        VBox root = new VBox();
        root.getChildren().addAll(Books, Customer, Logout);
        
        GridPane gp1 = new GridPane();
        gp1.setAlignment(Pos.CENTER); 
        //add a Button in the GridPane at column 0 and row 0
        gp1.add(login, 0, 4);
        gp1.add(user, 0, 1);
        gp1.add(pass, 0, 2);
        //add a TextField in the GridPane at column 0 and row 1
        gp1.add(usertext, 2, 1);
        gp1.add(passtext, 2, 2);
        gp1.add(welcome, 0,0);
        
        Scene scene = new Scene(gp1, 500, 500);
        return scene;
    }
    * **/
    
    public static void main(String[] args) {
        launch(args);
    }
}
