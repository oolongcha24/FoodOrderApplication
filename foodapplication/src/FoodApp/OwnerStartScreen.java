
package FoodApp;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

 public class OwnerStartScreen{

    Button but1, but2, but3; //initialize 3 buttons

    public void openScreen(){ //Method that open the "Owner-start-screen"   
        
        Stage stage = new Stage();
        stage.setTitle("Owner Start Screen"); //set titile of the window
        

        
        but1 = new Button("Foods"); //create a button and call it "Foods"
        but2 = new Button("Customers");
        but3 = new Button("Logout");
        but1.setTranslateX(9); //set the X position of button
        but1.setTranslateY(-20); //set the Y position of button
        but2.setTranslateX(9);
        but2.setTranslateY(20);
        but3.setTranslateX(9);
        but3.setTranslateY(60);
        
        Text subtitle = new Text ("Welcome back, owner!"); //add subtitle to the screen
        subtitle.setFont(new Font(20)); //set the size of subtitle to 20
        subtitle.setTranslateX(9);
        subtitle.setTranslateY(-60);
        
        
        StackPane layout = new StackPane(); 
        layout.getChildren().add(but1); // add button to the screen
        layout.getChildren().add(but2);
        layout.getChildren().add(but3);
        layout.getChildren().add(subtitle); //add text to the screen
  
        but3.setOnAction(new EventHandler<ActionEvent>() { //When "Logout" button is clicked, it run the following code...

            @Override
            public void handle(ActionEvent e) {   
                System.out.println("Logout is clicked");
                stage.close(); //close the current stage
                
                //Go back to login in screen
                Stage stage = new Stage(); 
                LoginScreen loginscreen = new LoginScreen(); 
                loginscreen.start(stage);
               }});   //End of the action for but3
        
        but2.setOnAction(new EventHandler<ActionEvent>() { //When "Customers" button is clicked, it run the following code...

            @Override
            public void handle(ActionEvent e) {
                    System.out.println("Customer is clicked");
                    stage.close(); //close the current screen
                    OwnerCustomerScreen Owner_Customer_Screen = new OwnerCustomerScreen(); //open the "OwnerCustomerScreen"
                    Owner_Customer_Screen.openScreen();
            }});
        
        but1.setOnAction(new EventHandler<ActionEvent>() { //When "Foods" button is clicked, it run the following code...

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Foods is clicked");
                stage.close(); //close the current screen
                OwnerFoodScreen Owner_Food_Screen = new OwnerFoodScreen(); //open the "OwnerFoodScreen"
                Owner_Food_Screen.openScreen();
            }});
        
        
        Scene scene = new Scene(layout, 300, 275); //set the size of the window 
        stage.setScene(scene); 
        stage.show(); //show the window after doen setting up

    }
    
}









