package ru.ghost.services;

import org.springframework.stereotype.Service;
import ru.ghost.dtos.Person;
import ru.ghost.dtos.Question;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;
    private final QuestionService questionService;
    private final LocalizationService localizationService;


    public QuizServiceImpl(IOService ioService,
                           QuestionService questionService,
                           LocalizationService localizationService) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.localizationService = localizationService;
    }

    @Override
    public void run() {

        Person person = getPersonInfo();
        List<Question> questions = questionService.getQuestions();
        person.setScore(startTest(questions));
        finishTest(person);
    }

    private Person getPersonInfo() {
        Person person = new Person();
        ioService.printLine(localizationService.localize("tag.test.greetings"));
        ioService.printLine(localizationService.localize("tag.your.firstname"));
        person.setFirstname(ioService.inputLine());
        ioService.printLine(localizationService.localize("tag.your.lastname"));
        person.setLastname(ioService.inputLine());
        ioService.printLine(localizationService.localize("tag.test.good_luck"));
        return person;
    }

    private int startTest(List<Question> questions) {

        int result = 0;

        for (Question question : questions) {
            ioService.printLine(question.getQuestionText());

            for (int i = 0; i < question.getAnswers().size(); i++) {
                String str = question.getAnswers().get(i).getAnswer();
                ioService.printLine((i + 1) + " " + str);
            }
            int numb = Integer.parseInt(ioService.inputLine()) - 1;

            if (question.getAnswers().get(numb).isValue())
                result++;
        }
        return result;
    }

    private void finishTest(Person person) {
        ioService.printLine(
                localizationService.localize("tag.test.result",
                        person.getFirstname(),
                        person.getLastname(),
                        person.getScore()));
    }
}
