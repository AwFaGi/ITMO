package org.example.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoverageTest {
    @Test
    public void getTest() {
        assertEquals("земля", Coverage.LAND.getName());
        assertEquals("вода", Coverage.WATER.getName());
        assertEquals("лед", Coverage.ICE.getName());
    }
}
