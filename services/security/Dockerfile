FROM eclipse-temurin:21-jre

ARG JAR_FILE=target/security-*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar

EXPOSE 8085
ENTRYPOINT ["java","-jar","/app/app.jar"]