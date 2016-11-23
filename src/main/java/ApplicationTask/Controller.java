package ApplicationTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;


@RestController
public class Controller {
    public  String s;
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
    public Customer2 save(@RequestBody Customer2 text) {

       // bd_save(text.getText());
        //Customer otvet= new Customer();
        //otvet.setText("Ok");
    //     return otvet;
        System.out.print(text.getText());
        System.out.print("!!!!!!");
        return text;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/load") ///адрес который принимает запросы
    public Customer b(@RequestBody Customer text) {

        System.out.println(bd_load());
        Customer otvet= new Customer();
        otvet.setText(bd_load());
        return otvet;
        //new Customer("Ok");
    }




    public  String  bd_load()
    {

        jdbcTemplate.query("SELECT task_text FROM TASK",(rs, rowNum) ->
                new Customer(rs.getString("task_text"))).
                forEach(task -> s=(task.toString()));
        return  s;
    }

    public void bd_save(String mes)
    {
        jdbcTemplate.execute("drop table TASK");
        jdbcTemplate.execute("CREATE TABLE TASK (task_text TEXT NOT NULL);");
        jdbcTemplate.execute("INSERT INTO TASK (task_text) VALUES ('"+ mes+"')");

    }
}
