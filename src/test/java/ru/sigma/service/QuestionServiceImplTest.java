package ru.sigma.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sigma.config.QuestionServiceTestConfiguration;
import ru.sigma.domain.Answer;
import ru.sigma.domain.Option;
import ru.sigma.domain.Question;
import ru.sigma.domain.QuestionWithOption;
import ru.sigma.domain.QuestionWithoutAnswer;
import ru.sigma.domain.QuestionWithoutOption;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = QuestionServiceTestConfiguration.class)
@DisplayName("Test QuestionServiceImpl")
class QuestionServiceImplTest {
    
    private QuestionWithOption question1;
    private QuestionWithOption question2;
    private QuestionWithoutOption question3;
    private QuestionWithoutAnswer question4;
    private QuestionWithOption question5;
    private QuestionWithOption question6;
    private QuestionWithoutOption question7;
    private QuestionWithoutAnswer question8;
    
    @Autowired
    private QuestionService out;
    
    @BeforeEach
    void setUp() {
        question1 = new QuestionWithOption("Question1", List.of(new Option("Answer1"), new Option("Answer2"), new Option("Answer3")), new Answer("RightAnswer"));
        question2 = new QuestionWithOption("Question2", List.of(new Option("Answer1"), new Option("Answer2")), new Answer("RightAnswer"));
        question3 = new QuestionWithoutOption("Question3", new Answer("RightAnswer"));
        question4 = new QuestionWithoutAnswer("Question4");
        
        question5 = new QuestionWithOption("Вопрос 1", List.of(new Option("Вариант 1"), new Option("Вариант 2"), new Option("Вариант 3")), new Answer("Правильный ответ"));
        question6 = new QuestionWithOption("Вопрос 2", List.of(new Option("Вариант 1"), new Option("Вариант 2")), new Answer("Правильный ответ"));
        question7 = new QuestionWithoutOption("Вопрос 3", new Answer("Правильный ответ"));
        question8 = new QuestionWithoutAnswer("Вопрос 4");
    }
    
    @DisplayName("Test method getQuestions")
    @Test
    void getEnglishQuestions() {
        Locale.setDefault(Locale.ROOT);
        List<Question> actual = out.getQuestions();
        List<Question> expected = List.of(question1, question2, question3, question4);
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
    
    @DisplayName("Test method getQuestions")
    @Test
    void getRussianQuestions() {
        Locale.setDefault(new Locale("ru", "RU"));
        List<Question> actual = out.getQuestions();
        List<Question> expected = List.of(question5, question6, question7, question8);
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
    
    @DisplayName("Test method checkAnswer")
    @Test
    void checkAnswer() {
        assertThat(out.checkAnswer("RightAnswer", question1.getRightAnswer().getAnswer())).isTrue();
        assertThat(out.checkAnswer("Answer2", question1.getRightAnswer().getAnswer())).isFalse();
    }
}