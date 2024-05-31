package com.example.lab4.controller;

import com.example.lab4.dto.JwtDTO;
import com.example.lab4.dto.MessageDTO;
import com.example.lab4.dto.UserDTO;
import com.example.lab4.entity.User;
import com.example.lab4.security.JwtUtil;
import com.example.lab4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Validated @RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();

        if (!username.matches("^[a-zA-Z]{3,10}$") || !userDTO.getPassword().matches("^[a-zA-Z]{3,10}$")){
            return ResponseEntity.badRequest().body(new MessageDTO(true, "Таки взломать хотели!"));
        }

        if(username.isBlank() || userDTO.getPassword().isBlank()){
            return ResponseEntity.badRequest().body(new MessageDTO(true, "Неверный логин или пароль"));
        }

        if (!userService.existsUser(username)){
            return ResponseEntity.badRequest().body(new MessageDTO(true, "Такого пользователя нет"));
        }

        UserDetails userDetails = userService.loadUserByUsername(username);

        if(!encoder.matches(userDTO.getPassword(), userDetails.getPassword())){
            return ResponseEntity.badRequest().body(new MessageDTO(true, "Неверный логин или пароль"));
        }

        return ResponseEntity.ok(new JwtDTO(username, jwtUtil.generateToken(username)));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Validated @RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();

        if (!username.matches("^[a-zA-Z]{3,10}$") || !userDTO.getPassword().matches("^[a-zA-Z]{3,10}$")){
            return ResponseEntity.badRequest().body(new MessageDTO(true, "Таки взломать хотели!"));
        }

        if(username.isBlank() || userDTO.getPassword().isBlank()){
            return ResponseEntity.badRequest().body(new MessageDTO(true, "Неверный логин или пароль"));
        }

        if (userService.existsUser(username)){
            return ResponseEntity.badRequest().body(new MessageDTO(true, "Пользователь уже зарегистрирован"));
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(userDTO.getPassword()));

        userService.addUser(user);

        return ResponseEntity.ok(new JwtDTO(username, jwtUtil.generateToken(username)));
    }
}
