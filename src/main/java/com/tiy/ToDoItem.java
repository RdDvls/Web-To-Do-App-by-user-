package com.tiy;

import javax.persistence.*;

/**
 * Created by RdDvls on 9/15/16.
 */

@Entity
@Table(name = "todos")
public class ToDoItem {
    @Id
    @GeneratedValue
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @ManyToOne

    User user;

    @Column(nullable = false)
    String todoText;

    @Column(nullable = false)
    boolean isDone;

    public ToDoItem() {
    }

    public ToDoItem(String todoText, boolean isDone, User user) {
        this.todoText = todoText;
        this.isDone = isDone;
        this.user = user;
    }
}
