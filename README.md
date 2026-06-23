# Hotel Management

Backend for a hotel management platform built as a Spring Boot microservice architecture following
Domain-Driven Design. The reservation domain is modeled after the hotel reservation system design in
System Design Interview (Alex Xu), Chapter 7.

## Stack

- Java 26
- Spring Boot 4.0.7 (Spring Framework 7)
- Spring Cloud 2025.1 (Eureka, Config Server, Gateway, OpenFeign, Resilience4j)
- Gradle multi-project build
- PostgreSQL per service
- Docker Compose for local infrastructure

## Platform modules

| Module | Port | Description |
| --- | --- | --- |
| platform/discovery-server | 8761 | Eureka service registry |
| platform/config-server | 8888 | Centralized configuration (native profile) |
| platform/api-gateway | 8080 | Edge gateway and routing |

## Domain services

| Module | Port | Route | Database |
| --- | --- | --- | --- |
| services/hotel-service | 8081 | /api/hotels | hotel |
| services/rate-service | 8082 | /api/rates | rate |
| services/reservation-service | 8083 | /api/reservations | reservation |
| services/payment-service | 8084 | /api/payments | payment |
| services/guest-service | 8085 | /api/guests | guest |
| services/hotel-management-service | 8086 | /api/admin | none |
| services/housekeeping-service | 8087 | /api/housekeeping | housekeeping |
| services/notification-service | 8088 | /api/notifications | notification |

## Shared

- shared/shared-kernel: shared domain primitives (Money, DateRange, DomainException)

## Build

    ./gradlew build

## Run locally

Start PostgreSQL:

    docker compose up -d

Start the platform services first, then the domain services, for example:

    ./gradlew :platform:discovery-server:bootRun
    ./gradlew :platform:config-server:bootRun
    ./gradlew :platform:api-gateway:bootRun
    ./gradlew :services:reservation-service:bootRun

The Eureka dashboard is at http://localhost:8761 and the gateway at http://localhost:8080.

## Tests

    ./gradlew test

Integration tests use Testcontainers and require a running Docker daemon.
