WAR_NAME= "todo-0.0.1-SNAPSHOT.war"
TOMCAT_HOME=/home/rafal-zwiazek/projects/apache-tomcat-11.0.0-M5

mvn clean install
cp "target/todo-0.0.1-SNAPSHOT.war" "$TOMCAT_HOME/webapps"
open "http://localhost:8080/todo-0.0.1-SNAPSHOT/"
