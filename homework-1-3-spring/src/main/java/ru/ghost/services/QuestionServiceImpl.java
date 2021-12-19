package ru.ghost.services;

import org.springframework.stereotype.Service;
import ru.ghost.daos.QuestionDao;
import ru.ghost.dtos.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getQuestions() {
        return dao.getQuestions();
    }
}
