package ru.ghost.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.dto.Answer;
import ru.ghost.dto.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = QuizServiceImpl.class)
class QuizServiceImplTest {

    @Autowired
    private QuizService quizService;

    @MockBean
    private IOService ioService;
    @MockBean
    private QuestionService questionService;

    private final List<Question> questions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Answer answer = Answer.builder()
                .answer("Test")
                .value(true)
                .build();
        List<Answer> answers = Collections.singletonList(answer);
        Question question = new Question("Test question", answers);
        questions.add(question);
    }

    @Test
    void run() throws IOException {

        doReturn(questions).when(questionService).getQuestions();
        doReturn("Test").when(ioService).inputLine();
        doReturn("Test").when(ioService).inputLine();
        doReturn("1").when(ioService).inputLine();

        quizService.run();

        verify(questionService, times(1)).getQuestions();
        verify(ioService, times(9)).printLine(anyString());
        verify(ioService, times(3)).inputLine();
    }
}