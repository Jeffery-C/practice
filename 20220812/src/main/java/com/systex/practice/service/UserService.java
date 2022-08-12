package com.systex.practice.service;

import com.systex.practice.controller.dto.request.CreateUserRequest;
import com.systex.practice.controller.dto.request.UpdateUserRequest;
import com.systex.practice.model.UserRepository;
import com.systex.practice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(int id) {
        User user = this.userRepository.findById(id);
//        if (user == null)
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        return user;
    }

    public String createUser(CreateUserRequest request) {
        User user = new User();
        user.setId(request.getId());
        user.setName(request.getName());
        user.setAge(request.getAge());

        this.userRepository.save(user);

        return "OK";
    }

    public String updateUserById(int id, UpdateUserRequest request) {

        User user = this.userRepository.findById(id);

        if (null == user) return "FAIL";

        user.setName(request.getName());
        user.setAge(request.getAge());

        this.userRepository.save(user);

        return "OK";
    }

    public String deleteUserById(int id) {

        User user = this.userRepository.findById(id);

        if (null == user) return "FAIL";

        this.userRepository.deleteById(id);
        return "OK";
    }
}
