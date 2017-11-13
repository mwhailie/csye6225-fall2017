package com.csye6225.demo.controllers;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.csye6225.demo.pojos.User;
import com.csye6225.demo.repositories.UserRepository;
import com.google.gson.JsonObject;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class ResetPasswordController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(@RequestBody String email, HttpServletResponse response) {
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        JsonObject jsonObject = new JsonObject();


        User user;
        try{
            user = userRepository.findByEmail(email);
        }catch (Exception e){
            jsonObject.addProperty("message", "user does not exist");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return jsonObject.toString();
        }

        AmazonSNSClient snsClient = new AmazonSNSClient();
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));

        CreateTopicRequest createTopicRequest = new CreateTopicRequest("resetPassword");
        CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
        createTopicResult.getTopicArn();
//        String topicArn = "arn:aws:sns:us-east-1:123456789012:resetPassword";
        String topicArn = createTopicResult.getTopicArn();

        String msg = email;
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);

        response.setStatus(HttpServletResponse.SC_OK);
        return jsonObject.toString();
    }
}
