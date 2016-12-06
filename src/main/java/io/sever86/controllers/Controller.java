package io.sever86.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.sever86.dao.TaskDao;
import io.sever86.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controller {
    @Autowired
    TaskDao taskDao;


    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    //org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Controller.class);


    @RequestMapping(method = RequestMethod.POST, path = "/save") ///адрес который принимает запросы
    public String save(@RequestBody List<Task> text) {
        for (Task a : text)
            taskDao.save(a);

        return "Ok";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/load") ///адрес который принимает запросы
    public List<Task> load(@RequestBody String text) {
        log.warn("taskDao.load():",taskDao.load());
        return taskDao.load();
    }


}
