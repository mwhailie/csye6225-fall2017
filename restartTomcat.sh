sudo service tomcat7 stop
cd /var/lib/tomcat7
ls -al
cd /var/lib/tomcat7/webapps
ls -al
sudo rm -rf ROOT
sudo service tomcat7 start
