package com.example.lab4.service;

import com.example.lab4.entity.User;
import com.example.lab4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;
    public List<User> getAll(){
        return repository.findAllBy();
    }

    public User addUser(User user){
        return repository.save(user);
    }

    public boolean existsUser(String username){
        return repository.existsUserByUsername(username);
    }

    public User findUserByUsername(String username){
        return repository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException(String.format("%s not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new HashSet<>());
    }
}
