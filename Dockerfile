FROM openjdk:21-jdk
VOLUME /app
ADD target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]