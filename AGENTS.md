# Portfolio Backend - AI Agent Guide

## 🧠 Project Context
This is a **Spring Boot** application serving a personal portfolio. It acts as a hybrid application:
1.  **SSR Web App:** Serves HTML using **Thymeleaf** for an admin panel.
2.  **REST API:** Serves JSON for external consumers (React, Mobile, etc.).

**Key Tech Stack:** Java 21, Spring Boot (Web, Security, JDBC), PostgreSQL, Thymeleaf, Swagger/OpenAPI.
**Frontend Stack:** HTML5, CSS3 (Native), Thymeleaf. **NO** external CSS/JS frameworks (Tailwind, Bootstrap, React, Vue, etc.).

## 📜 Interaction Protocols & Rules

### 1. Analysis & Planning (Pre-Work)
*   **Explain First:** Before modifying code, briefly explain what you will change and which files are affected.
*   **Threshold:** If you need to modify >3 files, **STOP** and ask for confirmation first.
*   **Ambiguity:** If multiple implementations are possible, ask before deciding.

### 2. Scope & Safety
*   **Minimal Impact:** Modify only files directly related to the request. Avoid unsolicited refactoring.
*   **Business Logic Protection:** Do NOT modify Controllers, Services, Repositories, Entities, or DTOs unless strictly necessary.
*   **Legacy Protection:** Do NOT delete existing functionality without explicit approval.

### 3. Frontend Guidelines
*   **Native Only:** Use pure CSS3 with variables (`:root`), Flexbox, and Grid.
*   **Design:** Aim for modern, elegant, and responsive UI without libraries.
*   **Structure:** Keep CSS in `src/main/resources/static/css`.

### 4. Code Quality & Git
*   **Validation:** Ensure code compiles and linters pass.
*   **Commits:** After success, suggest a git command using **Conventional Commits** (e.g., `feat: validate project input`, `style: update form css`).

## 🏗 Architecture & Patterns

### 1. Data Access (Critical)
*   **Pattern:** Repository Pattern with **direct `JdbcTemplate` usage**.
*   **Deviation:** This project does **NOT** use JPA/Hibernate or Spring Data Derived Queries.
*   **Implementation:** Interfaces (e.g., `IProjectRepository`) are implemented by manual classes (e.g., `ProjectRepositoryImpl`).
*   **Action for Agents:** When modifying the database schema (`schema.sql`), you **MUST** manually update the SQL queries and `RowMapper` in the corresponding `*RepositoryImpl.java`.
    *   *Example:* See `src/main/java/com/portfolio/my_portfolio_backend/repository/ProjectRepositoryImpl.java`.

### 2. Controller Strategy
*   **Web Controllers (`controller/web`):** Return `String` (view names). handle `Model` and `BindingResult`. Use `FlashAttributes` for messages.
*   **API Controllers (`controller/api`):** Return `ResponseEntity<Dto>`. Annotated with `@RestController` and Swagger annotations (`@Operation`).
*   **DTOs:** Mappers (`mapper` package) convert Entities <-> DTOs. Always use DTOs for API responses.

### 3. Security (Hybrid)
*   **Config:** `config/WebSecurityConfig.java`.
*   **Web:** Form Login enabled. CSRF **enabled**. Session-based.
*   **API (`/api/**`):** HTTP Basic Auth. CSRF **disabled**. Stateless-ish (though Basic Auth sends credentials every time).
*   **Rules:**
    *   `GET /api/**`: Public.
    *   `POST/PUT/DELETE /api/**`: Authenticated.
    *   `swagger-ui`: Public.

### 4. File Handling
*   **Storage:** Local filesystem in `src/main/resources/static/img/projects`.
*   **Service:** `FileStorageService` manages uploads.
*   **Serving:** Static resources are mapped to classpath locations.

## 🛠 Development Workflow

### Build & Run
*   **Command:** `./mvnw spring-boot:run`
*   **Java Version:** 21 (Virtual Threads enabled: `spring.threads.virtual.enabled=true`).

### Database Changes
1.  Modify `src/main/resources/schema.sql`.
2.  Update Entity class (`model/`).
3.  Update `RowMapper` and SQL queries in `repository/*RepositoryImpl.java`.
4.  Update DTOs and Mappers if necessary.

### AI Integration
*   **Groq API:** Used for content moderation. API Key is injected via `GROQ_API_KEY` (check `application.properties`).

## 📂 Key Directories
*   `src/main/resources/templates`: Thymeleaf HTML views.
*   `src/main/java/com/portfolio/my_portfolio_backend/repository`: SQL implementations.
*   `src/main/java/com/portfolio/my_portfolio_backend/controller/api`: REST Endpoints.
