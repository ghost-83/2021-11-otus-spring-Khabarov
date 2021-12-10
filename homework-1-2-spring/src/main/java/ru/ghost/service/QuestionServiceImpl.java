package ru.ghost.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ghost.dao.QuestionDao;
import ru.ghost.dto.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private final String questionFileName;
    private final QuestionDao dao;

    public QuestionServiceImpl(@Value("${questions.source}") String questionFileName, QuestionDao dao) {
        this.questionFileName = questionFileName;
        this.dao = dao;
    }

    @Override
    public List<Question> getQuestions() {
        return dao.getQuestions(questionFileName);
    }
}
