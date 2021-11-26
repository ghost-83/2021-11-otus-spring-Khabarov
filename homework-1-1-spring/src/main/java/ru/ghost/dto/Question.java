package ru.ghost.dto;

import java.util.List;

public class Question {

    private final String questionText;
    private final List<String> answers;

    public Question(String questionText, List<String> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
