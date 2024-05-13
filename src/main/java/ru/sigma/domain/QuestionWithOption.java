package ru.sigma.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionWithOption implements Question {
    
    private String question;
    private List<Option> options;
    private Answer rightAnswer;
    
}