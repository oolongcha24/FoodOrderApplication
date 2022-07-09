/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FoodApp;


import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreen extends Application{
    
    Admin admin = new Admin(); //initialize the owner info
    ArrayList<Customer> customers = new ArrayList<Customer>(); //initialize the customers info
    
    
    //method
    public Customer checkCustomerExist(String enteredUsername, String enteredPassword){  //This method use to check if the entered username exist in the customer list
        for (Customer c : customers) { //an enhanced loop that go over the customers's list
            if(enteredUsername.equals(c.name) == true){ //Check if username match is in one of the customer list, if yes, go to the following if statement
                if(enteredPassword.equals(c.password) == true){ //Check if the password match, if yes, return that customer information.
                    return c;}}}
        return null; //return null when username or password invalid.
    }
    
    
    public static void main(String[] args) {
        launch(args); 
    }

    
    @Override
    public void start(Stage primaryStage){
        
        
        customers = admin.getCustomerList(); //To load all customer info from customer list textfile
        
        
        primaryStage.setTitle("Login Screen"); //Title of the window
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        grid.add(scenetitle, 0, 0, 2, 1);

        //Username label
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        //Username enter box
        TextField userBox = new TextField(); 
        grid.add(userBox, 1, 1);

        //Password label
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
     
        //password enter box
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        
        
        Button sign_in_button = new Button("Sign in"); //create a sign in butadmton printed "sign in"
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(sign_in_button);
        grid.add(hbBtn, 1, 4);

              
       
        sign_in_button.setOnAction(new EventHandler<ActionEvent>() { //When sign in button clicked, it run the following code...

            @Override
            public void handle(ActionEvent e) {   

                String entered_username = userBox.getText(); //scan the usernmae textbox
                String entered_password = pwBox.getText(); //scan the password textbox
     
                // For customer login
                Customer LoginCustomer = checkCustomerExist(entered_username, entered_password); //Use the "checkCustomerExist" to check whether the login info match one of the customer from customers arraylist
                if( LoginCustomer != null){  //Refer to the "checkCustomerExist" method.
                    System.out.println("Enterd as Customer: " + LoginCustomer.name);
                    primaryStage.close();
                    CustomerStartScreen Customer_Start_Screen = new CustomerStartScreen();
                    /////////////////////TEST!! 
                    Customer_Start_Screen.openScreen(LoginCustomer);///Pass the LoghinCustomer info into CustomerStartScreen class
                }
                
                //For admin(Owner) login
                else if(entered_username.equals(admin.owner_username) == true && entered_password.equals(admin.owner_password) == true){ //if both username and password is "admin", then it go to Owner login screen....
                    System.out.println("Entered as Owner(Admin)");
                        primaryStage.close(); //close the login stage
                        OwnerStartScreen Owner_Start_Screen = new OwnerStartScreen();  //Intialize the OwnerStarttScreen object
                        Owner_Start_Screen.openScreen(); //Call the method inside "OwnerStarttScreen", and open up that screen

                    } 
                
                
                else{ //If the login usernanme and password doesn't match any of customer and the owner, it print out a invalid statement on screen
                    Text Invalidinput = new Text();
                    grid.add(Invalidinput, 1, 6);
                    Invalidinput.setFill(Color.FIREBRICK);
                    Invalidinput.setText("Incorrect username or password ");
                    }
               }



        });   //End of the button action


        Scene scene = new Scene(grid, 300, 275); //set scene size
        primaryStage.setScene(scene);
        primaryStage.show(); //show scene
    }


    


}








