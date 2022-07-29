package com.example.demo.service;

import com.example.demo.model.Meal;
import com.example.demo.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    List<Meal> meals;
    List<Order> orders;

    public RestaurantService() {
        // add new meal
        this.meals = new ArrayList<>();
        this.meals.add(new Meal("hamburger", 69,
                "This is a delicious burger."));
        this.meals.add(new Meal("tea", 30,
                "Cool and quench thirst."));
        this.meals.add(new Meal("sprite", 35,
                "Cool and quench thirst."));
        this.meals.add(new Meal("fries", 49,
                "Fantastic fries."));

        // add new order
        this.orders = new ArrayList<>();
        // create order 1
        Order tempOrder = new Order(1, "Jack");
        tempOrder.addMeal(this.getMealByName("hamburger"));
        tempOrder.addMeal(this.getMealByName("hamburger"));
        tempOrder.addMeal(this.getMealByName("sprite"));
        tempOrder.calcTotalPrice();
        this.orders.add(tempOrder);

        // create order 2
        tempOrder = new Order(2,"Mary");
        tempOrder.addMeal(this.getMealByName("fries"));
        tempOrder.addMeal(this.getMealByName("tea"));
        tempOrder.calcTotalPrice();
        this.orders.add(tempOrder);

        // create order 3
        tempOrder = new Order(3, "John");
        tempOrder.addMeal(this.getMealByName("hamburger"));
        tempOrder.addMeal(this.getMealByName("fries"));
        tempOrder.addMeal(this.getMealByName("sprite"));
        tempOrder.calcTotalPrice();
        this.orders.add(tempOrder);

    }

    public Meal getMealByName(String name) {
        for (Meal meal: this.meals) {
            if (meal.getName().toLowerCase().equals(name.toLowerCase())) return meal;
        }
        return null;
    }

    public Order getOrderBySeq(int seq) {
        for (Order order: this.orders) {
            if (order.getSeq() == seq) return order;
        }
        return null;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
