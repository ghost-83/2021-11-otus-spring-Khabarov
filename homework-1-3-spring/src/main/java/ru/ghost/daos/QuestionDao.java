package ru.ghost.daos;

import ru.ghost.dtos.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions();
}
