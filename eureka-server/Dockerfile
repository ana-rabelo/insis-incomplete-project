FROM maven:3.9.0-eclipse-temurin-17-alpine AS maven-build
WORKDIR /usr/src/eureka-server
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=maven-build /usr/src/eureka-server/target/*.jar ./eureka-server.jar
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]