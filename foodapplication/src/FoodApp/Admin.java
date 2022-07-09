package FoodApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {
    public String owner_username = "admin";
    public String owner_password = "admin";
    ArrayList<Food> foodlist = new ArrayList<Food>(); //food list
    ArrayList<Customer> customerlist = new ArrayList<Customer>(); //Customer list
    String user_info;
    String pass_info;
    double point_info;
    String food_name;
    double food_price;


    
    public ArrayList<Customer> getCustomerList(){ //Return the customer arraylist
        File file = new File("customers.txt");
        try { //ignore this try catch, it is required for File class
            file.createNewFile(); //create the file if the file not exist yet. Else do nothing.
            Scanner scan = new Scanner(file);
        
            //A loop that scan over the saved customerlist textfile, and add into "customerlist" arraylist, for LoggIn info and OwnerCustomerScreen needs.
        while(true== scan.hasNext()){
            scan.next();
            user_info = scan.next();
            scan.next();
            pass_info = scan.next();
            scan.next();
            point_info = scan.nextDouble();
            
            customerlist.add(new Customer(user_info, pass_info, point_info));  //Store the customers info into a arraylist for further uses.
        }
                
        }catch (IOException ex) {}
        
        return customerlist;
    }
    
        public ArrayList<Food> getFoodList(){ //Return the foodlist arraylist
        File file = new File("foods.txt");
        try { //ignore this try catch, it is required for File class
            file.createNewFile(); //create the file if the file not exist yet. Else do nothing.
            Scanner scan = new Scanner(file);
        
            //A loop that scan over the saved foodlist textfile, and add into "foodlist" arraylist, for LoggIn info and OwnerCustomerScreen needs.
            while (true == scan.hasNext()){ //check if the next line is exist
                scan.next();  //Ignore the line from textfile
                food_name = scan.next();   //Read the username info from textfile
                scan.next();  //Ignore the line from textfile
                food_price = scan.nextDouble();   //Read the password info from textfile

                foodlist.add(new Food(food_name, food_price));  //Store the customers info into a arraylist for further uses.
            }
            
        } catch (IOException ex) {}
        
        
        return foodlist;
    }


    public void addCustomer(String name, String password){
        
        //customerlist.add(new Customer(name, password, 0));  //add the new customer's (login username and password) into customerlist's arraylist
        
        
        try {
        File customer_file = new File("customers.txt");
        customer_file.createNewFile();
        FileWriter file = new FileWriter("customers.txt", true);
        file.write("\nUsername: " + name + " Password: " + password + " Points: " + "0");
        //Format "username: (username) password: (password) points: (points)
        file.close(); 
        } catch (IOException ex) {}

    }
    
    public void deleteCustomer(String username) throws IOException{  //remove Customer, by searching it's username from the arraylist, and deletes it.
        ArrayList<String> copy = new ArrayList<String>(); //Create a copy Arraylist, which use to copy the customer info
        File file = new File("customers.txt");
        file.createNewFile();
        Scanner scan = new Scanner(file);
            
        while(true== scan.hasNextLine()){
            copy.add( scan.nextLine() ); //In "customer list.txt" , scan and save each line into the "copy" arraylist
        }
            
        for( int i=0; i<copy.size();i++){ //Go over the "copy" arraylist, if the string contains username that want to be deleted, it will remove it from "copy" arraylist
            if( copy.get(i).contains(username) == true ){
                System.out.println("found the customer username, deleting");
                copy.remove(i);
            }
        }
            
        PrintWriter w = new PrintWriter(file); //clean the textfile (blank)
        w.print(""); 
        w.close();

        FileWriter w2 = new FileWriter(file); //Rewrite the customer list by printing out the "copy" arraylist on the textfile
        for(String word : copy){
            w2.write("\n" + word);
        }
        w2.close();
            
    }
    
    /////////////////////////////////////
    
    public void updateCustomer(Customer current_customer, double point){ //update the point of user when purchase foods
        ArrayList<String> copy = new ArrayList<String>();
        File file = new File("customers.txt");
        try {
            file.createNewFile();
            
            Scanner scan = new Scanner(file);
            
            while(true== scan.hasNextLine()){
                copy.add( scan.nextLine() );
            }
            for( int i=0; i<copy.size();i++){
                if( copy.get(i).contains(current_customer.name) == true ){
                    System.out.println("found the customer username, updating points");
                    copy.remove(i);
                    copy.add("Username: " + current_customer.name + " Password: " + current_customer.password + " Points: " + point + "\n");
                }
            }
            PrintWriter w = new PrintWriter(file); //clean the textfile (blank)
        w.print(""); 
        w.close();

        FileWriter w2 = new FileWriter(file); //Rewrite the customer list by printing out the "copy" arraylist on the textfile
        for(String word : copy){
            w2.write("\n" + word);
        }
        w2.close();
            
        } catch (IOException ex) {}
        
        
        
    }
            
    ///////////////////////////////////////      

    
    public void addFood(String name, double price){
        
        try {
        File foods_file = new File("foods.txt");
        /////write and save the new food info into the "foods" textfile
        foods_file.createNewFile(); //create the file if the file not exist yet. Else do nothing.
        FileWriter file = new FileWriter("foods.txt", true);
        file.write("FoodName: " + name + " Price: " + price +"\n");
        //Format "Food name: (foodname) Price: (price) 
        file.close(); 
        } catch (IOException ex) {}
        
    }
  
    
    public void deleteFood(String foodname) throws IOException{ 
        ArrayList<String> copy = new ArrayList<String>(); //Create a copy Arraylist, which use to copy the Food list info
        File file = new File("foods.txt");
        file.createNewFile();
        Scanner scan = new Scanner(file);
            
        while(true== scan.hasNextLine()){
            copy.add( scan.nextLine() ); //In "customer list.txt" , scan and save each line into the "copy" arraylist
        }
            
        for( int i=0; i<copy.size();i++){ //Go over the "copy" arraylist, if the string contains foodname that want to be deleted, it will remove it from "copy" arraylist
            if( copy.get(i).contains(foodname) == true ){
                System.out.println("found the customer username, deleting");
                copy.remove(i);
            }
        }
            
        PrintWriter w = new PrintWriter(file); //clean the textfile (blank)
        w.print(""); 
        w.close();

        FileWriter w2 = new FileWriter(file); //Rewrite the customer list by printing out the "copy" arraylist on the textfile
        for(String word : copy){
            w2.write(word + "\n");
        }
        w2.close();
          
    }
    
}









