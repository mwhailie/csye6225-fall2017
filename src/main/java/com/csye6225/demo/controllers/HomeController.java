package com.csye6225.demo.controllers;


//our's
import com.csye6225.demo.pojos.User;
import com.csye6225.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//Professor's
import com.google.gson.JsonObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.Date;

/**
 * SHIRUI_WANG,001226459, wang.shirui@husky.neu.edu
 * WENHE_MA, 001238705, ma.wenhe@husky.neu.edu
 * YUTING_JING, 00121590 , jing.yu@husky.neu.edu
 * HAOAN_YAN, 001220895, yan.hao@husky.neu.edu
 */

@Controller
public class HomeController {
  @Autowired
  private UserRepository userRepository;


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

  @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String test() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "authorized for /test");
    return jsonObject.toString();
  }

<<<<<<< HEAD
  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String loginPost(@RequestBody String sUser) {
    Gson gson = new Gson();
    User user = gson.fromJson(sUser,User.class);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "Hi "+user.getName());
    if(userRepository.findByEmail(user.getEmail())!=null && user.getPassword().equals(userRepository.findByEmail(user.getEmail()).getPassword())){
      jsonObject.addProperty("message", "Hi "+user.getName()+"Login successfully! ");

    }
    else jsonObject.addProperty("message", "Login failure! ");
      return jsonObject.toString();
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
  @ResponseBody
  public String logout(@RequestParam String userStr) {
    Gson gson = new Gson();
    User user = gson.fromJson(userStr,User.class);
    return "Hi, "+user.getName()+", this is logout";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String registerPost(@RequestBody String sUser) {
    Gson gson = new Gson();
    User user = gson.fromJson(sUser,User.class);
    userRepository.save(user);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "Hi "+user.getName()+"Register successfully! ");
=======
  @RequestMapping(value = "/testPost", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String testPost() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "authorized for /testPost");
>>>>>>> 2ec0e99ed8939649f32402b8ad08bfdf9a8d20c6
    return jsonObject.toString();
  }

}
