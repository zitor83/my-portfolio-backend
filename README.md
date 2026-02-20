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

## 🛠️ Tecnologías y Arquitectura

### Backend (Core)
* **Java 21:** Última versión LTS del lenguaje.
* **Spring Boot 4:** Framework principal en su versión más reciente (4.0.1).
* **Spring Security:** Gestión de accesos, protección de rutas y autenticación.
* **Spring Data JDBC:** Para la persistencia de datos eficiente.
* **PostgreSQL:** Base de datos relacional (Producción).
* **Lombok:** Para reducción de código repetitivo (*Boilerplate*).
* **Bean Validation:** Validación de datos de entrada (JSR-380).

### Testing & Calidad
* **JUnit 5:** Framework de testing unitario.
* **Mockito:** Mocking de repositorios para tests de servicios aislados.
* **Coverage:** Validación de lógica de negocio y manejo de excepciones (`ValidationException`).

### Frontend
* **Thymeleaf:** Motor de plantillas para renderizado en servidor (SSR).
* **HTML5 & CSS3:** Diseño responsivo y estructurado (Sin frameworks JS pesados).

### Funcionalidades Clave implementadas
* **Arquitectura MVC:** Separación clara entre Modelos, Vistas y Controladores.
* **Patrón DTO (Data Transfer Object):** Desacoplamiento de la capa de persistencia y la vista.
* **Gestión de Proyectos Multimedia (Fase 1):** Sistema de creación y visualización que incluye:
    * Subida de imágenes al servidor (*File Upload*).
    * Renombrado seguro de archivos mediante UUID.
    * Configuración de recursos estáticos para visualización en tiempo real.
    * Control de formato (JPG/PNG) y peso optimizado (Max 1MB).
* **Manejo de Errores:** Páginas personalizadas y captura de excepciones (I/O) para una UX robusta.

---

## 🛡️ Política de Contacto y Privacidad

Como desarrollador Backend, la seguridad y la privacidad de los datos son prioritarias. Por ello, he implementado las siguientes medidas en el Frontend:

1.  **Protección de Datos:** No se exponen datos sensibles (teléfono, email personal) en el código fuente HTML para evitar el *scraping* por parte de bots y spammers.
2.  **Formulario Seguro:** La comunicación se realiza a través de un formulario de contacto integrado con **Formspree**, garantizando que los mensajes lleguen sin comprometer la privacidad.

---

## 📅 Roadmap (Estado del Proyecto)
* ✅ **Core & Despliegue:** Arquitectura MVC, Base de Datos PostgreSQL y Dockerización en Render.
* ✅ **Unit Testing:** Cobertura de Servicios (`Education`, `Experience`, `Skill`) con JUnit 5 y Mockito.
* ✅ **Gestión de Proyectos (Creación y Lectura):**
    * Formulario de creación con subida de imágenes (`MultipartFile`).
    * Configuración de almacenamiento local y visualización dinámica (`ResourceHandler`).
    * Integración de la sección dinámica en la Landing Page.
* ✅ **Gestión de Habilidades (CRUD Completo):**
    * Listado de administración con estilos personalizados.
    * Formulario para Crear y Editar habilidades.
    * Funcionalidad de Eliminación con confirmación de seguridad.
    * Mapeo avanzado Entidad-DTO.
* ✅ **Gestión de Trayectoria (Experiencia y Educación):**
    * Controladores y vistas implementados para el historial académico y laboral.
    * Manejo avanzado de fechas con `LocalDate` y formateo visual en Thymeleaf (`#temporals`).
    * Lógica condicional para experiencias "Actuales".
* ✅ **Gestión de Información Personal (Perfil):**
    * Estrategia "Single-User" (ID forzado) para gestión exclusiva del propietario.
    * Arquitectura refactorizada: Validación web movida al Controlador, dejando el Servicio puro.
    * Feedback al usuario corregido (`RedirectAttributes` vs `Model`).
* ✅ **UI/UX y Navegación (Backoffice):**
    * Implementación de menú de administración dedicado (`nav-admin`) y separación pública/privada.
    * Diseño Responsive mediante Media Queries para gestión desde móviles.
    * Implementación de **Sticky Footer** usando Flexbox (100vh) para evitar espacios en blanco.
    * Maquetación avanzada de tablas y formularios.
    * Página de error personalizada (`error-page.html`).
* ✅ **Seguridad y Autenticación (Spring Security):**
    * Integración de `spring-boot-starter-security`.
    * Configuración granular de rutas (`SecurityFilterChain`): Backoffice protegido (`.authenticated()`) y Landing Page pública (`.permitAll()`).
    * **Autenticación en Base de Datos:** Implementación de entidad `User`, DTO, Mapper y capa de persistencia (PostgreSQL).
    * Implementación de `UserDetailsService` para conectar la base de datos con el flujo de login.
    * Hasheo seguro de contraseñas utilizando el algoritmo `BCryptPasswordEncoder`.
* [ ] **Completar CRUD Proyectos:** Implementar Update y Delete para la sección de proyectos (actualmente solo Create/Read).

---
*Desarrollado con ❤️ por José Antonio Ortiz Sánchez*