# CSYE6225

This is a web application based on Spring Boot, RESTful API and deployed on AWS.

## Team Members

* **Shirui Wang** , *001226459* , wang.shirui@husky.neu.edu
* **Wenhe Ma** , *001238705* , ma.wenhe@husky.neu.edu
* **Yuting Jing** , *001221590* , jing.yu@husky.neu.edu
* **Haoan Yan** , *001220895* , yan.hao@husky.neu.edu

## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management
* [Travis](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Set up

To run this project on local environment, you may have to change the following code in application.properties file.
```
spring.datasource.url=jdbc:mysql://localhost:3306/db_csye6225
spring.datasource.username=root
spring.datasource.password=root
```
as your database setting

Then execute the following command in your mysql
```
create database db_csye6225;
```

## Make Unauthenticated HTTP Request

Execute following command on your bash shell
``` 
$ curl https://csye6225-fall2017-mawenhe.me/csye6225app
```

### Expected Response:
```
{"message":"you are not logged in!!!"}
```

## Authenticate for HTTP Request

Execute following command on your bash shell
```
$ curl -X POST \
  http://csye6225-fall2017-mawenhe.me/csye6225app \
  -H 'authorization: Basic MUAxOjE=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 9362bd7c-8dc6-22b2-6e9f-60696512376e'
```
where *authorization* is given to a registered user with username and password.

### Expected Response:
 ```
 {"message":"you are logged in. current time is Tue Sep 19 20:03:49 EDT 2017"}
 ```

## GitHub Releases
* [v6.0](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v6.0) - Assignment 8
* [v5.0](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v5.0) - Assignment 7
* [v4.0](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v4.0) - Assignment 6
* [v3.0](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v3.0) - Assignment 5
* [v2.1](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v2.1) - Assignment 4
* [v1.0](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v1.0) - Assignment 3


## TravisCI

* [TravisCI](https://travis-ci.com/mwhailie/csye6225-fall2017/builds/) - TravisCI builds
