# 🚀 Mi Portfolio Personal

[![DigitalOcean](https://img.shields.io/badge/DigitalOcean-Deployed-blue?style=for-the-badge&logo=digitalocean&logoColor=white)](https://jortiz.dev)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)]()
[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)]()
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green?style=for-the-badge&logo=springboot)]()
[![Groq AI](https://img.shields.io/badge/AI_Moderation-Groq_Llama_3.1-f55036?style=for-the-badge)]()
[![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)]()
[![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)]()

Bienvenido al repositorio de mi portfolio profesional. Este proyecto es una aplicación web dinámica diseñada para mostrar mi trayectoria, habilidades y proyectos. Está construida sobre una arquitectura backend robusta y desplegada en un entorno de producción real.

---

### 🟢 DEMO EN VIVO
Explora la aplicación y la arquitectura backend directamente:

* 👉 **Website Principal:** [https://jortiz.dev](https://jortiz.dev)
* 👉 **Documentación API (Swagger):** [https://jortiz.dev/swagger-ui/index.html](https://jortiz.dev/swagger-ui/index.html)

> ⚡ **Rendimiento en Producción:**
> El proyecto está alojado en infraestructura propia (VPS), garantizando **alta disponibilidad (24/7)** y tiempos de respuesta óptimos, dejando atrás las limitaciones de las capas gratuitas (PaaS).

---

## 💡 Contexto y Motivación del Proyecto

### ¿Por qué Java y Spring Boot para un Portfolio?
Construir una web personal con un backend completo en Java podría parecer excesivo cuando una página estática cumpliría la misma función visual. Sin embargo, **el objetivo de este proyecto es demostrar el código y la arquitectura que lo sustentan**.

He diseñado esta aplicación como un **entorno de práctica y demostración real** para:

1. **Aplicar Arquitectura Empresarial:** Uso de patrones MVC, DTOs y capas de servicio en un entorno controlado.
2. **Gestionar Datos Reales:** Persistencia, relaciones SQL y optimización de consultas, yendo más allá de la teoría.
3. **Seguridad y DevOps:** Administración de servidores Linux, contenerización con Docker, configuración de proxies inversos, certificados SSL y pipelines CI/CD.
4. **Diseño de APIs Profesionales:** Creación y documentación de una API RESTful pública y consumible, aplicando estándares *stateless* y autenticación híbrida.
5. **Integración de IA:** Conexión con modelos de lenguaje (LLMs) para automatizar la moderación en tiempo real.

Este portfolio es mi carta de presentación como **Backend & Software Developer**.

---

## 🔐 Arquitectura de Seguridad: Híbrida y Segura

Para garantizar la protección de los datos y demostrar buenas prácticas en el diseño de APIs, la aplicación implementa **Spring Security 6** con un enfoque híbrido. Esto permite dar soporte tanto a clientes web tradicionales como a integraciones REST.

* **Seguridad Web (Stateful - MVC):** El panel de administración utiliza autenticación basada en sesiones (`formLogin`). Esto permite gestionar el portfolio visualmente de forma segura, manteniendo activa la protección contra ataques CSRF en todas las vistas web.
* **Seguridad API (Stateless - REST):** Las rutas bajo `/api/**` operan sin estado. Se desactiva la creación de sesiones y la protección CSRF (necesario para el estándar REST). Las operaciones de lectura (`GET`) son públicas, mientras que las acciones destructivas (`POST`, `PUT`, `DELETE`) están protegidas mediante **HTTP Basic Authentication**.

Esta dualidad asegura que el contenido de producción sea inmutable para visitantes anónimos, pero mantiene la API completamente accesible y funcional para integraciones o testing.

---

## 🤖 Moderación de Contenido con Inteligencia Artificial (LLM)

El portfolio incluye un **Libro de Visitas** interactivo. Para mantener el espacio libre de spam y contenido tóxico sin requerir moderación manual, he integrado la **API de Groq (Llama 3.1)**.

* **Validación Síncrona:** Antes de persistir cualquier comentario en PostgreSQL, el backend consulta a la IA para analizar el texto. Si se detecta contenido inapropiado, la transacción se cancela automáticamente.
* **Prompt Engineering:** La IA está configurada para tolerar lenguaje informal y abreviaturas, pero bloquea de forma estricta insultos, faltas de respeto o intentos de inyección de código.
* **Paginación Server-Side:** Para manejar un alto volumen de comentarios de forma eficiente, se utiliza la interfaz `Pageable` de Spring Data, sirviendo los datos al frontend en bloques optimizados.

---

## 📖 API REST y Documentación Interactiva (Swagger)

Los datos del proyecto se exponen a través de una API RESTful diseñada bajo estándares de la industria. Para facilitar su consumo, integra **Swagger UI / OpenAPI 3.1**.

* **Documentación Viva:** Accesible desde la propia web (sección "💻 API Docs").
* **Estructura RESTful:** Endpoints clasificados lógicamente con uso correcto de verbos y códigos de estado HTTP.
* **Esquemas Detallados:** Uso de `@Schema` en los DTOs para documentar ejemplos de peticiones y respuestas.
* **Testing Integrado:** Permite ejecutar peticiones `GET` directamente desde el navegador para comprobar la persistencia de datos en tiempo real.

---

## 🛠️ Tecnologías y Arquitectura

### ☁️ Infraestructura y DevOps (Producción)
* **VPS (DigitalOcean):** Servidor Linux (Ubuntu).
* **Docker & Docker Compose:** Contenerización de servicios.
* **Nginx:** Proxy Inverso.
* **Let's Encrypt (Certbot):** Certificados SSL (HTTPS).
* **GitHub Actions:** CI/CD para despliegue continuo.
* **Gestión de Secretos:** `.env` y GitHub Secrets.

### ⚙️ Backend (Core)
* **Java 21:** LTS actual.
* **Spring Boot 3:** Framework principal.
* **Spring Security 6:** Autenticación híbrida y protección de rutas.
* **Springdoc OpenAPI:** Documentación de la API.
* **Spring Data JDBC:** Persistencia de datos nativa y segura con `JdbcTemplate`.
* **PostgreSQL:** Base de datos relacional.
* **Lombok:** Reducción de código repetitivo.

### 🤖 Integraciones y Testing
* **Groq API:** Consumo de LLMs mediante `RestClient`.
* **JUnit 5 & Mockito:** Pruebas unitarias e integración.

### 🎨 Frontend
* **Thymeleaf & Spring Security Dialect:** Renderizado Server-Side.
* **HTML5 & CSS3:** Diseño responsivo (Grid/Flexbox) sin frameworks pesados.

---

## 📅 Roadmap de Desarrollo

* ✅ **Infraestructura:** VPS, Docker, Nginx, HTTPS y CI/CD.
* ✅ **Core:** Arquitectura MVC y PostgreSQL.
* ✅ **Seguridad:** Spring Security 6, configuración híbrida (Web Stateful + API Stateless).
* ✅ **API REST:** Separación `/web` vs `/api` y documentación con Swagger.
* ✅ **Módulo de Proyectos:** CRUD completo con gestión de imágenes.
* ✅ **Módulo de Habilidades:** Mapeo de DTOs y persistencia de ordenamiento (`ORDER BY`).
* ✅ **Libro de Visitas:** Paginación Server-Side y moderación IA en tiempo real.
* ✅ **Calidad de Código:** Refactorización a `JdbcTemplate` con transacciones seguras (`@Transactional`).
* ⏳ **Demo Interactiva:** Implementación de un usuario "Guest" con permisos restringidos para probar endpoints protegidos en la API. *(En progreso)*

---
*Desarrollado por José Antonio Ortiz Sánchez*