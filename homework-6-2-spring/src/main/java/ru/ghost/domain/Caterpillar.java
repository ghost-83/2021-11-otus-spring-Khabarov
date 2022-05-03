package ru.ghost.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Caterpillar {
    private int number;
    private int size;

    public Caterpillar(int number) {
        this.number = number;
        this.size = 1;
    }
}
