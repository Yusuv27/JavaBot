#!/bin/bash

git reset --hard

# Обновить локальный репозиторий из удалённого
git pull

# Собрать проект Maven
mvn clean package

# Построить Docker-образ с тегом lanciot
docker build -t infobot .

# Запустить контейнер в фоне, удалить после остановки
docker run -d --rm --network host infobot

# Показать запущенные контейнеры
docker ps
