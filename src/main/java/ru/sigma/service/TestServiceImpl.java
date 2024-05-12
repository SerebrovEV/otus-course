package ru.sigma.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sigma.domain.Question;
import ru.sigma.domain.QuestionWithoutAnswer;
import ru.sigma.domain.User;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    
    private final QuestionService questionService;
    private final InputService inputService;
    private final OutputService outputService;
    private final UserService userService;
    private final MessageService messageService;
    
    @Value("${language.types}")
    private final List<String> languages;
    
    @Value("${number.pass_test}")
    private int passResult;
    
    @Override
    public void run() {
        choseLocale();
        System.out.println(Locale.getDefault());
        messageService.printMessage("message.hello");
        User user = userService.createUser();
        messageService.printMessage("message.list-of-questions");
        int rightAnswers = 0;
        for (Question question : questionService.getQuestions()) {
            questionService.printQuestion(question);
            String answer = getAnswer();
            if(!(question instanceof QuestionWithoutAnswer) &&
                    questionService.checkAnswer(answer, question.getRightAnswer().getAnswer())){
                rightAnswers++;
            }
        }
        userService.addTestResult(user, rightAnswers);
        printTestResult(user);
        messageService.printMessage("message.goodbye", userService.getPrintableUser(user));
    }
    
    public void choseLocale() {
        messageService.printMessage("message.start-application");
        languages.forEach(outputService::outputData);
        messageService.printMessage("message.enter-data");
        String userLanguage = inputService.inputData();
        Locale.setDefault(Locale.forLanguageTag(
                languages.stream()
                        .filter(s -> s.equals(userLanguage) && !s.equals("default"))
                        .findFirst()
                        .orElse("")));
    }
    
    public String getAnswer() {
        messageService.printMessage("message.enter-answer");
        String enteredAnswer = inputService.inputData();
        messageService.printMessage("message.typed-answer");
        outputService.outputData(enteredAnswer);
        return enteredAnswer;
    }
    
    public void printTestResult(User user) {
        int testResult = userService.getLastTestResult(user);
        messageService.printMessage("message.test-result", String.valueOf(testResult));
        if (testResult >= passResult) {
            messageService.printMessage("message.pass-test");
        } else {
            messageService.printMessage("message.not-pass-test");
        }
    }
}