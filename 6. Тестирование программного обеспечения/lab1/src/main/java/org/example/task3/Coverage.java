package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Coverage {
    LAND ("земля"),
    WATER ("вода"),
    ICE ("лед");

    private final String name;
}
