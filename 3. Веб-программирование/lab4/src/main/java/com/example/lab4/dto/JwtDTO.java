package com.example.lab4.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class JwtDTO {

    @NonNull
    private String username;
    @NonNull
    private String jwtToken;
}
