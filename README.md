# Módulo BFA Espacial - API REST

Proyecto desarrollado en Spring Boot como parte del **Módulo de Test IQ - BFA Dimensional y Espacial**.

El módulo BFA Espacial forma parte de la plataforma IQ Test y contempla la gestión de información relacionada con estudiantes, períodos académicos, versiones de formulario, aplicación de subtests y resultados psicométricos.

En este taller se implementa una primera capa de servicios REST sobre entidades pertenecientes al modelo del proyecto.

Actualmente se encuentran desarrollados los endpoints correspondientes a las entidades:

- `Estudiante`
- `Periodo Académico`
- `Baremo`

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Bean Validation
- Maven
- Postman

---

## Estructura del proyecto

El código se encuentra organizado utilizando una separación por responsabilidades:

```text
src/main/java/com/uam/admisiones
├── controllers
│   ├── BaremoController.java
│   ├── EstudianteController.java
│   └── PeriodoAcademicoController.java
│
├── dto
│   ├── BaremoDto.java
│   ├── EstudianteDto.java
│   └── PeriodoAcademicoDto.java
│
├── exceptions
│   ├── ApiErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   ├── RegistroDuplicadoException.java
│   ├── RegistroNoEncontradoException.java
│   └── ValidationErrorResponse.java
│
├── services
│   ├── BaremoService.java
│   ├── EstudianteService.java
│   └── PeriodoAcademicoService.java
│
└── ApiEstudiantesApplication.java
```

Además, el proyecto contiene carpetas para scripts SQL, pruebas y evidencias:

```text
database/
├── entidad_baremo.sql
├── entidad_estudiante.sql
└── entidad_periodo_academico.sql

requests/
├── baremos.http
└── estudiantes.http

evidencias/
├── Prueba Endpoints Entidad Baremo.pdf
├── Prueba Endpoints Entidad Estudiante.pdf
└── Prueba Endpoints Entidad Periodo Academico.pdf
```

---

# Entidades implementadas

## Estudiante

La entidad `Estudiante` representa al estudiante que participa dentro del contexto del módulo BFA Espacial.

Para cumplir con los requisitos del taller, el DTO contiene un identificador y cinco atributos adicionales.

| Campo | Tipo | Validación principal |
|---|---|---|
| `cif` | String | obligatorio, máximo 15 caracteres |
| `nombre` | String | obligatorio, entre 3 y 120 caracteres |
| `periodoAdmisionId` | Integer | obligatorio y mayor que cero |
| `estadoEvaluacion` | String | PENDIENTE, APROBADO o RECHAZADO |
| `email` | String | obligatorio y formato de correo válido |
| `fechaNacimiento` | LocalDate | obligatoria y debe pertenecer al pasado |

### Ejemplo

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

---

## Periodo Académico

La entidad `Periodo Académico` representa los períodos en los cuales pueden desarrollarse las aplicaciones del módulo BFA Espacial.

La entidad contiene un identificador y cinco atributos adicionales.

| Campo | Tipo | Validación principal |
|---|---|---|
| `id` | Integer | obligatorio y mayor que cero |
| `codigo` | String | obligatorio, máximo 20 caracteres |
| `fechaInicio` | LocalDate | obligatoria |
| `fechaFin` | LocalDate | obligatoria |
| `estado` | String | obligatorio, máximo 20 caracteres |
| `versionActivaId` | Integer | obligatorio y mayor que cero |

Adicionalmente, el servicio valida que la fecha de finalización no sea anterior a la fecha de inicio.

### Ejemplo

```json
{
  "id": 1,
  "codigo": "2026-2",
  "fechaInicio": "2026-08-17",
  "fechaFin": "2026-12-05",
  "estado": "ACTIVO",
  "versionActivaId": 1
}
```

---

## Baremo

La entidad `Baremo` representa la tabla de conversión que relaciona una puntuación directa con un percentil, según el factor espacial y la versión del formulario.

El DTO contiene un identificador y seis atributos adicionales.

