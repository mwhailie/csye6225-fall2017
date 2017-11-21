
#!/bin/bash
sudo /etc/init.d/tomcat8 restart

sudo python ./awslogs-agent-setup.py -n -r us-east-1 --region us-east-1 -c ./awslogs.conf

sudo service awslogs restart