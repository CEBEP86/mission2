package ApplicationTask;

import ApplicationTask.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Array;
import java.util.*;


@Component

public  class TaskDaoJdbcTemplate implements TaskDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public String s = "";

    public Task add(Task task) {
        return new Task();
    }

    ;

    public void delete(Integer id) {
    }

    ;

    public String load() {
        List<Task> load_task =
                jdbcTemplate.query("SELECT task_text FROM TASK", (rs, rowNum) ->
                        //  new Task(rs.getString("task_text"))).
                        //  forEach(task -> ));


                {
                    Task task = new Task();
                    task.setText(rs.getString("task_text"));
                    return task;
                });
        String stroka = "";
        for (Task name : load_task) {
            stroka += name.toString();

        }
        return stroka;
    }


    public void save(Task task) {
        if(task.getId()==1) {
            jdbcTemplate.execute("drop table TASK");
            jdbcTemplate.execute("CREATE TABLE TASK (id INT ,task_text TEXT NOT NULL);");
        }
        jdbcTemplate.execute("INSERT INTO TASK  VALUES ("+task.getId()+",'"+ task.getText() + "')");

}

}
