/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.control.CheckBox;
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
public class Books {
    
 
        private String title;
        private double price;
        private CheckBox select; 
        private Boolean bought; 
            
        public Books(String title, double price) {
            this.title = title;
            this.price = price; 
            bought = false; 
            select = new CheckBox(); 
        }

    public Books() {
        
    }
        
        public String getTitle(){
            return this.title; 
        }
        public double getPrice(){
            return this.price; 
        }
        
        
        public CheckBox getSelect(){
            return this.select;
        }
        
        public Boolean getChecbox(){
            return this.select.isSelected(); 
        }
        public Boolean getStatus(){
            return bought; 
        }
        public void setStatus(Boolean t){
            bought = t; 
        }
        
        
        public static void remover(String filepath, String removeTerm, int positionofTerm, String delimiter){
            int position = positionofTerm -1;
            String tempFile = "temp.txt";
            File oldFile = new File (filepath);
            File newFile = new File (tempFile);
            
            String currentLine;
            String data[];
            
            try {
                FileWriter fw = new FileWriter(tempFile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                
                FileReader fr = new FileReader(filepath);
                BufferedReader br = new BufferedReader(fr);
                
                while ((currentLine = br.readLine()) != null  ){
                    data = currentLine.split(",");
                    if(!(data[position].equalsIgnoreCase(removeTerm))){
                        pw.println(currentLine);
                    }
                }
                pw.flush();
                pw.close();
                fr.close();
                br.close();
                bw.close();
                fw.close();
                
                oldFile.delete();
                File dump = new File(filepath);
                newFile.renameTo(dump);
            
            }
            
            catch (Exception e) {
                
                
            }
        }
        
        
        
        private static Double HandleString(String string){
            try{
                return Double.parseDouble(string);
            }
                catch(Exception e){
                           //System.out.println("Enter a number for the price");
                           return 20.5;
                       }
            
         
            
        }
        
        
        public static Scene ownerbooks(Stage stage){
             TableView<Books> table = new TableView<Books>();//BLOCK TO INITIALIZE TABLE
         final ObservableList<Books> data =
        FXCollections.observableArrayList(
           
          );
         
         
          AnchorPane root =new AnchorPane();
    
    
   table.setEditable(true);
 
        TableColumn BookCol = new TableColumn("Name:"); //BLOCK TO DEFINE COLUMNS IN TABLE AND MAKE SURE THE APPROPITATE VALUES ARE PUT THERE
       
        BookCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("title"));
 
        TableColumn PriceCol = new TableColumn("Book Price ($CAD):");
      
        PriceCol.setCellValueFactory(
                new PropertyValueFactory<Books, String>("price"));
 
        
 
        table.setItems(data);
        table.getColumns().addAll(BookCol, PriceCol); //ADD THE COLUMNS TO THE TABLE
        
        table.setMaxWidth(250);
        table.setMinWidth(250);
        table.setMaxHeight(300);
        table.setMinHeight(300);
    
    BookCol.setMinWidth(125); //DIMENSIONS OF EACH COLUMN
    BookCol.setMaxWidth(125);
    PriceCol.setMinWidth(125);
    PriceCol.setMaxWidth(125);
    BookCol.setSortable(false);
    PriceCol.setSortable(false);
    
   
    HBox hbox3 = new HBox(table);
    
    //ADD UI ELEMENTS
    TextField tfMi = new TextField();
    TextField Price = new TextField();
    Label label1 = new Label("Name:");
    Label label = new Label("Price:");
    
    Label warning = new Label("");
    Price.setPrefWidth(35);
    Button btADD = new Button("Add");
    HBox hbox1 = new HBox(5, label1, tfMi, label,Price,btADD);
    
    HBox hbox5 = new HBox(5, warning);
    
    Button btDELETE = new Button("Delete");
    Button btBACK = new Button("Back");
    
    
    HBox hbox2 = new HBox(5, btDELETE, btBACK);
    
    String FieldDelimiter = ", ";
      try(BufferedReader bw = new BufferedReader(new FileReader("ownerbooks.txt") )){
          String line;
           while ((line = bw.readLine()) != null) {
               String[] fields = line.split(FieldDelimiter, -1);
               
               Books book = new Books(fields[0],  parseDouble(fields[1]) );
               data.add(book);
               
           }
      }catch (IOException e){
          
      }
     
    
    btADD.setOnAction( //CODE FOR ADD BUTTON
            new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                      
                        if ((tfMi.getText() == null || tfMi.getText().trim().isEmpty()) || (Price.getText() == null || Price.getText().trim().isEmpty()) ) //CHECK IF ANY TEXTBOXES ARE EMPTY
                        {
                            
                            warning.setText("Please enter name AND price");
                       tfMi.clear();
                       Price.clear();
                        }else{ //OTHERWISE ADD DATA TO TABLE
                       
                       warning.setText("");
                       
                       if(HandleString(Price.getText()) == 20.5 ){
                           warning.setText("Enter a number for the price");
                           tfMi.clear();
                           Price.clear();
                       }
                       else{
                       
                           
                           Books b = new Books(tfMi.getText(), Double.parseDouble(Price.getText()));
                           data.add(b);
                          
                      /**data.add(new Books(
                               tfMi.getText(),
                              Double.parseDouble(Price.getText()))); **/
                       }
                       
                       try (BufferedWriter bw = new BufferedWriter(new FileWriter("ownerbooks.txt", true))) {  
            bw.write(tfMi.getText()); // takes name of book and sends to textfile
            bw.write(", ");
            bw.write(Price.getText());// takes price of book and sends to textfile
            //bw.write(".");
            bw.newLine();
            
        } catch (IOException error) {
            error.printStackTrace();
        }  
                      
                       tfMi.clear();
                       Price.clear();
                        }
                    }
                } 
    );
   
    btDELETE.setOnAction(//CODE FOR DELETE BUTTON
            new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                       
                       ObservableList<Books> productslist;
                      productslist = table.getSelectionModel().getSelectedItems();
                      
                      String x = productslist.get(0).getTitle(); 
                       remover("ownerbooks.txt", x, 1, "," );
                       
                       table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
                       
                   
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
    
    
    
    
    //btBACK.setOnAction(event-> stage.setScene(Login2.create2(stage)));
    
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
    Scene scenebooks = new Scene(root, 500, 500);
    
    return scenebooks;
           
           
           
       } 
        
        
    }
