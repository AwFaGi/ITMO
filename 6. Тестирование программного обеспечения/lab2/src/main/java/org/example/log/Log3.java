package org.example.log;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log3 extends LogFunction{
    private final Ln ln;
    private static final Double ln3 = 1.09861228867;

    @Override
    public Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x,eps)/ ln3;
    }
}
