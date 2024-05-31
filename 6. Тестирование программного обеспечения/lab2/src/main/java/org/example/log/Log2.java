package org.example.log;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log2 extends LogFunction {
    private final Ln ln;
    private static final Double ln2 = 0.69314718055;

    @Override
    public Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x, eps) / ln2;
    }
}
