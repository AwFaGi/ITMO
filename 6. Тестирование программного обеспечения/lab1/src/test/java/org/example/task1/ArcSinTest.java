package org.example.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ArcSinTest {

    private final double defaultDelta = 1e-9;

    @Test
    void testArcsinWithZero() {
        double result = ArcSin.arcsin(0.0);
        assertEquals(Math.asin(0.0), result, defaultDelta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, -1.0})
    void testArcsinWithModuleOfOne(double value) {
        double result = ArcSin.arcsin(value);
        assertEquals(Math.asin(value), result, 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.3, 0.5, 0.707, 0.88})
    void testArcsinWithPositive(double value) {
        double result = ArcSin.arcsin(value);
        assertEquals(Math.asin(value), result, defaultDelta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.06, -0.25, -0.5, -0.707, -0.88})
    void testArcsinWithNegative(double value) {
        double result = ArcSin.arcsin(value);
        assertEquals(Math.asin(value), result, defaultDelta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.3, 0.5, 0.707, 0.88})
    void testArcsinWithSymmetry(double value) {
        double result1 = ArcSin.arcsin(value);
        double result2 = ArcSin.arcsin(-value);
        assertEquals(0, result1 + result2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.001, -2, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void testArcsinWithOutOfBounds(double value){
        double result = ArcSin.arcsin(value);
        assertEquals(Double.NaN, result);
    }
}

