package ru.ghost.dtos;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private final String questionText;
    private final List<Answer> answers;

    public Question(String questionText, List<Answer> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }
}
