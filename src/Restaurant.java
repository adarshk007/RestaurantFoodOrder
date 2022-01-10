package src;
import java.io.*;
import java.util.*;

class Restaurant{

    private Map<String,Meals> mealsMap = new HashMap<>();
    private Integer totalSlots;
    private Integer distanceMul;

    public Integer getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(Integer totalSlots) {
        this.totalSlots = totalSlots;
    }

     public Integer getDistanceMul() {
        return distanceMul;
    }

    public void setDistanceMul(Integer multiplier) {
        this.distanceMul = multiplier;
    }

    public void addMeals(String mealCode, Meals meals) {
        if(!mealsMap.containsKey(mealCode)) {
            mealsMap.put(mealCode, meals);
        } else {
            System.out.println("Meal Already Exists !!");
        }
    }

    public Map<String, Meals> getMeals(){
        return mealsMap;
    }


}