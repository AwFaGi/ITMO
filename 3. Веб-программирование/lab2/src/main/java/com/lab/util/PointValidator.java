package com.lab.util;

import java.util.Arrays;
import java.util.List;

public class PointValidator {
    public static boolean validate(Point point){
        return validateX(point) && validateY(point) && validateR(point);
    }

    private static final double X_MIN = -3;
    private static final double X_MAX = 5;
    private static final double Y_MIN = -3;
    private static final double Y_MAX = 3;
    private static final double R_MIN = 1;
    private static final double R_MAX = 3;

    private static boolean validateX(Point point){
        double value = point.getX();
        return value >= X_MIN && value <= X_MAX;
    }

    private static boolean validateY(Point point){
        double value = point.getY();
        return value >= Y_MIN && value <= Y_MAX;
    }

    private static boolean validateR(Point point){
        double value = point.getR();
        return value >= R_MIN && value <= R_MAX;
    }
}
