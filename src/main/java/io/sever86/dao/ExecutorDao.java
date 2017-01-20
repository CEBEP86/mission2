package io.sever86.dao;
import io.sever86.domain.Executors;
import java.util.List;

/**
 * Created by home on 13.01.2017.
 */
public interface ExecutorDao {

    public void addExecutors(Integer no, Executors taskExecutors);

    public List<io.sever86.domain.Executors> findExecutors(Integer id);

}
