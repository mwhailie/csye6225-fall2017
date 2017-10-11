package com.csye6225.demo.controllers;

import com.csye6225.demo.pojos.Task;
import com.csye6225.demo.pojos.User;
import com.csye6225.demo.repositories.UserRepository;
import com.csye6225.demo.repositories.TaskRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

import java.util.Date;

@Controller
public class TaskController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);


    @RequestMapping(value = "/tasks", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
        public String createTasks(@RequestBody String sTask, Principal principal, HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_CREATED);
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        Task task = gson.fromJson(sTask,Task.class);
        User user = userRepository.findByName(principal.getName());
        task.setUser(user);
        taskRepository.save(task);
        jsonObject.addProperty("message", "task: "+task.getId());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String updateTasks(@PathVariable("id") String id, @RequestParam String description) {
        JsonObject jsonObject = new JsonObject();
        Task task = taskRepository.findOne(id);
        task.setDescription(description);
        taskRepository.save(task);
        jsonObject.addProperty("task_id", task.getId());
        jsonObject.addProperty("description", task.getDescription());
        return jsonObject.toString();
    }


    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteTask (@PathVariable String id, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        taskRepository.delete(id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "Delete task " + id +" successfully! ");
        return jsonObject.toString();
    }

}
