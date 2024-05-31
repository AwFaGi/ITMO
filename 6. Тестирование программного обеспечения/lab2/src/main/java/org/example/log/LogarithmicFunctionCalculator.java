package org.example.log;

import org.example.utils.AbstractFunction;

public class LogarithmicFunctionCalculator extends AbstractFunction {

    private final Ln ln;
    private final Log2 log2;
    private final Log3 log3;
    private final Log5 log5;
    private final Log10 log10;

    public LogarithmicFunctionCalculator(Ln ln, Log2 log2, Log3 log3, Log5 log5, Log10 log10) {
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public LogarithmicFunctionCalculator() {
        this.ln = new Ln();
        this.log2 = new Log2(ln);
        this.log3 = new Log3(ln);
        this.log5 = new Log5(ln);
        this.log10 = new Log10(ln);
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        double lnResult = ln.checkAndCalculate(x, eps);
        double log2Result = log2.checkAndCalculate(x, eps);
        double log3Result = log3.checkAndCalculate(x, eps);
        double log5Result = log5.checkAndCalculate(x, eps);
        double log10Result = log10.checkAndCalculate(x, eps);
        double result = ((((log2Result - lnResult) / log3Result) + lnResult) * (((log2Result / log10Result) + log3Result + log5Result)) * Math.pow((log3Result - lnResult), 2));
        if (Double.isNaN(result)) throw new IllegalArgumentException("ODZ exception!");
        return result;
    }
}
