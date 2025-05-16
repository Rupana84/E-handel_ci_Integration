# User Service – E-handel_ci_Integration

This is the **UserService** microservice for the E-handel backend system, developed as part of a group examination project using a **microservices architecture** with Spring Boot and MySQL.

##  Responsible Developer

**Gurpreet Singh Rupana**  
Responsible for the development and testing of the **User Service**.

##  Part of a Microservices System

This service is part of a distributed e-commerce backend project, where each team member is responsible for one microservice:

| Microservice        | Developer              |
|---------------------|------------------------|
| User Service        | Gurpreet Singh Rupana  |
| Order Service       | Ali Asheer             |
| Product Service     | Holy                   |
| Payment Service     | Ahmed                  |

---

##  Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **WebClient** (for service-to-service communication)
- **JUnit 5 / Mockito** (for testing)
- **GitHub Actions** (for CI)

---

##  Features

- Create, read, update, and delete user accounts.
- Fetch product data from `ProductService` using WebClient.
- RESTful API with validation and error handling.
- Tested with unit, component, and integration tests.
- CI pipeline with GitHub Actions (MySQL Docker service + Maven test).

---

##  How to Run

1. Clone the repository:

  # git clone https://github.com/Rupana84/E-handel_ci_Integration.git
  cd userservice
   
2. Configure your application.properties with
     spring.datasource.url=jdbc:mysql://localhost:3306/user_db
     spring.datasource.username=your-username
     spring.datasource.password=your-password

4. Run with Maven:

   # mvn spring-boot:run

5. Testing

   # mvn clean test
   CI tests will also run automatically on push and pull requests via GitHub Actions.

  ### API Endpoints

| Method | Endpoint                      | Description                         |
|--------|-------------------------------|-------------------------------------|
| GET    | /users                        | Get all users                       |
| GET    | /users/{id}                   | Get user by ID                      |
| POST   | /users                        | Create new user                     |
| PUT    | /users/{id}                   | Update user                         |
| DELETE | /users/{id}                   | Delete user                         |
| GET    | /users/products-from-service | Fetch products from ProductService   |

Folder Structure
userservice/
src/
main/java/com/example/user/
test/java/com/example/user/
pom.xml
.github/workflows/user-service-ci.yml



Credits
- Gurpreet Singh Rupana User Service
- Ali Asheer Order Service
- Holy Product Service
- Ahmed Payment Service
