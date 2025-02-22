📚 Library Management System (Spring Boot)
🚀 Project Overview
This is a RESTful API built using Spring Boot 3.4.2 that allows users to perform CRUD operations on books in a library. It includes validation, authentication, Swagger API documentation, and unit tests.

🛠 Features
✅ CRUD Operations – Add, Update, Retrieve, and Delete books
✅ Spring Boot 3.4.2 – Latest version for better performance
✅ Spring Security – Basic authentication to secure endpoints
✅ Validation – Uses JSR-380 Bean Validation
✅ Swagger UI – API documentation for easy testing
✅ Unit Testing – Covers Service layers using JUnit & Mockito
✅ Global Exception Handling – Returns meaningful error messages

🏗 Tech Stack
  Backend: Spring Boot 3.4.2, Spring Data JPA, Spring Security
  Database: MySQL (or H2 for testing)
Documentation: Springdoc OpenAPI (Swagger UI)
  Testing: JUnit 5, Mockito
📂 Project Structure

library-management-system/
│── src/
│   ├── main/
│   │   ├── java/com/library/management/
│   │   │   ├── config/               # Security & Swagger Config
│   │   │   ├── controller/           # REST Controllers
│   │   │   ├── dto/                  # Data Transfer Objects (DTOs)
│   │   │   ├── entity/               # JPA Entity Classes
│   │   │   ├── exception/            # Global Exception Handling
│   │   │   ├── repository/           # JPA Repository Interfaces
│   │   │   ├── service/              # Business Logic Layer
│   │   │   ├── LibraryManagementApplication.java  # Main Application Class
│   ├── resources/
│   │   ├── application.properties    # Database & Config Settings
│── test/                             # Unit Tests for Services
│── pom.xml                           # Maven Dependencies
│── README.md                         # Documentation

⚙ Installation & Setup
🔹 Step 1: Clone the Repository
      git clone https://github.com/your-username/library-management-system.git
      cd library-management-system
🔹 Step 2: Configure Database
      MySQL Setup:
      Create a database named library_db
      Update src/main/resources/application.properties with your MySQL credentials:
      Properties
        spring.datasource.url=jdbc:mysql://localhost:3306/library_db
        spring.datasource.username=your_userName
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
      H2 (For Testing)
      If using H2, replace the database properties with:
      Properties
        spring.datasource.url=jdbc:h2:mem:librarydb
        spring.datasource.driver-class-name=org.h2.Driver
        spring.h2.console.enabled=true
        spring.h2.console.path=/h2-console
🔹 Step 3: Build & Run the Project
      mvn clean package
      mvn spring-boot:run
📌 The application will start at http://localhost:8080/

🛠 API Endpoints
  Method |	Endpoint	     | Description	     | Auth Required
  POST	 | /api/books	     | Add a new book	   | ✅ Yes
  GET	   | /api/books	     | Get all books	   | ❌ No
  GET	   | /api/books/{id} | Get book by ID	   | ❌ No
  PUT	   | /api/books/{id} | Update book by ID | ✅ Yes
  DELETE | /api/books/{id} | Delete book by ID | ✅ Yes
📌 To access secure endpoints, use:
    Username: admin
    Password: admin123
    
🔍 Swagger API Documentation
📌 After running the project, open:

🔗 Swagger UI: http://localhost:8080/swagger-ui.html
🔗 OpenAPI Docs: http://localhost:8080/v3/api-docs
🔍 Running Tests
To execute unit tests, run:
    mvn test
📌 This will test:
   ✅ BookServiceImplTest.java (Service Layer)
  

