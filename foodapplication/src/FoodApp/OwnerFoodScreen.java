
package FoodApp;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.geometry.Pos;
import java.io.IOException;


public class OwnerFoodScreen {
    Admin admin = new Admin();
    ArrayList<Food> Foodlist = new ArrayList();
    ObservableList<Food> b; //To create the columns
    
 
    
    public ObservableList<Food> getFood(){
    Foodlist = admin.getFoodList(); //Get Foodlist info from Admin class
    b = FXCollections.observableArrayList(Foodlist);
    return b;
    }
    
    public void openScreen(){
    Stage stage = new Stage();
    stage.setTitle("Owner Food Screen");
    
        //Setup column 1
        TableColumn<Food, String> Column_1 = new TableColumn<>("Food name");  //<Object, data type>
        Column_1.setMinWidth(120); //set minimum size of column
        Column_1.setCellValueFactory(new PropertyValueFactory<>("name")); //Let this column use the "name" data of "Food"
        
        //Setup column 2
        TableColumn<Food, String> Column_2 = new TableColumn<>("Food price"); 
        Column_2.setMinWidth(120); //set minimum size of column
        Column_2.setCellValueFactory(new PropertyValueFactory<>("price")); //Let this column use the "price" data of "Food"
         
        //set up table
        TableView<Food> table = new TableView<>();  //create a table
        table.setItems(getFood()); //get all the data Food info from getFood method
        table.getColumns().addAll(Column_1, Column_2); //add 2 column to the table
        
        TextField addFoodName = new TextField();
        addFoodName.setPromptText("Food Name"); //Tell the user to enter Username
        addFoodName.setMaxWidth(125); //Set maximum size
        
        TextField addPrice = new TextField(); //Similiar to "addFoodName" textfield
        addPrice.setPromptText("Price");
        addPrice.setMaxWidth(125);
        
        // Add Button
        Button add_Button = new Button("Add"); // Add Button
        add_Button.setOnAction(new EventHandler<ActionEvent>() { //Run the following code when the "Add" button is clicked
            @Override
            public void handle(ActionEvent e) {
                if((addFoodName.getText().equals("") == false) && (addPrice.getText().equals("") == false)){ //make sure that both textbox is not empty before proceeding
                   try{ //if the price textbox is not a number, it will catch a error, and printout Invalid inputs
                       
                    b.add(new Food(addFoodName.getText(), (Double.parseDouble(addPrice.getText())))); //scan the text in the textfile and create a new Customer (Display the new customer on table)
                   admin.addFood(addFoodName.getText() , (Double.parseDouble(addPrice.getText()))); //Call the addFood() method in Admin class
                   
                    addFoodName.clear(); //clear out the text in the textfile after added to the list
                    addPrice.clear();
                    System.out.println("New Food added");
                    }   catch (NumberFormatException ex2){
                            System.out.println("Please enter valid input for price, not string!");
                        }
                        
                    }
                }
                
            });

         // Delete Button
         Button delete_Button = new Button("Delete the selected Food");
        delete_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ArrayList<Food> selectedFood = new ArrayList<Food>(table.getSelectionModel().getSelectedItems());
                for(Food b: selectedFood){
                    try {
                        admin.deleteFood(b.name); //call the deleteFood method from Admin class
                    } catch (IOException ex) {}
                    
                }
                selectedFood.forEach(b :: remove); //remove those selected items in the table ....../////////////////////////////////////////////////////////
            }
        });
        
        //Setup back button
        Button back_Button = new Button("Back");
        back_Button.setOnAction(new EventHandler<ActionEvent>() { //When the "Back" button is pressed, run the following code
            @Override
            public void handle(ActionEvent e) {
                stage.close();
                OwnerStartScreen Owner_Start_Screen = new OwnerStartScreen(); //Go back to "owner start screen"
                Owner_Start_Screen.openScreen();
            }
        });
        
        
        HBox hb1  = new HBox(); //Horizontal box display delete and back button
        hb1.getChildren().addAll(delete_Button, back_Button);
        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(10); //gap between delete and back button
        
        HBox hb2 = new HBox(); //A horizontal box that display textbox and "add" button
        hb2.getChildren().addAll(addFoodName, addPrice, add_Button);
        hb2.setSpacing(3); //gap between textbox and "add" button
        
        VBox vbox = new VBox(); //A Vetical box that merge the table and hb1 and hb2 together.
        vbox.getChildren().addAll(table, hb2, hb1);
        vbox.setSpacing(3);
 
        Scene scene = new Scene(vbox,300,400);
        stage.setScene(scene);
        stage.show();
    }
}








