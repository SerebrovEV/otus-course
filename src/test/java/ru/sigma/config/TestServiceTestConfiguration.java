package ru.sigma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.sigma.dao.QuestionDao;
import ru.sigma.dao.QuestionDaoCsv;
import ru.sigma.dao.ResourceDao;
import ru.sigma.dao.ResourceDaoFile;
import ru.sigma.dao.UserDao;
import ru.sigma.dao.UserDaoImpl;
import ru.sigma.service.InputService;
import ru.sigma.service.MessageService;
import ru.sigma.service.MessageServiceImpl;
import ru.sigma.service.OutputService;
import ru.sigma.service.QuestionService;
import ru.sigma.service.QuestionServiceImpl;
import ru.sigma.service.TestService;
import ru.sigma.service.TestServiceImpl;
import ru.sigma.service.UserService;
import ru.sigma.service.UserServiceImpl;


import java.util.List;

@TestConfiguration
@PropertySource(value = "classpath:application-test-test.yml", factory = YamlPropertySourceFactory.class)
public class TestServiceTestConfiguration {
    
    @Bean
    UserDao userDao() {
        return new UserDaoImpl();
    }
    
    @Bean
    UserService userService(UserDao userDao,
                            MessageService messageService,
                            InputService inputService) {
        return new UserServiceImpl(userDao, messageService, inputService);
    }
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean
    MessageService messageService(OutputService outputService, MessageSource messageSource) {
        return new MessageServiceImpl(outputService, messageSource);
    }
    
    @Bean
    ResourceDao resource(ApplicationContext applicationContext) {
        return new ResourceDaoFile(applicationContext);
    }
    
    @Bean
    QuestionDao questionDao(ResourceDao resourceDaoFile, @Value("${info.delimiter}") String delimiter) {
        return new QuestionDaoCsv(resourceDaoFile, delimiter);
    }
    
    @Bean
    QuestionService questionService(QuestionDao questionDao,
                                    OutputService outputService,
                                    @Value("${info.file}") String fileName,
                                    @Value("${info.type}") String fileType) {
        return new QuestionServiceImpl(questionDao, outputService, fileName, fileType);
    }
    
    @Bean
    TestService testService(QuestionService questionService,
                            InputService inputService,
                            OutputService outputService,
                            UserService userService,
                            MessageService messageService,
                            @Value("${language.types}") List<String> languages) {
        return new TestServiceImpl(questionService, inputService, outputService, userService, messageService, languages);
    }
}