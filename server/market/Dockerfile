# Start with a base image containing Java runtime
FROM java:8

# Add Author info
LABEL maintainer="dj5427@naver.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/market-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} sogaething.jar

# Run the jar file
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/sogaething.jar"]