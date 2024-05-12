package ru.sigma.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sigma.config.ResourceDaoFileTestConfiguration;
import ru.sigma.domain.Answer;
import ru.sigma.domain.Option;
import ru.sigma.domain.Question;
import ru.sigma.domain.QuestionWithOption;
import ru.sigma.domain.QuestionWithoutAnswer;
import ru.sigma.domain.QuestionWithoutOption;
import ru.sigma.exception.ReadEmptyStringLineException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ResourceDaoFileTestConfiguration.class)
@DisplayName("Test QuestionDaoCsv")
class QuestionDaoCsvTest {
    
    private QuestionWithOption question1;
    private QuestionWithOption question2;
    private QuestionWithoutOption question3;
    private QuestionWithoutAnswer question4;
    @Autowired
    private ResourceDao resourceDao;
    
    @Autowired
    private QuestionDao out;
    private final String delimiter = ",";
    
    
    @BeforeEach
    void setUp() {
        question1 = new QuestionWithOption("Question1", List.of(new Option("Answer1"), new Option("Answer2"), new Option("Answer3")), new Answer("RightAnswer"));
        question2 = new QuestionWithOption("Question2", List.of(new Option("Answer1"), new Option("Answer2")), new Answer("RightAnswer"));
        question3 = new QuestionWithoutOption("Question3", new Answer("RightAnswer"));
        question4 = new QuestionWithoutAnswer("Question4");
        
        
    }
    
    @DisplayName("Test method getQuestions")
    @Test
    void getQuestions() {
        List<Question> actual = out.getQuestions("test.csv");
        List<Question> expected = List.of(question1, question2, question3, question4);
        System.out.println(question4);
        assertThat(actual).isEqualTo(expected);
        
    }
    
    @DisplayName("Test RuntimeException in method getQuestions")
    @Test
    void getQuestionsThrowRuntimeException() {
        QuestionDaoCsv out = new QuestionDaoCsv(resourceDao, delimiter);
        assertThatThrownBy(() -> out.getQuestions("empty.csv")).isInstanceOf(RuntimeException.class);
    }
    
    @DisplayName("Test ReadEmptyStringException in method getQuestion")
    @Test
    void getQuestionsThrowReadEmptyStringException() {
        QuestionDaoCsv out = new QuestionDaoCsv(resourceDao, delimiter);
        assertThatThrownBy(() -> out.getQuestions("empty_test.csv")).isInstanceOf(ReadEmptyStringLineException.class);
    }
    
    @DisplayName("Test ReadEmptyStringException in method parseQuestion")
    @Test
    void parseQuestionThrowReadEmptyStringException() {
        QuestionDaoCsv out = new QuestionDaoCsv(resourceDao, delimiter);
        assertThatThrownBy(() -> out.getQuestions("empty_test2.csv")).isInstanceOf(ReadEmptyStringLineException.class);
    }
}