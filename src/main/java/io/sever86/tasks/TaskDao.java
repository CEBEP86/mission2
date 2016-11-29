package io.sever86.tasks;

import  java.util.*;

/**
 * Created by Администратор on 24.11.2016.
 */
public interface TaskDao{


        public Task add(Task task);

        public List<Task>  load();

        public void save(Task task);

        public void delete(Integer id);

}
