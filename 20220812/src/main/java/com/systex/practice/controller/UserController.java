package com.systex.practice.controller;

import com.systex.practice.controller.dto.request.CreateUserRequest;
import com.systex.practice.controller.dto.request.UpdateUserRequest;
import com.systex.practice.controller.dto.response.StatusResponse;
import com.systex.practice.model.entity.User;
import com.systex.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return this.userService.getUserById(id);
    }

    @PostMapping()
    public StatusResponse createUser(@RequestBody CreateUserRequest request) {
        String response = this.userService.createUser(request);
        return new StatusResponse(response);
    }

    @PutMapping("/{id}")
    public StatusResponse updateUserById(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        String response = this.userService.updateUserById(id, request);
        return new StatusResponse(response);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteUserById(@PathVariable int id) {
        String response = this.userService.deleteUserById(id);
        return new StatusResponse(response);
    }
}
