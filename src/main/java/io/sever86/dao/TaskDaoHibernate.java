package io.sever86.dao;

import io.sever86.repositories.TaskRepository;
import io.sever86.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Администратор on 26.11.2016.
 */
public class TaskDaoHibernate implements TaskDao {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task add(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> load() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void delete(Integer id) {
        taskRepository.delete(id);
    }
}

