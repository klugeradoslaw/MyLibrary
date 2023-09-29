FROM maven:3.8.5-openjdk-17-slim AS MAVEN_BUILD
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package

FROM openjdk:17.0.2-slim-buster
EXPOSE 8080
COPY --from=MAVEN_BUILD /target/MyLibrary-*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]