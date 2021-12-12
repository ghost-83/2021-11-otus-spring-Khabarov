package ru.ghost.services;

import ru.ghost.dtos.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions();
}
