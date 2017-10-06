#!/bin/bash
#delete a stack instance

instanceId=$(aws cloudformation describe-stack-resources --stack-name "mystack" | jq -r '.StackResources[0].PhysicalResourceId')

#enable-api-termination
aws ec2 modify-instance-attribute --no-disable-api-termination --instance-id $instanceId

#delete the stack named mystack
aws cloudformation delete-stack --stack-name $1
