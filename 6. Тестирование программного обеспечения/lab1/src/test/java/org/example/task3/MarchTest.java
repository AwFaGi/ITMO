package org.example.task3;

import org.example.task3.March;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarchTest {
    private March march;

    @BeforeEach
    public void setUp() {
        march = new March("осушенное болото", 350, 70, true);
    }

    @Test
    public void decreaseTreeTest() {
        for (int i = 0; i < 10; i++) {
            march.decreaseReeds();
        }
        assertEquals(60, march.getCountReeds());
    }

    @Test
    public void increaseTreeTest() {
        for (int i = 0; i < 10; i++) {
            march.increaseReeds();
        }
        assertEquals(80, march.getCountReeds());
    }

    @Test
    public void badForest(){
        assertThrows(IllegalArgumentException.class,
                () -> new March("осушенное болото", -350, 70, true)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new March("осушенное болото", 350, -70, true)
        );
    }

    @Test
    public void getParams(){
        assertEquals(350, march.getDepth());
        assertEquals("осушенное болото", march.getName());
        assertTrue(march.isVegetation());
        assertEquals(70, march.getCountReeds());
    }

}
