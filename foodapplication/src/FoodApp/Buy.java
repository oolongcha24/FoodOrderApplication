package FoodApp;

import java.util.ArrayList;



//
public class Buy {
    

    private double totalCost;
    private ArrayList<Food> foodList;	
    Admin admin = new Admin();

//
    public Buy(double totalCost){ 

        this.totalCost = totalCost;
        totalCost = 0.0;
        foodList = new ArrayList<Food>();


    }

//
    public void costCalculate(ArrayList<Food> list){
        double priceSum = 0.0;

        for(Food element : list){
            priceSum += element.price;
        }
		
	setTotalCost(priceSum);
    }
//








    public void setTotalCost(double cost){
        if (cost >= 0.0){
            this.totalCost = cost;
        }
    }
//


    public double getTotalCost(){
        return this.totalCost;
    }   
//

    
        public Customer buy(Customer current_customer, ArrayList<Food> list  ){	

	double purchasePrice = 0.0;
	double pointsOfTotalCost = 0.0;
	double totalPoints = 0.0;

	
	
        costCalculate(list);
        purchasePrice = getTotalCost();
	
	pointsOfTotalCost = purchasePrice*10.0;

	totalPoints = current_customer.checkPoint() + pointsOfTotalCost;
	current_customer.UpdatePoints(totalPoints);
        admin.updateCustomer(current_customer, totalPoints); //Update the current totalpoints that the customer have in the "Customer list" textfile
        return current_customer;
    }

//
    public Customer buyAndRedeem(Customer current_customer, ArrayList<Food> list){

        double purchasePrice = 0.0;
        double redeemableAmount = 0.0;
        double finalPrice = 0.0;

        double pointsOfTotalCost = 0.0;
        double totalPoints = 0.0;

        if (current_customer.checkPoint()>= 0.0){

            costCalculate(list); //caculate the "totalCost"
            purchasePrice = getTotalCost(); //purchasePrice = totalCost
            totalPoints = current_customer.checkPoint(); //curent customer's point = totalPoints
            redeemableAmount = totalPoints/100.0; //totalPoints covert to money = redeemableAmount

            if (redeemableAmount >= purchasePrice){ //When there is enough redeem points

                setTotalCost(finalPrice);
	
                current_customer.UpdatePoints(((redeemableAmount - purchasePrice))*100);	
                admin.updateCustomer(current_customer, current_customer.checkPoint()); //Update the current totalpoints that the customer have in the "Customer list" textfile
            }	
	
            if(redeemableAmount < purchasePrice){ //When there is not enough redeem points
	
                finalPrice = purchasePrice - redeemableAmount;
                setTotalCost(finalPrice);
                pointsOfTotalCost = (finalPrice *10.0);
                current_customer.UpdatePoints(pointsOfTotalCost);
                admin.updateCustomer(current_customer, current_customer.checkPoint()); //Update the current totalpoints that the customer have in the "Customer list" textfile
	

            }
            
        }
        
        return current_customer;
    }
    
    public ArrayList<Food> getSelectedFoodList(ArrayList<Food> foodlist){
        ArrayList<Food> SelectedFoodList = new ArrayList<Food>(); //A new arraylist represent the checkbox selected food list
        for(Food b : foodlist){ //an enhance for loop that go over the foodlist, and see which food is checked
            if(b.select.isSelected() == true){ //If the checkbox is selected, add it ot the "SelectedFoodList"
                SelectedFoodList.add(b);
            }
        }
        return SelectedFoodList;
    }
    
    
}





