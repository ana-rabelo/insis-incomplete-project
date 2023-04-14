FROM maven:3.9.0-eclipse-temurin-17-alpine AS maven-build
WORKDIR /usr/src/product-query
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine AS app-runtime
WORKDIR /app
COPY --from=maven-build /usr/src/product-query/target/*.jar ./product-query.jar
ENTRYPOINT ["java", "-jar", "product-query.jar"]