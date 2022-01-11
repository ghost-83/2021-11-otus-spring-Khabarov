package ru.ghost.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ghost.domains.Answer;
import ru.ghost.dtos.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = QuestionDaoImpl.class)
class QuestionDaoImplTest {

    @Autowired
    private QuestionDaoImpl questionDao;

    @Test
    void getQuestions() {

        List<Question> questions = questionDao.getQuestions();
        assertEquals(5, questions.size());
        Question question = questions.get(1);
        assertEquals("Short length in java (bytes)?", question.getQuestionText());
        Answer answer = question.getAnswers().get(1);
        assertEquals("2", answer.getAnswer());
        assertTrue(answer.isValue());
    }
}