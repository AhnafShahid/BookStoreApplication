/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsa;

/**
 *
 * @author Ahnaf
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
 * @author mmoda
 */
public class Owner {
    
    private String name;
        private String pass;
    
        //POINTS??
    public Owner(String name, String pass){
        this.name = name;
            this.pass = pass; 
    }
     public String getName(){
            return this.name; 
        }
    public String getPass(){
            return this.pass; 
        }
    
    
    public static Scene ownercustomer(Stage stage){
         TableView<Owner> table = new TableView<Owner>();//BLOCK TO INITIALIZE TABLE
     final ObservableList<Owner> data =
        FXCollections.observableArrayList(
            
          );
        
      AnchorPane root =new AnchorPane();
    
    
   table.setEditable(true);
 
        TableColumn UserCol = new TableColumn("Username:"); //BLOCK TO DEFINE COLUMNS IN TABLE AND MAKE SURE THE APPROPITATE VALUES ARE PUT THERE
       
        UserCol.setCellValueFactory(
                new PropertyValueFactory<Owner, String>("name"));
 
        TableColumn PassCol = new TableColumn("Password:");
      
        PassCol.setCellValueFactory(
                new PropertyValueFactory<Owner, String>("pass"));
        
        TableColumn PointsCol = new TableColumn("Points:");
      
        PointsCol.setCellValueFactory(
                new PropertyValueFactory<Owner, String>("Points"));
 
        
 
        table.setItems(data);
        table.getColumns().addAll(UserCol, PassCol, PointsCol ); //ADD THE COLUMNS TO THE TABLE
        table.setMaxWidth(250);
        table.setMinWidth(250);
        table.setMaxHeight(300);
        table.setMinHeight(300);
        
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
    Button btBACK = new Button("Back");
    HBox hbox2 = new HBox(5, btDELETE, btBACK);
    
    String FieldDelimiter = ", ";
      try(BufferedReader bw = new BufferedReader(new FileReader("ownercustomer.txt"))){
          String line;
           while ((line = bw.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                Owner cL = new Owner(fields[0], fields[1]);
                
               data.add(cL);
                
           }
          
      }catch(IOException e){
          e.printStackTrace();
      } 
    
 
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
                                 
                       data.add(new Owner(
                               user.getText(),
                               pass.getText()));
                       
                       try (BufferedWriter bw = new BufferedWriter(new FileWriter("ownercustomer.txt", true))) {  
            bw.write(user.getText()); // takes name of book and sends to textfile
            bw.write(", ");
            bw.write(pass.getText());// takes price of book and sends to textfile
            bw.newLine();
            
        } catch (IOException error) {
            error.printStackTrace();
        }
                       
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
    
    btBACK.setOnAction(//CODE FOR DELETE BUTTON
            new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                      stage.setScene(Login2.create2(stage));
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
        
       Scene scenecustomer = new Scene(root, 500, 500);
       return scenecustomer;
    }
    
}
