package io.sever86.dao;

import io.sever86.controllers.TaskController;
import io.sever86.domain.Personal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by home on 13.01.2017.
 */
public class PersonalDaoJdbcTemplate implements PersonalDao {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Personal> rowMapperPersona = (ResultSet rs, int rowNum) -> {
        Personal personal = new Personal();
        personal.setId(rs.getInt("id"));
        personal.setFirstName(rs.getString("first_name"));
        personal.setSecondName(rs.getString("last_name"));
        personal.setLastName(rs.getString("second_name"));
        personal.setTax(rs.getBigDecimal("tax"));
        personal.setEabled(rs.getBoolean("enabled"));
        return personal;
    };
    public void addPersonal(Personal personal){
         PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO personal(first_name,last_name,second_name,date_birth,tax,login,password,enabled) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, personal.getFirstName());
            ps.setString(2, personal.getLastName());
            ps.setString(3, personal.getSecondName());
            ps.setTimestamp(4, personal.getDatebirth());
            ps.setBigDecimal(5, personal.getTax());
            ps.setString(6, personal.getLogin());
            ps.setString(7, personal.getPassword());
            ps.setBoolean(8, personal.getEabled());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator);
    }
    public List<Personal> loadPersonalInf() {
        return jdbcTemplate.query("SELECT id, first_name,last_name,second_name,tax,enabled FROM personal", rowMapperPersona);
    }

}
