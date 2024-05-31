package org.example.integrations;

import org.example.MainFunction;
import org.example.log.*;
import org.example.trig.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {
    private final double percentage = 0.001;
    private static final double eps = 1e-9;
    public static TrigonometricFunctionCalculator trigCalculator = mock(TrigonometricFunctionCalculator.class);
    public static LogarithmicFunctionCalculator logCalculator = mock(LogarithmicFunctionCalculator.class);

    public TrigonometricFunctionCalculator trigCalculatorR = new TrigonometricFunctionCalculator(
            new Sin(new Cos()), new Cos(), new Tan(new Sin(new Cos()), new Cos()),
            new Cot(new Sin(new Cos()), new Cos()), new Sec(new Cos()),
            new Csc(new Sin(new Cos()))
    );
    public LogarithmicFunctionCalculator logCalculatorR = new LogarithmicFunctionCalculator(
            new Ln(), new Log2(new Ln()), new Log3(new Ln()), new Log5(new Ln()), new Log10(new Ln())
    );

    @BeforeAll
    public static void setupMocks() {
        setupLog(logCalculator);
        setupTrig(trigCalculator);
    }

    private static void setupTrig(TrigonometricFunctionCalculator func) {
        String fileName = "src/test/resources/trigCsv/TrigPart.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] a = line.split(", ");

                double x = Double.parseDouble(a[0]);
                double y = Double.parseDouble(a[1]);

                when(func.checkAndCalculate(x, eps)).thenReturn(y);
            }
        } catch (IOException ignored) {

        }
    }

    private static void setupLog(LogarithmicFunctionCalculator func) {
        String fileName = "src/test/resources/logCsv/LogPart.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] a = line.split(", ");

                double x = Double.parseDouble(a[0]);
                double y = Double.parseDouble(a[1]);

                when(func.checkAndCalculate(x, eps)).thenReturn(y);
            }
        } catch (IOException ignored) {

        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/main.csv")
    @DisplayName("all mocks test")
    void allMock(Double x, Double trueResult) {
        try {
            MainFunction calculator = new MainFunction(logCalculator, trigCalculator);
            double result = calculator.checkAndCalculate(x, eps);
            assertEquals(trueResult, result, Math.abs(result*percentage));
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/main.csv")
    @DisplayName("log real test")
    void trigMock(Double x, Double trueResult) {
        try {
            MainFunction calculator = new MainFunction(logCalculatorR, trigCalculator);
            double result = calculator.checkAndCalculate(x, eps);
            assertEquals(trueResult, result, Math.abs(result*percentage));
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/main.csv")
    @DisplayName("trig real test")
    void logMock(Double x, Double trueResult) {
        try {
            MainFunction calculator = new MainFunction(logCalculator, trigCalculatorR);
            double result = calculator.checkAndCalculate(x, eps);
            assertEquals(trueResult, result, Math.abs(result*percentage));
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/main.csv")
    @DisplayName("all real test")
    void noMock(Double x, Double trueResult) {
        try {
            MainFunction calculator = new MainFunction(logCalculator, trigCalculator);
            double result = calculator.checkAndCalculate(x, eps);
            assertEquals(trueResult, result, Math.abs(result*percentage));
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}