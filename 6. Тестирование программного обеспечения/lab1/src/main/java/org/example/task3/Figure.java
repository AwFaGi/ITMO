package org.example.task3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Figure {
    private String name;
    private MovementType movement;
    private int step;
    private int coordinateX;
    private Landscape location;

    public Figure(String name, MovementType movement, int coordinateX, Landscape location) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        if (movement == null) {
            throw new IllegalArgumentException("Movement cannot be null");
        }
        this.movement = movement;
        if (coordinateX < 0) {
            throw new IllegalArgumentException("CoordinateX cannot be negative");
        }
        this.coordinateX = coordinateX;
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = location;
        this.step = 0;
    }

    public void go() {
        coordinateX += movement.getSpeed();
        System.out.println(movement.getName());
    }

    public void increaseStep() {
        step++;
    }

    public void move(Landscape newLocation) {
        if (newLocation != null) {
            go();
            this.location = newLocation;
            increaseStep();
        }
        else {
            throw new IllegalArgumentException("New location cannot be null");
        }
    }
}

