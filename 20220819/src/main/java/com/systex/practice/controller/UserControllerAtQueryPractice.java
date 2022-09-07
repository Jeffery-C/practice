package com.systex.practice.controller;

import com.systex.practice.controller.dto.request.CreateUserRequest;
import com.systex.practice.controller.dto.request.UpdateUserRequest;
import com.systex.practice.controller.dto.response.StatusResponse;
import com.systex.practice.model.entity.User;
import com.systex.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/practice/user")
public class UserControllerAtQueryPractice {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsersByNameAndAge(@RequestParam String name,@RequestParam int age) {
        return this.userService.getUsersByNameAndAge(name, age);
    }

    @PostMapping
    public StatusResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        String status = this.userService.createUserUsingAtQuery(createUserRequest);
        return new StatusResponse(status);
    }

    @PutMapping("/{id}")
    public StatusResponse UpdateUserById(@PathVariable int id, @RequestBody UpdateUserRequest updateUserRequest) {
        String status = this.userService.updateUserByIdAtQuery(id, updateUserRequest);
        return new StatusResponse(status);
    }

    @DeleteMapping
    public StatusResponse DeleteUserById(@RequestParam String name, @RequestParam int age) {
        String status = this.userService.deleteUserByIdUsingAtQuery(name, age);
        return new StatusResponse(status);
    }

}
