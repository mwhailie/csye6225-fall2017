package com.csye6225.demo.controllers;

import com.amazonaws.services.sns.AmazonSNSClient;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class ResetPasswordController {
    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String resetPassword(HttpServletResponse response) {
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        JsonObject jsonObject = new JsonObject();

        AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));

        CreateTopicRequest createTopicRequest = new CreateTopicRequest("resetPassword");
        CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);

        String topicArn = "arn:aws:sns:us-east-1:123456789012:resetPassword";

        String msg = "My text published to SNS topic with email endpoint";
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);

        response.setStatus(HttpServletResponse.SC_OK);
        return jsonObject.toString();
    }
}
