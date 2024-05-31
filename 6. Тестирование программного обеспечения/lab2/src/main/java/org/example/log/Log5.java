package org.example.log;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log5 extends LogFunction {
    private final Ln ln;
    private static final Double ln5 = 1.60943791243;

    @Override
    public Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x, eps) / ln5;
    }
}
