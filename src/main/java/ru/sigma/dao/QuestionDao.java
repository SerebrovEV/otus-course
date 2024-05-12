package ru.sigma.dao;

import ru.sigma.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions(String filePath);
}
