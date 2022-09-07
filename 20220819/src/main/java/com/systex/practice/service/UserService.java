package com.systex.practice.service;

import com.systex.practice.controller.dto.request.CreateUserRequest;
import com.systex.practice.controller.dto.request.UpdateUserRequest;
import com.systex.practice.model.UserRepository;
import com.systex.practice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<User> getAllUsersOrderByField(String field, boolean isASC) {
        List<User> userList = new ArrayList<>();
        if (isASC) {
            userList = this.userRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        }
        else {
            userList = this.userRepository.findAll(Sort.by(Sort.Direction.DESC, field));
        }
        return userList;
    }

    public List<User> getAllUsersOrderByField(String field) {
        return this.getAllUsersOrderByField(field, true);
    }

    public List<User> getAllUsersIfAgeGreaterThanEqual(int age) {
        return this.userRepository.findByAgeGreaterThanEqual(age);
    }

    public List<User> getUsersByNameAndAge(String name, int age) {
        return this.userRepository.getUsersByNameAndAgeUsingAtQuery(name, age);
    }

    public String createUserUsingAtQuery(CreateUserRequest createUserRequest) {
        this.userRepository.createUserAtQuery(createUserRequest.getName(), createUserRequest.getAge());
        return "OK";
    }

    public String updateUserByIdAtQuery(int id, UpdateUserRequest updateUserRequest) {
        int changeNumber = this.userRepository.updateUserAtQuery(id, updateUserRequest.getName(), updateUserRequest.getAge());
        String status = "FAIL";
        if (changeNumber > 0) status = "OK";
        return status;
    }


    public String deleteUserByIdUsingAtQuery(String name, int age) {
        int changeNumber = this.userRepository.deleteByNameAndAgeUsingAtQuery(name, age);
        String status = "FAIL";
        if (changeNumber > 0) status = "OK";
        return status;
    }
}
