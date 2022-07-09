package FoodApp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerCostScreen {
    
    Button but1;  //logout button
    
    public void openScreen(Customer current_Customer, double totalCost){ 
    
    Stage stage = new Stage();
    stage.setTitle("Customer Cost Screen");
    
    but1 = new Button("Logout");
    
    Text sub1 = new Text("Thank you for your purchase!"); 
    Text sub2 = new Text("Total Cost: "+ totalCost );
    Text sub3 = new Text("Your current Points: " + current_Customer.points  + " Status: " + current_Customer.getStatus() + ".");
    

    
    but1.setOnAction(new EventHandler<ActionEvent>() { //When "Logout" button is clicked, it run the following code...

            @Override
            public void handle(ActionEvent e) {   
                System.out.println("Logout is clicked");
                stage.close(); //close the current stage
                
                //Go back to login in screen
                Stage stage = new Stage(); 
                LoginScreen loginscreen = new LoginScreen(); 
                loginscreen.start(stage);
               }});   //End of the action for but1
    
    VBox vbox = new VBox();
    vbox.getChildren().addAll(sub1, sub2, sub3, but1);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(20);
    
    Scene scene = new Scene(vbox, 300, 275);
    stage.setScene(scene);
    stage.show();

    }
}




