package src;
import java.io.*;
import java.util.*;
// import static Constants.*;

class Orders {

    private Integer orderId;
    private List<String> mealsList;
    private Boolean isComplete;
    private String msg = "";
    private Double distance;
    private Double totalTime = 0d;
    private Integer slotOccupied = 0;

    // SETTERS //
    public void setOrderId(Integer id) {
        this.orderId = id;
    }

    public void setTotalTime(Double val) {
        this.totalTime = val;
    }

    public void setSlotOccupied(Integer slots) {
        this.slotOccupied = slots;
    }

    public void setMeals(List<String> mealsList) {
        this.mealsList = mealsList;
    }

    public void setStatus(Boolean status) {
        this.isComplete = status;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    } 

    // GETTERS //
    public String getMsg() {
        return this.msg;
    }

    public List<String> getMeals() {
        return this.mealsList;
    }
    public Boolean getStatus() {
        return this.isComplete;
    }
    public Double getDistance() {
        return  this.distance;
    }
    public Integer getOrderId() {
        return this.orderId;
    }

    public Double getTotalTime() {
        return this.totalTime;
    }

    public Integer getSlotOccupied() {
        return this.slotOccupied;
    }


}