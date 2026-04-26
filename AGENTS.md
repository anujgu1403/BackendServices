🚀 Overview
The Cart-Service is a Spring Boot microservice responsible for managing the customer shopping cart lifecycle. It acts as an orchestrator between the Catalog-Service (for product enrichment) and the PostgreSQL database (for persistence).

🛠 Tech Stack
Runtime: Java 21 (Toolchain)

Framework: Spring Boot 3.3.3

Communication: Reactive WebFlux (Mono/Flux) for integration, WebMVC for REST endpoints.

Resiliency: Resilience4j (Circuit Breaker, Retries, Fallbacks).

Mapping: MapStruct 1.5.5 (Model <-> Entity <-> DTO).

Database: PostgreSQL (Runtime) / H2 (Testing).

Core Library: com.retail.core:retail-core:1.0.1 (Shared business logic).

🏗 Architectural Patterns
1. Item Processing Flow

When an item is added or updated (addItem, updateQuantity):

Map: Request DTO is mapped to a CartModel.

Enrich: Call Catalog-Service via catalogIntegrator.

Resilience: If Catalog-Service fails, trigger fallbackCatalog.

Persist: Save the enriched CartEntity to PostgreSQL.

Response: Return Cart DTO via cartModelToCartMapper.

2. Resiliency & Fallback Strategy

Library: Resilience4j.

Pattern: Circuit Breaker + Retry.

Fallback Logic: Instead of throwing raw exceptions, fallbacks return a ProductServiceResponse populated with an AlertModel (Meta-data) to inform the consumer of partial failures.

📝 Coding Standards for AI
Dependency Injection: Use Constructor Injection exclusively.

Boilerplate: Use Lombok (@Data, @Builder, @RequiredArgsConstructor).

Reactive Streams: - Use .flatMap() for sequential non-blocking operations (e.g., calling the repository after the integrator).

Use Flux.fromIterable() when processing a list of cart items for enrichment.

Error Handling: - Business errors must be wrapped in AlertModel and returned in the response metadata.

Use ErrorConstants from retail-core for consistency.

📂 Key Service Boundaries
CartItemJpaRepository: Handles findByCartIdAndProductId for upsert logic.

CatalogIntegrator: Handles external calls with Resilience4j annotations.

CartModelToCartItemEntityMapper: Central MapStruct mapper for DB conversion.