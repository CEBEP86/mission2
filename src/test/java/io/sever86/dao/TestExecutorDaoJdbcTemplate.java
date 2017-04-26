package io.sever86.dao;

import io.sever86.configs.TestConfig;
import io.sever86.domain.Executor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by home on 13.01.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional

public class TestExecutorDaoJdbcTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ExecutorDao underTest;
    @Test
    public void shouldAddExecutor() {
        Executor taskExecutor =new Executor();
        taskExecutor.setPersonalId(4);
        taskExecutor.setHour(new BigDecimal(4));
        List<Executor> listExecutor=new ArrayList<Executor>();
        listExecutor.add(taskExecutor);
        for (Executor i:  listExecutor  )
            underTest.addExecutor(1,i);
        Integer savedId = jdbcTemplate.queryForObject("SELECT personal_id FROM personal_on_task WHERE task_id = 1", Integer.class);
        assertEquals(taskExecutor.getPersonalId(), savedId);
    }
    @Test
    public void shouldFindExecutor() {
        Executor taskExecutor1 =new Executor();
        taskExecutor1.setPersonalId(4);
        taskExecutor1.setHour(new BigDecimal(4));
        Executor taskExecutor2 =new Executor();
        taskExecutor2.setPersonalId(2);
        taskExecutor2.setHour(new BigDecimal(1));
        List<Executor> listExecutor=new ArrayList<Executor>();
        listExecutor.add(taskExecutor1);
        listExecutor.add(taskExecutor2);
        for (Executor i:  listExecutor  )
            underTest.addExecutor(1,i);

        List<Executor> findExecutor=new ArrayList<Executor>();
        findExecutor=underTest.findExecutor(1);
        assertTrue(findExecutor.stream().filter(task -> task.getPersonalId() == taskExecutor1.getPersonalId()).filter(task -> task.getHour().equals(taskExecutor1.getHour())).count() == 1);
        assertTrue(findExecutor.stream().filter(task -> task.getPersonalId() == taskExecutor2.getPersonalId()).filter(task -> task.getHour().equals(taskExecutor2.getHour())).count() == 1);
    }


}
