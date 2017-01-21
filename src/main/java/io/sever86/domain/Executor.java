package io.sever86.domain;

import java.math.BigDecimal;

/**
 * Created by Администратор on 29.12.2016.
 */
public class Executor {
    private Integer persoanalId;
    private BigDecimal hour;

    public Executor() {
    }


    public BigDecimal getHour() {
        return hour;
    }

    public void setHour(BigDecimal hour) {
        this.hour = hour;
    }

    public Integer getPersonalId() {
        return persoanalId;
    }

    public void setPersonalId(Integer persoanalId) {
        this.persoanalId = persoanalId;
    }
}
