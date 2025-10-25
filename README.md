# Edge Registry Service

The Edge Registry Service is a simple backend application built with Spring Boot and PostgreSQL that keeps track of network edges or endpoints. Each edge stores a source, target, and URL, and the service automatically checks whether those URLs are reachable.

This project started as a small system to manage and monitor endpoints and grew into a structured backend example that’s ready to integrate with a frontend dashboard or external tools.

---

## What It Does

- Stores edges with details such as source, target, and URL  
- Checks the status of each URL at regular intervals and updates the database  
- Provides a REST API to create, read, update, delete, and filter edges  
- Supports input validation for clean and consistent data  
- Includes Flyway for database migrations and Docker for local PostgreSQL setup  

---

## Tech Stack

**Backend:** Spring Boot 3.5  
**Database:** PostgreSQL (via Docker)  
**Migrations:** Flyway  
**Validation:** Jakarta Validation (Hibernate Validator)  
**Build Tool:** Maven  
**Language:** Java 21  

---

## API Overview

All endpoints are prefixed with `/api/v1/edges`

| Method | Endpoint | Description |
|--------|-----------|--------------|
| `GET` | `/api/v1/edges` | Returns all edges |
| `GET` | `/api/v1/edges?status=UP` | Returns only edges that are currently up |
| `GET` | `/api/v1/edges/{id}` | Returns a specific edge by ID |
| `POST` | `/api/v1/edges` | Creates a new edge |
| `PUT` | `/api/v1/edges/{id}` | Updates an existing edge |
| `DELETE` | `/api/v1/edges/{id}` | Deletes an edge |
| `POST` | `/api/v1/edges/check-statuses` | Triggers a manual check of all URLs (optional) |

---

## Example Request

```bash
curl -X POST http://localhost:8080/api/v1/edges \
  -H "Content-Type: application/json" \
  -d '{
    "source": "self",
    "target": "google",
    "url": "https://www.google.com"
  }'
```

---
## Validation Rules 

When creating or updating an edge, the following fields are validated automatically:

| Field    | Rule                       |
| -------- | -------------------------- |
| `source` | Must not be blank          |
| `target` | Must not be blank          |
| `url`    | Must be a valid URL format |

---
## Background Status Checks

The service runs an automatic task that checks every edge’s URL periodically and updates its status and last checked timestamp.
You can change how often it runs by editing the schedule in EdgeStatusService.java.

---
## Running Locally
1. Start PostgreSQL with Docker
    ```bash docker run --name edge-db \
      -e POSTGRES_USER=postgres \
      -e POSTGRES_PASSWORD=postgres \
      -e POSTGRES_DB=edge \
      -p 5432:5432 \
      -d postgres:15-alpine
    ```
2.   Run the application from IntelliJ or with      
  ``` ./mvnw spring-boot:run```
4. Visit http://localhost:8080/api/v1/edges
