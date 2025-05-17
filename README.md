# User Service – E-handel\_ci\_Integration

This is the **User Service** microservice in the fullstack e-commerce system, developed as part of a collaborative group project at Folkuniversitetet.

##  Contributors

* **Gurpreet Singh Rupana** – User Service
* **Ali Asheer** – Order Service
* **Holy** – Product Service *(used in this repo only for WebClient integration)*
* **Ahmed** – Payment Service *(not included in this version)*

---

##  Project Structure

```
E-handel_ci_Integration/
├── userservice/
│   ├── src/
│   │   └── main/java/com/example/user/
│   │       ├── UserApplication.java
│   │       ├── UserController.java
│   │       ├── UserService.java
│   │       ├── UserRepository.java
│   │       └── ProductClient.java
│   └── resources/
│       └── application.properties
├── orderservice/
├── productService/
│   └── product-microservice-holy/ *(only used for WebClient call)*
└── README.md
```

---

##  Purpose of Product Service in This Version

The **Product Service** is included **only to support WebClient functionality** in User Service, for the endpoint:

```
GET http://localhost:8080/users/products-from-service
```

This enables the User Service to consume and fetch product data during runtime, even if the Product Service is not fully integrated yet.

---

##  How to Run

1. Clone the repository:

```bash
git clone https://github.com/Rupana84/E-handel_ci_Integration.git
cd E-handel_ci_Integration
```

2. Navigate to `userservice` and run:

```bash
mvn spring-boot:run
```

Make sure your local MySQL database is running with:

* DB name: `test_db`
* User: `root`
* Password: `pass123`

---

##  API Endpoints (User Service)

| Endpoint                       | Method | Description                                       |
| ------------------------------ | ------ | ------------------------------------------------- |
| `/users`                       | GET    | Get all users                                     |
| `/users/{id}`                  | GET    | Get user by ID                                    |
| `/users`                       | POST   | Create a new user                                 |
| `/users/{id}`                  | PUT    | Update existing user                              |
| `/users/{id}`                  | DELETE | Delete user                                       |
| `/users/products-from-service` | GET    | Fetch products via WebClient from Product Service |

---

##  Testing

Use Postman or Swagger UI to test endpoints. Make sure the Product Service is reachable if calling the `/products-from-service` endpoint.

---

##  Technologies Used

* Java 17
* Spring Boot 3.4+
* Spring Data JPA
* MySQL
* WebClient (REST communication)
* Maven

---

##  Notes

* This repo uses **ProductMicro** code temporarily only for WebClient testing.
* Team members will merge their own services into main later if needed.

Feel free to reach out to contributors via GitHub for collaboration or review.
