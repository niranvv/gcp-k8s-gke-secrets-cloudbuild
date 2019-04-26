#
# Build stage
#
FROM openjdk:8-jdk-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/employee-0.0.1-SNAPSHOT.war /usr/local/lib/employee.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/employee.war"]