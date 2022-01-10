package src;
import java.io.*;
import java.util.*;
// import static Constants.*;

class RestaurantService{ 

    public Restaurant newRestaurant() { 
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setTotalSlots(Constants.restaurantSlot[0]);
        newRestaurant.setDistanceMul(Constants.multiplier[0]);
        this.createMeals(newRestaurant);
        return newRestaurant;
    }

    private void createMeals(Restaurant newRestaurant) {
        int size  = Constants.mealCode.length;
        for(int i=0;i<size; i++) {
            Meals newMeal= new Meals();
            newMeal.setSlots(Constants.mealSlot[i]);
            newMeal.setMealType(Constants.mealName[i]);
            newMeal.setTimeToPrepare(Constants.mealTime[i]);
            newRestaurant.addMeals(Constants.mealCode[i],newMeal);
        }
    }


}