| Campo | Tipo | Validación principal |
|---|---|---|
| `id` | Integer | obligatorio y mayor que cero |
| `versionFormularioId` | Integer | obligatorio y mayor que cero |
| `factor` | String | obligatorio: S1A, S1B, S1, S2 o ST |
| `puntuacionDirecta` | Integer | obligatoria y no negativa |
| `percentil` | Integer | obligatorio, entre 1 y 99 |
| `vigencia` | LocalDate | obligatoria |
| `estado` | String | obligatorio: ACTIVO o INACTIVO |

### Ejemplo

```json
{
  "id": 1,
  "versionFormularioId": 1,
  "factor": "S1",
  "puntuacionDirecta": 12,
  "percentil": 45,
  "vigencia": "2026-08-17",
  "estado": "ACTIVO"
}
```

---

# Endpoints

## Estudiante

Ruta base:

```text
/api/estudiantes
```

| Método | Ruta | Función | Respuesta |
|---|---|---|---|
| GET | `/api/estudiantes` | Listar estudiantes | `200 OK` |
| GET | `/api/estudiantes/{cif}` | Buscar estudiante por CIF | `200 OK` o `404 Not Found` |
| POST | `/api/estudiantes` | Registrar estudiante | `201 Created` o `400 Bad Request` |
| PUT | `/api/estudiantes/{cif}` | Actualizar estudiante | `200 OK`, `400 Bad Request` o `404 Not Found` |
| DELETE | `/api/estudiantes/{cif}` | Eliminar estudiante | `200 OK` o `404 Not Found` |

---

## Periodo Académico

Ruta base:

```text
/api/periodos-academicos
```

| Método | Ruta | Función | Respuesta |
|---|---|---|---|
| GET | `/api/periodos-academicos` | Listar períodos académicos | `200 OK` |
| GET | `/api/periodos-academicos/{id}` | Buscar período por identificador | `200 OK` o `404 Not Found` |
| POST | `/api/periodos-academicos` | Registrar período académico | `201 Created` o `400 Bad Request` |
| PUT | `/api/periodos-academicos/{id}` | Actualizar período académico | `200 OK`, `400 Bad Request` o `404 Not Found` |
| DELETE | `/api/periodos-academicos/{id}` | Eliminar período académico | `200 OK` o `404 Not Found` |

---

## Baremo

Ruta base:

```text
/api/baremos
```

| Método | Ruta | Función | Respuesta |
|---|---|---|---|
| GET | `/api/baremos` | Listar baremos | `200 OK` |
| GET | `/api/baremos/{id}` | Buscar baremo por identificador | `200 OK` o `404 Not Found` |
| POST | `/api/baremos` | Registrar baremo | `201 Created` o `400 Bad Request` |
| PUT | `/api/baremos/{id}` | Actualizar baremo | `200 OK`, `400 Bad Request` o `404 Not Found` |
| DELETE | `/api/baremos/{id}` | Eliminar baremo | `200 OK` o `404 Not Found` |

---

# Validación de datos

Los DTO utilizan Bean Validation para verificar la información recibida antes de ejecutar las operaciones correspondientes.

Entre las anotaciones utilizadas se encuentran:

```java
@NotBlank
@NotNull
@Size
@Min
@Positive
@Email
@Past
@Pattern
```

Los endpoints que reciben información mediante `POST` y `PUT` utilizan:

```java
@Valid
```

Esto permite que Spring Boot valide automáticamente los datos enviados.

---

# Manejo de errores

El proyecto utiliza un manejador global de excepciones mediante:

```java
@RestControllerAdvice
```

La clase `GlobalExceptionHandler` permite devolver los errores en formato JSON y utilizar códigos HTTP acordes con cada situación.

## Datos inválidos

Cuando los datos enviados no cumplen las validaciones se devuelve:

```text
400 Bad Request
```

Ejemplo:

```json
{
  "estado": 400,
  "mensaje": "Los datos enviados no son válidos",
  "errores": {
    "codigo": "El código es obligatorio",
    "versionActivaId": "El ID de la versión activa debe ser mayor que cero"
  }
}
```

## Registro no encontrado

Cuando se intenta consultar, actualizar o eliminar un identificador inexistente se devuelve:

```text
404 Not Found
```

Ejemplo:

```json
{
  "estado": 404,
  "mensaje": "No se encontró un período académico con ID 999"
}
```

## Registro duplicado

Cuando se intenta registrar un elemento cuyo identificador ya existe, la API responde con:

```text
400 Bad Request
```

