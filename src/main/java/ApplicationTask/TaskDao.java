package ApplicationTask;

import  java.util.*;

/**
 * Created by Администратор on 24.11.2016.
 */
public interface TaskDao{+

        public Task create(Task task);
        public Task read(Integer id);
        public List<Task> readAll();
        public Task update(Task task);
        public void delete(Integer id);

}
