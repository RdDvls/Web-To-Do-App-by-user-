package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RdDvls on 9/15/16.
 */
@Controller
public class WebToDoAppController {
    @Autowired
    UserRepository users;

    @Autowired
    ToDoRepository todos;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, String todoText) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        User savedUser = (User)session.getAttribute("user");
        List<ToDoItem> todoList = new ArrayList<ToDoItem>();
        if(savedUser != null){
            todoList = todos.findToDoByUser(savedUser);
        }
        model.addAttribute("todos",todoList);
        return "home";
    }



    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            throw new Exception("Create a user!!!");
        } else if (!password.equals(user.getPassword())) {
                throw new Exception("Incorrect password");
            }
            session.setAttribute("user", user);
            return "redirect:/";
        }

    @RequestMapping(path = "/newuser",method = RequestMethod.POST)
    public String newuser(HttpSession session, String userName, String password)throws Exception{
        User user = users.findFirstByName(userName);
        if(user == null) {
            user = new User(userName, password);
            users.save(user);
        }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping(path = "/addToDo", method = RequestMethod.POST)
    public String addToDo (HttpSession session, String todoText, boolean isDone){
        User user = (User) session.getAttribute("user");
        ToDoItem item = new ToDoItem(todoText,isDone,user);
        todos.save(item);
        return "redirect:/";
    }
    @RequestMapping(path = "/deleteToDo", method = RequestMethod.GET)
    public String deleteTodo(Model model, Integer todoID){
        if(todoID != null){
            todos.delete(todoID);
        }
        return "redirect:/";
    }

}
