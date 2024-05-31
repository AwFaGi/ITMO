package com.example.lab4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s335183_users")
public class User {
    @Id
    @SequenceGenerator(name = "user_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "user_id_generator")
    private Long id;
    private String username;
    private String password;
}
