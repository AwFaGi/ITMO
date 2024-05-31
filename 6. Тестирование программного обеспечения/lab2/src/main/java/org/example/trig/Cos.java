package org.example.trig;

import static java.lang.Math.PI;

public class Cos extends TrigFunction {

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * PI);
        if ((Double.compare(x, PI) == 0) || Double.compare(x, -PI) == 0) {
            return -1.0;
        }
        if ((Double.compare(x, 0D) == 0)) {
            return 1.0;
        }
        int n = 0;
        double resultCos = 0;
        double xSquare = x * x;
        double pow = 1;
        double fact = 1;
        int sign = 1;
        double term = 1;

        while (Math.abs(term) > eps) {
            resultCos += term;

            sign = -sign;
            fact *= (2 * n + 1) * (2 * n + 2);
            pow *= xSquare;
            n += 1;
            term = sign * pow / fact;
        }
        return resultCos;
    }
}
