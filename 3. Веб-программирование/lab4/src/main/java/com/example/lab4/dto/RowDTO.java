package com.example.lab4.dto;

import com.example.lab4.entity.Point;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;

@Data
@NoArgsConstructor
public class RowDTO {

    @NonNull
    private double x;

    @NonNull
    private double y;

    @NonNull
    private double r;

    @NonNull
    private boolean status;

    @NonNull
    private Instant timeStamp;

    @NonNull
    private long workingTime;

    public RowDTO(Point point){
        x = point.getX();
        y = point.getY();
        r = point.getR();
        status = point.isStatus();
        timeStamp = point.getTimeStamp();
        workingTime = point.getWorkingTime();
    }

}
