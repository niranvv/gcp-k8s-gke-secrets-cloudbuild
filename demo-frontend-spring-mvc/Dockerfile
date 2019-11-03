#
# Build stage
#
FROM maven:3.5-jdk-8-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package 
# -DskipTests

#
# Package stage
#
FROM openjdk:8
COPY --from=build /home/app/target/gs-serving-web-content-0.1.0.jar /usr/local/lib/frontend-mvc.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/frontend-mvc.war"]