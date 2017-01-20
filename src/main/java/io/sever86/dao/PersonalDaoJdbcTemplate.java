package io.sever86.dao;

import io.sever86.controllers.TaskController;
import io.sever86.domain.Persona;
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
public class PersonalDaoJdbcTemplate implements PersonalDao {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Persona> rowMapperPersona = (ResultSet rs, int rowNum) -> {
        Persona persona = new Persona();
        persona.setId(rs.getInt("id"));
        persona.setFirstName(rs.getString("first_name"));
        persona.setSecondName(rs.getString("last_name"));
        persona.setLastName(rs.getString("second_name"));
        persona.setTax(rs.getBigDecimal("tax"));
        persona.setEabled(rs.getInt("enabled"));
        return persona;
    };
    public void addPersonal(Persona persona){
        KeyHolder keyHolder = new GeneratedKeyHolder(); //ID generator for BD
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO personal(first_name,last_name,second_name,date_birth,tax,login,password,enabled) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getFirstName());
            ps.setString(2, persona.getLastName());
            ps.setString(3, persona.getSecondName());
            ps.setTimestamp(4, persona.getDatebirth());
            ps.setBigDecimal(5, persona.getTax());
            ps.setString(6, persona.getLogin());
            ps.setString(7, persona.getPassword());
            ps.setInt(8, persona.getEabled());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
    }
    public List<Persona> loadPersonalInf() {
        return jdbcTemplate.query("SELECT id, first_name,last_name,second_name,tax,enabled FROM personal", rowMapperPersona);
    }

}
