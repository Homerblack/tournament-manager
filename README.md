# 🏆 Tournament Manager

A backend application for managing football/eFootball tournament-related data, built with **Java and Spring Boot**.

This project was developed to practice and demonstrate backend development concepts including REST APIs, database persistence, authentication/authorization, input validation, and API documentation.

## 🚀 Tech Stack

* **Java 21**
* **Spring Boot 3.3.5**
* **Spring Web**
* **Spring Data JPA**
* **PostgreSQL**
* **Spring Security**
* **Spring Validation**
* **SpringDoc OpenAPI / Swagger**
* **Lombok**
* **Maven**
* **Docker**

## ✨ What I Practiced

Through this project, I worked with:

* Building RESTful backend APIs with Spring Boot
* Designing a backend application using Java
* Persisting application data with JPA and PostgreSQL
* Implementing request validation
* Configuring Spring Security
* Documenting APIs with OpenAPI / Swagger
* Organizing a Spring Boot project into maintainable components
* Writing and running tests
* Containerizing the application with Docker

## 🏗️ Project Structure

The project follows a standard Spring Boot structure:

```text
tournament-manager/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
├── .mvn/
├── Dockerfile
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## 🔧 Requirements

Before running the project, make sure you have:

* Java 21+
* Maven (or use the included Maven Wrapper)
* PostgreSQL
* Docker (optional)

## ▶️ Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/Homerblack/tournament-manager.git
cd tournament-manager
```

### 2. Configure the database

Create a PostgreSQL database and configure the required database settings in the application's configuration.

For example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<database-name>
spring.datasource.username=<username>
spring.datasource.password=<password>
```

> Do not commit passwords, API keys, or other secrets to the repository.

### 3. Run with Maven

Using the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application should then start as a Spring Boot application.

## 📚 API Documentation

This project uses **SpringDoc OpenAPI** for API documentation.

Once the application is running, Swagger UI can be accessed through the application's Swagger endpoint.

```text
http://localhost:8080/swagger-ui/index.html
```

> If your application uses a different port or Swagger path, update this URL accordingly.

## 🧪 Testing

The project includes a test structure under:

```text
src/test/java
```

Run the test suite with:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

## 🐳 Docker

A `Dockerfile` is included in the project for containerizing the application.

Build the Docker image:

```bash
docker build -t tournament-manager .
```

Run the container:

```bash
docker run -p 8080:8080 tournament-manager
```

Database configuration may need to be provided through environment variables depending on your local setup.

## 🔐 Security

Spring Security is included in the project to handle application security and authentication/authorization requirements.

Sensitive configuration such as database passwords should be provided through environment variables or local configuration rather than committed to Git.

## 📌 Future Improvements

Some areas I would like to continue improving include:

* Expanding automated test coverage
* Improving API documentation
* Adding more tournament management functionality
* Improving authentication and authorization
* Adding integration tests
* Improving Docker-based deployment
* Connecting the backend with a dedicated frontend application

## 🎯 Purpose of the Project

I built this project as a hands-on way to strengthen my backend development skills with **Java and Spring Boot** and to understand how different backend technologies work together in a real application.

I'm continuing to improve the project as I learn more about software design, testing, security, and deployment.

---

### 👨‍💻 Author

**Homerblack**

GitHub: https://github.com/Homerblack
