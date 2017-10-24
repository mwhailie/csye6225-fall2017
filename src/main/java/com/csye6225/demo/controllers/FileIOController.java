package com.csye6225.demo.controllers;

import com.csye6225.demo.pojos.Attachment;
import com.csye6225.demo.pojos.Task;
import com.csye6225.demo.repositories.AttachmentRepository;
import com.csye6225.demo.repositories.TaskRepository;
import com.csye6225.demo.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.List;

@Controller
public class FileIOController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;


    private final static Logger logger = LoggerFactory.getLogger(FileIOController.class);

    @RequestMapping(value = "/tasks/{id}/attachments", method = RequestMethod.POST)
    @ResponseBody
    public String attachFile(@PathVariable String id,  @RequestParam("file") MultipartFile file) throws Exception {
        Task task = taskRepository.findOne(id);

        System.out.println(task);

        JsonObject jsonObject = new JsonObject();

        //Gson gson = new Gson();
       // Attachment attachment = gson.fromJson("",Attachment.class);

        Attachment attachment = new Attachment();
        String folder = "/myFile";
        String relativePath = System.getProperty("user.dir");
        //getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String filePath = saveFile(file, relativePath + folder);

        attachment.setPath(filePath);
        attachment.setTask(task);
        attachmentRepository.save(attachment);

        jsonObject.addProperty("path", attachment.getPath());
        jsonObject.addProperty("task", attachment.getTask().toString());
        jsonObject.addProperty("attachment_id", attachment.getId());
        //jsonObject.addProperty("description", task.getDescription());
        return jsonObject.toString();
    }

    //save file
    private String saveFile(MultipartFile file, String path) throws IOException {

        if(!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);

            //if path not exist, create the folder
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            String finalPath = path + File.separator + filename;

            //transfer the files into the target folder
            file.transferTo(new File(finalPath));
            return finalPath;
        } else {
            return "file not exist";
        }
    }


    @RequestMapping(value = "/tasks/{id}/attachments", method = RequestMethod.GET)
    @ResponseBody
    public String listFile(@PathVariable String id) throws Exception {
        Task task = taskRepository.findOne(id);
        //List<Attachment> attachList = task.getAttachmentList();
        List<Attachment> attachList = attachmentRepository.findByTask(task);

        JsonArray array = new JsonArray();
        if (attachList.size() > 0) {
            for (int i = 0; i < attachList.size(); i++) {
                try {
                    JsonObject e = new JsonObject();
                    Attachment curAttachment = attachList.get(i);
                    e.addProperty("id", curAttachment.getId());
                    e.addProperty("path", curAttachment.getPath());
                    array.add(e);
                } catch (Exception e) {
                    logger.error("Error in deserializing received constraints", e);
                    return null;
                }
            }
        }
        return array.toString();
    }

    @RequestMapping(value = "/tasks/{id}/attachments/{idAttachments}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFile(@PathVariable String id, @PathVariable String idAttachments, HttpServletResponse response) throws Exception {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        Attachment attachment = attachmentRepository.findOne(new Integer(idAttachments));
        Task task = taskRepository.findOne(id);
        String filePath = attachment.getPath();
        JsonObject jsonObject = new JsonObject();
        boolean deleteSuccess = false;
        if(attachment.getTask() == task) {
            deleteSuccess = delete(filePath);
            if(deleteSuccess){
                attachmentRepository.delete(new Integer(idAttachments));
            }
            jsonObject.addProperty("message", deleteSuccess ? "Delete Successfully" : "Delete Failed: no such file");
        }
        else{
            jsonObject.addProperty("message", "Delete Failed: Attachment not match the task");
        }
        return jsonObject.toString();
    }

    private static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("[log] Delete File failed:" + fileName + "not exist！");
            return false;
        } else {
            if (file.isFile())
                return file.delete();
        }
        return false;

    }


}
