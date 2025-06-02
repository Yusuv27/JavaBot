FROM openjdk:17-jdk-slim

# Рабочий каталог внутри контейнера
WORKDIR /app

# Копируем jar-файл в контейнер
COPY target/JavaBot-Start-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Команда запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]

