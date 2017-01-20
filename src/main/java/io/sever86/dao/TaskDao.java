package io.sever86.dao;

import io.sever86.domain.Executors;

import io.sever86.domain.Task;

import java.util.List;

/**
 * Created by Администратор on 24.11.2016.
 */
public interface
TaskDao {

    public Integer addTask(Task task);

    public Task findOneTask(Integer id);

    public List<Task> findAllTask();

    public void deleteTask(Integer id);


}
