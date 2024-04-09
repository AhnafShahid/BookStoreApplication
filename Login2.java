package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

/**
 *
 * @author mmoda
 */
public class Login2 {
    private static ArrayList <Customer> customers = new ArrayList <Customer>(); 
    private static int checker = 0; 
    
    public static void addCust(Customer c){
        customers.add(c); 
    }
    
    
    public static void read(){
      String FieldDelimiter = ", ";
      try(BufferedReader bw = new BufferedReader(new FileReader("ownercustomer.txt"))){
          String line;
           while ((line = bw.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                Customer cL = new Customer(fields[0], fields[1]);
                customers.add(cL); 

                
           }
          
      }catch(IOException e){
        //  e.printStackTrace();
      } 
    }
    
    //create login screen
    
    public static Scene create1(Stage stage){
        Button login = new Button("Login");
    Label user = new Label("Username:");
    Label pass = new Label("Password:");
    Label welcome = new Label("Welcome to the Bookstore App");
    Label warning = new Label("");
    if (checker == 0){
    read(); 
    checker++; 
    }
   
   
    
    TextField usertext = new TextField();
    PasswordField passtext = new PasswordField(); 
        
      usertext.clear();
      passtext.clear();
        
        GridPane gp1 = new GridPane();
        gp1.setAlignment(Pos.CENTER); 
        gp1.add(login, 0, 4);
        gp1.add(user, 0, 1);
        gp1.add(pass, 0, 2);
        //add a TextField in the GridPane at column 0 and row 1
        gp1.add(usertext, 2, 1);
        gp1.add(passtext, 2, 2);
        gp1.add(welcome, 0,0);
        gp1.add(warning, 2, 5);
        
        
        login.setOnAction(event->{
         if(usertext.getText().equals("admin") && passtext.getText().equals("admin")){
            Scene newscene = Login2.create2(stage);
        stage.setScene(newscene);
        }
        else{
             
            for (int i = 0; i<customers.size(); i++){
                if (usertext.getText().equals(customers.get(i).getCustName()) && 
                        passtext.getText().equals(customers.get(i).getPassword())){
                    Customer c = customers.get(i); 
                    stage.setScene(c.customerstartscreen(stage,c));
                       
                }
                else{
                    warning.setText("Please enter valid customer \n username and password");
                }

            }
            
          //  Customer c = new Customer(usertext.getText(), passtext.getText());
            //stage.setScene(c.customerstartscreen(stage,c));
            
        }
        
        
        
        });
       
        Scene scene = new Scene(gp1, 500, 500);
        return scene;
    }
    
    
    //create owner start screen
    
    public static Scene create2(Stage stage){
     Button Books4 = new Button("Books");
    Button Customer5 = new Button("Customers");
    Button Logout = new Button("Logout");
        Books4.setPrefWidth(100);
        Customer5.setPrefWidth(100);
        Logout.setPrefWidth(100);
        VBox root = new VBox();
        root.getChildren().addAll(Books4, Customer5, Logout);
       
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        Logout.setOnAction(event -> stage.setScene(Login2.create1(stage)));
        
        Books4.setOnAction(event-> stage.setScene(Books.ownerbooks(stage)));
        
        Customer5.setOnAction(event -> stage.setScene(Customer.ownercustomer(stage)));
        
        Scene scene = new Scene(root, 500,500);
        
        return scene;
        
    }
    
    
}
