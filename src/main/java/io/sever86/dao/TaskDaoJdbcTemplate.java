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

    private RowMapper<Task> rowMapperTask = (ResultSet rs, int rowNum) -> { //Создали список методом спринга
        Task task = new Task();
        task.setTaskNo(rs.getInt("task_no"));
        task.setCreatorID(rs.getInt("creator_id"));
        task.setTaskName(rs.getString("task_name"));
        task.setDescription(rs.getString("description"));
        task.setStartTime(rs.getTimestamp("start_time"));
        task.setFinishTime(rs.getTimestamp("finish_time"));
        task.setResponceble(rs.getInt("responceble_id"));
        task.setCost(rs.getFloat("cost"));
        return task;
    };

    private RowMapper<Executors> rowMapperExecutors = (ResultSet rs, int rowNum) -> { //Создали список методом спринга
            Executors executors=new Executors();
            executors.setWorkerId(rs.getInt("worker_id"));
            executors.setHour(rs.getInt("hour"));
            return executors;
    };
    private RowMapper<Task> rowMapperTasks = (ResultSet rs, int rowNum) -> { //Создали список методом спринга
        Task task = new Task();
        task.setTaskName(rs.getString("task_name"));
        return task;
    };




    public void addTask(Task task) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); //ID generator for BD

        PreparedStatementCreator preparedStatementCreator = connection -> { //Создание запроса в БД
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tasks(creator_id,task_name,description,start_time,finish_time,responceble_id,cost) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, task.getCreatorID());
            ps.setString(2, task.getTaskName());
            ps.setString(3, task.getDescription());
            ps.setTimestamp(4, task.getStartTime());
            ps.setTimestamp(5, task.getFinishTime());
            ps.setInt(6, task.getResponceble());
            ps.setFloat(7, task.getCost());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
    }

    public void addExecutors(Task task, Executors executors) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); //ID generator for BD
        PreparedStatementCreator preparedStatementCreatorForExecutors = connection -> { //Создание запроса в БД
            PreparedStatement ps = connection.prepareStatement("INSERT INTO worker_on_task(worker_id,task_id,hour) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, executors.getWorkerId());
            ps.setInt(2, task.getTaskNo());
            ps.setInt(3, executors.getHour());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreatorForExecutors, keyHolder);
    }

    public Task findOneTask(Integer id) {
        return jdbcTemplate.query("SELECT * FROM tasks WHERE task_no=?", rowMapperTask, id).get(0);

    }

    public  List<Executors> findExecutors(Integer id)
    {

        return jdbcTemplate.query("SELECT worker_id,hour  FROM worker_on_task WHERE task_id=?",rowMapperExecutors, id);


    }

    public List<Task> findAllTask() {
        return jdbcTemplate.query("SELECT task_name FROM tasks ",rowMapperTasks);
    }


    public void deleteTask(Integer id) {
      //  jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }


    public List<Persona> findAllPersonal() {
       // return jdbcTemplate.query("SELECT id, task_text FROM TASK", rowMapper);
        return null;
    }

    public void changeTask(Task task) {
        if (task == null) {
            throw new NullPointerException("Task Id can not be null here");
        }
        //jdbcTemplate.update("UPDATE TASK SET task_text = ? WHERE id = ?", task.getText(), task.getId());
    }
}