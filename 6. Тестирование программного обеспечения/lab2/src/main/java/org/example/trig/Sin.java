package org.example.trig;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Sin extends TrigFunction {
    private final Cos cos;

    public Sin() {
        this.cos = new Cos();
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * Math.PI);
        if (x == 0 || x == Math.PI) {
            return 0.0;
        } else if (x == Math.PI / 2) {
            return 1.0;
        } else if (x == -Math.PI / 2) {
            return -1.0;
        }

        return cos.calculateValue(x - Math.PI / 2, eps);
    }
}
