package ru.sigma.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sigma.dao.QuestionDao;
import ru.sigma.domain.Option;
import ru.sigma.domain.Question;
import ru.sigma.domain.QuestionWithOption;

import java.util.List;
import java.util.Locale;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final OutputService outputService;
    private final String fileName;
    private final String fileType;
    
    public QuestionServiceImpl(QuestionDao questionDao,
                               OutputService outputService,
                               @Value("${info.file}") String fileName,
                               @Value("${info.type}") String fileType) {
        this.questionDao = questionDao;
        this.outputService = outputService;
        this.fileName = fileName;
        this.fileType = fileType;
    }
    
    @Override
    public List<Question> getQuestions() {
        final String filePath;
        if (Locale.getDefault().equals(Locale.ROOT)) {
            filePath = fileName + fileType;
        } else {
            filePath = fileName + "_" + Locale.getDefault() + fileType;
        }
        return questionDao.getQuestions(filePath);
    }
    
    @Override
    public boolean checkAnswer(String userAnswer, String rightAnswer) {
        return userAnswer.equalsIgnoreCase(rightAnswer);
    }
    
    @Override
    public void printQuestion(Question question) {
        outputService.outputData(question.getQuestion());
        if (question instanceof QuestionWithOption) {
            List<Option> opinions = ((QuestionWithOption) question).getOptions();
            StringBuilder answers = new StringBuilder();
            for (int i = 0; i < opinions.size(); i++) {
                if (!(i == opinions.size() - 1)) {
                    answers.append(opinions.get(i).getOption()).append("  ");
                } else {
                    answers.append(opinions.get(i).getOption());
                }
            }
            outputService.outputData(String.valueOf(answers));
        }
    }
}