

package FoodApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerStartScreen {
    Buy buy_class = new Buy(0); //intialize buy class
    Admin admin = new Admin();
    ArrayList<Food> foodlist = new ArrayList();
    ObservableList<Food> b; //To create the columns
//    Buy buy = new Buy();
    public ObservableList<Food> getFood(){
    foodlist = admin.getFoodList(); //Get foodlist info from Admin class
    b = FXCollections.observableArrayList(foodlist);
    return b;
    }
    
    
    public void openScreen(Customer current_Customer){
    
    Stage stage = new Stage();
    stage.setTitle("Customer Start Screen");
    
    
    Text subtitle = new Text("Welcome " + current_Customer.name + ". You have " + current_Customer.points + " points. Your status is " + current_Customer.getStatus() + "."); 
    //Top part: Welcome (customer). You have (points) points. Your status is (status).
    subtitle.setFont(new Font(20));

    //Setup column 1
        TableColumn<Food, String> Column_1 = new TableColumn<>("Food name");  //<Object, data type>
        Column_1.setMinWidth(120); //set minimum size of column
        Column_1.setCellValueFactory(new PropertyValueFactory<>("name")); //Let this column use the "name" data of "Food"
        
        //Setup column 2
        TableColumn<Food, String> Column_2 = new TableColumn<>("Food price"); 
        Column_2.setMinWidth(120); //set minimum size of column
        Column_2.setCellValueFactory(new PropertyValueFactory<>("price")); //Let this column use the "price" data of "Food"
         
       //Setup column 3 (CHECKBOXES) 
       TableColumn<Food, CheckBox> selectColumn = new TableColumn<>("Select");
       selectColumn.setMinWidth(120); // set minimun width of column
       selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
       
       
        
        //set up table
        TableView<Food> table = new TableView<>();  //create a table
        table.setItems(getFood()); //get all the data food info from getFood method
        table.getColumns().addAll(Column_1, Column_2, selectColumn); //add 3 column to the table
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);//Allow muti-select in column by pressing "shift" and click on the item
        
        TextField addFoodName = new TextField();
        addFoodName.setPromptText("Food Name"); //Tell the user to enter Username
        addFoodName.setMaxWidth(125); //Set maximum size
        
        TextField addPrice = new TextField(); //Similiar to "addFoodName" textfield
        addPrice.setPromptText("Price");
        addPrice.setMaxWidth(125);
            
        Button buy = new Button("Buy");
        
        
       
        
        
        buy.setOnAction(new EventHandler<ActionEvent>() { //When the "Buy" button is pressed, run the following code
            @Override
            public void handle(ActionEvent e) {

                ArrayList<Food> selectedFood = buy_class.getSelectedFoodList(foodlist);
                Customer updated_customer = buy_class.buy(current_Customer, selectedFood); //call the buy method in buy class
                double totalCost = buy_class.getTotalCost();
                
                for(Food b: selectedFood){
                    try {
                        admin.deleteFood(b.name); //call the deleteFood method from Admin class
                    } catch (IOException ex) {}
                    
                }
                selectedFood.forEach(b :: remove); //remove those selected items in the table ....../////////////////////////////////////////////////////////
                
                
            
            stage.close();
            CustomerCostScreen Customer_Cost_Screen = new CustomerCostScreen(); //open the "CustomerCostScreen"
            Customer_Cost_Screen.openScreen(updated_customer, totalCost);
            }
        });
        
        Button buyRedeem = new Button("BuyRedeem");
        buyRedeem.setOnAction(new EventHandler<ActionEvent>() { //When the "BuyRedeem" button is pressed, run the following code
            @Override
            public void handle(ActionEvent e) {
                ArrayList<Food> selectedFood = buy_class.getSelectedFoodList(foodlist);
                Customer updated_customer = buy_class.buyAndRedeem(current_Customer, selectedFood);
                double totalCost = buy_class.getTotalCost();
                
                for(Food b: selectedFood){
                    try {
                        admin.deleteFood(b.name); //call the deleteFood method from Admin class
                    } catch (IOException ex) {}
                    
                }
                selectedFood.forEach(b :: remove); //remove those selected items in the table ....../////////////////////////////////////////////////////////
                
                stage.close();
                CustomerCostScreen Customer_Cost_Screen = new CustomerCostScreen(); //open the "CustomerCostScreen"
                Customer_Cost_Screen.openScreen(updated_customer, totalCost); 
            }
        });
        
    //Setup back button
        Button back_Button = new Button("Back");
        back_Button.setOnAction(new EventHandler<ActionEvent>() { //When the "Back" button is pressed, run the following code
            @Override
            public void handle(ActionEvent e) {
                stage.close();
                //Go back to login in screen
                Stage stage = new Stage(); 
                LoginScreen loginscreen = new LoginScreen(); 
                loginscreen.start(stage);
            }
        });
        
    HBox hb1  = new HBox(); 
    hb1.getChildren().addAll(back_Button, buy, buyRedeem);
    hb1.setAlignment(Pos.CENTER);
    hb1.setSpacing(10);
        
    VBox vbox = new VBox(); //A Vetical box that merge the subtitle, table ,hb1 and hb2 together.
    vbox.getChildren().addAll(subtitle, table, hb1);
    vbox.setSpacing(3);
 
    Scene scene = new Scene(vbox,500,500);
    stage.setScene(scene);
    stage.show();


    
    }
    
}














