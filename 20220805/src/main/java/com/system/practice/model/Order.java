package com.system.practice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private int seq;
    private static int nextSeq = 1;
    private int totalPrice;
    private String waiter;
    private List<Meal> mealList;

    public Order() {
    }

    public Order(String waiter) {
        setSeq();
        this.waiter = waiter;
    }

    // Automatic order number
    public void setSeq() {
        this.seq = Order.nextSeq++;
    }


    public void setMealList(List<Meal> mealList) {
        this.totalPrice = 0;
        for (Meal meal : mealList) {
            this.totalPrice += meal.getPrice();
        }
        this.mealList = mealList;
    }
}
