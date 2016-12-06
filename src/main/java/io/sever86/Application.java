package io.sever86;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    public static void main(String args[]) throws SQLException {
        SpringApplication.run(Application.class, args);
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Override
    public void run(String... strings) throws Exception {
    }

}




