package ru.ghost.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {

    private String answer;
    private boolean value;
}
