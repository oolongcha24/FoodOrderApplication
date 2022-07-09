
package FoodApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.SelectionMode;

//Reference from https://www.youtube.com/watch?v=mtdlX2NMy4M
//and https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
public class OwnerCustomerScreen {
    Admin admin = new Admin();
    ArrayList<Customer> customerlist = new ArrayList();
    ObservableList<Customer> c; //Observablelist is use for making the columns
    //ObservableList<Customer> selectedCustomer; //Use for deletion of selected item
  //  File customer_file = new File("customer list.txt");
    
    
    public ObservableList<Customer> getCustomer(){
        customerlist = admin.getCustomerList(); //get the customer list info from Admin class's getCustomerList method
        c = FXCollections.observableArrayList(customerlist); 
        return c;
    }
    
    public void openScreen(){ //Method that open the "Owner-start-screen"  
        
        Stage stage = new Stage();
        stage.setTitle("Owner Customer Screen");
                
        //Setup column 1
        TableColumn<Customer, String> Column_1 = new TableColumn<>("Username");  //<Object, data type>
        Column_1.setMinWidth(120); //set minimum size of column
        Column_1.setCellValueFactory(new PropertyValueFactory<>("name")); //Let this column use the "name" data of "Customer"
        
        //Setup column 2
        TableColumn<Customer, String> Column_2 = new TableColumn<>("Password"); 
        Column_2.setMinWidth(120); //set minimum size of column
        Column_2.setCellValueFactory(new PropertyValueFactory<>("password")); //Let this column use the "password" data of "Customer"
        
        //Setup column 3
        TableColumn<Customer, String> Column_3 = new TableColumn<>("Points");  
        Column_3.setMinWidth(60); //set minimum size of column
        Column_3.setCellValueFactory(new PropertyValueFactory<>("points")); //Let this column use the "points" data of "Customer"
        
        //set up table
        TableView<Customer> table = new TableView<>();  //create a table
        table.setItems(getCustomer()); //get all the data customer info from getCustomer method
        table.getColumns().addAll(Column_1, Column_2, Column_3); //add all 3 column to the table
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);//Allow muti-select in column by pressing "shift" and click on the item
        
       
        TextField addUsername = new TextField(); //Create a textfiled for entering username to add to the customerlist
        addUsername.setPromptText("Username"); //Tell the user to enter Username
        addUsername.setMaxWidth(125); //Set maximum size
        
        TextField addPassword = new TextField(); //Similiar to "addUsername" textfield
        addPassword.setPromptText("Password");
        addPassword.setMaxWidth(125);

        //Sett up Add button
        Button add_Button = new Button("Add"); //Create a new button "Add"
        add_Button.setOnAction(new EventHandler<ActionEvent>() { //Run the following code when the "Add" button is clicked
            @Override
            public void handle(ActionEvent e) {
                if((addUsername.getText().equals("") == false) && (addPassword.getText().equals("") == false)){ //make sure that both textbox is not empty before proceeding
                    c.add(new Customer(addUsername.getText(), addPassword.getText(), 0 )); //scan the text in the textfile and create a new Customer (Display the new customer on table)
                    admin.addCustomer(addUsername.getText() , addPassword.getText()); //Call the addCustomer() method in Admin class
                    addUsername.clear(); //clear out the text in the textfile after added to the list
                    addPassword.clear();
                    System.out.println("New customer added");
                    }
                }
            });
        
//Reference: https://www.youtube.com/watch?v=uz2sWCnTq6E

        //Setup delete button
        Button delete_Button = new Button("Delete the selected customer");
        delete_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ArrayList<Customer> selectedCustomer = new ArrayList<Customer>(table.getSelectionModel().getSelectedItems());
                for(Customer c: selectedCustomer){
                    try {
                        //System.out.println(c.name);
                        admin.deleteCustomer(c.name); //call the deleteCustomer method from Admin class
                        //call removeCustomer() method from Admin class
                        ///////////////////////////////__
                    } catch (IOException ex) {}
                    
                }
                selectedCustomer.forEach(c :: remove); //remove those selected items in the table ....../////////////////////////////////////////////////////////
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
        hb1.setSpacing(10); //gap between delte and back button
        
        HBox hb2 = new HBox(); //A horizontal ox that display textbox and "add" button
        hb2.getChildren().addAll(addUsername, addPassword, add_Button);
        hb2.setSpacing(3); //gap between textbox and "add" button
        
        VBox vbox = new VBox(); //A Vetical box that merge the table and hb1 and hb2 together.
        vbox.getChildren().addAll(table, hb2, hb1);
        vbox.setSpacing(3);
 
        Scene scene = new Scene(vbox,300,400);
        stage.setScene(scene);
        stage.show();
    }
}










