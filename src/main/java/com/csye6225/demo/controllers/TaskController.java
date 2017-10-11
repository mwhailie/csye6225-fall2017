package com.csye6225.demo.controllers;

import com.csye6225.demo.pojos.Task;
import com.csye6225.demo.repositories.TaskRepository;
import com.csye6225.demo.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public String createTasks(@RequestBody String sTask, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        Task task = gson.fromJson(sTask,Task.class);
        task.setId(UUID.randomUUID().toString());
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
