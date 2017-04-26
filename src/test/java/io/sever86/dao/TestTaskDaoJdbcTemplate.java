package io.sever86.dao;
        import io.sever86.configs.TestConfig;
        import io.sever86.domain.Task;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.jdbc.core.PreparedStatementCreator;
        import org.springframework.jdbc.support.GeneratedKeyHolder;
        import org.springframework.jdbc.support.KeyHolder;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
        import org.springframework.transaction.annotation.Transactional;

        import java.math.BigDecimal;
        import java.sql.PreparedStatement;
        import java.sql.Statement;
        import java.util.Calendar;
        import java.util.List;
        import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class TestTaskDaoJdbcTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    TaskDao underTest;
    @Test
    public void shouldAddTask() {
        Task task = new Task();
        task.setId(2);
        task.setCreatorID(2);
        task.setTaskName("some task");
        task.setDescription("description");
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        task.setStartTime(ourJavaTimestampObject);
        task.setFinishTime(ourJavaTimestampObject);
        task.setResponcebleID(5);
        task.setCost(new BigDecimal(1.33));
        Integer result = underTest.addTask(task);
        Integer savedId = jdbcTemplate.queryForObject("SELECT task_no FROM tasks WHERE task_name = 'some task'", Integer.class);
        assertEquals(result, savedId);
    }
    @Test
    public void shouldDeleteTask() {
         Task task = new Task();
         task.setId(2);
         task.setCreatorID(2);
         task.setTaskName("some task");
         task.setDescription("description");
         Calendar calendar = Calendar.getInstance();
         java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
         task.setStartTime(ourJavaTimestampObject);
         task.setFinishTime(ourJavaTimestampObject);
         task.setResponcebleID(5);
         task.setCost(new BigDecimal(1.33));
         Integer result = underTest.addTask(task);
         Integer savedId = jdbcTemplate.queryForObject("SELECT task_no FROM tasks WHERE task_name = 'some task'", Integer.class);
         assertEquals(result, savedId);
         underTest.deleteTask(result);
         try {
             savedId=null;
             savedId = jdbcTemplate.queryForObject("SELECT task_no FROM tasks WHERE task_name = 'some task'", Integer.class);
         }catch(Throwable throwable){assertNull(savedId);}
     }
    @Test
    public void shouldFindAllTask() {
        Task taskBillet1 = new Task();
        taskBillet1.setId(2);
        taskBillet1.setCreatorID(2);
        taskBillet1.setTaskName("some task");
        taskBillet1.setDescription("description");
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        taskBillet1.setStartTime(ourJavaTimestampObject);
        taskBillet1.setFinishTime(ourJavaTimestampObject);
        taskBillet1.setResponcebleID(5);
        taskBillet1.setCost(new BigDecimal(1.33));

        Task taskBillet2 = new Task();
        taskBillet2 =taskBillet1;
        taskBillet2.setId(3);
        taskBillet2.setCreatorID(3);
        taskBillet2.setTaskName("other task");
        taskBillet2.setDescription("description2");
        calendar = Calendar.getInstance();
        ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        taskBillet2.setStartTime(ourJavaTimestampObject);
        taskBillet2.setFinishTime(ourJavaTimestampObject);
        taskBillet2.setResponcebleID(5);
        taskBillet2.setCost(new BigDecimal(1.33));

         Integer task1 = underTest.addTask(taskBillet1);
         taskBillet1.setId(task1);
         Integer task2 = underTest.addTask(taskBillet2);
        taskBillet2.setId(task2);
         final Task taskBillet3=taskBillet2;
        List<Task> persistedTasks = underTest.findAllTask();
        assertTrue(persistedTasks.stream().filter(task -> task.getId() == taskBillet1.getId()).filter(task -> task.getTaskName().equals(taskBillet1.getTaskName())).count() == 1);
        assertTrue(persistedTasks.stream().filter(task -> task.getId() == taskBillet3.getId()).filter(task -> task.getTaskName().equals(taskBillet3.getTaskName())).count() == 1);
    }
    @Test
    public void shouldFindOneTask() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tasks(creator_id,task_name,description,start_time,finish_time,responceble_id,cost)VALUES (1,'Task1','test','2016-12-07 19:10:25-07','2016-12-08 11:43:25-07',1,9.00)",Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        final Task task = underTest.findOneTask((Integer) keyHolder.getKeys().get("task_no"));
        assertEquals("test",task.getDescription());

    }

    }

