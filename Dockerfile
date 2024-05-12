FROM jelastic/maven:3.9.5-openjdk-21 as builder
WORKDIR /app
COPY . .
#RUN chmod +x maven package
RUN maven package
FROM openjdk:21
WORKDIR /app
COPY . .
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]