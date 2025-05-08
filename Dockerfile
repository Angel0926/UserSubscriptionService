# Используем официальный образ с Java 17
FROM eclipse-temurin:17-jdk

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR-файл внутрь контейнера
COPY target/UserSubscriptionService-1.0-SNAPSHOT.jar app.jar

# Указываем порт, который слушает приложение внутри контейнера
EXPOSE 8083

# Устанавливаем команду запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
