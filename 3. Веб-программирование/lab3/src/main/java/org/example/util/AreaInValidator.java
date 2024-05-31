package org.example.util;

import org.example.beans.ExtendedPoint;

public class AreaInValidator {
    public static boolean validate(ExtendedPoint extendedPoint){
        double x = extendedPoint.getX();
        double y = extendedPoint.getY();

        if (x > 0 && y > 0){
            return validate1(extendedPoint);
        } else if (x <= 0 && y >= 0) {
            return validate2(extendedPoint);
        } else if (x <= 0 && y <= 0) {
            return validate3(extendedPoint);
        } else if (x >= 0 && y <= 0) {
            return validate4(extendedPoint);
        }

        return false;

    }

    private static boolean validate1(ExtendedPoint extendedPoint){
        double x = extendedPoint.getX();
        double y = extendedPoint.getY();
        double r = extendedPoint.getR();

        return x <= r && y <= r/2;
    }

    private static boolean validate2(ExtendedPoint extendedPoint){
        double x = extendedPoint.getX();
        double y = extendedPoint.getY();
        double r = extendedPoint.getR();

        return x*x + y*y <= r*r;
    }

    private static boolean validate3(ExtendedPoint extendedPoint){
        return false;
    }

    private static boolean validate4(ExtendedPoint extendedPoint){
        double x = extendedPoint.getX();
        double y = extendedPoint.getY();
        double r = extendedPoint.getR();

        return y >= x - r;
    }

}