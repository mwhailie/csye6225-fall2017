# CSYE6225

## Team Members

* **Shirui Wang** , *001226459* , wang.shirui@husky.neu.edu
* **Wenhe Ma** , *001238705* , ma.wenhe@husky.neu.edu
* **Yuting Jing** , *001221590* , jing.yu@husky.neu.edu
* **Haoan Yan** , *001220895* , yan.hao@husky.neu.edu


## Set up

To run this project, you may have to change the following code in application.properties
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
``` bash
$ curl http://localhost:8080
```

### Expected Response:
```
{"message":"you are not logged in!!!"}
```

## Authenticate for HTTP Request

Execute following command on your bash shell
``` bash
$ curl -u user:password http://localhost:8080
```

where *user* is the username and *password* is the password.

### Expected Response:
 ```
 {"message":"you are logged in. current time is Tue Sep 19 20:03:49 EDT 2017"}
 ```

## GitHub Release
* [v2.1](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v2.1) - Assignment 4
* [v1,0](https://github.com/mwhailie/csye6225-fall2017/releases/tag/v1.0) - Assigment 3


## TravisCI

* [TravisCI](https://travis-ci.com/mwhailie/csye6225-fall2017/builds/) - TravisCI builds
