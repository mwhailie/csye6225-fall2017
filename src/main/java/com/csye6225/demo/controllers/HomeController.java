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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
