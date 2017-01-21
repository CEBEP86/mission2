package io.sever86.dao;

import io.sever86.domain.Task;

import java.util.List;

/**
 * Created by Администратор on 24.11.2016.
 */
public interface
TaskDao {

     Integer addTask(Task task);

     Task findOneTask(Integer id);

     List<Task> findAllTask();

     void deleteTask(Integer id);


}
