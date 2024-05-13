package ru.sigma.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.sigma.domain.Answer;
import ru.sigma.domain.Option;
import ru.sigma.domain.Question;
import ru.sigma.domain.QuestionWithOption;
import ru.sigma.domain.QuestionWithoutAnswer;
import ru.sigma.domain.QuestionWithoutOption;
import ru.sigma.exception.ReadEmptyStringLineException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionDaoCsv implements QuestionDao {

    private final ResourceDao resourceDaoFile;
    private final String delimiter;

    public QuestionDaoCsv(ResourceDao resourceDaoFile,
                          @Value("${info.delimiter}") String delimiter) {
        this.resourceDaoFile = resourceDaoFile;
        this.delimiter = delimiter;
    }

    @Override
    public List<Question> getQuestions(String filePath) {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceDaoFile.get(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    questions.add(parseQuestion(line));
                } else throw new ReadEmptyStringLineException("Passed an empty string!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    private Question parseQuestion(@NonNull String line) {
        List<String> stringList = Arrays.asList(line.split(delimiter));
        if (stringList.size() == 1) {
            return new QuestionWithoutAnswer(stringList.get(0));
        } else if (stringList.size() == 2) {
            return new QuestionWithoutOption(stringList.get(0), new Answer(stringList.get(1)));
        } else if (stringList.size() > 2) {
            return new QuestionWithOption(
                    stringList.get(0),
                    stringList.stream()
                            .skip(2)
                            .map(Option::new)
                            .collect(Collectors.toList()),
                    new Answer(stringList.get(1)));
        } else {
            throw new ReadEmptyStringLineException("Passed an empty string!");
        }
    }
}

