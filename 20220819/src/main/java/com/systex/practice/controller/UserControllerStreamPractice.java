package com.systex.practice.controller;

import com.systex.practice.controller.dto.response.StatusResponse;
import com.systex.practice.model.entity.User;
import com.systex.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1/streamPractice/user")
public class UserControllerStreamPractice {

    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public List<String> getAllUsersName() {
        List<User> userList = this.userService.getAllUsers();
        List<String> userNameList = new ArrayList<>();
        userList.stream().map(user -> user.getName()).distinct().sorted().forEach(userName -> userNameList.add(userName));
        return userNameList;
    }

    @GetMapping("/idAndName")
    public Map<Integer, String> getAllUsersIdAndName() {
        List<User> userList = this.userService.getAllUsers();
        Map<Integer, String> idAndNameMap = new HashMap<>();
        userList.stream().forEach(user -> idAndNameMap.put(user.getId(), user.getName()));
        return idAndNameMap;
    }

    @GetMapping("/findFirst")
    public Object getFirstUserByName(@RequestParam String name) {
        List<User> userList = this.userService.getAllUsers();
        Optional<User> userOptional = userList.stream().filter(user -> user.getName().equals(name)).findFirst();
        if (null == userOptional) return new StatusResponse("FAIL");
        return userOptional;
    }

    @GetMapping
    public Object getAllUsersOrderByAgeThenId(@RequestParam(required = false) Boolean isOrderByAgeThenId) {
        List<User> userList = this.userService.getAllUsers();
        if (null == isOrderByAgeThenId || !isOrderByAgeThenId) {
            return userList;
        }

        return userList.stream().sorted(Comparator.comparing(User::getAge).thenComparing(User::getId));
    }

    @GetMapping("/stringData")
    public String getAllUserToString() {
        List<User> userList = this.userService.getAllUsers();
        User firstUser = userList.remove(0);
        StringBuilder stringBuilder = new StringBuilder(firstUser.getName()+", "+firstUser.getAge());
        userList.stream().forEach(user -> stringBuilder.append("|"+user.getName()+", "+user.getAge()));

        return stringBuilder.toString();
    }
}
