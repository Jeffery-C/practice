package com.system.practice.service;

import com.system.practice.BeanUtil;
import com.system.practice.model.Meal;
import com.system.practice.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orderList;

    // Use BeanUtil.class to put MealService.class into bean pool and use it.
    MealService mealService = BeanUtil.getBean(MealService.class);

    public OrderService() {
        // add order data
        this.orderList = new ArrayList<>();
        // create order 1
        Order tempOrder = new Order("Jack");
        List<Meal> tempMeal = new ArrayList<>();
        tempMeal.add(this.mealService.getMealByName("hamburger"));
        tempMeal.add(this.mealService.getMealByName("hamburger"));
        tempMeal.add(this.mealService.getMealByName("sprite"));
        tempOrder.setMealList(tempMeal);
        this.orderList.add(tempOrder);

        // create order 2
        tempOrder = new Order("Mary");
        tempMeal = new ArrayList<>();
        tempMeal.add(this.mealService.getMealByName("fries"));
        tempMeal.add(this.mealService.getMealByName("tea"));
        tempOrder.setMealList(tempMeal);
        this.orderList.add(tempOrder);

        // create order 3
        tempOrder = new Order("John");
        tempMeal = new ArrayList<>();
        tempMeal.add(this.mealService.getMealByName("hamburger"));
        tempMeal.add(this.mealService.getMealByName("fries"));
        tempMeal.add(this.mealService.getMealByName("sprite"));
        tempOrder.setMealList(tempMeal);
        this.orderList.add(tempOrder);
    }

    public List<Order> getAllOrders() {
        return this.orderList;
    }

    public Order getOrderBySeq(int seq) {
        Order returnOrder = null;

        for (Order order : this.orderList) {
            if (order.getSeq() == seq) returnOrder = order;
        }

        if (returnOrder == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.");

        return returnOrder;
    }

    public void createOrder(Order order) {

        // check waiter
        if (order.getWaiter() == null || "".equals(order.getWaiter()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request.");

        // create new order and put all meal from order.getMealList()
        Order newOrder = new Order(order.getWaiter());
        List<Meal> newMeal = new ArrayList<>();
        for (Meal meal : order.getMealList()) {
            newMeal.add(this.mealService.getMealByName(meal.getName()));
        }
        newOrder.setMealList(newMeal);
        this.orderList.add(newOrder);
        throw new ResponseStatusException(HttpStatus.CREATED, "New order is created.");
    }

    public void updateOrder(int seq, Order order) {
        // check seq == order.getSeq() ?
        if (seq != order.getSeq())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request.");

        // get update order
        Order updateOrder = this.getOrderBySeq(seq);
        if (order.getWaiter() != null && !order.getWaiter().equals(""))
            updateOrder.setWaiter(order.getWaiter());
        if (order.getMealList() == null)
            throw new ResponseStatusException(HttpStatus.OK, "Order successfully updated");

        // create updateMealList and put all meal from order.getMealList()
        List<Meal> updateMealList = new ArrayList<>();

        for (Meal meal : order.getMealList()) {
            if (meal.getName() == null) continue;
            updateMealList.add(this.mealService.getMealByName(meal.getName()));
        }
        if (updateMealList.size() < 1)
            throw new ResponseStatusException(HttpStatus.OK, "Order successfully updated");

        updateOrder.setMealList(updateMealList);

        throw new ResponseStatusException(HttpStatus.OK, "Order successfully updated");
    }

    public void deleteOrderBySeq(int seq) {
        Order deleteOrder = this.getOrderBySeq(seq);
        this.orderList.remove(deleteOrder);

        throw new ResponseStatusException(HttpStatus.OK, "Order successfully deleted");
    }
}
