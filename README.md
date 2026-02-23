# 🚀 Mi Portfolio Personal

[![Render](https://img.shields.io/badge/Render-Deployed-success?style=for-the-badge&logo=render&logoColor=white)](https://my-portfolio-backend-454a.onrender.com)
![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-green?style=for-the-badge&logo=springboot)

Bienvenido al repositorio de mi portfolio profesional. Este proyecto es una aplicación web dinámica diseñada para mostrar mi trayectoria, habilidades y proyectos.

---

### 🟢 DEMO EN VIVO
Puedes ver la aplicación funcionando ahora mismo aquí:

👉 **[https://my-portfolio-backend-454a.onrender.com](https://my-portfolio-backend-454a.onrender.com)**

> ⚠️ **Aviso de Tiempo de Carga:**
> Al estar alojado en la capa gratuita de Render, el servidor entra en "reposo" cuando no se usa.
> **La primera carga puede tardar unos 50 segundos en arrancar.** ¡Gracias por tu paciencia!

---

## 💡 Contexto y Motivación del Proyecto

### ¿Por qué Java y Spring Boot para un Portfolio?
Soy consciente de que utilizar una arquitectura backend robusta en Java para una web personal podría considerarse "sobreingeniería", ya que una página estática bastaría.

Sin embargo, **el objetivo principal de este proyecto no es solo el resultado visual, sino el código que lo sustenta**. He diseñado esta aplicación como un **entorno de práctica real** para:

1.  **Aplicar Arquitectura Empresarial:** Implementar patrones MVC, DTOs y Servicios en un entorno controlado.
2.  **Gestionar Datos Reales:** Salir de los ejemplos teóricos y enfrentarme a problemas de persistencia, relaciones SQL y migraciones con PostgreSQL.
3.  **Seguridad y Despliegue:** Aprender a manejar variables de entorno, protección de credenciales y ciclos de vida de desarrollo de software (SDLC).

En resumen: Este portfolio es mi "laboratorio" para demostrar y afianzar mis habilidades como **Backend Developer**.

---

## 🔐 Arquitectura de Seguridad: Modo Dual (Público vs. Privado)

Para demostrar mis conocimientos en ciberseguridad y control de accesos, la aplicación está diseñada con un **Modo Dual** utilizando Spring Security:

* **Modo Escaparate (Público):** Cualquier visitante puede navegar por la página principal, ver mis proyectos y mi experiencia. La interfaz es limpia y de solo lectura. Thymeleaf oculta de forma dinámica cualquier botón o enlace administrativo (`sec:authorize`).
* **Modo Backoffice (Privado):** A través de una ruta de acceso segura y un formulario de Login personalizado, el propietario (yo) puede iniciar sesión. Al autenticarme contra la base de datos (contraseñas encriptadas con BCrypt), el servidor me otorga una sesión segura, la interfaz se transforma y se habilitan los paneles CRUD completos para gestionar el contenido de la web en tiempo real.

---

## 🛠️ Tecnologías y Arquitectura

### Backend (Core)
* **Java 21:** Última versión LTS del lenguaje.
* **Spring Boot 4:** Framework principal en su versión más reciente (4.0.1).
* **Spring Security 6:** Gestión de accesos, protección de rutas, autenticación y manejo de sesiones.
* **Spring Data JDBC:** Para la persistencia de datos eficiente.
* **PostgreSQL:** Base de datos relacional (Producción).
* **Lombok:** Para reducción de código repetitivo (*Boilerplate*).
* **Bean Validation:** Validación de datos de entrada (JSR-380).

### Testing & Calidad
* **JUnit 5:** Framework de testing unitario.
* **Mockito:** Mocking de repositorios para tests de servicios aislados.

### Frontend
* **Thymeleaf & Spring Security Dialect:** Motor de plantillas para renderizado en servidor (SSR) y renderizado condicional según roles.
* **HTML5 & CSS3:** Diseño responsivo y estructurado (Sin frameworks JS pesados).

---

## 📅 Roadmap (Estado del Proyecto)
* ✅ **Core & Despliegue:** Arquitectura MVC, Base de Datos PostgreSQL y Dockerización en Render.
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
    * **Renderizado Condicional:** Interfaz dinámica mediante `thymeleaf-extras-springsecurity6` para ocultar botones administrativos a usuarios no logueados.
    * Implementación de **Sticky Footer** usando Flexbox (100vh) para evitar espacios en blanco.
    * Página de error personalizada (`error-page.html`).
* ✅ **Seguridad y Autenticación Avanzada (Spring Security 6):**
    * **Autenticación en Base de Datos:** Entidad `User`, DTO, Mapper, Repositorio (PostgreSQL) y `UserDetailsService` personalizado.
    * Formulario de Login personalizado (`/login`) con manejo de errores, redirección inteligente de sesiones activas y mensajes de éxito (`?error` y `?logout`).
    * Mecanismo seguro de cierre de sesión (`/logout`) con invalidación de sesión (`invalidateHttpSession`) y borrado de cookies.
    * Hasheo de contraseñas utilizando algoritmo `BCryptPasswordEncoder`.
* [ ] **Completar CRUD Proyectos:** Implementar Update y Delete para la sección de proyectos (actualmente solo Create/Read).

---
*Desarrollado con ❤️ por José Antonio Ortiz Sánchez*