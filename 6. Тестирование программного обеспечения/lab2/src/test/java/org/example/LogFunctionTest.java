package org.example;

import org.example.log.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogFunctionTest {

    private final Ln ln = new Ln();
    private final CsvFilePrinter lnLogger = new CsvFilePrinter("src/test/resources/logs/log/ln.csv");
    private final Log2 log2 = new Log2(ln);
    private final CsvFilePrinter log2Logger = new CsvFilePrinter("src/test/resources/logs/log/log2.csv");
    private final Log3 log3 = new Log3(ln);
    private final CsvFilePrinter log3Logger = new CsvFilePrinter("src/test/resources/logs/log/log3.csv");
    private final Log5 log5 = new Log5(ln);
    private final CsvFilePrinter log5Logger = new CsvFilePrinter("src/test/resources/logs/log/log5.csv");
    private final Log10 log10 = new Log10(ln);
    private final CsvFilePrinter log10Logger = new CsvFilePrinter("src/test/resources/logs/log/log10.csv");
    private final LogarithmicFunctionCalculator lfc = new LogarithmicFunctionCalculator(ln, log2, log3, log5, log10);
    private final CsvFilePrinter lfcLogger = new CsvFilePrinter("src/test/resources/logs/log/logPart.csv");
    private final double accuracy = 1e-6;
    private final double eps = 1e-9;

    @BeforeAll
    public static void prepareEnv() {
        String directoryPath = "src/test/resources/logs/log/";
        File directory = new File(directoryPath);
        directory.mkdirs();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/Ln.csv")
    @DisplayName("ln(x) test")
    void lnTest(Double x, Double expected) {
        try {
            double result = ln.checkAndCalculate(x, eps);
            lnLogger.log(x, result);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/Log2.csv")
    @DisplayName("log2(x) test")
    void log2Test(Double x, Double expected) {
        try {
            double result = log2.checkAndCalculate(x, eps);
            log2Logger.log(x, result);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/Log3.csv")
    @DisplayName("log3(x) test")
    void log3Test(Double x, Double expected) {
        try {
            double result = log3.checkAndCalculate(x, eps);
            log3Logger.log(x, result);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/Log5.csv")
    @DisplayName("log5(x) test")
    void log5Test(Double x, Double expected) {
        try {
            double result = log5.checkAndCalculate(x, eps);
            log5Logger.log(x, result);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/Log10.csv")
    @DisplayName("log10(x) test")
    void log10Test(Double x, Double expected) {
        try {
            double result = log10.checkAndCalculate(x, eps);
            log10Logger.log(x, result);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logCsv/LogPart.csv")
    @DisplayName("log part test")
    void logPartTest(Double x, Double expected) {
        try {
            double result = lfc.checkAndCalculate(x, eps);
            lfcLogger.log(x, result);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("value x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }

    }

}