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
* **Seguridad:** Gestión de credenciales mediante Variables de Entorno.

---

## 🛡️ Política de Contacto y Privacidad

Como desarrollador Backend, la seguridad y la privacidad de los datos son prioritarias. Por ello, he implementado las siguientes medidas en el Frontend:

1.  **Protección de Datos:** No se exponen datos sensibles (teléfono, email personal) en el código fuente HTML para evitar el *scraping* por parte de bots y spammers.
2.  **Formulario Seguro:** La comunicación se realiza a través de un formulario de contacto integrado con **Formspree**, garantizando que los mensajes lleguen sin comprometer la privacidad.

---

## 📅 Roadmap (Estado del Proyecto)

Este proyecto está vivo y en constante evolución. Tras completar el MVP y el despliegue, el desarrollo se centra ahora en la **gestión dinámica de contenido y la calidad**:

* [x] **Core & Despliegue:** Arquitectura MVC, Base de Datos PostgreSQL y Dockerización en Render.
* [x] **Unit Testing:** Cobertura de Servicios (`Education`, `Experience`, `Skill`) con JUnit 5 y Mockito.
* [x] **Gestión de Proyectos (Creación y Lectura):**
    * Formulario de creación con subida de imágenes (`MultipartFile`).
    * Configuración de almacenamiento local y visualización dinámica (`ResourceHandler`).
    * Integración de la sección dinámica en la Landing Page (Fragmentos Thymeleaf).
* [x] **Refactorización UI/UX:**
    * Maquetación avanzada de la tabla de administración.
    * Página de error personalizada (`error-page.html`) integrada con el diseño.
* [ ] **Completar CRUD (Update & Delete):**
    * Implementar botón de borrado (Eliminación en BD y limpieza de archivos físicos).
    * Implementar formulario de edición (Gestión de reemplazo de imágenes).
* [ ] **Navegación Backoffice:** Conectar la Landing Page (Pública) con el Panel de Administración (Privado) mediante un acceso gestionado.
* [ ] **Seguridad Avanzada:** Implementación de panel de administración con **Spring Security** para proteger las rutas de gestión.
* [ ] **Integration Testing:** Tests de Controladores (`MockMvc`) y flujos completos.

---
*Desarrollado con ❤️ por José Antonio Ortiz Sánchez*