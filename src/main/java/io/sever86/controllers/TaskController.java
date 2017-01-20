package io.sever86.controllers;


import io.sever86.dao.ExecutorDao;
import io.sever86.dao.PersonalDao;
import io.sever86.dao.TaskDao;
import io.sever86.domain.Executors;
import io.sever86.domain.Persona;
import io.sever86.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@RestController
public class TaskController {
    @Autowired
    TaskDao taskDao;
    @Autowired
    ExecutorDao executorDao;
    @Autowired
    PersonalDao personalDao;



    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private List<Executors> inputExecutors = new ArrayList<Executors>();
    private Task inputTask = new Task();
    private Integer incrementTransactionTask = 0;
    private Integer incrementTransactionExecutors = 0;
// GETTERS AND SETTERS NEEDED FOR TESTS
    public void setInputTask(Task task){this.inputTask=task;}
    public Task getInputTask(){return inputTask;}

    public void setInputExecutors(List<Executors> executors){this.inputExecutors=executors;}
    public List<Executors> getInputExecutors(){return inputExecutors;}


    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/create-task")
    @ResponseBody
    public String createTask(@RequestBody Task input_information) {
        inputTask = input_information;
        transaction(1);
        return "ok";
    }


    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/create-task-add-executors")
    @ResponseBody
    public String createTaskAddExecutors(@RequestBody List<Executors> input_information) {
        inputExecutors = input_information;
        transaction(2);
        return "ok";
    }

    @Transactional
    public void transaction(Integer a) {
        if (a == 1) incrementTransactionTask = 1;
        if (a == 2) incrementTransactionExecutors = 1;
        if (incrementTransactionTask == 1) if (incrementTransactionExecutors == 1) {
            incrementTransactionTask = 0;
            incrementTransactionExecutors = 0;
            Integer key_bd = 0;
            key_bd =(Integer) taskDao.addTask(inputTask);
            for (Executors i : inputExecutors) {
                executorDao.addExecutors(key_bd, i);
            }
            inputExecutors.clear();
        }

    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/read-task-executors") ///адрес который принимает запросы
    @ResponseBody
    public List<Executors> createTaskAddExecutors(@RequestBody Integer input_information) {
        return executorDao.findExecutors(input_information);
    }


    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/read-task") ///адрес который принимает запросы
    @ResponseBody
    public Task readTask(@RequestBody Integer input_information) {
        return taskDao.findOneTask(input_information);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/find-all-tasks") ///адрес который принимает запросы
    @ResponseBody
    public List<Task> findAllTasksClient() {
        return taskDao.findAllTask();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/load-personal-information") ///адрес который принимает запросы
    @ResponseBody
    public List<Persona> loadPersonalInformation() {
        return personalDao.loadPersonalInf();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/remove-task") ///адрес который принимает запросы
    @ResponseBody
    public String removeTask(@RequestBody Integer input_information) {
            taskDao.deleteTask(input_information);
          return "ok";
    }

}
