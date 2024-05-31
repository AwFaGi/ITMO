package org.example.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FigureTest {
    private Forest forest;
    private March march;
    private Figure figure;

    @BeforeEach
    public void setUp() {
        forest = new Forest("лес", ForestType.TAIGA, true, 100, 500);
        march = new March("осушенное болото", 350, 70, true);
        figure = new Figure("человек", MovementType.SLOW, 5, forest);
    }

    @Test
    public void moveTest() {
        figure.move(march);
        figure.move(forest);
        figure.setMovement(MovementType.FAST);
        figure.move(march);
        assertEquals("осушенное болото", figure.getLocation().getName());
        assertEquals(12, figure.getCoordinateX());
        assertEquals(3, figure.getStep());
        assertThrows(IllegalArgumentException.class,
                () -> figure.move(null)
        );
    }

    @Test
    public void badFigures(){
        assertThrows(IllegalArgumentException.class,
                () -> new Figure(null, MovementType.SLOW, 5, forest)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Figure("человек", null, 5, forest)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Figure("человек", MovementType.SLOW, -5, forest)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Figure("человек", MovementType.SLOW, 5, null)
        );
    }

    @Test
    public void getParams(){
        assertEquals(MovementType.SLOW, figure.getMovement());
        assertEquals("человек", figure.getName());
    }
}
