package com.systex.practice.service;

import com.systex.practice.controller.dto.request.CreateUserRequest;
import com.systex.practice.controller.dto.request.UpdateUserRequest;
import com.systex.practice.model.UserRepository;
import com.systex.practice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public List<User> getUserListByCondition(String name, Integer age) {
        return this.userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + name));
                predicateList.add(criteriaBuilder.greaterThan(root.get("age"), age));

                Predicate[] predicates = new Predicate[predicateList.size()];

                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        });
    }


    public List<User> getUserListWithPageByCondition(Pageable page, String name, Integer age) {
        Specification<User> userSpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + name));
                predicateList.add(criteriaBuilder.greaterThan(root.get("age"), age));

                Predicate[] predicates = new Predicate[predicateList.size()];

                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        };

        Page<User> userPage = this.userRepository.findAll(userSpecification, page);
        return userPage.getContent();

    }

    public List<User> getUserListWithPage(Pageable page) {
        return this.userRepository.findAll(page).getContent();
    }
}
