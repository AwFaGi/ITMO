package org.example;

import lombok.AllArgsConstructor;
import org.example.log.LogarithmicFunctionCalculator;
import org.example.trig.TrigonometricFunctionCalculator;
import org.example.utils.AbstractFunction;

@AllArgsConstructor
public class MainFunction extends AbstractFunction {

    private final LogarithmicFunctionCalculator logCalculator;
    private final TrigonometricFunctionCalculator trigCalculator;


    @Override
    public Double calculateValue(Double x, Double eps) {
        if (x > 0) {
            return logCalculator.checkAndCalculate(x, eps);
        } else {
            return trigCalculator.checkAndCalculate(x, eps);
        }
    }

}