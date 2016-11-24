package ApplicationTask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Администратор on 24.11.2016.
 */
@Configuration
public class Config {

    @Bean
    TaskDao taskDao() {
        return new TaskDao ();    }
}

