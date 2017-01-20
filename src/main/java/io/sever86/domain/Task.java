package io.sever86.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Task {

    public Task(Integer id, Integer creator, String task_name, String description, Timestamp start_time,
                Timestamp finish_time, Integer responceble_id, BigDecimal cost) {
        this.id = id;
        this.creatorID = creator;
        this.taskName = task_name;
        this.description = description;
        this.startTime = start_time;
        this.finishTime = finish_time;
        this.responcebleID = responceble_id;
        this.cost = cost;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %s %s %s %s %s %s", id,
                creatorID, taskName, description, startTime, finishTime, responcebleID, cost);
    }


    private Integer id;
    private Integer creatorID;
    private String taskName;
    private String description;
    private Timestamp startTime;
    private Timestamp finishTime;
    private Integer responcebleID;
    private BigDecimal cost;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getResponcebleID() {
        return responcebleID;
    }

    public void setResponcebleID(Integer responcebleID) {
        this.responcebleID = responcebleID;
    }


}

