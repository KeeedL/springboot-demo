FROM maven:3.8.4-openjdk-17 AS builder
COPY /web-gateway /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package


FROM openjdk:17-jdk-alpine
COPY --from=builder ./usr/src/app/target/web-gateway-*.jar /usr/app/web-gateway.jar
WORKDIR /usr/app
CMD ["java", "-jar", "web-gateway.jar"]
