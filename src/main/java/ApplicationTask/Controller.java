package ApplicationTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.util.List;


@RestController
public class Controller {
    @Autowired
    TaskDaoJdbcTemplate TaskDao;///попробуй потом просто с таскдао

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /*
      public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) { ///обработка сигнала in
          System.out.println(name);
          return new Greeting(counter.incrementAndGet(),
                  String.format(template, name));
      }  */
////////////////////////////////
    @RequestMapping(method = RequestMethod.POST, path = "/save") ///адрес который принимает запросы
    public String save(@RequestBody List<Task> text) {
        for(Task a:text)
        TaskDao.save(a);

         return "Ok";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/load") ///адрес который принимает запросы
    public List<Task> load(@RequestBody String text) {

       System.out.println( TaskDao.load());
        return TaskDao.load();
        }





}
