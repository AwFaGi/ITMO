package org.example.integrations;

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

public class TrigTest {

    public static Sin sin = mock(Sin.class);
    public static Cos cos = mock(Cos.class);
    public static Tan tan = mock(Tan.class);
    public static Cot cot = mock(Cot.class);
    public static Sec sec = mock(Sec.class);
    public static Csc csc = mock(Csc.class);

    public static Sin sinR = new Sin(new Cos());
    public static Cos cosR = new Cos();
    public static Tan tanR = new Tan(new Sin(new Cos()), new Cos());
    public static Cot cotR = new Cot(new Sin(new Cos()), new Cos());
    public static Sec secR = new Sec(new Cos());
    public static Csc cscR = new Csc(new Sin(new Cos()));

    private static final double eps = 1e-9;
    private final double percentage = 0.001;


    @BeforeAll
    public static void setupMocks() {
        setup(sin, "src/test/resources/trigCsv/Sin.csv");
        setup(cos, "src/test/resources/trigCsv/Cos.csv");
        setup(tan, "src/test/resources/trigCsv/Tan.csv");
        setup(cot, "src/test/resources/trigCsv/Cot.csv");
        setup(sec, "src/test/resources/trigCsv/Sec.csv");
        setup(csc, "src/test/resources/trigCsv/Csc.csv");
    }

    private static void setup(TrigFunction func, String fileName) {

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
    @CsvFileSource(resources = "/trigCsv/TrigPart.csv")
    @DisplayName("all mock test")
    void trigTestMock(Double x, Double expected) {
        try {
            TrigonometricFunctionCalculator tfc = new TrigonometricFunctionCalculator(sin, cos, tan, cot, sec, csc);
            double result = tfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/trigCsv/TrigPart.csv")
    @DisplayName("all mock, real cos test")
    void trigTestRealCos(Double x, Double expected) {
        try {
            TrigonometricFunctionCalculator tfc = new TrigonometricFunctionCalculator(sin, cosR, tan, cot, sec, csc);
            double result = tfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigCsv/TrigPart.csv")
    @DisplayName("all mock, real cos, sin test")
    void trigTestRealCosSin(Double x, Double expected) {
        try {
            TrigonometricFunctionCalculator tfc = new TrigonometricFunctionCalculator(sinR, cosR, tan, cot, sec, csc);
            double result = tfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigCsv/TrigPart.csv")
    @DisplayName("all mock, real cos, sin, tan test")
    void trigTestRealCosSinTan(Double x, Double expected) {
        try {
            TrigonometricFunctionCalculator tfc = new TrigonometricFunctionCalculator(sinR, cosR, tanR, cot, sec, csc);
            double result = tfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigCsv/TrigPart.csv")
    @DisplayName("all mock, real cos, sin, tan, cot test")
    void trigTestRealCosSinTanCot(Double x, Double expected) {
        try {
            TrigonometricFunctionCalculator tfc = new TrigonometricFunctionCalculator(sinR, cosR, tanR, cotR, sec, csc);
            double result = tfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigCsv/TrigPart.csv")
    @DisplayName("all mock, real cos, sin, tan, cot, sec test")
    void trigTestRealCosSinTanCotSec(Double x, Double expected) {
        try {
            TrigonometricFunctionCalculator tfc = new TrigonometricFunctionCalculator(sinR, cosR, tanR, cotR, secR, csc);
            double result = tfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigCsv/TrigPart.csv")
    @DisplayName("all real test")
    void trigTestAllReal(Double x, Double expected) {
        try {
            TrigonometricFunctionCalculator tfc = new TrigonometricFunctionCalculator(sinR, cosR, tanR, cotR, secR, cscR);
            double result = tfc.checkAndCalculate(x, eps);
            assertEquals(expected, result, Math.abs(result*percentage));
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}