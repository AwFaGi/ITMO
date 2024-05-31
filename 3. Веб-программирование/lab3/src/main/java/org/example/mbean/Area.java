package org.example.mbean;

public class Area implements AreaMBean {

    private double area;

    @Override
    public void registerShot(double r) {
        area = calculateArea(r);
    }

    @Override
    public double getArea() {
        return area;
    }

    private double calculateArea(double r){
        double result = 0;

        result += Math.PI * r * r / 4;
        result += r * r / 2;
        result += r * r / 2;

        return result;

    }
}
