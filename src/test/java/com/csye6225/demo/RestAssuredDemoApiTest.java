package com.csye6225.demo;

import com.csye6225.demo.controllers.HomeController;
import com.csye6225.demo.controllers.TaskController;
import io.restassured.*;
import io.restassured.internal.http.HTTPBuilder;
import org.apache.http.client.ClientProtocolException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RestAssuredDemoApiTest {


  @Test
  public void testGetHomePage() throws URISyntaxException {
    RestAssured.when().get(new URI("http://localhost:8080/")).then().statusCode(200);
  }

  @Test
  public void testCreateTask() throws Exception {
    RestAssured.given().auth().basic("user", "password").expect().statusCode(201).when().post(new URI("http://localhost:8080/tasks"));
  }
}
