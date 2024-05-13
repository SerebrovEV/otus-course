package ru.sigma.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWithoutAnswer implements Question{
    private String question;
    private final Answer rightAnswer = null;
}