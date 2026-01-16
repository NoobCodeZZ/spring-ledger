# Spring Ledger

A Spring Boot application for managing financial ledger entries and loan transactions. Built with modern Java technologies including Spring Data JPA, Hibernate, and PostgreSQL with Flyway database migrations.

## Tech Stack

- **Framework**: Spring Boot with Spring Web MVC
- **Database**: PostgreSQL with Spring Data JPA & Hibernate ORM
- **Migration**: Flyway for database version control
- **Build Tool**: Gradle
- **Code Generation**: Lombok for reducing boilerplate
- **Logging**: SLF4J with Logback

## Features

- RESTful API endpoints for loan and bank management
- Database-first approach with Flyway migrations
- Entity-based data modeling with JPA
- Automated boilerplate reduction using Lombok annotations
- Transaction management and persistence

## Project Structure

- `controller/` - REST API endpoints
- `db/` - Database entities and repositories
- `models/` - DTOs for data transfer
- `service/` - Business logic layer
- `resources/db/migration/` - Flyway SQL migration scripts

## Getting Started

### Prerequisites

- Java 17 or higher
- PostgreSQL database
- Gradle (or use the included Gradle wrapper)

### Configuration

Configure your database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Running the Application

Using Gradle wrapper:

```bash
./gradlew bootRun
```

Or on Windows:

```bash
gradlew.bat bootRun
```

### Building the Application

```bash
./gradlew build
```

## Database Migrations

This project uses Flyway for database version control. Migration scripts are located in `src/main/resources/db/migration/`. Flyway automatically runs pending migrations on application startup.

## License

This project is available under the MIT License.

