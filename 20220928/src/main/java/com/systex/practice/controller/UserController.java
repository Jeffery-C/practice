package com.systex.practice.controller;

import com.systex.practice.controller.dto.request.CreateUserRequest;
import com.systex.practice.controller.dto.request.UpdateUserRequest;
import com.systex.practice.controller.dto.response.StatusResponse;
import com.systex.practice.model.entity.User;
import com.systex.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/condition")
    public List<User> getUserListByCondition(@PathParam("name") String name, @PathParam("age") Integer age) {
        return this.userService.getUserListByCondition(name, age);
    }

    @GetMapping("/page/{page}")
    public List<User> getUserListWithPageByCondition(
            @PathVariable Integer page,
            @PathParam("pageSize") Integer pageSize,
            @PathParam("name") String name,
            @PathParam("age") Integer age) {
        return this.userService.getUserListWithPageByCondition(
                PageRequest.of(page, pageSize), name, age);
    }

    @GetMapping("/all/page/{page}")
    public List<User> getUserListWithPage(
            @PathVariable Integer page,
            @PathParam("pageSize") Integer pageSize,
            @PathParam("sortBy") String sortBy,
            @PathParam("isSortByASC") Boolean isSortByASC
    ) {
        Optional<Boolean> optionalIsSortByASC = Optional.ofNullable(isSortByASC);
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (!optionalIsSortByASC.orElse(true)) sortDirection = Sort.Direction.DESC;

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        if (sortBy != null) pageRequest = PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy));

        return this.userService.getUserListWithPage(pageRequest);
    }

}
