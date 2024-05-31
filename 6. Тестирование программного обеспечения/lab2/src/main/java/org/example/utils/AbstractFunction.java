package org.example.utils;

import lombok.Data;

import static java.lang.Double.NaN;
import static java.lang.Double.isInfinite;
import static java.lang.Double.isNaN;

@Data
public abstract class AbstractFunction {

    protected abstract Double calculateValue(Double x, Double eps);

    public Double checkX(double x) {
        if (isNaN(x) || isInfinite(x)) {
            return NaN;
        }
        return x;
    }

    public final Double checkAndCalculate(Double x, Double eps) {
        checkX(x);
        if(isNaN(x)) {
            return x;
        }
        return calculateValue(x, eps);
    }
}
