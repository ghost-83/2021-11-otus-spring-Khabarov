package ru.ghost.dao;

import ru.ghost.dto.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions(String questionFileName);
}
