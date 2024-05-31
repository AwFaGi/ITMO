package com.example.lab4.repository;

import com.example.lab4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    List<User> findAllBy();
    boolean existsUserByUsername(String username);

}
