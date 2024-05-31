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

public class SinTest {
    public static Cos cos = mock(Cos.class);
    public static Cos cosR = new Cos();

    private static final double eps = 1e-9;
    private final double accuracy = 0.001;


    @BeforeAll
    public static void setupMocks() {
        setup(cos, "src/test/resources/trigCsv/Cos.csv");
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
    @CsvFileSource(resources = "/trigCsv/mock/Sin.csv")
    @DisplayName("mock test")
    void testWithMock(Double x, Double expected) {
        try {
            Sin sin = new Sin(cos);
            double result = sin.checkAndCalculate(x, eps);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigCsv/mock/Sin.csv")
    @DisplayName("real test")
    void testWithReal(Double x, Double expected) {
        try {
            Sin sin = new Sin(cosR);
            double result = sin.checkAndCalculate(x, eps);
            assertEquals(expected, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}