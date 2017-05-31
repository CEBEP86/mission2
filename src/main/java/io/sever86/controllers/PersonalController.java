package io.sever86.controllers;

import io.sever86.dao.ExecutorDao;
import io.sever86.dao.PersonalDao;
import io.sever86.dao.TaskDao;
import io.sever86.domain.Executor;
import io.sever86.domain.Personal;
import io.sever86.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonalController {
    @Autowired
    TaskDao taskDao;
    @Autowired
    ExecutorDao executorDao;
    @Autowired
    PersonalDao personalDao;
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);


    @RequestMapping(method = RequestMethod.GET, value = "/api/load-personal-information")
    @ResponseBody
    public List<Personal> loadPersonalInformation() {
        return personalDao.loadPersonalInf();
    }


}
