package com.example.lab4.service;

import com.example.lab4.dto.PointDTO;
import com.example.lab4.dto.RowDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PointCheckService {

    public RowDTO processPoint(PointDTO pointDTO){
        long startTime = System.nanoTime();
        RowDTO rowDTO = new RowDTO();
        rowDTO.setX(pointDTO.getX());
        rowDTO.setY(pointDTO.getY());
        rowDTO.setR(pointDTO.getR());
        rowDTO.setStatus(checkStatus(pointDTO.getX(), pointDTO.getY(), pointDTO.getR()));
        rowDTO.setTimeStamp(Instant.now());
        rowDTO.setWorkingTime(System.nanoTime() - startTime);
        return rowDTO;
    }

    private boolean checkStatus(double x, double y, double r){
        if (x > 0 && y > 0){ // 1
            return x*x + y*y <= (r/2)*(r/2);
        } else if (x <= 0 && y >= 0) { // 2
            return x + r > y;
        } else if (x <= 0 && y <= 0) { // 3
            return false;
        } else if (x >= 0 && y <= 0) { // 4
            return x <= r && y >= -r/2;
        }
        return false;
    }
}
