package org.example.trig;

import lombok.AllArgsConstructor;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.PI;

@AllArgsConstructor
public class Csc extends TrigFunction {

    private final Sin sin;

    public Csc() {
        this.sin = new Sin();
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * PI);

        double resultSin = sin.calculateValue(x, eps);

        if (Math.abs(resultSin) <= eps) {
            throw new IllegalArgumentException("probably zero division");
        }

        return 1.0 / resultSin;
    }
}
