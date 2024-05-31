package org.example.log;

import org.example.utils.AbstractFunction;

public abstract class LogFunction extends AbstractFunction {
    @Override
    public Double checkX(double x) {
        x = super.checkX(x);
        if (x <= 0) {
            throw new ArithmeticException("value x should be > 0");
        }
        return x;
    }
}
