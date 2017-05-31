package io.sever86.controllers;


import io.sever86.dao.ExecutorDao;
import io.sever86.dao.PersonalDao;
import io.sever86.dao.TaskDao;
import io.sever86.domain.Executor;
import io.sever86.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private List<Executor> inputExecutor = new ArrayList<Executor>();
    private Task inputTask = new Task();
    private Integer incrementTransactionTask = 0;
    private Integer incrementTransactionExecutor = 0;
// GETTERS AND SETTERS NEEDED FOR TESTS
    public void setInputTask(Task task){this.inputTask=task;}
    public Task getInputTask(){return inputTask;}

    public void setInputExecutor(List<Executor> executor){this.inputExecutor =executor;}
    public List<Executor> getInputExecutor(){return inputExecutor;}



    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/api/create-task")
    @ResponseBody
    public void createTask(@RequestBody Task task) {
        inputTask = task;
        saveInDataBase(1);
    }


    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/api/create-task-add-executor")
    @ResponseBody
    public String createTaskAddExecutor(@RequestBody List<Executor> input_information) {
        inputExecutor = input_information;
        saveInDataBase(2);
        return "ok";
    }



    @Transactional
    public void saveInDataBase(Integer a) {
        if (a == 1) incrementTransactionTask = 1;
        if (a == 2) incrementTransactionExecutor = 1;
        if (incrementTransactionTask == 1) if (incrementTransactionExecutor == 1) {
            incrementTransactionTask = 0;
            incrementTransactionExecutor = 0;
            Integer key_bd = 0;
            key_bd =(Integer) taskDao.addTask(inputTask);
            for (Executor i : inputExecutor) {
                executorDao.addExecutor(key_bd, i);
            }
            inputExecutor.clear();
        }

    }



    @RequestMapping(method = RequestMethod.GET, path = "/api/read-task/{id}")
    @ResponseBody
    public Task readTask(@PathVariable Integer id) {
        return taskDao.findOneTask(id);
    }





    @RequestMapping(method = RequestMethod.GET, value = "/api/find-all-tasks")
    @ResponseBody
    public List<Task> findAllTasksClient() {
        return taskDao.findAllTask();
    }




    @Transactional
    @RequestMapping(method = RequestMethod.DELETE , path = "/api/remove-task/{id}")
    @ResponseBody
    public void removeTask(@PathVariable Integer id) {
            taskDao.deleteTask(id);
    }

}
