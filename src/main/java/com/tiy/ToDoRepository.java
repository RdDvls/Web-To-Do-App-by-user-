package com.tiy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by RdDvls on 9/15/16.
 */
public interface ToDoRepository extends CrudRepository<ToDoItem, Integer>{
//    @Query("SELECT td From TodoItem td WHERE td.user LIKE ?1%")
    List<ToDoItem> findToDoByUser(User user);
}
