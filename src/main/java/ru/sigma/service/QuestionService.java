package ru.sigma.service;


import ru.sigma.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
    
    boolean checkAnswer(String userAnswer, String rightAnswer);
    
    void printQuestion(Question question);
}