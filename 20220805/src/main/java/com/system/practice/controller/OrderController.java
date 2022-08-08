package com.system.practice.controller;

import com.system.practice.model.Order;
import com.system.practice.service.MealService;
import com.system.practice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/{seq}")
    public Order getOrderBySeq(@PathVariable int seq) {
        return this.orderService.getOrderBySeq(seq);
    }

    @PostMapping()
    public void createOrder(@RequestBody Order order) {
        this.orderService.createOrder(order);
    }

    @PutMapping("/{seq}")
    public void updateOrder(@PathVariable int seq, @RequestBody Order order) {
        this.orderService.updateOrder(seq, order);
    }

    @DeleteMapping("/{seq}")
    public void deleteOrderBySeq(@PathVariable int seq) {
        this.orderService.deleteOrderBySeq(seq);
    }
}
