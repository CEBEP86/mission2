package io.sever86.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controller {
    @Autowired
    TaskDao TaskDao;///попробуй потом просто с таскдао


    private static final Logger log = LoggerFactory.getLogger(Application.class);


    @RequestMapping(method = RequestMethod.POST, path = "/save") ///адрес который принимает запросы
    public String save(@RequestBody List<Task> text) {
        for (Task a : text)
            TaskDao.save(a);

        return "Ok";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/load") ///адрес который принимает запросы
    public List<Task> load(@RequestBody String text) {

        System.out.println(TaskDao.load());
        return TaskDao.load();
    }


}
