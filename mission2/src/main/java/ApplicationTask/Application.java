package ApplicationTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Application implements CommandLineRunner {


    public static void main(String args[]) throws SQLException {
        SpringApplication.run(Application.class, args);

    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
    }

}




