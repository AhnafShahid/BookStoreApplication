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
public class ownerbook extends Application { 
    private TableView<Book> table = new TableView<Book>();//BLOCK TO INITIALIZE TABLE
    private final ObservableList<Book> data =
        FXCollections.observableArrayList(
            
          );
  @Override 
  public void start(Stage primaryStage) {
    
    AnchorPane root =new AnchorPane();
    
    
   table.setEditable(true);
 
        TableColumn BookCol = new TableColumn("Name:"); //BLOCK TO DEFINE COLUMNS IN TABLE AND MAKE SURE THE APPROPITATE VALUES ARE PUT THERE
       
        BookCol.setCellValueFactory(
                new PropertyValueFactory<ownerbook.Book, String>("Name"));
 
        TableColumn PriceCol = new TableColumn("Book Price ($CAD):");
      
        PriceCol.setCellValueFactory(
                new PropertyValueFactory<ownerbook.Book, String>("Price"));
 
        
 
        table.setItems(data);
        table.getColumns().addAll(BookCol, PriceCol); //ADD THE COLUMNS TO THE TABLE
    
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
    HBox hbox2 = new HBox(5, btDELETE);
    
 
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
                                 
                       data.add(new Book(
                               tfMi.getText(),
                               Price.getText()));
                       
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
    Scene scenebooks = new Scene(root, 500, 500);
    primaryStage.setTitle("Bookstore App"); // Set the stage title
    primaryStage.setScene(scenebooks); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  
  public static void main(String[] args) {
    launch(args);
  }
  
  public static class Book { //CREATE BOOK OBJECT
 
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Price;
        
 
        private Book(String bName, String bPrice) {
            this.Name = new SimpleStringProperty(bName);
            this.Price = new SimpleStringProperty(bPrice);
            
        }
 
        public String getName() {
            return Name.get();
        }
 
        public void setName(String bName) {
            Name.set(bName);
        }
 
        public String getPrice() {
            return Price.get();
        }
 
        public void setPrice(String bName) {
            Price.set(bName);
        }
 
        
    }
} 

