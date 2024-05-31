package org.example.beans;

import org.example.util.AreaInValidator;
import org.example.util.CustomFormatter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class ExtendedPoint {
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    private double x;
    private double y;
    private double r;
    private boolean status;
    private Instant timeStamp;
    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setR(double r) {
        this.r = r;
    }

    private long workingTime;

    public ExtendedPoint(){

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

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    private ZoneId zoneId;

    public String getTimeStampFormatted(){
        return timeStamp.atZone(zoneId).format(CustomFormatter.getFormatterAtOffset(zoneId));
    }

    public long getWorkingTime() {
        return workingTime;
    }

    public void selfEval() {
        long startTime = System.nanoTime();
        setTimeStamp(Instant.now());
        setX(this.x);
        setY(this.y);
        setR(this.r);
        setStatus(AreaInValidator.validate(this));
        setWorkingTime(System.nanoTime() - startTime);
    }
}
