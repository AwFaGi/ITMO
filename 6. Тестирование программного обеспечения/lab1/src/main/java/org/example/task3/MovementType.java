package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovementType {
    SLOW ("медленно брели", 1),
    FAST ("быстро побежали", 5);

    private final String name;
    private final int speed;
}
