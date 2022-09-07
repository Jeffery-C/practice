package com.systex.practice.model;

import com.systex.practice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByOrderByIdAsc();

    List<User> findByOrderByNameAsc();

    List<User> findByOrderByAgeAsc();

    User findById(int id);

    List<User> findByAgeGreaterThanEqual(int age);

    Long deleteById(int id);

    @Query(value = "SELECT * FROM user WHERE name = :name AND age = :age", nativeQuery = true)
    List<User> getUsersByNameAndAgeUsingAtQuery(@Param("name") String name, @Param("age") int age);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user (name, age) VALUES (:name, :age)", nativeQuery = true)
    void createUserAtQuery(String name, int age);


    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET name = :name, age = :age WHERE id = :id", nativeQuery = true)
    int updateUserAtQuery(int id, String name, int age);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user WHERE name = :name AND age = :age", nativeQuery = true)
    int deleteByNameAndAgeUsingAtQuery(String name, int age);
}
