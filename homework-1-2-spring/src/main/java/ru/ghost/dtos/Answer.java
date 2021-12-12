package ru.ghost.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {

    private String answer;
    private boolean value;
}
