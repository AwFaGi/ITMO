package org.example.integrations;

import org.example.log.*;
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


public class LogTest {

    public static Ln ln = mock(Ln.class);
    public static Log2 log2 = mock(Log2.class);
    public static Log3 log3 = mock(Log3.class);
    public static Log5 log5 = mock(Log5.class);
    public static Log10 log10 = mock(Log10.class);


    public static Ln lnR = new Ln();
    public static Log2 log2R = new Log2(new Ln());
    public static Log3 log3R = new Log3(new Ln());
    public static Log5 log5R = new Log5(new Ln());
    public static Log10 log10R = new Log10(new Ln());

    private static final double eps = 1e-9;
    private final double percentage = 0.01;


    @BeforeAll
    public static void setupMocks() {
        setup(ln, "src/test/resources/logCsv/Sin.csv");
        setup(log2, "src/test/resources/logCsv/Cos.csv");
        setup(log3, "src/test/resources/logCsv/Tan.csv");
        setup(log5, "src/test/resources/logCsv/Cot.csv");
        setup(log10, "src/test/resources/logCsv/Sec.csv");
    }

    private static void setup(LogFunction func, String fileName) {

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
    @CsvFileSource(resources = "/logCsv/LogPart.csv")
    @DisplayName("all mocks test")
    void LogMocks(Double x, Double expected) {
        try {
            LogarithmicFunctionCalculator lfc = new LogarithmicFunctionCalculator(ln, log2, log3, log5, log10);
            double result = lfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/LogPart.csv")
    @DisplayName("all mocks, real ln test")
    void LogRealLn(Double x, Double expected) {
        try {
            LogarithmicFunctionCalculator lfc = new LogarithmicFunctionCalculator(lnR, log2, log3, log5, log10);
            double result = lfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/LogPart.csv")
    @DisplayName("all mocks, real ln, log2 test")
    void LogRealLnLog2(Double x, Double expected) {
        try {
            LogarithmicFunctionCalculator lfc = new LogarithmicFunctionCalculator(lnR, log2R, log3, log5, log10);
            double result = lfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/LogPart.csv")
    @DisplayName("all mocks, real ln, log2, log3 test")
    void LogRealLnLog2Log3(Double x, Double expected) {
        try {
            LogarithmicFunctionCalculator lfc = new LogarithmicFunctionCalculator(lnR, log2R, log3R, log5, log10);
            double result = lfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/LogPart.csv")
    @DisplayName("all mocks, real ln, log2, log3, log5 test")
    void LogRealLnLog2Log3Log5(Double x, Double expected) {
        try {
            LogarithmicFunctionCalculator lfc = new LogarithmicFunctionCalculator(lnR, log2R, log3R, log5R, log10);
            double result = lfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/LogPart.csv")
    @DisplayName("all real test")
    void LogAllReal(Double x, Double expected) {
        try {
            LogarithmicFunctionCalculator lfc = new LogarithmicFunctionCalculator(lnR, log2R, log3R, log5R, log10R);
            double result = lfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}