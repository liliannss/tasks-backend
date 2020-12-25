FROM tomcat:8.5.50-jdk8-open.jdk8-open

ARG WAR_FILE 
ARG CONTEXT 

#COPY frontend/target/tasks.war /usr/local/tomcat/webapps/tasks.war

COPY ${WAR_FILE} /usr/local/tomcat/webapps/${CONTEXT}.war