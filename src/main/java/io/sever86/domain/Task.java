package io.sever86.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")

public class Task {

    public Task(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return String.format(
                "%s", "%s", id,
                text);
    }

    @Id
    @Column(name = "id")

    private Integer id;
    @Column(name = "task_text")
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

