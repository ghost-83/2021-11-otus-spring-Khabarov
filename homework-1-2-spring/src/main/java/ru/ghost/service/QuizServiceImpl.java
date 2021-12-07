package ru.ghost.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.ghost.dto.Person;
import ru.ghost.dto.Question;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;
    private final QuestionService questionService;

    public QuizServiceImpl(IOService ioService, QuestionService questionService) {
        this.ioService = ioService;
        this.questionService = questionService;
    }

    @SneakyThrows
    @Override
    public void run() {

        Person person = new Person();
        List<Question> questions = questionService.getQuestions();

        ioService.printLine("Welcome to the test!");
        ioService.printLine("Please enter your name:");
        person.setFirstname(ioService.inputLine());
        ioService.printLine("Please enter your last name:");
        person.setLastname(ioService.inputLine());

        ioService.printLine("\nHappy passing the test!");
        ioService.printLine("Answer questions. Good luck!\n\n");

        for (Question question : questions) {
            ioService.printLine(question.getQuestionText());
            for (int i = 0; i < question.getAnswers().size(); i++) {
                ioService.printLine((i + 1) + " " + question.getAnswers().get(i).getAnswer());
            }
            if (question.getAnswers().get(Integer.parseInt(ioService.inputLine()) - 1).getValue())
                person.setScore(person.getScore() + 1);
        }

        ioService.printLine(person.getFirstname() + " " + person.getLastname() + ", your result:");
        ioService.printLine(person.getScore() + " points.");
    }
}
