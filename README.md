# Spring Boot Projects Overview

This repository contains several Spring Boot-based applications demonstrating various concepts and integrations. Below is an overview of the different packages and their respective functionalities.

## Package: `spring-boot`

### `demo-book`
- **JPA**: Integration with Java Persistence API for ORM functionality.
- **Controller <-> Service <-> Repository**: Implements the typical three-layer architecture.
- **Mapping Entity <-> DTO (via MapStruct)**: Uses MapStruct for entity-to-DTO and DTO-to-entity mapping.
- **AOP Logging**: Aspect-Oriented Programming for logging.
- **Integration Test**: Includes integration tests for various components of the application.

### `restdata`
- **Spring Data REST**: Exposes repositories as RESTful APIs automatically.
- **Event Handler**: Handles domain events to trigger necessary actions across the system.

## Package: `spring-boot-cloud`

### `config-server`
- **Centralized Configuration**: Centralizes and versions the configurations for all applications in the system.

### `discovery-server (Eureka)`
- **Dynamic Registration & Discovery**: Allows applications to register themselves dynamically with the discovery server and communicate with each other using their application names (no need for hardcoded host addresses for API calls).

### `order-service`
- **Feign Client**: Uses Feign to communicate with other services via HTTP.
- **Circuit Breaker**: Implements a circuit breaker pattern to handle failures gracefully with Resilience4j.

### `inventory-service`
- **Bean Configuration**: Configuration of beans for application contexts.
- **Unit Tests**: Includes unit tests for validating the functionality of the service.

## Package: `spring-boot-reactive`

- **WebFlux**: Implements reactive programming using Spring WebFlux.
- **R2DBC**: Integrates with Reactive Relational Database Connectivity for non-blocking database interactions.

## Package: `kafka`

*Prerequisite: Ensure Docker and Docker Compose are installed on your machine to run Kafka and Schema Registry in containers.*

- **Message Producer**: Implements Kafka producer functionality for sending messages to Kafka topics.
- **Message Consumer**: Implements Kafka consumer functionality for receiving messages from Kafka topics.
- **Use of Avro**: Manages serialization, deserialization, and versioning of data transfer objects (DTOs) using Avro.

---
