FROM openjdk:17-oracle

WORKDIR /app2

COPY target/tutorial5-1.0.jar app2.jar
CMD ["java", "-jar", "app2.jar"]
EXPOSE 8080
