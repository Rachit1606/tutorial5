FROM openjdk:17-oracle
WORKDIR /app
COPY target/tutorial5-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080
