package org.example.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StateTerritoryTest {
    @Test
    public void getName() {
        assertEquals("холод", StateTerritory.COLD.getName());
        assertEquals("прохладно", StateTerritory.CHILLY.getName());
        assertEquals("тепло", StateTerritory.WARM.getName());
        assertEquals("жар", StateTerritory.HOT.getName());
    }

    @Test
    public void getTemperature() {
        assertEquals(-10, StateTerritory.COLD.getTemperature());
        assertEquals(5, StateTerritory.CHILLY.getTemperature());
        assertEquals(15, StateTerritory.WARM.getTemperature());
        assertEquals(25, StateTerritory.HOT.getTemperature());
    }

}
