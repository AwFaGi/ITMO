package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateTerritory {
    COLD("холод", -10),
    CHILLY("прохладно", 5),
    WARM("тепло", 15),
    HOT("жар", 25);

    private final String name;
    private final int temperature;
}
