WAR_NAME= "todo-0.0.1-SNAPSHOT.war"
TOMCAT_HOME=/Users/rafalzwiazek/Desktop/Tomcat11

mvn clean install
cp "target/todo-0.0.1-SNAPSHOT.war" "$TOMCAT_HOME/webapps"
open "http://localhost:8080/todo-0.0.1-SNAPSHOT/"


