#!/bin/bash

# Объявление цветов
RED="\033[1;31m"     # Красный
GREEN="\033[1;32m"   # Зеленый
YELLOW="\033[1;33m"  # Желтый
BLUE="\033[1;34m"    # Синий
MAGENTA="\033[1;35m" # Пурпурный
CYAN="\033[1;36m"    # Голубой
WHITE="\033[1;37m"   # Белый
RESET="\033[1;0m"    # Сброс цвета

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
    echo "${BLUE}[INFO]${RESET} Удаляем локальное изменение на сервере: применяем git reset --hard HEAD"
    git reset --hard HEAD

    if [ $? -eq 0 ]; then
        echo "${GREEN}Удаление завершена успешно.${RESET}"
    else
        echo "${RED}Удаление завершилась ошибкой.${RESET}"
        exit 1
    fi

    echo "${BLUE}[INFO]${RESET} Обновляем код на сервере: применяем git pull"

    git pull

    if [ $? -eq 0 ]; then
        echo "${GREEN}Обновление завершена успешно.${RESET}"
    else
        echo "${RED}Удаление завершилась ошибкой.${RESET}"
        exit 1
    fi

    if [ ! -f "pom.xml" ]; then
        echo "pom.xml не найден. Убедитесь, что вы находитесь в корневой директории проекта."
        exit 1
    fi

    echo "Запускаем сборку проекта..."
    mvn clean package

    if [ $? -eq 0 ]; then
        echo "${GREEN}Сборка завершена успешно.${RESET}"
    else
        echo "${RED}Сборка завершилась с ошибкой.${RESET}"
        exit 1
    fi

    echo "${BLUE}[INFO]${RESET} Перезапускаем бота"
    echo "${BLUE}[INFO]${RESET} Останавливаем текущего бота"
    ./run_java_bot.sh stop

    if [ $? -eq 0 ]; then
        echo "${GREEN}Бот остановлен успешно.${RESET}"
    else
        echo "${RED}Остановка завершилась с ошибкой.${RESET}"
        exit 1
    fi

    echo "${BLUE}[INFO]${RESET} Запускаем бота"

        if [ $? -eq 0 ]; then
            echo "${GREEN}Бот запущен успешно.${RESET}"
        else
            echo "${RED}Запуск бота завершился с ошибкой.${RESET}"
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