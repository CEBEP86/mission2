package io.sever86.configs;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.sever86.dao.*;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
@Configuration
public class TestConfig {
    private Logger log = LoggerFactory.getLogger(TestConfig.class);
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
        config.setJdbcUrl("jdbc:postgresql://localhost:5433/test");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("ii");
        config.setPassword("ii");
        return new HikariDataSource(config);
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
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
    @Bean
    PlatformTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager result = new DataSourceTransactionManager();
        result.setDataSource(dataSource());
        return result;
    }
}
