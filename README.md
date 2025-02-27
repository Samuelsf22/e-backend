# ⚙️ E-Commerce Backend

## 📌 About the Project

This is a **high-performance, reactive backend** for an e-commerce application, built using **Spring WebFlux** and following a **Hexagonal Architecture** approach. The system is designed for scalability, security, and maintainability.

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/Java-007396?logo=java&logoColor=fff&style=flat)
![Spring WebFlux](https://img.shields.io/badge/Spring%20WebFlux-6DB33F?logo=spring&logoColor=fff&style=flat)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?logo=postgresql&logoColor=fff&style=flat)
![JWT](https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens&logoColor=fff&style=flat)
![GitFlow](https://img.shields.io/badge/GitFlow-000000?logo=git&logoColor=fff&style=flat)

## 🏗️ Architecture

This project follows the **Hexagonal Architecture (Ports & Adapters)** pattern to ensure flexibility, testability, and clear separation of concerns.

- **Domain Layer**: Business logic and core entities.
- **Application Layer**: Use cases and orchestration.
- **Infrastructure Layer**: Database, external APIs, and framework-specific implementations.

## 🚀 Features

✅ **Reactive Programming with Spring WebFlux**  
✅ **JWT-based Authentication & Authorization**  
✅ **GitFlow Workflow for Version Control**  
✅ **PostgreSQL Database with R2DBC**  
✅ **Asynchronous Event Handling**

## 🛠️ Installation & Setup

```bash
# Clone the repository
git clone git@github.com:Samuelsf22/e-backend.git

# Navigate to the project directory
cd e-backend

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
