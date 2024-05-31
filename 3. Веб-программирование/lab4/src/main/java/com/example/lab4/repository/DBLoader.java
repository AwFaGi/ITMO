package com.example.lab4.repository;

import com.example.lab4.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBLoader implements CommandLineRunner {

    private final UserRepository repository;

    @Autowired
    public DBLoader(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
//        repository.save(new User(3450L, "admin", "admin"));
    }
}
