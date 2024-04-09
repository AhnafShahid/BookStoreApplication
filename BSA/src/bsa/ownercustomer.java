/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsa;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author Ahnaf
 */
public class ownercustomer extends Application { 
    private TableView<CustomerList> table = new TableView<CustomerList>();//BLOCK TO INITIALIZE TABLE
    private final ObservableList<CustomerList> data =
        FXCollections.observableArrayList(
            
          );
  @Override 
  public void start(Stage primaryStage) {
    
    AnchorPane root =new AnchorPane();
    
    
   table.setEditable(true);
 
        TableColumn UserCol = new TableColumn("Username:"); //BLOCK TO DEFINE COLUMNS IN TABLE AND MAKE SURE THE APPROPITATE VALUES ARE PUT THERE
       
        UserCol.setCellValueFactory(
                new PropertyValueFactory<ownercustomer.CustomerList, String>("Username"));
 
        TableColumn PassCol = new TableColumn("Password:");
      
        PassCol.setCellValueFactory(
                new PropertyValueFactory<ownercustomer.CustomerList, String>("Password"));
        
        TableColumn PointsCol = new TableColumn("Points:");
      
        PointsCol.setCellValueFactory(
                new PropertyValueFactory<ownercustomer.CustomerList, String>("Points"));
 
        
 
        table.setItems(data);
        table.getColumns().addAll(UserCol, PassCol, PointsCol ); //ADD THE COLUMNS TO THE TABLE
    
    UserCol.setMinWidth(100); //DIMENSIONS OF EACH COLUMN
    UserCol.setMaxWidth(100);
    PassCol.setMinWidth(100);
    PassCol.setMaxWidth(100);
    PointsCol.setMaxWidth(50);
    PointsCol.setMinWidth(50);
    UserCol.setSortable(false);
    PassCol.setSortable(false);
    PointsCol.setSortable(false);
    
   
    HBox hbox3 = new HBox(table);
    
    //ADD UI ELEMENTS
    TextField user = new TextField();
    TextField pass = new TextField();
    Label label1 = new Label("Username:");
    Label label = new Label("Password:");
    
    Label warning = new Label("");
    
    Button btADD = new Button("Add");
    HBox hbox1 = new HBox(5, label1, user, label,pass,btADD);
    
    HBox hbox5 = new HBox(5, warning);
    
    Button btDELETE = new Button("Delete");
    HBox hbox2 = new HBox(5, btDELETE);
    
 
    btADD.setOnAction( //CODE FOR ADD BUTTON
            new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if ((user.getText().trim().isEmpty() || user.getText() == null) || (pass.getText().trim().isEmpty() || pass.getText() == null ) ) //CHECK IF ANY TEXTBOXES ARE EMPTY
                        {
                            
                            warning.setText("Please enter ALL credentials");
                            user.clear();
                       pass.clear();
                        }else{ //OTHERWISE ADD DATA TO TABLE
                       
                       warning.setText("");
                                 
                       data.add(new CustomerList(
                               user.getText(),
                               pass.getText()));
                       
                       user.clear();
                       pass.clear();
                        }
                    }
                } 
    );
    btDELETE.setOnAction(//CODE FOR DELETE BUTTON
            new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                      final int selectedIdx = table.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
          
 
          final int newSelectedIdx =
            (selectedIdx == table.getItems().size() - 1)
               ? selectedIdx - 1
               : selectedIdx;
 
          table.getItems().remove(selectedIdx);
          
          table.getSelectionModel().select(newSelectedIdx);
        }
                    }
                } 
    );
    
    //DIMENSIONS OF ANCHORPANE ELEMENTS
    root.getChildren().addAll(hbox1);
    
    AnchorPane.setBottomAnchor(hbox1,50d);
    root.getChildren().addAll(hbox2);
    AnchorPane.setLeftAnchor(hbox2,10d);
    AnchorPane.setBottomAnchor(hbox2,10d);
    root.getChildren().addAll(hbox3);
    AnchorPane.setTopAnchor(hbox3,10d);
    AnchorPane.setLeftAnchor(hbox3,160d);
    
    root.getChildren().addAll(hbox5);
    AnchorPane.setTopAnchor(hbox5,10d);
    
    
    // Create a scene and place it in the stage
    Scene scenecustomer = new Scene(root, 500, 500);
    primaryStage.setTitle("Bookstore App"); // Set the stage title
    primaryStage.setScene(scenecustomer); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  
  public static void main(String[] args) {
    launch(args);
  }
  
  public static class CustomerList { //CREATE BOOK OBJECT
 
        private final SimpleStringProperty Username;
        private final SimpleStringProperty Password;
        
        
 
        private CustomerList(String uName, String PPassword) {
            this.Username = new SimpleStringProperty(uName);
            this.Password = new SimpleStringProperty(PPassword);
            
            
        }
 
        public String getUsername() {
            return Username.get();
        }
 
        public void setUsername(String uName) {
            Username.set(uName);
        }
 
        public String getPassword() {
            return Password.get();
        }
 
        public void setPassword(String uName) {
            Password.set(uName);
        }
 
        
    }
} 

