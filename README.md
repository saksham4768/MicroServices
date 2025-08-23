# 🏦 Microservices-based Banking Application

A self-learning project demonstrating **Spring Boot Microservices** with modern DevOps practices.

## 🚀 Features
- **Three Microservices**: `Loans`, `Accounts`, and `Cards`  
- **RESTful APIs** with **OpenAPI (Swagger)** documentation  
- **Global Exception Handling** & **DTO Mapper** for clean and robust API design  
- **Configuration Management**:  
  - Record classes  
  - Environment variables  
  - Profile-specific property files (`application-dev.properties`, `application-prod.properties`)  
- **In-memory Database** using **H2**  
- **Centralized Logging** with **SLF4J**  

## 🐳 Docker & Deployment
- Containerized using **Docker** with multiple approaches:
  - **Dockerfile**
  - **Paketo Buildpacks**
  - **Google Jib**
- **Docker Compose** to orchestrate all microservices together in a multi-service environment  

## 🛠️ Tech Stack
- **Backend**: Java, Spring Boot  
- **API Documentation**: OpenAPI (Swagger)  
- **Database**: H2 (in-memory)  
- **Logging**: SLF4J  
- **Containerization**: Docker, Paketo Buildpacks, Google Jib  
- **Orchestration**: Docker Compose  
