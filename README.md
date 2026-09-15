# API REST de Estudiantes

Proyecto Spring Boot para administrar la entidad `estudiante` del sistema de admisiones.

## Entidad seleccionada

La entidad original tenía:

- `cif`
- `nombre`
- `periodo_admision_id`
- `estado_evaluacion`

El taller exige **un identificador y al menos cinco atributos adicionales**. Por eso se conservaron
los campos existentes y se agregaron:

- `email`
- `fecha_nacimiento`

La entidad usada por la API queda así:

| Campo | Tipo | Regla principal |
|---|---|---|
| `cif` | String | obligatorio, máximo 15 caracteres |
| `nombre` | String | obligatorio, entre 3 y 120 caracteres |
| `periodoAdmisionId` | Integer | obligatorio, mínimo 1 |
| `estadoEvaluacion` | String | PENDIENTE, APROBADO o RECHAZADO |
| `email` | String | obligatorio, formato de correo válido |
| `fechaNacimiento` | LocalDate | obligatoria y debe estar en el pasado |

## Tecnologías

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Bean Validation
- Maven

## Estructura

```text
src/main/java/com/uam/admisiones
├── controllers
│   └── EstudianteController.java
├── dto
│   └── EstudianteDto.java
├── exceptions
│   ├── ApiErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   ├── RegistroDuplicadoException.java
│   ├── RegistroNoEncontradoException.java
│   └── ValidationErrorResponse.java
├── services
│   └── EstudianteService.java
└── ApiEstudiantesApplication.java
```

## Ejecución

Requisitos:

- JDK 17 o superior
- Maven 3.9 o superior

Ejecutar:

```bash
mvn spring-boot:run
```

La API queda disponible en:

```text
http://localhost:8080
```

## Endpoints

| Método | Ruta | Función | Respuesta |
|---|---|---|---|
| GET | `/api/estudiantes` | Listar estudiantes | `200 OK` |
| GET | `/api/estudiantes/{cif}` | Buscar por CIF | `200 OK` o `404 Not Found` |
| POST | `/api/estudiantes` | Crear estudiante | `201 Created` o `400 Bad Request` |
| PUT | `/api/estudiantes/{cif}` | Actualizar estudiante | `200 OK`, `400` o `404` |
| DELETE | `/api/estudiantes/{cif}` | Eliminar estudiante | `200 OK` o `404 Not Found` |

## Ejemplo JSON

```json
{
  "cif": "2026-0001",
  "nombre": "Ana Martinez",
  "periodoAdmisionId": 1,
  "estadoEvaluacion": "PENDIENTE",
  "email": "ana.martinez@correo.com",
  "fechaNacimiento": "2005-04-18"
}
```

## Manejo de validaciones

Ejemplo de respuesta `400 Bad Request`:

```json
{
  "estado": 400,
  "mensaje": "Los datos enviados no son válidos",
  "errores": {
    "nombre": "El nombre es obligatorio",
    "email": "El correo electrónico no tiene un formato válido"
  }
}
```

Ejemplo de respuesta `404 Not Found`:

```json
{
  "estado": 404,
  "mensaje": "No se encontró un estudiante con CIF NO-EXISTE"
}
```

## Pruebas

El archivo:

```text
requests/estudiantes.http
```

incluye las ocho pruebas requeridas por el taller:

- cinco exitosas, una por cada endpoint;
- dos con datos inválidos;
- una con un identificador inexistente.

Puede ejecutarse directamente con el cliente HTTP de IntelliJ IDEA.

## Persistencia

Para este taller los datos se almacenan temporalmente en memoria mediante un `LinkedHashMap`.
Esto permite demostrar el CRUD, las validaciones, los códigos HTTP y las respuestas JSON usando
únicamente las dependencias solicitadas (`Spring Web` y `Validation`).

Al reiniciar la aplicación, los registros creados se eliminan.

El archivo `database/entidad_estudiante.sql` contiene una versión corregida de la tabla para el
modelo de datos.
