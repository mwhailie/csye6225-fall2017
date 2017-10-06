#!/bin/bash
#create a stack instance

aws cloudformation create-stack --stack-name $1 --template-body file:///home/hailie/Documents/csye6225/Assignment4/csye6225-spring-boot-starter-webapp/infrastructure/cloudformation/simple-ec2-instance-securitygroup-cloudformation-stack.json

