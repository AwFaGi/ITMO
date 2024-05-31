package org.example.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomFormatter {

    static private final String FORMAT_STRING =  "dd.MM.yyyy HH:mm:ss";

    static public DateTimeFormatter formatter= DateTimeFormatter.ofPattern(FORMAT_STRING);

    static public DateTimeFormatter getFormatterAtOffset(ZoneId zoneId){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_STRING);
        dateTimeFormatter.withZone(zoneId);
        return dateTimeFormatter;
    }
}
