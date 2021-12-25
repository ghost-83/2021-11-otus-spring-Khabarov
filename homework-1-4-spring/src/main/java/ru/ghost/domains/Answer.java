package ru.ghost.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Answer {

    private String answer;
    private boolean value;

    public Answer(String answer, boolean value) {
        this.answer = answer;
        this.value = value;
    }
}
