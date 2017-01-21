package io.sever86.dao;

import io.sever86.controllers.TaskController;
import io.sever86.domain.Executor;
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

/**
 * Created by home on 13.01.2017.
 */
public class ExecutorDaoJdbcTemplate implements ExecutorDao {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Executor> rowMapperExecutors = (ResultSet rs, int rowNum) -> {
        Executor executor = new Executor();
        executor.setPersonalId(rs.getInt("personal_id"));
        executor.setHour(rs.getBigDecimal("hour"));
        return executor;
    };
    public void addExecutor(Integer no, Executor executor) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); //ID generator for BD
        PreparedStatementCreator preparedStatementCreatorForExecutors = connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO personal_on_task(personal_id,task_id,hour) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, executor.getPersonalId());
            ps.setInt(2, no);
            ps.setBigDecimal(3, executor.getHour());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreatorForExecutors, keyHolder);
    }
    public List<Executor> findExecutor(Integer id) {
        return jdbcTemplate.query("SELECT personal_id,hour  FROM personal_on_task WHERE task_id=?", rowMapperExecutors, id);
    }

}
