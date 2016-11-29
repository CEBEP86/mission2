package io.sever86.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//@Component

public  class TaskDaoJdbcTemplate implements TaskDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public String s = "";

    public Task add(Task task) {
        return new Task();
    }



    public void delete(Integer id) {
    }



    public List<Task>  load() {
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
