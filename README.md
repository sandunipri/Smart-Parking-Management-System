# Smart Parking Management System

This is a microservices-based Smart Parking Management System developed using Spring Boot and Spring Cloud. The system manages user authentication, parking space availability, and parking reservations. It includes a service discovery mechanism through Eureka and an API Gateway for centralized routing.

## Project Structure

- `user-service/` – Manages user registration, authentication, and user roles
- `parking-reservation-service/` – Handles parking reservations, active sessions, and history
- `parking-space-service/` – Manages available parking slots and their details
- `api-gateway/` – Central gateway for routing and securing API requests
- `discovery-server/` – Eureka service registry
- `config-server/` – Centralized configuration service
- `docs/screenshots/` – Screenshots and documentation assets

## Technologies Used

- Java 17
- Spring Boot
- Spring Cloud (Eureka, Config Server, OpenFeign)
- Spring Security with JWT
- PostgreSQL / MySQL
- API Gateway
- Postman
- Maven
- Git, GitHub

## Resources

- [Postman Collection](./postman_collection.json)
- ![Eureka Dashboard](./docs/screenshots/eureka_dashboard.png)

- ![Screenshot (354)](https://github.com/user-attachments/assets/783e4f62-cf6d-42eb-81c7-68d0da117e5d)
- ![Screenshot (353)](https://github.com/user-attachments/assets/f69ec51e-7d36-4868-9c2d-8604b463c40a)

 
## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/smart-parking-system.git
   cd smart-parking-system

