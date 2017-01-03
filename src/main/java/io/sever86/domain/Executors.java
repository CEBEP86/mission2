package io.sever86.domain;

/**
 * Created by Администратор on 29.12.2016.
 */
public class Executors {
    public Executors(Integer workerId,Integer hour) {
        this.workerId=workerId;
        this.hour=hour;
       }

    public Executors() {
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s", workerId,hour);
    }



    private Integer workerId;
    private Integer hour;



    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }
}
