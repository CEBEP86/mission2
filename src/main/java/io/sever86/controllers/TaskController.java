package io.sever86.controllers;


import io.sever86.dao.TaskDao;
import io.sever86.domain.Executors;
import io.sever86.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@RestController
public class TaskController {
    @Autowired
    TaskDao taskDao;


    private static final Logger log = LoggerFactory.getLogger(TaskController.class);



    @RequestMapping(method = RequestMethod.POST, path = "/save") ///адрес который принимает запросы
    @ResponseBody

    public String save(@RequestBody List<Task> input_text) {
        Task task=new Task();
        Executors taskExecutors=new Executors();

        task.setTaskNo(2);
        task.setCreatorID(2);
        task.setTaskName("task_name");
        task.setDescription("description");
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        task.setStartTime(ourJavaTimestampObject);
        task.setFinishTime(ourJavaTimestampObject);
        taskExecutors.setWorkerId(4);
        taskExecutors.setHour(4);
        task.setResponceble(5);
        task.setCost((float) 1.33);

        List<Executors> listExecutors=new ArrayList<Executors>();
        listExecutors.add(taskExecutors);
        listExecutors.add(taskExecutors);
        for (Executors i:  listExecutors  ) {
            taskDao.addExecutors(task,i);
        }

        taskDao.addTask(task);


        log.info("tasks add complete");
     //   List<Task> result = taskDao.findAllTask();
        return "ok";
    }


@Transactional
    @RequestMapping(method = RequestMethod.GET,value = "/load") ///адрес который принимает запросы
    @ResponseBody
    //public List<Task> load() {
    public String load() {
    log.info("Load tasks begin");
    Task result=new Task();
    result = taskDao.findOneTask(1);
    List<Executors> result2 = taskDao.findExecutors(1);
    log.info("Loaded tasks: {}", result.getDescription().toString());
    for (Executors i:  result2  ) {
        log.info("Loaded tasks: {}", i.toString());
    }
    log.info("Load tasks complete");


    log.info("Load taskLIST");
    List<Task> result3 = taskDao.findAllTask();
    for (Task i:  result3  ) {
        log.info("Loaded tasks: {}", i.getTaskName().toString());
    }



    return "ok";
    }





}
