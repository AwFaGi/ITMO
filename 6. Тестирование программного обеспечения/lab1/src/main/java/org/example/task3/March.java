package org.example.task3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class March extends Landscape {
    private double depth;
    private int countReeds;
    private boolean vegetation;

    public March(String name, double depth, int countReeds, boolean vegetation) {
        super(name);
        if (depth < 0) {
            throw new IllegalArgumentException("Depth cannot be negative");
        }
        this.depth = depth;
        if (countReeds < 0) {
            throw new IllegalArgumentException("Count reeds cannot be negative");
        }
        this.countReeds = countReeds;
        this.vegetation = vegetation;
    }

    public void increaseReeds() {
        countReeds++;
    }

    public void decreaseReeds() {
        countReeds--;
    }
}