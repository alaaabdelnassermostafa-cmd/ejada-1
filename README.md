# Library Catalog System

A simple library catalog system built with Spring Boot for an intern project.

## Project Features
- **Phase 1:** Author management CRUD operations.
- **Phase 2:** Book management CRUD operations.
- **Phase 3:** Enhancements including DTOs, Mapping, validation, and Global Exception Handling.

## Technologies Used
- Java 17+
- Spring Boot (Spring Web, Spring Data JPA)
- H2 Database
- Maven
- Springdoc OpenAPI (Swagger)

## Setup and Run
1. Clone the repository.
2. Run the application using Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Access the H2 Database console at: `http://localhost:8080/h2-console`
   - **JDBC URL:** `jdbc:h2:mem:librarydb` (or as configured in `application.properties`)
   - **User:** `sa`
   - **Password:** *[empty]*

## API Documentation (Swagger)
The API is documented using Swagger UI. Once the application is running, you can view and test the API at:
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
