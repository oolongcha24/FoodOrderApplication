
package FoodApp;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Customer {
    public String name;
    public String password;
    public double points;
    
    
    
    public Customer(String name, String password, double points){
        this.name = name;
        this.password = password;
        this.points = points; //Each customer initially have 0.0 points.
       
    }

    public double checkPoint(){ //return points of current customer
        return this.points;
    }
    

//

    public String getStatus(){
        String s = "silver";
        String g = "gold";

        double pointCount = checkPoint();

        if (pointCount >= 0 && pointCount < 1000.0)
            return s;

        if(pointCount >= 0 && pointCount >= 1000.0);
            return g;
    }
    
    
    


//getMethods (Needed for OwnerCustomerScreen's ObservableList, for making the table)
    public String getName(){ 
        return this.name;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public double getPoints(){
        return this.points;
    }
    
    public void UpdatePoints(double p){//A setter method for updating the points of a customer

        this.points = p;
    }
    

    
}







