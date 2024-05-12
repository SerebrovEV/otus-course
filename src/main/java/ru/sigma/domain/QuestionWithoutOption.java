package ru.sigma.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWithoutOption implements Question {
    private String question;
    private Answer rightAnswer;
}