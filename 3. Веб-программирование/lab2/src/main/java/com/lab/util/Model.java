package com.lab.util;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private ArrayList<Point> data = new ArrayList<>();

    public void add(Point point){
        data.add(point);
    }

    public ArrayList<Point> get(){
        return new ArrayList<>(data);
    }

    @Override
    public String toString() {
        return "Model{" +
                "data=" + data +
                '}';
    }
}
