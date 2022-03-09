# The first line is always FROM - this defines a base image: I need Java 8 and Tomcat to run
FROM tomcat:8.0-jre8

LABEL maintainer="Mark Deane"

# let's imagine that the WAR file already exists...
# we want to pass the WAR file to Tomcat's webapps directory
ADD target/FrontController.war /usr/local/tomcat/webapps

# the EXPOSE command informs Docker that the container listens on some specified port at runtime
EXPOSE 8080

# the CMD instruction is typically the last line of the Dockker file and specifies what to runwhen the container is run
# in our case the Tomcat server is started by running this shell script
CMD ["catalina.sh", "run"]
