package io.sever86.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.sever86.dao.ExecutorDao;
import io.sever86.dao.PersonalDao;
import io.sever86.dao.TaskDao;
import io.sever86.dao.TaskDaoJdbcTemplate;
import io.sever86.dao.ExecutorDaoJdbcTemplate;
import io.sever86.dao.PersonalDaoJdbcTemplate;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {


    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String user;
    @Value("${spring.datasource.password}")
    String pass;
    @Value("${spring.datasource.driver-class-name}")
    String draive;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setDriverClassName(draive);
        config.setUsername(user);
        config.setPassword(pass);


        return new HikariDataSource(config);
    }


    @Bean
    TaskDao taskDao() {
        return new TaskDaoJdbcTemplate();
    }
    @Bean
    ExecutorDao executorDao() {
        return new ExecutorDaoJdbcTemplate();
    }
    @Bean
    PersonalDao personalDao() {  return new PersonalDaoJdbcTemplate();    }
}


