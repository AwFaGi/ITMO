package com.example.lab4.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MessageDTO {

    @NonNull
    private boolean isError;
    @NonNull
    private String message;
}
