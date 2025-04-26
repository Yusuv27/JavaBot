#!/bin/bash

JAR_FILE="target/JavaBot-Start-1.0-SNAPSHOT.jar"
LOG_FILE="output.log"
PID_FILE="java-bot.pid"

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
    *)
        echo "Использование: $0 {start|stop}"
        exit 1
        ;;
esac
