package ru.ghost.daos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.ghost.dtos.Answer;
import ru.ghost.dtos.Question;
import ru.ghost.exceptions.QuestionDaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final String questionFileName;

    public QuestionDaoImpl(@Value("${app.csv-file-path.question}") String questionFileName) {
        this.questionFileName = questionFileName;
    }

    @Override
    public List<Question> getQuestions() {

        List<Question> questions = new ArrayList<>();

        try (InputStream file = getClass().getClassLoader().getResourceAsStream(questionFileName)) {

            if (file != null) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(file, StandardCharsets.UTF_8);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                    while (bufferedReader.ready()) {
                        String[] strings = bufferedReader.readLine().split(";");
                        List<Answer> answers = Arrays.stream(strings)
                                .filter(e -> !e.equals(strings[0]))
                                .map(e -> {
                                    String[] tmp = e.split("===");
                                    return Answer.builder()
                                            .answer(tmp[0])
                                            .value(Boolean.parseBoolean(tmp[1]))
                                            .build();
                                })
                                .collect(Collectors.toList());

                        questions.add(new Question(strings[0], answers));
                    }
                }
            }
        } catch (IOException e) {
            throw new QuestionDaoException("Failed to fetch questions!", e);
        }

        return questions;
    }
}
