# RateTheAnime

RateTheAnime es una API REST desarrollada con Spring Boot que permite a los usuarios registrarse, autenticarse mediante JWT y gestionar una colección de animes calificados. El proyecto fue desarrollado como parte del proceso formativo de Desarrollo Full Stack y tiene como objetivo aplicar conceptos de arquitectura REST, persistencia de datos, seguridad y documentación de APIs.

---

## Características

* Registro y autenticación de usuarios.
* Seguridad basada en JWT (JSON Web Token).
* Gestión de usuarios.
* Gestión de animes calificados.
* Operaciones CRUD completas.
* Persistencia de datos con JPA e Hibernate.
* Base de datos MySQL.
* Documentación interactiva con Swagger/OpenAPI.
* Soporte para Docker y Docker Compose.

---

## Tecnologías Utilizadas

| Tecnología      | Descripción                   |
| --------------- | ----------------------------- |
| Java 21         | Lenguaje principal            |
| Spring Boot 4   | Framework backend             |
| Spring Security | Seguridad y autenticación     |
| JWT             | Autorización basada en tokens |
| Spring Data JPA | Persistencia de datos         |
| Hibernate       | ORM                           |
| MySQL           | Base de datos                 |
| Maven           | Gestión de dependencias       |
| Swagger/OpenAPI | Documentación de API          |
| Docker          | Contenedorización             |

---

## Estructura del Proyecto

```text
src/main/java/com/duoc/ratetheanime
│
├── Controller
├── dto
├── model
├── repository
├── security
├── service
├── config
└── exception
```

### Descripción de Capas

* **Controller:** Expone los endpoints REST.
* **Service:** Contiene la lógica de negocio.
* **Repository:** Acceso a datos mediante JPA.
* **Model:** Entidades de la base de datos.
* **DTO:** Objetos de transferencia de datos.
* **Security:** Configuración JWT y filtros de autenticación.
* **Exception:** Manejo global de errores.

---

## Configuración

### Requisitos Previos

* Java 21
* Maven
* MySQL
* Docker (opcional)
* Postman (opcional)

### Configuración de Base de Datos

Crear una base de datos llamada:

```sql
CREATE DATABASE ratetheanime;
```

Configuración utilizada:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/ratetheanime
spring.datasource.username=root
spring.datasource.password=root
```

Modificar estos valores según el entorno local.

---

## Ejecución del Proyecto

### Desde el IDE

1. Abrir el proyecto.
2. Esperar que Maven descargue las dependencias.
3. Ejecutar:

```java
RatetheanimeApplication.java
```

La aplicación iniciará en:

```text
http://localhost:8080
```

---

## Autenticación

El sistema utiliza JWT para proteger los endpoints.

### Registro

```http
POST /api/v1/auth/register
```

### Inicio de Sesión

```http
POST /api/v1/auth/login
```

Al autenticarse correctamente se obtiene un token JWT que debe enviarse en el header:

```http
Authorization: Bearer TOKEN
```

---

## Endpoints Principales

### Autenticación

| Método | Endpoint              |
| ------ | --------------------- |
| POST   | /api/v1/auth/register |
| POST   | /api/v1/auth/login    |

### Usuarios

| Método | Endpoint              |
| ------ | --------------------- |
| GET    | /api/v1/usuarios      |
| GET    | /api/v1/usuarios/{id} |
| POST   | /api/v1/usuarios      |
| PUT    | /api/v1/usuarios/{id} |
| DELETE | /api/v1/usuarios/{id} |

### Animes Calificados

| Método | Endpoint              |
| ------ | --------------------- |
| GET    | /api/ratedanimes      |
| GET    | /api/ratedanimes/{id} |
| POST   | /api/ratedanimes      |
| PUT    | /api/ratedanimes/{id} |
| DELETE | /api/ratedanimes/{id} |

### Búsqueda de Anime

| Método | Endpoint             |
| ------ | -------------------- |
| GET    | /api/v1/anime/buscar |

---

## Documentación Swagger

Una vez ejecutada la aplicación, la documentación estará disponible en:

```text
http://localhost:8080/swagger-ui.html
```

o

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Docker

El proyecto incluye:

```text
Dockerfile
docker-compose.yml
```

Para ejecutar:

```bash
docker-compose up --build
```

---

## Pruebas Unitarias

El proyecto incluye pruebas unitarias para validar el correcto funcionamiento de los componentes principales de la aplicación.

## Ejecutar Pruebas Unitarias:

```text
.\mvnw.cmd test
```
## Tecnologias Utilizadas:

* JUnit5
* Spring Boot Test
* Mockito

---

## Autores

* Augusto Medina
* Arturo Cabello

---




