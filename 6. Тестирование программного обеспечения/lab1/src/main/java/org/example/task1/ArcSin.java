package org.example.task1;

public class ArcSin {
    public static double arcsin(double x){

        if (x < -1 || x > 1){
            return Double.NaN;
        }

        double result = x;
        double prev = x - 1;
        double coefficient = 1;

        int n = 3;
        while (result != prev){

            coefficient = coefficient * ((n-2) * (n-2)) / ((n-1) * n);

            double newValue = coefficient * Math.pow(x, n);

            prev = result;
            result += newValue;

            n += 2;
        }

        return result;

    }
}
