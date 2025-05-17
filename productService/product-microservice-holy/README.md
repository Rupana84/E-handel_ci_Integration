# ProductMicroService

## Overview
ProductMicroService is a Spring Boot-based microservice that manages products and communicates with OrderMicroService to retrieve order-related information using WebClient.

## Features
- Create, retrieve, update, and delete products.
- Communicate with OrderMicroService to get order count for a specific product.
- Uses WebClient for making REST API calls.
- Configurable via environment variables.

## Technologies Used
- Java 17
- Spring Boot 3
- Spring WebFlux (WebClient)
- Spring Data JPA
- MySQL
- Docker (Optional)
- AWS Elastic Beanstalk (for deployment)

## Setup & Installation

### Prerequisites
- Java 17 or later
- Maven
- MySQL database

### Clone the Repository
```sh
git clone https://github.com/Grupp3Ehandel/product-microservice-holy.git
cd ProductMicroService
```

### Configure Database
Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_service_db
spring.datasource.username=root
spring.datasource.password=root
```

### Set Environment Variables
Before running the application, set the environment variable for the OrderMicroService URL:

#### Windows (Command Prompt)
```sh
set WEB_CLIENT_URL=http://order-service-env.abc123.eu-north-1.elasticbeanstalk.com
```
#### macOS/Linux
```sh
export WEB_CLIENT_URL=http://order-service-env.abc123.eu-north-1.elasticbeanstalk.com
```

Alternatively, add it to `application.properties`:
```properties
webclient.url=${WEB_CLIENT_URL}
```

### Build & Run
```sh
mvn clean install
mvn spring-boot:run
```

## REST API Endpoints

### Product Endpoints
| Method | Endpoint       | Description         |
|--------|---------------|---------------------|
| GET    | /products     | Get all products   |
| GET    | /products/{id} | Get product by ID  |
| POST   | /products     | Create a new product |
| PUT    | /products/{id} | Update a product  |
| DELETE | /products/{id} | Delete a product  |

### Order Service Communication
| Method | Endpoint                     | Description |
|--------|------------------------------|-------------|
| GET    | /products/popular/{id}       | Get order count for a product |

## WebClient Integration
ProductMicroService communicates with OrderMicroService using WebClient. The client is defined in `OrderServiceClient.java`:

```java
@Component
public class OrderServiceClient {
    private final WebClient webClient;

    public OrderServiceClient(WebClient.Builder webClientBuilder, @Value("${webclient.url}") String webClientUrl) {
        this.webClient = webClientBuilder.baseUrl(webClientUrl).build();
    }

    public Mono<Integer> getOrderCountByProductId(Long productId) {
        return webClient.get()
                .uri("/orders/count/{productId}", productId)
                .retrieve()
                .bodyToMono(Integer.class);
    }
}
```

### Example Response from Order Service
```json
{
  "productId": 1,
  "orderCount": 50
}
```

### AWS Elastic Beanstalk Deployment
- Package the application:
  ```sh
  mvn package -DskipTests
  ```
- Deploy the `target/ProductMicroService.jar` file to AWS Elastic Beanstalk.

## Troubleshooting
- Ensure MySQL is running and credentials are correct.
- Verify the `WEB_CLIENT_URL` environment variable is set.
- Check logs for any WebClient errors using:
  ```sh
  mvn spring-boot:run
  ```

## Author
Ghori Najmun Nahar Holy



