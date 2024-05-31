package org.example.task3;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Territory {
    private StateTerritory state;
    private Coverage coverage;
    private String coverageColor;
    private List<Figure> figures = new ArrayList<>();
    private List<Landscape> landscapes = new ArrayList<>();

    public Territory(StateTerritory state, Coverage coverage, String coverageColor) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        this.state = state;
        if (coverage == null) {
            throw new IllegalArgumentException("Coverage cannot be null");
        }
        this.coverage = coverage;
        if (coverageColor == null) {
            throw new IllegalArgumentException("Coverage color cannot be null");
        }
        this.coverageColor = coverageColor;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public void changeState(int temperature) {
        if (temperature < 0) {
            state = StateTerritory.COLD;
            return;
        }
        if (temperature <= 5) {
            state = StateTerritory.CHILLY;
            return;
        }
        if (temperature <= 15) {
            state = StateTerritory.WARM;
            return;
        }
        if (temperature >= 25) {
            state = StateTerritory.HOT;
        }
    }

}
