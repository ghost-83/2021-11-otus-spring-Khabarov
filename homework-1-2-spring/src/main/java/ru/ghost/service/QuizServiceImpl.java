package ru.ghost.service;

import org.springframework.stereotype.Service;
import ru.ghost.dto.Person;
import ru.ghost.dto.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;
    private final QuestionService questionService;

    public QuizServiceImpl(IOService ioService, QuestionService questionService) {
        this.ioService = ioService;
        this.questionService = questionService;
    }

    @Override
    public void run() {

        Person person = new Person();
        List<Question> questions = questionService.getQuestions();
        ioService.setPrintStream(new PrintStream(System.out));
        ioService.setBufferedReader(new BufferedReader(new InputStreamReader(System.in)));

        ioService.printLine("Welcome to the test!");
        ioService.printLine("Please enter your name:");
        try {
            person.setFirstname(ioService.inputLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ioService.printLine("Please enter your last name:");
        try {
            person.setLastname(ioService.inputLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ioService.printLine("\nHappy passing the test!");
        ioService.printLine("Answer questions. Good luck!\n\n");

        for (Question question : questions) {
            ioService.printLine(question.getQuestionText());

            for (int i = 0; i < question.getAnswers().size(); i++) {
                String str = question.getAnswers().get(i).getAnswer();
                ioService.printLine((i + 1) + " " + str);
            }
            int numb = 0;
            try {
                numb = Integer.parseInt(ioService.inputLine()) - 1;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (question.getAnswers().get(numb).isValue())
                person.setScore(person.getScore() + 1);
        }

        ioService.printLine(person.getFirstname() + " " + person.getLastname() + ", your result:");
        ioService.printLine(person.getScore() + " points.");
    }
}
