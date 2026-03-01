# 🚀 Mi Portfolio Personal

[![DigitalOcean](https://img.shields.io/badge/DigitalOcean-Deployed-blue?style=for-the-badge&logo=digitalocean&logoColor=white)](https://jortiz.dev)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)]()
[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)]()
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-green?style=for-the-badge&logo=springboot)]()
[![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)]()

Bienvenido al repositorio de mi portfolio profesional. Este proyecto es una aplicación web dinámica diseñada para mostrar mi trayectoria, habilidades y proyectos, construida con arquitectura backend robusta y desplegada en un entorno de producción real.

---

### 🟢 DEMO EN VIVO
Puedes ver la aplicación funcionando y optimizada ahora mismo aquí:

👉 **[https://jortiz.dev](https://jortiz.dev)**

> ⚡ **Rendimiento de Producción:**
> El proyecto ha sido migrado de una capa gratuita (PaaS) a un entorno de infraestructura propia (VPS) para garantizar **alta disponibilidad (24/7) y tiempos de carga instantáneos**.

---

## 💡 Contexto y Motivación del Proyecto

### ¿Por qué Java y Spring Boot para un Portfolio?
Soy consciente de que utilizar una arquitectura backend robusta en Java para una web personal podría considerarse "sobreingeniería", ya que una página estática bastaría.

Sin embargo, **el objetivo principal de este proyecto no es solo el resultado visual, sino el código y la arquitectura que lo sustentan**. He diseñado esta aplicación como un **entorno de práctica real** para:

1.  **Aplicar Arquitectura Empresarial:** Implementar patrones MVC, DTOs y Servicios en un entorno controlado.
2.  **Gestionar Datos Reales:** Salir de los ejemplos teóricos y enfrentarme a problemas de persistencia, relaciones SQL y migraciones con PostgreSQL.
3.  **Seguridad y Despliegue (DevOps):** Aprender a manejar servidores Linux, contenerización, proxies inversos, certificados SSL y pipelines de CI/CD.

En resumen: Este portfolio es mi "laboratorio" para demostrar y afianzar mis habilidades como **Backend & Software Developer**.

---

## 🔐 Arquitectura de Seguridad: Modo Dual (Público vs. Privado)

Para demostrar mis conocimientos en ciberseguridad y control de accesos, la aplicación está diseñada con un **Modo Dual** utilizando Spring Security:

* **Modo Escaparate (Público):** Cualquier visitante puede navegar por la página principal, ver mis proyectos y mi experiencia. La interfaz es limpia y de solo lectura. Thymeleaf oculta de forma dinámica cualquier botón o enlace administrativo (`sec:authorize`).
* **Modo Backoffice (Privado):** A través de una ruta de acceso segura y un formulario de Login personalizado, el propietario (yo) puede iniciar sesión. Al autenticarme contra la base de datos (contraseñas encriptadas con BCrypt), el servidor me otorga una sesión segura, la interfaz se transforma y se habilitan los paneles CRUD completos para gestionar el contenido de la web en tiempo real.

---

## 🛠️ Tecnologías y Arquitectura

### ☁️ Infraestructura y DevOps (Producción)
* **VPS (DigitalOcean):** Servidor Linux (Ubuntu) dedicado.
* **Docker & Docker Compose:** Contenerización de la aplicación y la base de datos para despliegues predecibles y aislados.
* **Nginx:** Actuando como Proxy Inverso para gestionar el tráfico web.
* **Let's Encrypt (Certbot):** Gestión de certificados SSL para conexiones seguras (HTTPS).
* **GitHub Actions:** Pipeline de CI/CD para despliegue continuo y automático al hacer push a la rama principal.
* **Gestión de Secretos:** Uso de `.env` en producción y GitHub Secrets para inyectar credenciales de forma segura.

### ⚙️ Backend (Core)
* **Java 21:** Última versión LTS del lenguaje.
* **Spring Boot 4:** Framework principal en su versión más reciente (4.0.1).
* **Spring Security 6:** Gestión de accesos, protección de rutas, autenticación y manejo de sesiones.
* **Spring Data JDBC:** Para la persistencia de datos eficiente.
* **PostgreSQL:** Base de datos relacional (Producción).
* **Lombok:** Para reducción de código repetitivo (*Boilerplate*).
* **Bean Validation:** Validación de datos de entrada (JSR-380).

### 🧪 Testing & Calidad
* **JUnit 5:** Framework de testing unitario.
* **Mockito:** Mocking de repositorios para tests de servicios aislados.

### 🎨 Frontend
* **Thymeleaf & Spring Security Dialect:** Motor de plantillas para renderizado en servidor (SSR) y renderizado condicional según roles.
* **HTML5 & CSS3:** Diseño responsivo y estructurado (Sin frameworks JS pesados).

---

## 📅 Roadmap (Estado del Proyecto)
* ✅ **Infraestructura y DevOps:** Migración a VPS propio, contenerización con Docker, Proxy Inverso con Nginx, HTTPS y pipeline CI/CD (GitHub Actions).
* ✅ **Core & Despliegue Inicial:** Arquitectura MVC, Base de Datos PostgreSQL.
* ✅ **Unit Testing:** Cobertura de Servicios (`Education`, `Experience`, `Skill`) con JUnit 5 y Mockito.
* ✅ **Gestión de Proyectos (Creación y Lectura):**
    * Formulario de creación con subida de imágenes (`MultipartFile`).
    * Configuración de almacenamiento local y visualización dinámica (`ResourceHandler`).
* ✅ **Gestión de Habilidades (CRUD Completo):**
    * Listado de administración con estilos personalizados.
    * Formulario para Crear y Editar habilidades.
    * Funcionalidad de Eliminación con confirmación de seguridad.
    * Mapeo avanzado Entidad-DTO.
* ✅ **Gestión de Trayectoria (Experiencia y Educación):**
    * Controladores y vistas implementados para el historial académico y laboral.
    * Manejo avanzado de fechas con `LocalDate` y formateo visual en Thymeleaf (`#temporals`).
* ✅ **UI/UX y Navegación Dinámica:**
    * Implementación de menú de administración dedicado (`nav-admin`).
    * Diseño Responsive mediante Media Queries para gestión desde móviles.
    * **Renderizado Condicional:** Interfaz dinámica para ocultar botones administrativos a usuarios no logueados.
    * Implementación de **Sticky Footer**.
    * Página de error personalizada (`error-page.html`).
* ✅ **Seguridad y Autenticación Avanzada (Spring Security 6):**
    * **Autenticación en Base de Datos:** Entidad `User`, DTO, Mapper, Repositorio y `UserDetailsService` personalizado.
    * Formulario de Login personalizado y manejo seguro de sesiones y cookies.
    * Hasheo de contraseñas utilizando algoritmo `BCryptPasswordEncoder`.
* [ ] **Completar CRUD Proyectos:** Implementar Update y Delete para la sección de proyectos (actualmente solo Create/Read).

---
*Desarrollado con ❤️ por José Antonio Ortiz Sánchez*