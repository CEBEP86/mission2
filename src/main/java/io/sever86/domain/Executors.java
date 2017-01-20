package io.sever86.domain;

import java.math.BigDecimal;

/**
 * Created by Администратор on 29.12.2016.
 */
public class Executors {
    public Executors(Integer workerId, BigDecimal hour) {
        this.workerId = workerId;
        this.hour = hour;
    }

    public Executors() {
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s", workerId, hour);
    }


    private Integer workerId;
    private BigDecimal hour;


    public BigDecimal getHour() {
        return hour;
    }

    public void setHour(BigDecimal hour) {
        this.hour = hour;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }
}
