package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ForestType {
    TAIGA("тайга"),
    TUNDRA("тундра"),
    FOREST_STEPPE("лесостепь");

    private final String name;


}
