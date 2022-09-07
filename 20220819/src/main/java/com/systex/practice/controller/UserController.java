package com.systex.practice.controller;

import com.systex.practice.controller.dto.request.CreateUserRequest;
import com.systex.practice.controller.dto.request.UpdateUserRequest;
import com.systex.practice.controller.dto.response.StatusResponse;
import com.systex.practice.model.entity.User;
import com.systex.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/orderBy/{field}")
    public List<User> getAllUsersOrderByField(@PathVariable String field, @RequestParam(required = false) Boolean isASC) {
        if (null == isASC || isASC) {
            return this.userService.getAllUsersOrderByField(field);
        }
        return this.userService.getAllUsersOrderByField(field, isASC);
    }

    @GetMapping("/ageGreaterThanEqual/{age}")
    public List<User> getAllUsersIfAgeGreaterThanEqual(@PathVariable int age) {
        return this.userService.getAllUsersIfAgeGreaterThanEqual(age);
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