junto con un mensaje explicando el problema.

---

# Respuestas JSON

Los controladores utilizan `ResponseEntity` para controlar el contenido de la respuesta y su código HTTP.

Spring Boot realiza automáticamente la serialización de los DTO a formato JSON.

Ejemplo de respuesta exitosa:

```json
{
  "id": 1,
  "codigo": "2026-2",
  "fechaInicio": "2026-08-17",
  "fechaFin": "2026-12-05",
  "estado": "ACTIVO",
  "versionActivaId": 1
}
```

---

# Pruebas realizadas

Se realizaron pruebas de los cinco endpoints CRUD de cada entidad.

Para `Periodo Académico` se realizaron las ocho pruebas solicitadas:

1. `POST` exitoso para registrar un período académico.
2. `GET` exitoso para listar todos los períodos.
3. `GET` exitoso para buscar un período por ID.
4. `PUT` exitoso para actualizar un período.
5. `POST` con datos inválidos.
6. `PUT` con datos inválidos.
7. `GET` utilizando un ID inexistente.
8. `DELETE` exitoso para eliminar un período académico.

Para `Baremo` se realizaron las mismas ocho pruebas, disponibles en `requests/baremos.http`.

Las pruebas verifican los códigos HTTP:

```text
200 OK
201 Created
400 Bad Request
404 Not Found
```

---

# Evidencias

Las evidencias de las pruebas realizadas con Postman se encuentran almacenadas en:

```text
evidencias/
```

Los documentos incluidos son:

```text
Prueba Endpoints Entidad Baremo.pdf
Prueba Endpoints Entidad Estudiante.pdf
Prueba Endpoints Entidad Periodo Academico.pdf
```

Las evidencias muestran el método HTTP, URL utilizada, datos enviados, código HTTP obtenido y respuesta JSON.

---

# Modelo de datos

Los scripts SQL correspondientes a las entidades trabajadas se encuentran en:

```text
database/
```

Actualmente se incluyen:

```text
database/entidad_baremo.sql
database/entidad_estudiante.sql
database/entidad_periodo_academico.sql
```

Estas entidades forman parte del modelo utilizado por el módulo BFA Espacial.

---

# Persistencia actual

Para el alcance de este taller, los datos se almacenan temporalmente en memoria utilizando estructuras `LinkedHashMap`.

Esto permite demostrar el funcionamiento de:

- creación de registros;
- consulta de registros;
- actualización;
- eliminación;
- validaciones;
- manejo de errores;
- respuestas JSON;
- códigos HTTP.

Los datos almacenados se eliminan al reiniciar la aplicación.

Los archivos SQL incluidos representan las estructuras de las entidades dentro del modelo del proyecto, aunque la API desarrollada en este taller todavía utiliza almacenamiento temporal en memoria.

---

# Ejecución

## Requisitos

- JDK 17 o superior
- Maven

## Ejecutar el proyecto

Desde la carpeta raíz:

```bash
mvn spring-boot:run
```

También puede ejecutarse directamente desde IntelliJ IDEA utilizando la clase principal:

```text
ApiEstudiantesApplication
```

Una vez iniciado, el servidor se encuentra disponible en:

```text
http://localhost:8080
```

---

# Flujo de una solicitud

El recorrido general de una solicitud REST dentro del proyecto es:

```text
Cliente / Postman
        ↓
Controller
        ↓
DTO + Validaciones
        ↓
Service
        ↓
Procesamiento
        ↓
ResponseEntity
        ↓
Respuesta JSON
```

Por ejemplo, al registrar un período académico:

```text
POST /api/periodos-academicos
            ↓
PeriodoAcademicoController
            ↓
@Valid + PeriodoAcademicoDto
            ↓
PeriodoAcademicoService
            ↓
Registro del período
            ↓
201 Created
            ↓
Respuesta JSON
```

Si ocurre un error de validación o se solicita un registro inexistente, el error es procesado por `GlobalExceptionHandler` antes de enviar la respuesta JSON correspondiente.

---

## Taller #2 - Servicios Web

Implementación de endpoints REST con Spring Boot, validación de datos, manejo de respuestas JSON y códigos HTTP para entidades pertenecientes al **Módulo BFA Espacial de IQ Test**.
