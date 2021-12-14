package ru.ghost.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ghost.dto.Answer;
import ru.ghost.dto.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = QuestionDaoImpl.class)
class QuestionDaoImplTest {

    @Autowired
    private QuestionDaoImpl questionDao;
    @Value("${questions.source}")
    String questionFileName;

    @Test
    void getQuestions() {

        List<Question> questions = questionDao.getQuestions(questionFileName);
        assertEquals(5, questions.size());
        Question question = questions.get(1);
        assertEquals("Short length in java (bytes)?", question.getQuestionText());
        Answer answer = question.getAnswers().get(1);
        assertEquals("2", answer.getAnswer());
        assertTrue(answer.isValue());
    }

    @Test
    void getQuestionsIOException() {

        List<Question> questions = questionDao.getQuestions("test");
        assertEquals(0, questions.size());
    }
}