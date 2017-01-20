package io.sever86.dao;

import io.sever86.controllers.TaskController;
import io.sever86.domain.Executors;
import io.sever86.domain.Persona;
import io.sever86.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class TaskDaoJdbcTemplate implements TaskDao {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Task> rowMapperTask = (ResultSet rs, int rowNum) -> {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setCreatorID(rs.getInt("creator_id"));
        task.setTaskName(rs.getString("task_name"));
        task.setDescription(rs.getString("description"));
        task.setStartTime(rs.getTimestamp("start_time"));
        task.setFinishTime(rs.getTimestamp("finish_time"));
        task.setResponcebleID(rs.getInt("responceble_id"));
        task.setCost(rs.getBigDecimal("cost"));
        return task;
    };

    private RowMapper<Task> rowMapperTasks = (ResultSet rs, int rowNum) -> {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setTaskName(rs.getString("task_name"));
        return task;
    };

    public Integer addTask(Task task) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); //ID generator for BD

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tasks(creator_id,task_name,description,start_time,finish_time,responceble_id,cost) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, task.getCreatorID());
            ps.setString(2, task.getTaskName());
            ps.setString(3, task.getDescription());
            ps.setTimestamp(4, task.getStartTime());
            ps.setTimestamp(5, task.getFinishTime());
            ps.setInt(6, task.getResponcebleID());
            ps.setBigDecimal(7, task.getCost());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    public Task findOneTask(Integer id) {
        return jdbcTemplate.query("SELECT * FROM tasks WHERE id=?", rowMapperTask, id).get(0);

    }

    public List<Task> findAllTask() {
        return jdbcTemplate.query("SELECT id,task_name FROM tasks ", rowMapperTasks);
    }

    public void deleteTask(Integer id) {
        jdbcTemplate.update("DELETE FROM tasks WHERE id = ?", id);
        jdbcTemplate.update("DELETE FROM worker_on_task WHERE task_id = ?", id);

    }

}