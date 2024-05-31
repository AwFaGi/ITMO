package com.lab.util;

import java.time.LocalDateTime;

public class Point {
    private final double x;
    private final double y;
    private final double r;
    private boolean status;
    private final LocalDateTime timeStamp = java.time.LocalDateTime.now();
    private long workingTime;

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public void setWorkingTime(long workingTime) {
        this.workingTime = workingTime;
    }

    public boolean getStatus(){
        return status;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public long getWorkingTime() {
        return workingTime;
    }
}
