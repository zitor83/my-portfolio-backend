INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) VALUES
('José Antonio', 'Ortiz Sánchez', 'Java Backend Developer', 'Perfil híbrido que combina la visión estratégica de la Diplomatura en Empresariales con la especialización técnica en desarrollo Backend. Más de 10 años liderando equipos en entornos de alta presión, ahora enfocado en construir arquitecturas robustas con Java y Spring Boot.', 'img/dev-gabriel.png', 1, 'ortizsanchezjoseantonio@gmail.com', '+34 678 93 70 01', 'https://www.linkedin.com/in/jose-antonio-ortiz-sanchez/', 'https://github.com/zitor83');

INSERT INTO skills (name, level_percentage, icon_class, personal_info_id) VALUES
('Java', 80, 'img/logos/java.png', 1),
('Spring Boot', 70, 'img/logos/java.png', 1),
('HTML-5', 85, 'img/logos/html-5.png', 1),
('CSS', 75, 'img/logos/css-3.png', 1),
('JavaScript', 75, 'img/logos/js.png', 1),
('Angular', 65, 'img/logos/js.png', 1),
('PostgreSQL', 75, 'img/logos/servidor-sql.png', 1),
('Github', 85, 'img/logos/github.png', 1),
('Liderazgo de Equipos', 95, 'img/logos/js.png', 1),
('Gestión de Negocio', 90, 'img/logos/js.png', 1);

INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id) VALUES
('Desarrollo de Aplicaciones Web (DAW)', 'CESUR', '2024-09-15', NULL, 'Especialización en desarrollo Full Stack con foco en arquitecturas Java/Spring y despliegue de aplicaciones.', 1),
('Certificado de Profesionalidad - Programación (Java/SQL)','CPIFP Alan Turing','2025-04-13','2025-12-9','Enfoque practico en Java y SQL',1),
('Diplomatura en Ciencias Empresariales', 'Universidad de Málaga (UMA)', '2004-10-01', '2008-06-30', 'Enfoque en gestión de operaciones, finanzas y dirección de recursos humanos.', 1),
('Formación en Idioma Inglés', 'Escuela oficial de idiomas', '2002-08-14', '2008-06-30', 'Desarrollo de competencias lingüísticas para entornos profesionales y técnicos.', 1);

INSERT INTO experiences (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES
('Desarrollador Web (Prácticas)', 'Aliquindoi', '2025-11-03','2025-12-29', 'Colaboración en el desarrollo de soluciones tecnológicas y aplicación de metodologías ágiles en proyectos reales.', 1),
('AI Data Trainer / Evaluador de Modelos de IA (Freelance)','Outlier - TELUS','2024-10-1','2025-02-26','Análisis y evaluación de prompts para modelos LLM/NLP, realizando fine-tuning y control de calidad (QA).',1),
('Operations & Team Manager', 'Sector Hospitality', '2014-01-01', '2024-08-31', 'Liderazgo de equipos multidisciplinares, optimización de procesos operativos y gestión de KPIs en entornos de gran volumen.', 1);

INSERT INTO projects (title, description, image_url, project_url, personal_info_id) VALUES
('Portfolio Profesional', 'Desarrollo de este sitio web dinámico para centralizar mi trayectoria técnica y de gestión.', 'img/projects/project2.jpg', 'https://github.com/jaortiz/my-portfolio', 1);