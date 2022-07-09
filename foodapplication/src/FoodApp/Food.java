
package FoodApp;

import javafx.scene.control.CheckBox;


public class Food{
    String name;
    double price;
    CheckBox select;
    
    
    public Food(String name, double price){ //constructor
        this.name = name;
        this.price = price;
        this.select = new CheckBox();
    }
    
    //getMethods (Needed for OwnerBookScreen's ObservableList, for making the table)
    public String getName(){
        return this.name;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public CheckBox getSelect(){
        
        
        return this.select;
    }
    
    public void setSelect(CheckBox select){
        
        this.select = select;
        
    }
    
}







