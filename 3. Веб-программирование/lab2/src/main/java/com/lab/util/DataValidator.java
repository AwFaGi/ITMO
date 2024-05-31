package com.lab.util;

public class DataValidator {
    public static boolean validateDouble(String number){
        try{
            double value = Double.parseDouble(number);
            return true;
        }catch (NumberFormatException exception){
            return false;
        }
    }
}
