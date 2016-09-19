package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RdDvls on 9/19/16.
 */
@RestController
public class WebToDoAppJSONController {
    @Autowired
    ToDoRepository todos;
    @Autowired
    UserRepository users;

    @RequestMapping(path="/todos.json", method = RequestMethod.GET)
    public List<ToDoItem> getAllToDo(){
        ArrayList<ToDoItem> todoList = new ArrayList<ToDoItem>();
        Iterable<ToDoItem> allToDo = todos.findAll();
        for(ToDoItem item : allToDo){
            todoList.add(item);
        }
        return todoList;
    }

    @RequestMapping(path="/addtodo.json", method = RequestMethod.POST)
    public List<ToDoItem> addtodo(HttpSession session, @RequestBody ToDoItem todoItem)throws Exception{
        User user = (User)session.getAttribute("user");
        if(user == null){
            throw new Exception("Unable to add todo without an active user");
        }
        todoItem.user = user;
        todos.save(todoItem);
        return getAllToDo();
    }

    @RequestMapping(path = "/deletetodo.json", method = RequestMethod.GET)
    public String deletetodo(Model model, Integer todoID){
        if(todoID != null){
            todos.delete(todoID);
        }
        return "redirect:/";
    }



}
