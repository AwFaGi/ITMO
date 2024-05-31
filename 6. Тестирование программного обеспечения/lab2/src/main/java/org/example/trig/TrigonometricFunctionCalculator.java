package org.example.trig;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrigonometricFunctionCalculator extends TrigFunction {
    private final Sin sin;
    private final Cos cos;
    private final Tan tan;
    private final Cot cot;
    private final Sec sec;
    private final Csc csc;

    public TrigonometricFunctionCalculator() {
        this.sin = new Sin();
        this.cos = new Cos();
        this.tan = new Tan();
        this.cot = new Cot();
        this.sec = new Sec();
        this.csc = new Csc();
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        double sinResult = sin.checkAndCalculate(x, eps);
        double cosResult = cos.checkAndCalculate(x, eps);
        double tanResult = tan.checkAndCalculate(x, eps);
        double cotResult = cot.checkAndCalculate(x, eps);
        double secResult = sec.checkAndCalculate(x, eps);
        double cscResult = csc.checkAndCalculate(x, eps);

        double result = (((((((((((((cscResult / cotResult) * Math.pow(cscResult, 2)) + sinResult) * sinResult) * sinResult) * (cosResult + cscResult)) - (cscResult - sinResult)) - Math.pow((cotResult + cotResult), 3)) + (cscResult / sinResult)) * (cscResult / sinResult)) * (((sinResult * (cotResult - (cosResult + cosResult)))) / cosResult)) * (((((cotResult / (((Math.pow(cosResult, 2) + cscResult) - cscResult))) - secResult) / secResult) / (Math.pow((tanResult / ((cotResult * cotResult) - cotResult)), 2))) / (((((sinResult * (cotResult + cosResult))) + (cscResult + (secResult / cotResult))) / secResult) * (cscResult / (((Math.pow(tanResult, 2) + (cotResult + cosResult))) + sinResult)))) - (((((secResult * cscResult) / cotResult) + (cscResult + ((tanResult - tanResult) * (tanResult - tanResult) * (tanResult - tanResult))))) - secResult)));
        if (Double.isNaN(result)) throw new IllegalArgumentException("ODZ exception!");

        return result;
    }
}
