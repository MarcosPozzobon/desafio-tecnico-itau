FROM openjdk:17-jdk-slim as build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar desafio-tecnico-itau.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "desafio-tecnico-itau.jar"]
