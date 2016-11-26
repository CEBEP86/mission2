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

    public List<Task>  load() {
        /*List<Task> load_task =
                jdbcTemplate.query("SELECT task_text FROM TASK", (rs, rowNum) ->
                          new Task(rs.getString("id"),rs.getString("task_text"))).
                          forEach(task ->  ));


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
*/
        final String sql = "select * from TASK";
        final List<Task> tasks = new ArrayList<Task>();
        final List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> row : rows) {
            Task task = new Task();
            task.setId((Integer) row.get("id"));
            task.setText((String) row.get("task_text"));
            tasks.add(task);
        }
        return tasks;

    }


    public void save(Task task) {
        if(task.getId()==1) {
            jdbcTemplate.execute("drop table TASK");
            jdbcTemplate.execute("CREATE TABLE TASK (id INT ,task_text TEXT NOT NULL);");
        }
        jdbcTemplate.execute("INSERT INTO TASK  VALUES ("+task.getId()+",'"+ task.getText() + "')");

}

}
