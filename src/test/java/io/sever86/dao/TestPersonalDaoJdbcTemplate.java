package io.sever86.dao;

import io.sever86.configs.TestConfig;
import io.sever86.domain.Personal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by home on 13.01.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional

public class TestPersonalDaoJdbcTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    PersonalDao underTest;
    @Test
    public void shouldLoadAndAddPersonalInf() {
        Personal personal1 = new Personal();
        personal1.setId(1);
        personal1.setFirstName("Mariya");
        personal1.setLastName("Samsonova");
        personal1.setSecondName("Andreevna");
        personal1.setDatebirth( new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        personal1.setTax(new BigDecimal(2));
        personal1.setLogin("admin");
        personal1.setPassword("admin");

        Personal persona = new Personal();
        persona.setId(1);
        persona.setFirstName("Konstantin");
        persona.setLastName("Samsonov");
        persona.setSecondName("Andreevich");
        persona.setDatebirth( new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        persona.setTax(new BigDecimal(2));
        persona.setLogin("admin");
        persona.setPassword("admin");

        final Personal personal2 =persona;

        underTest.addPersonal(personal1);
        underTest.addPersonal(personal2);

        List<Personal> result=underTest.loadPersonalInf();

        assertTrue(result.stream().
                filter(personal -> personal.getTax().equals(personal1.getTax())).
                filter(personal -> personal.getFirstName ().equals(personal1.getFirstName())).
                count() == 1);
        assertTrue(result.stream().
                filter(personal -> personal.getTax() .equals( personal2.getTax())).
                filter(personal -> personal.getFirstName().equals(personal2.getFirstName())).
                count() == 1);
    }
   }
