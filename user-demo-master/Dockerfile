FROM openjdk:8-jdk-alpine 
ENV spring.datasource.url='jdbc:postgresql://localhost:5432/micro?useSSL=false'
ENV spring.datasource.username='postgres' 
ENV spring.datasource.password='admin' 
ENV spring.jpa.properties.hibernate.default_schema='demo' 
ARG JAR_FILE 
ADD ${JAR_FILE} app.jar 
ENTRYPOINT ["java","-jar","/app.jar"]