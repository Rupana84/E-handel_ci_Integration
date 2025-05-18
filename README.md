# OrderService – E-handel CI Integration

The **OrderService** is a microservice within the E-handel e-commerce platform. It is responsible for managing customer orders, including order creation, validation, status tracking, and interaction with other system components.

##  Tech Stack

- Java 17+
- Spring Boot
- Maven
- JUnit / Mockito (for testing)
- RESTful API


## 📁 Project Structure

```bash 
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── orderservice/
│   │   │               ├── controller/
│   │   │               │   ├── OrderController.java
│   │   │               │   └── ProductController.java
│   │   │               ├── service/
│   │   │               │   └── OrderService.java
│   │   │               ├── model/
│   │   │               │   ├── Order.java
│   │   │               │   └── Product.java
│   │   │               └── repository/
│   │   │                   ├── OrderRepository.java
│   │   │                   └── ProductRepository.java
│   │   └── resources/
│   │       └── application.properties
│   test/
│   └── java/
│       └── com/
│           └── example/
│               └── orderservice/
│                   ├── controller/
│                   │   └── OrderControllerTest.java
│                   ├── service/
│                   │   └── OrderServiceTest.java
│                   └── integration/
│                       └── OrderIntegrationTest.java
└── pom.xml
```

## Getting Started
To get started with the Orderservice module:

Clone the Repository:

```bash
git clone https://github.com/Rupana84/E-handel_ci_Integration.git
cd E-handel_ci_Integration/orderservice

```
## Build the Project:

Ensure you have Maven installed, then run:

```bash
mvn clean install
Run the Application:
```

## Access the API:

The REST API endpoints are available at:

```bash
http://localhost:8080/orders
```

## Running Tests
To run the unit and integration tests:
```bash
mvn test
```
This will execute all tests defined in the test/ directory.
