package org.example.trig;

import lombok.AllArgsConstructor;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.PI;

@AllArgsConstructor
public class Sec extends TrigFunction {

    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * PI);

        double resultCos = cos.calculateValue(x, eps);

        if (Math.abs(resultCos) <= eps) {
            throw new IllegalArgumentException("probably zero division");
        }

        return 1.0 / resultCos;
    }
}
