package io.sever86.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application implements CommandLineRunner {
//yo niger

    public static void main(String args[]) throws SQLException {
        SpringApplication.run(Application.class, args);
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Override
    public void run(String... strings) throws Exception {
    }

}




