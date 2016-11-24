package ApplicationTask;

import ApplicationTask.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.util.*;

/**
 * Created by Администратор on 23.11.2016.
 */
@Component

public  class TaskDaoJdbcTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public  String s;



    public  String  bd_load()
    {

        jdbcTemplate.query("SELECT task_text FROM TASK",(rs, rowNum) ->
                new Task(rs.getString("task_text"))).
                forEach(task -> s=(task.toString()));
        return  s;
    }

    public void bd_save(String mes)
    {
        jdbcTemplate.execute("drop table TASK");
        jdbcTemplate.execute("CREATE TABLE TASK (task_text TEXT NOT NULL);");
        jdbcTemplate.execute("INSERT INTO TASK (task_text) VALUES ('"+ mes+"')");

    }
}
