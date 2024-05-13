package ru.sigma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import ru.sigma.dao.OutputDao;
import ru.sigma.dao.OutputDaoSystemOut;
import ru.sigma.dao.QuestionDao;
import ru.sigma.dao.QuestionDaoCsv;
import ru.sigma.dao.ResourceDao;
import ru.sigma.dao.ResourceDaoFile;
import ru.sigma.service.OutputService;
import ru.sigma.service.OutputServicePrint;
import ru.sigma.service.QuestionService;
import ru.sigma.service.QuestionServiceImpl;

@TestConfiguration
@PropertySource(value = "classpath:application-test-question.yml", factory = YamlPropertySourceFactory.class)
public class QuestionServiceTestConfiguration {
    
    @Bean
    ResourceDao resource(ApplicationContext applicationContext) {
        return new ResourceDaoFile(applicationContext);
    }
    
    @Bean
    QuestionDao questionDao(ResourceDao resourceDao,
                            @Value("${info.delimiter}") String delimiter) {
        return new QuestionDaoCsv(resourceDao, delimiter);
    }
    
    @Bean
    OutputDao outputDao() {
        return new OutputDaoSystemOut();
    }
    
    @Bean
    OutputService outputService(OutputDao outputDao) {
        return new OutputServicePrint(outputDao);
    }
    
    @Bean
    QuestionService questionService(QuestionDao questionDao,
                                    OutputService outputService,
                                    @Value("${info.file}") String fileName,
                                    @Value("${info.type}") String fileType) {
        return new QuestionServiceImpl(questionDao, outputService, fileName, fileType);
    }
}