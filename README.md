# User Subscription Service

Микросервис на Java 17 и Spring Boot 3 для управления пользователями и их подписками.

## 🚀 Возможности

- CRUD для пользователей
- CRUD для подписок
- Назначение и удаление подписок у пользователей
- Получение всех пользователей и подписок
- Получение топ-3 популярных подписок
- Swagger-документация

## 🧰 Стек технологий

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Docker + Docker Compose
- Lombok
- SLF4J
- Swagger / OpenAPI

---

## ⚙️ Как запустить

### 📦 Через Docker

```bash
mvn clean package 
docker-compose up --build
```

## Swagger

http://localhost:8083/swagger-ui/index.html

