package ApplicationTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ApplicationTask.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;

import  java.util.*;

/**
 * Created by Администратор on 24.11.2016.
 */
public interface TaskDao{


        public Task add(Task task);

        public String load();

        public void save(Task task);

        public void delete(Integer id);

}
