package org.example.beans;

import org.example.util.Connector;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimeZone;


public class Model implements Serializable {
    private ArrayList<ExtendedPoint> data = new ArrayList<>();

    public String getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(String timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    String timezoneOffset;

    public void add(ExtendedPoint extendedPoint){
        data.add(extendedPoint);
        Connector.getInstance().makeBigAdd(extendedPoint);
    }

    private ZoneId zoneId = ZonedDateTime.now().getZone();

    public ArrayList<ExtendedPoint> get(){
        return new ArrayList<>(data);
    }

    public void timezoneChangedListener(){
        String strFromJavaScript = getTimezoneOffset();
        TimeZone tz = TimeZone.getTimeZone("GMT+" + strFromJavaScript);
        zoneId = tz.toZoneId();
    }

    public ArrayList<ExtendedPoint> getReversed() {
        ArrayList<ExtendedPoint> toReturn= new ArrayList<>(data);
        Collections.reverse(toReturn);
        toReturn.forEach(extendedPoint -> extendedPoint.setZoneId(zoneId));
        return toReturn;
    }

    @Override
    public String toString() {
        return "Model{" +
                "data=" + data +
                '}';
    }
}