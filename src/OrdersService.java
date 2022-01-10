package src;

import java.util.PriorityQueue;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

class OrdersService {
    
    // Class variables to store data 
    private final RestaurantService newService = new RestaurantService();
    private Restaurant newRestaurant1 = newService.newRestaurant();
    private Map<String,Meals> mealsMap = newRestaurant1.getMeals();
    private Integer restaurantSlots = newRestaurant1.getTotalSlots();
    private List<Orders> allOrders = new ArrayList<>();

    // My queue
    private PriorityQueue<Orders> currentOrders = new PriorityQueue<>((o1, o2) -> {
            if (o1.getTotalTime() < o2.getTotalTime()) {
                return -1;
            }
            if (o1.getTotalTime() > o2.getTotalTime()) {
                return 1;
            }
            return 0;
    });

    public void takeOrders(List<HashMap<String, List<String>>> data) {
        for(HashMap<String, List<String>> j: data) {     
                Orders order = new Orders();
                order.setDistance(Double.parseDouble(j.get("distance").get(0)));
                order.setMeals(j.get("meals"));
                order.setOrderId(Integer.parseInt(j.get("orderId").get(0)));
                order.setTotalTime(this.calculateTotalTime(order));
                order.setSlotOccupied(this.calculateTotalSlots(j.get("meals")));
                allOrders.add(order);
                this.processOrders(order);
        }
        this.display();
    }

    public void display() {
        for(Orders i: allOrders) {
            System.out.println(i.getMsg());
        }
    }

    public Double calculateTotalTime(Orders order) {
        List<String> meals= order.getMeals();
        Double time = order.getDistance()*newRestaurant1.getDistanceMul();
        Double maxTime = 0d;
        for(String i: meals) {
            Meals mealsInfo = newRestaurant1.getMeals().get(i);
            maxTime = Math.max(maxTime,mealsInfo.getTimeToPrepare());
        }
        time += maxTime;
        return time;
    }

    public Boolean isValidOrder(Orders order) {
        if(order.getSlotOccupied() > newRestaurant1.getTotalSlots() ||   order.getTotalTime() > 150d) {
            return false;
        }
        return true;
    }

    public void simulate(Orders order) {
        if(isValid(order.getSlotOccupied())) {
            this.restaurantSlots -= order.getSlotOccupied();
            this.currentOrders.add(order);
            this.updateStatus(order);
        } else {
            Double waitingTime = 0d;  
            while(!isValid(order.getSlotOccupied()) && this.currentOrders.size() > 0) {
                Orders updatedOrder = this.currentOrders.poll();
                this.restaurantSlots += updatedOrder.getSlotOccupied();
                // this.updateStatus(updatedOrder);
                waitingTime = Math.max(waitingTime,updatedOrder.getTotalTime());
            }

            order.setTotalTime(waitingTime + order.getTotalTime());
            this.restaurantSlots -= order.getSlotOccupied();
            this.currentOrders.add(order);
            this.updateStatus(order);
        }
    }

    public void updateStatus(Orders order) {
        DecimalFormat decimalFormat=new DecimalFormat("#.#");
        order.setMsg(
               String.format("Order %s will get delivered in %s minutes", 
               order.getOrderId(), decimalFormat.format(order.getTotalTime()))
            );
        order.setStatus(true);
    }

    public Integer calculateTotalSlots(List<String> meals) {
        Integer slots = 0;
        for (String i: meals) {
            slots += mealsMap.get(i).getSlots();
        }
        return slots;
    }

    public Boolean isValid(Integer slots) {
        if(slots > this.restaurantSlots) {
            return false;
        }
        return true;
    }

    public void processOrders(Orders order) {
       if(isValidOrder(order)) {
           this.simulate(order);
       } else {       
           order.setMsg(
               String.format("Order %s is denied because the restaurant cannot accommodate it.", 
               order.getOrderId())
            );
       }
    }
}