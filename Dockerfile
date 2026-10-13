
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

RUN for i in 1 2 3 4 5; do \
    mvn -B -ntp clean package -DskipTests && break; \
    if [ "$i" -eq 5 ]; then exit 1; fi; \
    echo "Maven download failed. Waiting 30 seconds before retry..."; \
    sleep 30; \
done

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
