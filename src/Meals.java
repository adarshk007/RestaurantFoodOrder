package src;
import java.io.*;
import java.util.*;

class Meals{

    private Integer preparationTimeInMin;
    private Integer slotsRequired;
    private Boolean available = true;
    private String mealType;

    public Integer getSlots() {
        return slotsRequired;
    }

    public void setSlots(Integer slots) {
        this.slotsRequired = slots;
    }

    public void toggleAvailability() {
        this.available = !this.available;
    }

    public Integer getTimeToPrepare() {
        return preparationTimeInMin;
    }

    public void setTimeToPrepare(Integer getTimeToPrepare) {
        this.preparationTimeInMin = getTimeToPrepare;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}