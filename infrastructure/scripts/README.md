# scripts

## Requirement

Assortment of scripts that run on the AWS Command Line Interface (CLI). To run these script, you need to first install and configure AWS Command Line Interface (CLI).

## Description

### `create-security-group.sh`
Create security groups.

### `delete-security-group.sh`
Delete security groups.

### `launch-ec2-instance.sh`
- Create security group.
- Configure security group.
- Launch EC2 Instance.
- Wait for instance to be in running state.
- Retrieving instance’s public IP address.
- Add/Update type A resource record set ec2.YOUR_DOMAIN_NAME.me in the Route 53 zone for your domain with the IP of the newly launched EC2 instance. TTL 60 seconds.

### `delete-security-group.sh`
Terminate EC2 instance. Will take the instance-id as command line argument.

`delete-security-group.sh <instance-id>`

### `create-csye6225-cloudformation-stack.sh`
Create a CloudFormation stack that contains following resources:
- Security Group
- EC2 Instance with the specifications below
- Resource Record in the Route 53 zone for your domain with the IP of the newly launched EC2 instance

`create-csye6225-cloudformation-stack.sh <stack-name>`

### `delete-csye6225-cloudformation-stack.sh`
Terminate EC2 instance. Will take the stack name as command line argument.

`delete-csye6225-cloudformation-stack.sh <stack-name>`
