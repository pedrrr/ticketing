FROM eclipse-temurin:21-jre

ARG JAR_FILE=target/gateway-*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar

EXPOSE 8090
ENTRYPOINT ["java","-jar","/app/app.jar"]