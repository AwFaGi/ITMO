package org.example.task3;

import org.junit.jupiter.api.Test;

import org.example.task3.ForestType;
import static org.junit.jupiter.api.Assertions.*;

public class ForestTypeTest {
    @Test
    public void testGet(){

        assertEquals("тайга", ForestType.TAIGA.getName());
        assertEquals("тундра", ForestType.TUNDRA.getName());
        assertEquals("лесостепь", ForestType.FOREST_STEPPE.getName());

    }
}
