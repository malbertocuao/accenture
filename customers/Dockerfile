FROM openjdk:17-jdk-slim

MAINTAINER ideasit.net

COPY target/customers-0.0.1-SNAPSHOT.jar customers-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "customers-0.0.1-SNAPSHOT.jar"]

# docker build . -t malbertocuao/bank-customers:v0.0.1
# docker build . -t malbertocuao/bank:customers-v0.0.1