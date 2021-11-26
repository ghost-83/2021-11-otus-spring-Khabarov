package ru.ghost.dao;

import ru.ghost.dto.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDaoImpl implements QuestionDao {

    private final String questionFileName;

    public QuestionDaoImpl(String questionFileName) {
        this.questionFileName = questionFileName;
    }

    @Override
    public List<Question> getQuestions() {

        List<Question> questions = new ArrayList<>();

        try (InputStream file = getClass().getClassLoader().getResourceAsStream(this.questionFileName)) {

            if (file != null) { // Можно вместо if здись использовать assert file != null?
                try (InputStreamReader inputStreamReader = new InputStreamReader(file, StandardCharsets.UTF_8);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                    while (bufferedReader.ready()) {
                        String[] strings = bufferedReader.readLine().split(";");
                        List<String> answers = Arrays.stream(strings)
                                .filter(e -> !e.equals(strings[0]))
                                .collect(Collectors.toList());
                        questions.add(new Question(strings[0], answers));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
