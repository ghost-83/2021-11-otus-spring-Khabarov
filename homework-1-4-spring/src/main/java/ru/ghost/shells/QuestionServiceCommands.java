package ru.ghost.shells;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ghost.services.LocalizationService;
import ru.ghost.services.QuizService;

@ShellComponent
@AllArgsConstructor
public class QuestionServiceCommands {

    private final QuizService quizService;
    private final LocalizationService localizationService;

    @ShellMethod(value = "Start quiz", key = {"s", "start"})
    public String startQuiz() {
        quizService.run();
        return localizationService.localize("tag.test.finish");
    }
}
