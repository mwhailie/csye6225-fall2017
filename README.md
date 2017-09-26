#CSYE6225

## Team Members

SHIRUI_WANG,001226459, wang.shirui@husky.neu.edu
WENHE_MA, 001238705, ma.wenhe@husky.neu.edu
YUTING_JING, 00121590 , jing.yu@husky.neu.edu
HAOAN_YAN, 001220895, yan.hao@husky.neu.edu


## Set Up Mysql
```
sudo mysql --password
create database db_csye6225;
create user 'root'@'localhost' identified by 'root';
grant all on db_csye6225.* to 'root'@'localhost';
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
