# 🚀 Mi Portfolio Personal

Bienvenido al repositorio de mi portfolio profesional. Este proyecto es una aplicación web dinámica diseñada para mostrar mi trayectoria, habilidades y proyectos, implementando una arquitectura de software robusta y escalable.

## 💡 Contexto y Motivación del Proyecto

### ¿Por qué Java y Spring Boot para un Portfolio?
Soy consciente de que utilizar una arquitectura backend robusta en Java para una web personal podría considerarse "sobreingeniería" (*overkill*), ya que una página estática bastaría.

Sin embargo, **el objetivo principal de este proyecto no es solo el resultado visual, sino el código que lo sustenta**. He diseñado esta aplicación como un **entorno de práctica real** para:

1.  **Aplicar Arquitectura Empresarial:** Implementar patrones MVC, DTOs y Servicios en un entorno controlado.
2.  **Gestionar Datos Reales:** Salir de los ejemplos teóricos y enfrentarme a problemas de persistencia, relaciones SQL y migraciones con PostgreSQL.
3.  **Seguridad y Despliegue:** Aprender a manejar variables de entorno y protección de credenciales.

En resumen: Este portfolio es mi "laboratorio" para demostrar y afianzar mis habilidades como **Backend Developer**.

---

## 🛠️ Tecnologías y Arquitectura

### Backend (Core)
* **Java 21 & Spring Boot 3:** Framework principal.
* **Spring Data JDBC:** Para la persistencia de datos eficiente.
* **PostgreSQL:** Base de datos relacional.
* **Lombok:** Para reducción de código repetitivo (*Boilerplate*).
* **Bean Validation:** Validación de datos de entrada (JSR-380).

### Frontend
* **Thymeleaf:** Motor de plantillas para renderizado en servidor (SSR).
* **HTML5 & CSS3:** Diseño responsivo y estructurado.

### Funcionalidades Clave implementadas
* **Arquitectura MVC:** Separación clara entre Modelos, Vistas y Controladores.
* **Patrón DTO (Data Transfer Object):** Desacoplamiento de la capa de persistencia y la vista.
* **Gestión de Archivos:** Servicio personalizado (`FileStorageService`) para la subida segura de imágenes con renombrado único (UUID).
* **Seguridad:** Gestión de credenciales mediante Variables de Entorno.

---

## 🚀 Estado del Despliegue y Ejecución

Actualmente, el proyecto está configurado para **desarrollo local** (requiere configuración de base de datos PostgreSQL y variables de entorno).

🚧 **Nota para visitantes:**
Para facilitar la visualización del proyecto sin necesidad de realizar instalaciones técnicas complejas en local, **estoy preparando el despliegue automático en la nube (Render)**.

### 📅 Roadmap (Próximos pasos)
* [x] Desarrollo del Backend y Base de Datos.
* [x] Integración de Frontend con Thymeleaf.
* [ ] **Despliegue en Producción (v1.1):** Próximamente se incluirá aquí el enlace a la demo en vivo.

Si eres reclutador o desarrollador y deseas revisar el código fuente, la estructura del proyecto sigue los estándares habituales de Maven y Spring Boot.

---