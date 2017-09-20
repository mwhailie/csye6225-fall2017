package com.csye6225.demo.controllers;


import com.csye6225.demo.pojos.User;
import com.csye6225.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import com.google.gson.JsonObject;

/**
 * SHIRUI_WANG,001226459, wang.shirui@husky.neu.edu
 * WENHE_MA, 001238705, ma.wenhe@husky.neu.edu
 * YUTING_JING, 001221590 , jing.yu@husky.neu.edu
 * HAOAN_YAN,0012
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
    jsonObject.addProperty("message", "Hello CSYE 6225!!!");

    return jsonObject.toString();
  }


//  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain")
//  @ResponseBody
//  public String login() {
//
//
//    return "this is login!";
//  }
//
//  @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
//  @ResponseBody
//  public String logout(@RequestParam String userStr) {
//    Gson gson = new Gson();
//    User user = gson.fromJson(userStr,User.class);
//    return "Hi, "+user.getName()+", this is logout";
////    return "Hi, "+userStr+", this is logout";
//  }
  @RequestMapping(value = "/register", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String register(@RequestParam String name
          , @RequestParam String password
          , @RequestParam String email) {

    User user = new User();
    user.setName(name);
    user.setPassword(password);
    user.setEmail(email);
    userRepository.save(user);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "Welcome!");
    return jsonObject.toString();
  }

}
