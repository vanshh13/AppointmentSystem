# 🗓️ Appointment Booking REST API

A backend RESTful API built with Java and Spring Boot that manages appointment scheduling. It supports user registration, booking appointments, viewing, updating, and canceling them. Admin users can manage users and appointments via protected endpoints.

---

## 🚀 Features

- 🔐 User Registration
- 📅 Book, View, Update, Cancel Appointments
- 🔑 Role-based Access (User/Admin)
- ✅ Input Validation & Exception Handling
- 📄 RESTful API design with meaningful status codes
- 🧪 Integration-ready for frontend or mobile clients

---

## 📦 Tech Stack

| Layer     | Technology               |
|-----------|---------------------------|
| Language  | Java 17+                  |
| Backend   | Spring Boot               |
| Security  | Spring Security           |
| Database  | MySQL                     |
| ORM       | Spring Data JPA (Hibernate) |
| Build Tool| Maven                     |

---

## 📁 Project Structure

```
AppointmentSystem/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/example/appointmentsystem/
│ │ │ ├── controller/ # REST Controllers
│ │ │ ├── dto/ # DTOs for requests/responses
│ │ │ ├── model/ # Entity classes
│ │ │ ├── repository/ # JPA Repositories
│ │ │ ├── service/ # Business logic
│ │ │ ├── config/ # Security 
│ │ │ └── AppointmentSystemApplication.java
│ │ └── resources/
│ │ ├── application.properties
│ │ └── data.sql (optional seed data)
├── pom.xml
└── README.md
```
