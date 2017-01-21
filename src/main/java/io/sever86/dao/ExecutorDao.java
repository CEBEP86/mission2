package io.sever86.dao;
import io.sever86.domain.Executor;
import java.util.List;

/**
 * Created by home on 13.01.2017.
 */
public interface ExecutorDao {

     void addExecutor(Integer no, Executor taskExecutor);

     List<Executor> findExecutor(Integer id);

}
