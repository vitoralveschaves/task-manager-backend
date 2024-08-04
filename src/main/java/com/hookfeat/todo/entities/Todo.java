package com.hookfeat.todo.entities;

import com.hookfeat.todo.entities.dtos.TodoRequestDto;
import com.hookfeat.todo.entities.enums.Status;
import com.hookfeat.todo.entities.enums.Time;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private Time time;
    private String description;
    private Status status;

    public Todo() {}

    public Todo(Integer id, String title, Time time, String description, Status status) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.description = description;
        this.status = status;
    }

    public Todo(TodoRequestDto todoRequestDto) {
        this.title = todoRequestDto.title();
        this.time = todoRequestDto.time();
        this.description = todoRequestDto.description();
        this.status = Status.NOT_STARTED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
