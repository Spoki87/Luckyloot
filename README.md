
# Luckyloot backend

LuckyLoot is a backend Spring Boot application that simulates a basic online gambling system. It includes features such as user registration/login, wallet management, and simple slot machine gameplay.

---

## 🔧 Tech Stack

- Java 17  
- Spring Boot 3.2  
- Spring Security + JWT  
- Hibernate / JPA  
- PostgreSQL
- Lombok  
- MapStruct  
- Swagger / OpenAPI 3

---

## 🚀 Getting Started

### 1. Clone and Run the Project

```bash
git clone https://github.com/Spoki87/luckyloot.git
cd luckyloot
./mvnw spring-boot:run
```

### 2. Add `application.properties`

Create a file at `src/main/resources/application.properties` with the following configuration:

```properties
spring.application.name=LuckyLoot

spring.datasource.url=jdbc:postgresql:
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# SMTP Mail Configuration
spring.mail.host=
spring.mail.port=
spring.mail.username=
spring.mail.password=
mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true

# JWT and App Config
jwt.secret.key=
app.base-url=
```

## 🛡️ Security

- JWT-based authentication and authorization
- Public endpoints only for registration, login, and password reset
- Passwords hashed using BCrypt

## 🗂️ Project Structure

```
com.luckyloot
├── user              # Authentication & user management
├── wallet            # Wallet operations (deposit/withdraw)
├── spin              # Spin (betting) logic
├── slotgame          # Slot machine game logic
├── security          # Security config (JWT, filters)
├── exception         # Global and custom exceptions
├── response          # Generic API response wrapper
└── config            # General configuration
```

---


## Example API Endpoints


| Endpoint                         | Method | Description                     |
|----------------------------------|--------|---------------------------------|
| `/api/user/register`            | POST   | User registration               |
| `/api/user/login`               | POST   | User login, returns JWT token   |
| `/api/spin`                     | POST   | Spin the slot machine           |
| `/api/wallet/deposit`          | POST   | Deposit funds into wallet       |
| `/api/wallet/withdraw`         | POST   | Withdraw funds from wallet      |


**Swagger UI available at:**  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
## Authors

- [@Patryk Pawlak](https://www.github.com/Spoki87)

