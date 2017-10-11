package com.csye6225.demo.controllers;

import com.csye6225.demo.pojos.Attachment;
import com.csye6225.demo.pojos.Task;
import com.csye6225.demo.repositories.AttachmentRepository;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileIOController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;


    private final static Logger logger = LoggerFactory.getLogger(FileIOController.class);

    @RequestMapping(value = "/tasks/{id}/attachment", method = RequestMethod.POST)
    @ResponseBody
    public String attachFile(@PathVariable String id,  @RequestParam("file") MultipartFile file) throws Exception {
        Task task = taskRepository.findOne(id);
        System.out.println("66666666666666666666666666666666666666");
        System.out.println(task);
        JsonObject jsonObject = new JsonObject();

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

    @RequestMapping(value="/upload",method=RequestMethod.POST)
    public String upload(
            @RequestParam("file") MultipartFile file) throws Exception {

        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            //String path = request.getServletContext().getRealPath("/images/");
            String path = "/home/haoan/Documents/myFile";
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            return "success";
        } else {
            return "error";
        }

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

}
