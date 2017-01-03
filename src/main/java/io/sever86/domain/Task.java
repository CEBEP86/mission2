package io.sever86.domain;

import java.sql.Timestamp;

public class Task {

    public Task(Integer task_no, Integer creator, String task_name, String description, Timestamp start_time,
                Timestamp finish_time, Integer responceble, Float cost) {
        this.taskNo = task_no;
        this.creatorID = creator;
        this.taskName =task_name;
        this.description=description;
        this.startTime =start_time;
        this.finishTime =finish_time;
         this.responcebleID =responceble;
        this.cost=cost;
        }

    public Task() {
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %s %s %s %s %s %s", taskNo,
                creatorID, taskName,description, startTime, finishTime, responcebleID,cost);
    }


    private Integer taskNo;
    private Integer creatorID;
    private String taskName;
    private String description;
    private Timestamp startTime;
    private Timestamp finishTime;
     private Integer responcebleID;
    private float cost;


    public Integer getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Integer taskNo) {
        this.taskNo = taskNo;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Integer getResponceble() {
        return responcebleID;
    }

    public void setResponceble(Integer responceble) {
        this.responcebleID = responceble;
    }


}

