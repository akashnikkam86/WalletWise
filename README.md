# 💰 WalletWise — Personal Finance Tracker

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Railway](https://img.shields.io/badge/Railway-0B0D0E?style=for-the-badge&logo=railway&logoColor=white)

> A full-stack personal finance management web application built with Java and Spring Boot.
> Track your income, manage expenses, and stay on top of your budgets — all in one place.

🌐 **Live Demo:** [walletwise-production.up.railway.app](https://walletwise-production.up.railway.app)

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Quick Start](#-quick-start)
- [Running the Application](#-running-the-application)
- [Deployment](#-deployment)
- [Security](#-security)
- [Author](#-author)

---

## ✨ Features

### 🔐 User Authentication & Authorization
- Secure registration and login with email & password
- Password hashing with BCrypt
- Role-based access control via Spring Security
- Custom `UserDetailsService` for user loading

### 💸 Expense Management
- Add, view, and manage personal expenses
- Categorised expense tracking
- Persistent data with PostgreSQL

### 📊 Budget Management
- Create and manage budgets per category
- Monitor spending against set budget limits
- Real-time budget status tracking

### 🗄️ Database & Migrations
- Versioned schema management with Flyway
- Auto-applied migrations on startup
- Clean, reproducible database setup

---

## 🛠 Tech Stack

### Backend

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17+ | Core language |
| Spring Boot | 3.x | Application framework |
| Spring Security | 6.x | Authentication & authorization |
| Spring Data JPA | 3.x | ORM & database layer |
| Hibernate | 6.x | JPA implementation |
| PostgreSQL | 16 | Primary relational database |
| Flyway | 10.x | Database migrations |
| Maven | 3.9+ | Build & dependency management |

### Infrastructure

| Technology | Purpose |
|-----------|---------|
| Docker | Containerisation |
| Railway | Cloud deployment & PostgreSQL hosting |
| GitHub | Version control & CI/CD |

---

## 📁 Project Structure

```
WalletWise/
├── src/
│   ├── main/
│   │   ├── java/com/walletwise/app/
│   │   │   ├── WalletWiseApplication.java   # Entry point
│   │   │   ├── user/                        # User module
│   │   │   │   ├── UserController.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── User.java                # JPA Entity
│   │   │   │   └── UserRepository.java
│   │   │   ├── expense/                     # Expense module
│   │   │   │   ├── ExpenseController.java
│   │   │   │   ├── ExpenseService.java
│   │   │   │   ├── Expense.java
│   │   │   │   └── ExpenseRepository.java
│   │   │   ├── budget/                      # Budget module
│   │   │   │   ├── BudgetController.java
│   │   │   │   ├── BudgetService.java
│   │   │   │   ├── Budget.java
│   │   │   │   └── BudgetRepository.java
│   │   │   └── config/                      # Configuration
│   │   │       ├── SecurityConfig.java
│   │   │       └── CustomUserDetailsService.java
│   │   └── resources/
│   │       ├── application.yml              # Base config
│   │       ├── application-prod.yml         # Production config
│   │       └── db/migration/               # Flyway migrations
│   │           ├── V1__create_users.sql
│   │           ├── V2__create_expenses.sql
│   │           └── V3__create_budgets.sql
│   └── test/
├── Dockerfile
├── pom.xml
└── README.md
```

---

## 🚀 Quick Start

```bash
# 1. Clone the repository
git clone https://github.com/akashnikkam86/WalletWise.git
cd WalletWise

# 2. Configure environment variables
# Set the following in application.yml or as env vars:
# SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/walletwise
# SPRING_DATASOURCE_USERNAME=your_db_user
# SPRING_DATASOURCE_PASSWORD=your_db_password

# 3. Build & run
./mvnw spring-boot:run

# 4. Access the application
# http://localhost:8080
```

---

## 🏃 Running the Application

```bash
# Development mode
./mvnw spring-boot:run

# Production build
./mvnw clean package -DskipTests
java -jar target/walletwise-*.jar
```

| Service | URL |
|---------|-----|
| App | http://localhost:8080 |
| Health | http://localhost:8080/actuator/health |

---

## 🚀 Deployment

Deployed on **Railway** with a managed PostgreSQL database.

### Environment Variables

Set the following in your Railway dashboard:

```env
SPRING_DATASOURCE_URL=your_railway_postgres_url
SPRING_DATASOURCE_USERNAME=your_db_user
SPRING_DATASOURCE_PASSWORD=your_db_password
```

### Production Checklist

- ✅ PostgreSQL database provisioned on Railway
- ✅ Environment variables configured
- ✅ Flyway migrations auto-run on startup
- ✅ App running on port 8080
- ✅ HTTPS handled by Railway automatically

---

## 🔒 Security

| Feature | Implementation |
|--------|---------------|
| Password hashing | BCrypt |
| Authentication | Spring Security |
| User loading | Custom `UserDetailsService` |
| SQL injection prevention | JPA parameterised queries |
| Input validation | Jakarta Bean Validation (`@Valid`) |

---

## 👤 Author

**Akash Nikkam**
[![GitHub](https://img.shields.io/badge/GitHub-akashnikkam86-181717?style=flat&logo=github)](https://github.com/akashnikkam86)

---

## 📄 License

This project is licensed under the MIT License.
