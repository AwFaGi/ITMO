package org.example.log;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Log10 extends LogFunction {
    private final Ln ln;
    private static final Double ln10 = 2.30258509299;

    @Override
    protected Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x, eps) / ln10;
    }
}
