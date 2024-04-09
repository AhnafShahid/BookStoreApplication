package project;

/**
 *
 * @author Ahnaf
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author mmoda
 */
public class Customer {
    private State mystate;
    private int points; 
    private String username;
    private String password;
    private double price; 
    private ArrayList<Books> book = new ArrayList<Books>();
    
    
    public Customer(String username, String password){
        this.username = username;
        this.password = password;
        points = 0;
        mystate = new Silver(); 
    }
    public String getUsername(){
            return this.username; 
        }
    public String getPassword(){
            return this.password; 
        }
    
    public State getState(){
        return mystate;
    }
    
    public void setstate(State s){
        mystate =s;
    }
    
    public int getPoints(){
        return this.mystate.points; 
    }
    
    
    //add book to checkout
    public void addbooks(Books b){
        book.add(b);
    }
    
    
    //remove book from checkout
    public void removebooks(Books b){
        book.remove(b);
    }
    
    
    //returns total price of all books selected
    public double totalprice(){
        double total =0;
        for(Books b: book){
            total = total+ b.getPrice();
        }
        return total;
    }
    
    public void redeemBook(){
        double price = totalprice(); 
        System.out.println("price in arraylist" + price); 
        //addpoints(-1*(int)(price*10));
        price = mystate.redeempoints(price, mystate); 
        for (int i = 0; i<book.size(); i++){
            book.get(i).setStatus(true); 
        }
        
        this.price = price; 
        clearBooks(); 
       
    }
    
    public void buyBook(){
        double price = totalprice(); 
        
        //addpoints((int)(price*10)); 
        for (int i = 0; i<book.size(); i++){
            book.get(i).setStatus(true); 
        }
        mystate = mystate.updatestatus((int)price*10, mystate);
        this.price = price; 
        clearBooks(); 
        
    }
    
    public void clearBooks(){
        book = new ArrayList <Books>();
        
    }
    public double getFinalPrice(){
        return this.price; 
    }

    public String getCustName(){
        return this.username; 
    }
    
  /*  public void overWrite(){
      String FieldDelimiter = ", ";
      try(BufferedReader bw = new BufferedReader(new FileReader("ownercustomer.txt"))) {
          try (BufferedWriter bf = new BufferedWriter(new FileWriter("ownercustomer.txt", true))){
              
          
          String line;
           while ((line = bw.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                if (fields [0] == getCustName() && fields [1] == getPassword())
                    //delete line
                    //write 1 line with custName()
                
           }
          }
      }catch(IOException e){
          e.printStackTrace();
      } 
    }
    
    
    */
    
    //customer cost screen 
    
    //customer cost screen 
    public static Scene create(Stage stage, Customer c, int checker){
        stage.setWidth(500); 
       stage.setHeight(500); 
       Button logout = new Button("Logout");
       

    Label points2 = new Label("Total Points: "+ c.mystate.getPoints());
    Label total = new Label("Total Cost: $" +c.getFinalPrice());
    Label points = new Label("Points Recieved: ");
    Label status = new Label("Current Status: " + c.mystate.getName()); 
   
    logout.setOnAction(event -> stage.setScene(Login2.create1(stage)));
       VBox vbox = new VBox(total, points, points2, status, logout);
        vbox.setSpacing(5); 
        vbox.setAlignment(Pos.CENTER);
       vbox.setPadding(new Insets (40, 40, 40, 40)); 
    Scene scene = new Scene(vbox, 500, 500);
    return scene;
    }
    
    
    
    public static Scene customerstartscreen(Stage stage, Customer c){
        

     TableView<Books> table = new TableView<Books>(); 
     final ObservableList <Books> data = 
            FXCollections.observableArrayList ( new Books ("Testing", 1), new Books ("testing 2", 10)); 
        
      final HBox hb = new HBox();
      
      Scene scene = new Scene (new Group()); 
      
       Label label1 = new Label ("Welcome " + c.getCustName()+"." +" You have " + c.mystate.getPoints()+ " points. Your status is " + c.mystate.getName());
       label1.setFont(new Font (20)); 
       table.setEditable(true);
       
       TableColumn bookNameCol = new TableColumn ("Book Name"); 
       bookNameCol.setMinWidth(250); 
       bookNameCol.setCellValueFactory(new PropertyValueFactory<Books, String> ("title")); 
      // bookNameCol.setCellFactory(TextFieldTableCell.forTableColumn()); 
       
       TableColumn priceCol = new TableColumn ("Price"); 
       priceCol.setMinWidth(100); 
       priceCol.setCellValueFactory(new PropertyValueFactory <Books, String> ("price")); 
       
       TableColumn boxCol = new TableColumn ("Select"); 
       boxCol.setMinWidth(100); 
       boxCol.setCellValueFactory(new PropertyValueFactory <Books, String> ("select")); 
       
       
       table.setItems(data); 
       table.getColumns().addAll(bookNameCol, priceCol, boxCol); 
       table.setMaxHeight(380);
       
       final Button buyButton = new Button("Buy"); 
       final Button redButton = new Button("Redeem points & Buy"); 
       final Button logButton = new Button("Logout"); 
       
        logButton.setOnAction(event -> stage.setScene(Login2.create1(stage)));
        
         buyButton.setOnAction(event -> {
             c.checkData(data); 
             c.buyBook();
             stage.setScene(Customer.create(stage,c,1));
                 });
         
          redButton.setOnAction(event -> {
              c.checkData(data);
              c.redeemBook();
              stage.setScene(Customer.create(stage,c,2));
                  });
       
       hb.getChildren().addAll(buyButton, redButton, logButton); 
       hb.setSpacing(5); 
       
       VBox vbox = new VBox(); 
       vbox.setSpacing(5); 
       vbox.setPadding(new Insets (10, 0, 0, 10)); 
       vbox.getChildren().addAll(label1, table, hb); 
       ((Group) scene.getRoot()).getChildren().addAll(vbox);
     
    
        return scene;
    }
    
    public void checkData(ObservableList<Books> data){
    for (int i = 0; i<data.size(); i++){ //new
        if(data.get(i).getChecbox() == true){
            book.add(data.get(i)); 
        }
    }
}
    
    
    
    
    //owner customer screen
    
    public static Scene ownercustomer(Stage stage){
         TableView<Customer> table = new TableView<Customer>();//BLOCK TO INITIALIZE TABLE
     final ObservableList<Customer> data =
        FXCollections.observableArrayList(
            
          );
        
      AnchorPane root =new AnchorPane();
    
    
   table.setEditable(true);
 
        TableColumn UserCol = new TableColumn("Username:"); //BLOCK TO DEFINE COLUMNS IN TABLE AND MAKE SURE THE APPROPITATE VALUES ARE PUT THERE
       
        UserCol.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("username"));
 
        TableColumn PassCol = new TableColumn("Password:");
      
        PassCol.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("password"));
        
        TableColumn PointsCol = new TableColumn("Points:");
      
        PointsCol.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("points"));
 
        
 
        table.setItems(data);
        table.getColumns().addAll(UserCol, PassCol, PointsCol ); //ADD THE COLUMNS TO THE TABLE
        table.setMaxWidth(240);
        
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
                Customer cL = new Customer(fields[0], fields[1]);
                
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
                       Customer c = new Customer(user.getText(), pass.getText());
                        c.mystate.getPoints();
                        data.add(c);          
                      /* data.add(new Customer(
                               user.getText(),
                               pass.getText()));*/
                      
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
