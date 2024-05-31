package org.example.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForestTest {
    private Forest forest;

    @BeforeEach
    public void setUp() {
        forest = new Forest("лес", ForestType.TAIGA, true, 100, 500);
    }

    @Test
    public void decreaseTreeTest() {
        for (int i = 0; i < 10; i++) {
            forest.decreaseTree();
        }
        assertEquals(90, forest.getCountTree());
    }

    @Test
    public void increaseTreeTest() {
        for (int i = 0; i < 10; i++) {
            forest.increaseTree();
        }
        assertEquals(110, forest.getCountTree());
    }

    @Test
    public void badForest(){
        assertThrows(IllegalArgumentException.class,
                () -> new Forest("лес", null, true, 100, 500)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Forest("лес", ForestType.TAIGA, true, -10, 500)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Forest("лес", ForestType.TAIGA, true, 100, -50)
        );
    }

    @Test
    public void getParams(){
        assertEquals(ForestType.TAIGA, forest.getForestType());
        assertEquals("лес", forest.getName());
        assertTrue(forest.isVegetation());
        assertEquals(500, forest.getHeightTree());
    }

}
