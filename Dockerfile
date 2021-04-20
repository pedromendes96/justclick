FROM openjdk:11
COPY ./ /app/
WORKDIR /app
RUN echo "" > /app/logs/clicks.json
COPY src/main/resources/docker-application.properties /app/application.properties
ENV SPRING_CONFIG_LOCATION=file:///app/application.properties
ENTRYPOINT ["java","-jar","/app/target/project-1.0.jar"]
