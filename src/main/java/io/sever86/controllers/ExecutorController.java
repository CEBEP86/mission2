package io.sever86.controllers;

import io.sever86.dao.ExecutorDao;
import io.sever86.dao.PersonalDao;
import io.sever86.dao.TaskDao;
import io.sever86.domain.Executor;
import io.sever86.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarse on 30.05.2017.
 */
@RestController
public class ExecutorController {
    @Autowired
    TaskDao taskDao;
    @Autowired
    ExecutorDao executorDao;
    @Autowired
    PersonalDao personalDao;
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/api/read-task-executor/{id}")
    @ResponseBody
    public List<Executor> createTaskAddExecutor(@PathVariable Integer id) {
        return executorDao.findExecutor(id);
    }


}
