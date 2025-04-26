#!/bin/bash

JAR_FILE="target/JavaBot-Start-1.0-SNAPSHOT-jar-with-dependencies.jar"
LOG_FILE="output.log"
PID_FILE="java-bot.pid"

install() {
    # Установка Java
    if ! command -v java &> /dev/null; then
        echo "Java не найден. Устанавливаем Java..."
        sudo apt update
        sudo apt install -y default-jdk
        echo "Java установлена."
    else
        echo "Java уже установлена."
    fi

    # Установка Maven
    if ! command -v mvn &> /dev/null; then
        echo "Maven не найден. Устанавливаем Maven..."
        sudo apt update
        sudo apt install -y maven
        echo "Maven установлен."
    else
        echo "Maven уже установлен."
    fi
}

build() {
    if [ ! -f "pom.xml" ]; then
        echo "pom.xml не найден. Убедитесь, что вы находитесь в корневой директории проекта."
        exit 1
    fi

    echo "Запускаем сборку проекта..."
    mvn clean package

    if [ $? -eq 0 ]; then
        echo "Сборка завершена успешно."
    else
        echo "Сборка завершилась с ошибкой."
        exit 1
    fi
}

start() {
    if [ -f "$PID_FILE" ]; then
        echo "JavaBot уже запущен (PID $(cat $PID_FILE))."
        exit 1
    fi

    nohup java -jar "$JAR_FILE" > "$LOG_FILE" 2>&1 &
    echo $! > "$PID_FILE"
    echo "JavaBot запущен в фоновом режиме. Логи записываются в $LOG_FILE"
}

stop() {
    if [ -f "$PID_FILE" ]; then
        kill $(cat "$PID_FILE")
        rm "$PID_FILE"
        echo "JavaBot остановлен."
    else
        echo "JavaBot не запущен."
    fi
}

# Проверка аргументов
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    build)
        build
        ;;
    install)
        install
        ;;
    *)
        echo "Использование: $0 {start|stop|build|install}"
        exit 1
        ;;
esac
