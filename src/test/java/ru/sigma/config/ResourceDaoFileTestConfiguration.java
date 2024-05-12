package ru.sigma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.sigma.dao.QuestionDao;
import ru.sigma.dao.QuestionDaoCsv;
import ru.sigma.dao.ResourceDao;
import ru.sigma.dao.ResourceDaoFile;

@Configuration
@PropertySource(value = "classpath:application-test-csv.yml", factory = YamlPropertySourceFactory.class)
public class ResourceDaoFileTestConfiguration {
    
    @Bean
    ResourceDao resource(ApplicationContext applicationContext) {
        return new ResourceDaoFile(applicationContext);
    }
    
    @Bean
    QuestionDao questionDao(ResourceDao resourceDao,
                            @Value("${info.delimiter}") String delimiter) {
        return new QuestionDaoCsv(resourceDao, delimiter);
    }
}