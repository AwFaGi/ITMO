package org.example.task3;

import org.example.task3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TerritoryTest {
    private Forest forest;
    private March march;
    private Figure figure1;
    private Figure figure2;
    private Territory territory;

    @BeforeEach
    public void setUp() {
        forest = new Forest("лес", ForestType.TAIGA, true, 100, 500);
        march = new March("осушенное болото", 350, 70, true);
        figure1 = new Figure("человек", MovementType.SLOW, 20, forest);
        figure2 = new Figure("животное", MovementType.FAST, 5, forest);
        territory = new Territory(StateTerritory.COLD, Coverage.ICE, "ярко-голубое");
    }

    @Test
    public void addFigureTest() {
        territory.addFigure(figure1);
        territory.addFigure(figure2);
        assertEquals(2, territory.getFigures().size());
    }

    @Test
    public void changeStateTest() {
        territory.changeState(-1);
        assertEquals(territory.getState(), StateTerritory.COLD);
        territory.changeState(5);
        assertEquals(territory.getState(), StateTerritory.CHILLY);
        territory.changeState(14);
        assertEquals(territory.getState(), StateTerritory.WARM);
        territory.changeState(25);
        assertEquals(territory.getState(), StateTerritory.HOT);
    }

    @Test
    public void badForest(){
        assertThrows(IllegalArgumentException.class,
                () -> new Territory(null, Coverage.ICE, "ярко-голубое")
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Territory(StateTerritory.COLD, null, "ярко-голубое")
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Territory(StateTerritory.COLD, Coverage.ICE, null)
        );
    }

    @Test
    public void getParams(){
        assertEquals(StateTerritory.COLD, territory.getState());
        assertEquals("ярко-голубое", territory.getCoverageColor());
        assertEquals(Coverage.ICE, territory.getCoverage());
    }

}
