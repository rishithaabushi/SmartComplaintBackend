FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
COPY settings.xml /root/.m2/settings.xml

RUN for i in 1 2 3 4 5; do \
    mvn -B -ntp clean package -DskipTests && break; \
    if [ "$i" -eq 5 ]; then exit 1; fi; \
    echo "Maven download failed. Waiting 30 seconds before retry..."; \
    sleep 30; \
done