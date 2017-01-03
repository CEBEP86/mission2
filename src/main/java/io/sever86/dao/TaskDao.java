package io.sever86.dao;

import io.sever86.domain.Executors;
import io.sever86.domain.Persona;
import io.sever86.domain.Task;

import java.util.List;

/**
 * Created by Администратор on 24.11.2016.
 */
public interface TaskDao {

    public void addTask(Task task);
    public void addExecutors(Task task,Executors taskExecutors );
    public Task findOneTask(Integer id) ;
    public  List<Executors> findExecutors(Integer id) ;
    public List<Task> findAllTask();

    public void changeTask(Task task) ;
    public List<Persona> findAllPersonal();
    public void deleteTask(Integer id) ;


}
