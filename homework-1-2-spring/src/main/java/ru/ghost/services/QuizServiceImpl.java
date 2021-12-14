package ru.ghost.services;

import org.springframework.stereotype.Service;
import ru.ghost.dtos.Person;
import ru.ghost.dtos.Question;

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
        startQuestion(person);

        for (Question question : questions) {
            ioService.printLine(question.getQuestionText());

            for (int i = 0; i < question.getAnswers().size(); i++) {
                String str = question.getAnswers().get(i).getAnswer();
                ioService.printLine((i + 1) + " " + str);
            }
            int numb;
            numb = Integer.parseInt(ioService.inputLine()) - 1;

            if (question.getAnswers().get(numb).isValue())
                person.setScore(person.getScore() + 1);
        }

        finishQuestion(person);
    }

    private void startQuestion(Person person) {
        ioService.printLine("Welcome to the test!");
        ioService.printLine("Please enter your name:");
        person.setFirstname(ioService.inputLine());
        ioService.printLine("Please enter your last name:");
        person.setLastname(ioService.inputLine());
        ioService.printLine("\nHappy passing the test!");
        ioService.printLine("Answer questions. Good luck!\n\n");
    }

    private void finishQuestion(Person person) {
        ioService.printLine(person.getFirstname() + " " + person.getLastname() + ", your result:");
        ioService.printLine(person.getScore() + " points.");
    }
}
