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
