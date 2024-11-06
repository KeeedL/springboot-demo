FROM maven:3.8.4-openjdk-17 AS builder
COPY /funds-processor /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package


FROM openjdk:17-jdk-alpine
COPY --from=builder ./usr/src/app/target/funds-processor-*.jar /usr/app/funds-processor.jar
WORKDIR /usr/app
CMD ["java", "-jar", "funds-processor.jar"]
