FROM openjdk:19-alpine
FROM yannoff/maven:3.8.5-openjdk-19

WORKDIR /app 

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 9090

CMD ["java", "-jar", "/app/target/grooming-0.1.0.jar"]