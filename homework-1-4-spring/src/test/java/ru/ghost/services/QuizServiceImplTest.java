package ru.ghost.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.domains.Answer;
import ru.ghost.dtos.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = QuizServiceImpl.class)
class QuizServiceImplTest {

    @Autowired
    private QuizService quizService;

    @MockBean
    private LocalizationService localizationService;
    @MockBean
    private IOService ioService;
    @MockBean
    private QuestionService questionService;

    private final List<Question> questions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Answer answer = new Answer("Test", true);

        List<Answer> answers = Collections.singletonList(answer);
        Question question = new Question("Test question", answers);
        questions.add(question);
    }

    @Test
    void run() {

        doReturn(questions).when(questionService).getQuestions();
        doReturn("Test").when(ioService).inputLine();
        doReturn("Test").when(ioService).inputLine();
        doReturn("1").when(ioService).inputLine();
        doReturn("___").when(localizationService).localize(anyString());
        doReturn("___").when(localizationService).localize(anyString(), anyString(), anyString(), anyInt());

        quizService.run();

        verify(questionService, times(1)).getQuestions();
        verify(ioService, times(7)).printLine(anyString());
        verify(ioService, times(3)).inputLine();
        verify(localizationService, times(4)).localize(anyString());
        verify(localizationService, times(1)).localize(anyString(), anyString(), anyString(), anyInt());
    }
}