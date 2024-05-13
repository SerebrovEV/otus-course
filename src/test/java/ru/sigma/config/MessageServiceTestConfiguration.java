package ru.sigma.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.sigma.dao.OutputDao;
import ru.sigma.dao.OutputDaoSystemOut;
import ru.sigma.service.MessageService;
import ru.sigma.service.MessageServiceImpl;
import ru.sigma.service.OutputService;
import ru.sigma.service.OutputServicePrint;


@TestConfiguration
public class MessageServiceTestConfiguration {
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
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
    MessageService messageService(OutputService outputService, MessageSource messageSource) {
        return new MessageServiceImpl(outputService, messageSource);
    }
}