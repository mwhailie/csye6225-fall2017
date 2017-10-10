package com.csye6225.demo.controllers;


//our's
import com.csye6225.demo.pojos.Task;
import com.csye6225.demo.pojos.User;
import com.csye6225.demo.repositories.TaskRepository;
import com.csye6225.demo.repositories.UserRepository;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

//Professor's
import com.google.gson.JsonObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * SHIRUI_WANG,001226459, wang.shirui@husky.neu.edu
 * WENHE_MA, 001238705, ma.wenhe@husky.neu.edu
 * YUTING_JING, 001221590 , jing.yu@husky.neu.edu
 * HAOAN_YAN, 001220895, yan.hao@husky.neu.edu
 */

@Controller
public class HomeController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TaskRepository taskRepository;

  private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String welcome() {

    JsonObject jsonObject = new JsonObject();

    if (SecurityContextHolder.getContext().getAuthentication() != null
            && SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
      jsonObject.addProperty("message", "you are not logged in!!!");
    } else {
      jsonObject.addProperty("message", "you are logged in. current time is " + new Date().toString());
    }

    return jsonObject.toString();
  }

  @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String loginPost(@RequestBody String sUser) {
    Gson gson = new Gson();
    User user = gson.fromJson(sUser, User.class);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "Hi " + user.getName());
    User check = userRepository.findByEmail(user.getEmail());
    if (check != null && BCrypt.checkpw(user.getPassword(), check.getPassword())) {
      jsonObject.addProperty("message", "Hi " + check.getName() + " Login successfully! ");

    } else jsonObject.addProperty("message", "Login failure! ");
    return jsonObject.toString();
  }

  @RequestMapping(value = "/user/logout", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
  @ResponseBody
  public String logout(@RequestParam String userStr) {
    Gson gson = new Gson();
    User user = gson.fromJson(userStr, User.class);
    return "Hi, " + user.getName() + ", this is logout";
  }

  @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String registerPost(@RequestBody String sUser) {
    Gson gson = new Gson();
    User user = gson.fromJson(sUser, User.class);
    String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    user.setPassword(pw_hash);
    User user_db = userRepository.findByEmail(user.getEmail());
    JsonObject jsonObject = new JsonObject();

    if (user_db == null) {
      userRepository.save(user);
      jsonObject.addProperty("message", "Hi " + user.getName() + ", register successfully! ");
    } else {
      jsonObject.addProperty("message", "Register failure!  " + user.getName() + " already exists! ");
    }
    return jsonObject.toString();
  }


}
