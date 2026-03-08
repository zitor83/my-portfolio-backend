# 🚀 Mi Portfolio Personal

[![DigitalOcean](https://img.shields.io/badge/DigitalOcean-Deployed-blue?style=for-the-badge&logo=digitalocean&logoColor=white)](https://jortiz.dev)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)]()
[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)]()
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-green?style=for-the-badge&logo=springboot)]()
[![Groq AI](https://img.shields.io/badge/AI_Moderation-Groq_Llama_3-f55036?style=for-the-badge)]()
[![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)]()
[![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)]()

Bienvenido al repositorio de mi portfolio profesional. Este proyecto es una aplicación web dinámica diseñada para mostrar mi trayectoria, habilidades y proyectos, construida con arquitectura backend robusta y desplegada en un entorno de producción real.

---

### 🟢 DEMO EN VIVO
Puedes probar la aplicación y explorar la arquitectura backend ahora mismo:

👉 **Website Principal:** [https://jortiz.dev](https://jortiz.dev)
👉 **Documentación API (Swagger):** [https://jortiz.dev/swagger-ui/index.html](https://jortiz.dev/swagger-ui/index.html)

> ⚡ **Rendimiento de Producción:**
> El proyecto ha sido migrado de una capa gratuita (PaaS) a un entorno de infraestructura propia (VPS) para garantizar **alta disponibilidad (24/7) y tiempos de carga instantáneos**.

---

## 💡 Contexto y Motivación del Proyecto

### ¿Por qué Java y Spring Boot para un Portfolio?
Soy consciente de que utilizar una arquitectura backend robusta en Java para una web personal podría considerarse "sobreingeniería", ya que una página estática bastaría.

Sin embargo, **el objetivo principal de este proyecto no es solo el resultado visual, sino el código y la arquitectura que lo sustentan**. He diseñado esta aplicación como un **entorno de práctica real** para:

1.  **Aplicar Arquitectura Empresarial:** Implementar patrones MVC, DTOs y Servicios en un entorno controlado.
2.  **Gestionar Datos Reales:** Salir de los ejemplos teóricos y enfrentarme a problemas de persistencia, relaciones SQL y optimización de consultas.
3.  **Seguridad y Despliegue (DevOps):** Aprender a manejar servidores Linux, contenerización, proxies inversos, certificados SSL y pipelines de CI/CD.
4.  **Diseño de APIs Profesionales:** Construir y documentar una API RESTful pública lista para ser consumida por terceros.
5.  **Integración de Inteligencia Artificial:** Conectar con modelos de lenguaje (LLMs) externos para la toma de decisiones automatizada en tiempo real.

En resumen: Este portfolio es mi "laboratorio" para demostrar y afianzar mis habilidades como **Backend & Software Developer**.

---

## 🤖 Moderación de Contenido con Inteligencia Artificial (LLM)

El portfolio incluye un **Libro de Visitas** interactivo. Para garantizar un entorno seguro y libre de spam sin requerir moderación manual, he integrado un modelo de lenguaje de IA a través de la **API de Groq (Llama 3.1)**.

* **Validación Síncrona:** El backend se comunica con la IA para analizar tanto el nombre del autor como el mensaje *antes* de persistir los datos en PostgreSQL. Si la IA detecta contenido inapropiado, la transacción se aborta instantáneamente.
* **Prompt Engineering:** El sistema utiliza instrucciones precisas para tolerar lenguaje informal, entusiasta o abreviaturas, pero bloqueando estrictamente insultos, ataques o inyecciones de código.
* **Optimización de Memoria (Paginación Server-Side):** Para manejar un volumen alto de comentarios, se ha implementado la interfaz `Pageable` de Spring Data. Los comentarios se sirven al frontend en bloques pequeños, procesados completamente del lado del servidor.

---

## 🔐 Arquitectura de Seguridad: Modo Dual (Público vs. Privado)

Para demostrar mis conocimientos en ciberseguridad y control de accesos, la aplicación está diseñada con un **Modo Dual** utilizando Spring Security 6:

* **Modo Escaparate (Vistas Públicas y API GET):** Cualquier visitante puede navegar por la página principal (Thymeleaf) o consumir los endpoints `GET` de la API REST para consultar mis datos en formato JSON.
* **Modo Backoffice (Vistas Privadas y API CUD):** A través de una ruta segura y un formulario de Login, el administrador se autentica contra la base de datos (contraseñas con BCrypt). Solo con una sesión válida se habilitan las vistas de administración y se permite el acceso a los métodos destructivos de la API (`POST`, `PUT`, `DELETE`), devolviendo un estricto código `403 Forbidden` a cualquier intento no autorizado.

---

## 📖 API REST y Documentación Interactiva (Swagger / OpenAPI 3)

El proyecto expone sus datos a través de una API RESTful diseñada bajo estándares profesionales. Para facilitar su exploración y consumo, he integrado **Swagger UI / OpenAPI 3.1**.
*Puedes explorarla en vivo aquí: [Swagger UI - Portfolio API](https://jortiz.dev/swagger-ui/index.html).*

* **Documentación Viva:** Disponible directamente desde la interfaz web (botón "💻 API Docs" en la navegación).
* **Estructura Organizada:** Endpoints clasificados lógicamente (Perfil, Proyectos, Experiencia, etc.) e implementando el estándar REST (pluralización de rutas, uso correcto de códigos HTTP).
* **Esquemas Claros:** Uso de anotaciones `@Schema` en los DTOs para proporcionar ejemplos de peticiones y respuestas realistas.
* **Pruebas en Vivo:** Posibilidad de ejecutar peticiones `GET` directamente desde el navegador, mientras que las operaciones de modificación quedan bloqueadas por Spring Security para usuarios anónimos.

---

## 🛠️ Tecnologías y Arquitectura

### ☁️ Infraestructura y DevOps (Producción)
* **VPS (DigitalOcean):** Servidor Linux (Ubuntu) dedicado.
* **Docker & Docker Compose:** Contenerización de la aplicación y la base de datos.
* **Nginx:** Actuando como Proxy Inverso para gestionar el tráfico web.
* **Let's Encrypt (Certbot):** Gestión de certificados SSL para conexiones seguras (HTTPS).
* **GitHub Actions:** Pipeline de CI/CD para despliegue continuo y automático.
* **Gestión de Secretos:** Uso de `.env` en producción y GitHub Secrets.

### ⚙️ Backend (Core)
* **Java 21:** Última versión LTS del lenguaje.
* **Spring Boot 4:** Framework principal.
* **Spring Security 6:** Gestión de accesos, autenticación y protección de API.
* **Springdoc OpenAPI:** Generación automática de documentación Swagger.
* **Spring Data JDBC & Pageable:** Para la persistencia de datos (implementaciones nativas `JdbcTemplate`) y paginación del lado del servidor.
* **PostgreSQL:** Base de datos relacional (Producción).
* **Lombok:** Para reducción de *Boilerplate*.
* **Bean Validation:** Validación de datos de entrada (JSR-380).

### 🤖 Integraciones Externas
* **Groq API:** Consumo de modelos LLM (Llama 3.1 8b) mediante `RestClient` para clasificación de textos.

### 🧪 Testing & Calidad
* **JUnit 5 & Mockito:** Frameworks para testing unitario y mocking de dependencias.

### 🎨 Frontend
* **Thymeleaf & Spring Security Dialect:** Motor de plantillas (SSR) y renderizado condicional.
* **HTML5 & CSS3:** Diseño responsivo (CSS Grid / Flexbox) sin dependencias de frameworks externos.

---

## 📅 Roadmap (Estado del Proyecto)
* ✅ **Infraestructura y DevOps:** Migración a VPS propio, Docker, Nginx, HTTPS y CI/CD.
* ✅ **Core & Despliegue Inicial:** Arquitectura MVC, PostgreSQL.
* ✅ **Seguridad y Autenticación:** Spring Security 6, BCrypt, Modo Dual (Público/Privado).
* ✅ **Diseño de API REST:** Separación de controladores (`/web` vs `/api`), endpoints protegidos.
* ✅ **Documentación Swagger:** Integración de OpenAPI 3.1 con interfaz interactiva.
* ✅ **Gestión de Proyectos (CRUD Completo):** Listado, Creación con subida de imágenes, Edición inteligente (mantenimiento de imágenes) y Borrado en cascada.
* ✅ **Gestión de Habilidades y Trayectoria:** CRUD completo, vistas de administración, mapeo de DTOs y persistencia de ordenamiento (`ORDER BY`).
* ✅ **Libro de Visitas e Inteligencia Artificial:** Integración de API de Groq para moderación síncrona en tiempo real y protección mediante Honeypot.
* ✅ **Rendimiento UI/UX:** Implementación de paginación de datos (Server-Side) con `Pageable`, diseño en cuadrículas CSS Grid y navegación sin recargas innecesarias.
* ✅ **Calidad de Código:** Refactorización de repositorios con `JdbcTemplate` para consultas nativas seguras y transacciones en capa de servicio (`@Transactional`).

---
*Desarrollado con ❤️ por José Antonio Ortiz Sánchez*