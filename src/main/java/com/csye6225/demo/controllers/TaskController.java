package com.csye6225.demo.controllers;

import com.csye6225.demo.pojos.Task;

import com.csye6225.demo.pojos.User;
import com.csye6225.demo.repositories.UserRepository;

import com.csye6225.demo.repositories.TaskRepository;
import com.csye6225.demo.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.UUID;

@Controller
public class TaskController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping(value = "/tasks", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
        public String createTasks(@RequestBody String sTask, Principal principal, HttpServletRequest request, HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        Task task = gson.fromJson(sTask,Task.class);

        User user;
        try{
            user = userRepository.findByName(principal.getName());
        }catch (Exception e){
            jsonObject.addProperty("message", "user does not exist");
            return jsonObject.toString();
        }
        task.setUser(user);
        taskRepository.save(task);
        jsonObject.addProperty("message", "task: "+task.getId());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String updateTasks(@PathVariable("id") String id, Principal principal,@RequestParam String description) {
        JsonObject jsonObject = new JsonObject();
        Task task ;
        try{
            task = taskRepository.findOne(id);
        }catch (Exception e){
            jsonObject.addProperty("message", "task does not exist");
            return jsonObject.toString();
        }
        User user;
        try{
            user = userRepository.findByName(principal.getName());
        }catch (Exception e){
            jsonObject.addProperty("message", "user does not exist");
            return jsonObject.toString();
        }
        if(user != task.getUser()){
            jsonObject.addProperty("message", "user does not match");
            return jsonObject.toString();
        }
        task.setDescription(description);
        taskRepository.save(task);
        jsonObject.addProperty("task_id", task.getId());
        jsonObject.addProperty("description", task.getDescription());
        return jsonObject.toString();
    }


    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteTask (@PathVariable String id, Principal principal,HttpServletResponse response) {
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        JsonObject jsonObject = new JsonObject();
        Task task ;
        try{
            task = taskRepository.findOne(id);
        }catch (Exception e){
            jsonObject.addProperty("message", "task does not exist");
            return jsonObject.toString();
        }
        User user;
        try{
            user = userRepository.findByName(principal.getName());
        }catch (Exception e){
            jsonObject.addProperty("message", "user does not exist");
            return jsonObject.toString();
        }
        if(user != task.getUser()){
            jsonObject.addProperty("message", "user does not match");
            return jsonObject.toString();
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        taskRepository.delete(id);
        jsonObject = new JsonObject();
        jsonObject.addProperty("message", "Delete task " + id +" successfully! ");
        return jsonObject.toString();
    }

}
