# product-service

Small Spring Boot demo service that manages products (CRUD read operations) using Spring Data JPA.

This repository contains a simple REST API for Products and a service layer that uses a relational database (MySQL by default).

Checklist
- [x] Project overview and features
- [x] Prerequisites
- [x] Configuration (application.properties)
- [x] Build & run instructions (Windows / Maven wrapper)
- [x] Available REST endpoints
- [x] Running tests

## Features

- REST API for managing products
- Uses Spring Boot, Spring Data JPA and Lombok
- Default configuration targets a MySQL database

## Tech stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL (runtime)
- Lombok
- Maven

## Prerequisites

- Java 17 (JDK)
- Maven (optional if you use the included Maven wrapper)
- A running MySQL instance (or adjust `application.properties` to use another DB)

## Configuration

The application reads database configuration from `src/main/resources/application.properties`.
Before running the app, update the datasource URL, username and password with your environment's values.

Example (properties file snippet):

```
spring.application.name=product-service
server.port=8088

spring.datasource.url=jdbc:mysql://<HOST>:3306/<DB_NAME>?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=<DB_USER>
spring.datasource.password=<DB_PASSWORD>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
management.endpoints.web.exposure.include=*
```

Notes:
- `spring.jpa.hibernate.ddl-auto=update` is convenient for development but not recommended for production migrations.
- Do not commit production credentials to source control.

## Build & Run (Windows)

Using the bundled Maven wrapper:

```cmd
mvnw.cmd clean package
mvnw.cmd spring-boot:run
```

Or run the generated jar:

```cmd
mvnw.cmd package
java -jar target/product-service-0.0.1-SNAPSHOT.jar
```

The application listens on port `8088` by default (changeable in `application.properties`).

## REST Endpoints

Base path: `/product/`

- GET `/product/products` — return list of products
- GET `/product/products/{id}` — return product by id
- POST `/product/addproduct` — create a new product (JSON body)

Example create payload:

```json
{
  "name": "pen",
  "description": "blue ink pen",
  "price": 10.0,
  "imageUrl": "/img/pen.png"
}
```

## Tests

Run unit/integration tests with the Maven wrapper:

```cmd
mvnw.cmd test
```

The project includes basic controller and service tests.

## Notes & Suggestions

- This is a simple demo; consider adding input validation (@Valid), global exception handling, and DTOs for production readiness.
- To run tests without a real database, consider adding a `application-test.properties` that switches the datasource to an in-memory database (H2), or use Spring's `@DataJpaTest` and Testcontainers for integration tests.
- Lombok is used for boilerplate reduction — make sure your IDE has Lombok support enabled.

## Contributing

Fork and open a pull request. Keep changes small and focused.

## License

This project does not include a license file. Add an appropriate license if you plan to publish.

## To create a new repository on the command line
- echo "# product-service" >> README.md
- git init
- git add -A
- git commit -m "first commit"
- git branch -M main
- git remote add origin https://github.com/Pabitramaiti/product-service.git
- git push -u origin main


## To push an existing repository from the command line
- git remote add origin https://github.com/Pabitramaiti/product-service.git
- git branch -M main
- git push -u origin main
