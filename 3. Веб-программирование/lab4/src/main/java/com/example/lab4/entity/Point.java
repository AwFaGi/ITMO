package com.example.lab4.entity;

import com.example.lab4.dto.RowDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "s335183_points")
public class Point {
    @Id
    @SequenceGenerator(name = "point_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "point_id_generator")
    private Long id;
    private double x;
    private double y;
    private double r;
    private boolean status;
    private Instant timeStamp;
    private long workingTime;

    @ManyToOne()
    private User user;

    public Point(RowDTO rowDTO, User user){
        this.x = rowDTO.getX();
        this.y = rowDTO.getY();
        this.r = rowDTO.getR();
        this.status = rowDTO.isStatus();
        this.timeStamp = rowDTO.getTimeStamp();
        this.workingTime = rowDTO.getWorkingTime();
        this.user = user;
    }
}
