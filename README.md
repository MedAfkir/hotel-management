# Hotel Management

Backend for a hotel management platform built with Spring Boot microservices and Domain-Driven Design.
The reservation domain follows the hotel reservation system from System Design Interview by Alex Xu.

## Stack

- Java 26
- Spring Boot 4.0.7 (Spring Framework 7)
- Spring Cloud 2025.1 (Eureka, Config Server, Gateway, OpenFeign, Resilience4j)
- Gradle multi-project build
- PostgreSQL per service
- Docker Compose for local infrastructure

## Platform modules

| Module                    | Port | Description                                |
|---------------------------|------|--------------------------------------------|
| platform/discovery-server | 8761 | Eureka service registry                    |
| platform/config-server    | 8888 | Centralized configuration (native profile) |
| platform/api-gateway      | 8080 | Edge gateway and routing                   |
| keycloak (compose)        | 8180 | OpenID Connect identity provider           |

## Domain services

| Module                            | Port | Route              | Database     |
|-----------------------------------|------|--------------------|--------------|
| services/hotel-service            | 8081 | /api/hotels        | hotel        |
| services/rate-service             | 8082 | /api/rates         | rate         |
| services/reservation-service      | 8083 | /api/reservations  | reservation  |
| services/payment-service          | 8084 | /api/payments      | payment      |
| services/guest-service            | 8085 | /api/guests        | guest        |
| services/hotel-management-service | 8086 | /api/admin         | none         |
| services/housekeeping-service     | 8087 | /api/housekeeping  | housekeeping |
| services/notification-service     | 8088 | /api/notifications | notification |

## Shared

- shared/shared-kernel: shared domain primitives (Money, DateRange, DomainException)
- shared/security-starter: OAuth2 resource-server auto-configuration and Keycloak role mapping

## Security

Authentication and authorization use OAuth2/OIDC with Keycloak as the identity provider.

- The gateway validates the bearer token at the edge and forwards it downstream.
- Every domain service is an independent resource server that re-validates the token (zero-trust).
- Keycloak realm roles are mapped to Spring authorities with a `ROLE_` prefix; enforce them with
  `@PreAuthorize("hasRole('ADMIN')")` on controllers or service methods.
- `/api/admin/**` requires `ROLE_ADMIN` at the gateway; all other routes require an authenticated user.

`docker compose up -d` starts Keycloak on port 8180 and imports the `hotel` realm from
`docker/keycloak/realm-hotel.json` (client `hotel-app`, roles ADMIN/STAFF/GUEST, users
admin/admin, staff/staff, guest/guest). The admin console is at http://localhost:8180 (admin/admin).

Keycloak must be running before the gateway and services start, since the JWT issuer metadata
is fetched at startup.

Get a token and call a protected route:

    TOKEN=$(curl -s http://localhost:8180/realms/hotel/protocol/openid-connect/token \
      -d grant_type=password -d client_id=hotel-app -d client_secret=hotel-app-secret \
      -d username=admin -d password=admin | jq -r .access_token)

    curl http://localhost:8080/api/hotels -H "Authorization: Bearer $TOKEN"

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
