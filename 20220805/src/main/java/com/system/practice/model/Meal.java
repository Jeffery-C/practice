package com.system.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Meal {
    private String name;
    private int price;
    private String description;
}
