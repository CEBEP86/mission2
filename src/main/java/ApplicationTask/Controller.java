package ApplicationTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Controller
@RestController
public class Controller implements TaskDaoJdbcTemplate.TaskDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /*
      public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) { ///обработка сигнала in
          System.out.println(name);
          return new Greeting(counter.incrementAndGet(),
                  String.format(template, name));
      }  */
////////////////////////////////
    @RequestMapping(method = RequestMethod.POST, path = "/save") ///адрес который принимает запросы
    public Task  save(@RequestBody Task text) {

        bd_save(text.getText());
        Task otvet= new Task();
        otvet.setText("Ok");
         return otvet;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/load") ///адрес который принимает запросы
    public Task b(@RequestBody Task text) {

        System.out.println(bd_load());
        Task otvet= new Task();
        otvet.setText(bd_load());
        return otvet;
        //new Customer("Ok");
    }





}
