package ru.ghost;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ghost.dto.Question;
import ru.ghost.service.QuestionService;

import java.util.List;

public class Homework11SpringApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService service = context.getBean(QuestionService.class);
        List<Question> questions = service.getQuestions();

        System.out.println("Test Questions!");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + (i + 1) + ": " + questions.get(i).getQuestionText().trim());
            System.out.println("Answers:");

            for (String answer : questions.get(i).getAnswers()) {
                System.out.println(answer.trim());
            }
            System.out.println();
        }

        context.close();
    }
}
