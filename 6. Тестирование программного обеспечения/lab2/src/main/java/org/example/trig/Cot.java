package org.example.trig;

import lombok.AllArgsConstructor;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.PI;

@AllArgsConstructor
public class Cot extends TrigFunction {

    private final Sin sin;
    private final Cos cos;

    public Cot() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * PI);
        double resultSin = sin.calculateValue(x, eps);
        double resultCos = cos.calculateValue(x, eps);

        if (Math.abs(resultSin) <= eps && resultCos > 0) {
            throw new IllegalArgumentException("probably zero division");
        } else if (Math.abs(resultSin) <= eps && resultCos < 0) {
            throw new IllegalArgumentException("probably zero division");
        }

        return resultCos / resultSin;
    }
}
