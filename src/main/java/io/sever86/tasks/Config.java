package io.sever86.tasks;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Администратор on 24.11.2016.
 */
@Configuration
@EnableJpaRepositories(basePackages = "io.sever86.tasks")
@EnableTransactionManagement
public class Config  {

    @Bean
    public DataSource dataSource() {
        final HikariConfig config = new HikariConfig();
        Properties property = new Properties();
        try {
            FileInputStream fis;
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);

            String host = property.getProperty("spring.datasource.url");
            String login = property.getProperty("spring.datasource.username");
            String password = property.getProperty("spring.datasource.password");
            String driver = property.getProperty("spring.datasource.driver-class-name");

            config.setJdbcUrl(host);
            config.setDriverClassName(driver);
            config.setUsername(login);
            config.setPassword(password);
        }catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        return new HikariDataSource(config);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("io.sever86.tasks");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    TaskDao taskDao() {
//        return new TaskDaoJdbcTemplate();
        return new TaskDaoHibernate();
    }
}


