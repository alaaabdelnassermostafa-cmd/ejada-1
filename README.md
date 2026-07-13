# Library Catalog System

A Spring Boot library catalog system built as an intern project, featuring full CRUD operations, Kafka event streaming, and Docker support.

[![Docker Hub](https://img.shields.io/badge/Docker%20Hub-alaaabdelnaser%2Flibrary--api-blue?logo=docker)](https://hub.docker.com/r/alaaabdelnaser/library-api)

## 🐳 Docker Hub Image
> **Public image:** [`alaaabdelnaser/library-api:1.0`](https://hub.docker.com/r/alaaabdelnaser/library-api)

Pull and run directly:
```bash
docker pull alaaabdelnaser/library-api:1.0
docker run -p 8080:8080 alaaabdelnaser/library-api:1.0
```

## Project Features
- **Phase 1:** Author management CRUD operations.
- **Phase 2:** Book management CRUD operations.
- **Phase 3:** Enhancements including DTOs, Mapping, validation, and Global Exception Handling.
- **Phase 4:** Docker support — Dockerfile, docker-compose with PostgreSQL, Kafka, and Kafka UI.
- **Phase 5:** Kafka integration — publishes and consumes events on every book/author create, update, and delete.

## Technologies Used
- Java 17+
- Spring Boot (Spring Web, Spring Data JPA)
- H2 Database (local dev) / PostgreSQL (Docker)
- Apache Kafka + Kafka UI
- Maven
- Springdoc OpenAPI (Swagger)
- Docker & Docker Compose

## 🚀 Running with Docker (Recommended)

### 1. Start all dependencies
```bash
docker compose up -d
```
This starts: **PostgreSQL** (port 5432), **Kafka** (port 9092), **Kafka UI** (port 8090)

### 2. Run the API container
```bash
docker run -d \
  --name library-api \
  --network demo_default \
  -p 8080:8080 \
  alaaabdelnaser/library-api:1.0
```

### 3. Access the services
| Service | URL |
|---|---|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Kafka UI | http://localhost:8090 |
| H2 Console (local only) | http://localhost:8080/h2-console |

## Running Locally (without Docker)
```bash
./mvnw spring-boot:run
```

## Kafka Event Flow
Every write operation publishes an event to Kafka:

| Action | Topic | Event Type |
|---|---|---|
| Create Author | `library.authors` | `CREATED` |
| Update Author | `library.authors` | `UPDATED` |
| Delete Author | `library.authors` | `DELETED` |
| Create Book | `library.books` | `CREATED` |
| Update Book | `library.books` | `UPDATED` |
| Delete Book | `library.books` | `DELETED` |

## API Documentation (Swagger)
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

## Sample API Requests

### 1. Create an Author
**POST** `/api/authors`
```json
{
  "firstName": "John",
  "lastName": "Doe"
}
```

### 2. Create a Book for the Author
**POST** `/api/books`
```json
{
  "title": "Spring Boot Mastery",
  "isbn": "1234567890123",
  "publishedYear": 2023,
  "authorId": 1
}
```

### 3. Get all Books
**GET** `/api/books`
```json
[
  {
    "id": 1,
    "title": "Spring Boot Mastery",
    "isbn": "1234567890123",
    "publishedYear": 2023,
    "authorName": "John Doe"
  }
]
```

## Git Workflow Practice
This repository demonstrates a clean Git workflow with branches for each feature phase. A Git merge conflict practice was also conducted between the `main` branch and the `feature/enhancements` branch to demonstrate conflict resolution skills.
