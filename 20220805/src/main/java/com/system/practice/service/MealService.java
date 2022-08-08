package com.system.practice.service;

import com.system.practice.model.Meal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MealService {

    // add meal data
    private final List<Meal> mealList = new ArrayList<>(Arrays.asList(
            new Meal("hamburger", 69, "This is a delicious burger."),
            new Meal("tea", 30, "Cool and quench thirst."),
            new Meal("sprite", 35, "Cool and quench thirst."),
            new Meal("fries", 49, "Fantastic fries.")
    ));

    public MealService() {
    }

    public Meal getMealByName(String name) {
        Meal returnMeal = null;

        for (Meal meal : this.mealList) {
            if (meal.getName().toLowerCase().equals(name.toLowerCase())) returnMeal = meal;
        }

        if (returnMeal == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found.");

        return returnMeal;
    }
}
