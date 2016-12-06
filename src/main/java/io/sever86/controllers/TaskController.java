package io.sever86.controllers;


import io.sever86.dao.TaskDao;
import io.sever86.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {
    @Autowired
    TaskDao taskDao;


    private static final Logger log = LoggerFactory.getLogger(TaskController.class);



    @RequestMapping(method = RequestMethod.POST, path = "/save") ///адрес который принимает запросы
    public String save(@RequestBody List<Task> text) {
        for (Task a : text)
            taskDao.save(a);
        return "ok";
    }



    @RequestMapping(method = RequestMethod.GET,value = "/load") ///адрес который принимает запросы
    @ResponseBody
    public List<Task> load() {
        List<Task> result = taskDao.load();
        log.info("Loaded tasks: {}", result);
        return result;
    }


}
