package org.example.task3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forest extends Landscape {
    private ForestType forestType;
    private boolean vegetation;
    private int countTree;
    private double heightTree;


    public Forest(String name, ForestType forestType, boolean vegetation, int countTree, double heightTree) {
        super(name);
        if (forestType == null) {
            throw new IllegalArgumentException("ForestType cannot be null");
        }
        this.forestType = forestType;
        this.vegetation = vegetation;
        if (countTree < 0) {
            throw new IllegalArgumentException("Count tree cannot be negative");
        }
        this.countTree = countTree;
        if (heightTree < 0) {
            throw new IllegalArgumentException("Height tree cannot be negative");
        }
        this.heightTree = heightTree;
    }

    public void increaseTree() {
        countTree++;
    }

    public void decreaseTree() {
        countTree--;
    }

}
