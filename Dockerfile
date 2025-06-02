FROM openjdk:17-jdk-slim

# Рабочий каталог внутри контейнера
WORKDIR /app

# Копируем jar-файл в контейнер
COPY target/JavaBot-Start-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

COPY src/main/java/org/example/FileScheduler /app/src/main/java/org/example/FileScheduler
# Команда запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]

