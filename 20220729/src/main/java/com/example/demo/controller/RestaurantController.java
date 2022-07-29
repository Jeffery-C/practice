package com.example.demo.controller;

import com.example.demo.model.Meal;
import com.example.demo.model.Order;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/")
    public String home(Model model) {
        List<Meal> meals = this.restaurantService.getMeals();
        List<Order> orders = this.restaurantService.getOrders();
        int income = 0;
        for (Order order: orders) {
            income += order.getTotalPrice();
        }
        model.addAttribute("income", income);
        model.addAttribute("meals", meals);
        model.addAttribute("orders", orders);

        return "home";
    }

    @GetMapping("/meal")
    public String getMealByName(@RequestParam String name, Model model) {
        Meal meal = this.restaurantService.getMealByName(name);
        if (meal == null) return "404";
        model.addAttribute("meal", meal);
        return "meal";
    }

    @GetMapping("/order/{seq}")
    public String getOrderBySeq(@PathVariable int seq, Model model) {
        Order order = this.restaurantService.getOrderBySeq(seq);
        if (order == null) return "404";
        model.addAttribute("order", order);
        return "order";
    }
}
