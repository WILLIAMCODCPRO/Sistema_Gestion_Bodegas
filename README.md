# LogiTrack S.A. - Sistema de Gestión de Bodegas

La empresa **LogiTrack S.A.** administra varias bodegas distribuidas en distintas ciudades, encargadas de almacenar productos y gestionar movimientos de inventario (entradas, salidas y transferencias).

Hasta ahora, el control de inventarios y auditorías se realizaba manualmente en hojas de cálculo, sin trazabilidad ni control de accesos.

La dirección general busca implementar un **sistema backend centralizado en Spring Boot**, que permita:

- Controlar todos los movimientos entre bodegas.
- Registrar automáticamente los cambios (auditorías).
- Proteger la información con autenticación JWT.
- Ofrecer endpoints REST documentados y seguros.

---

## Objetivo General

Desarrollar un sistema de gestión y auditoría de bodegas que permita registrar transacciones de inventario y generar reportes auditables de los cambios realizados por cada usuario.

---

## Requisitos Funcionales

### 1. Gestión de Bodegas
- Registrar, consultar, actualizar y eliminar bodegas.
- Campos:
  - `id`
  - `nombre`
  - `ubicacion`
  - `capacidad`
  - `encargado`

### 2. Gestión de Productos
- CRUD completo de productos.
- Campos:
  - `id`
  - `nombre`
  - `categoria`
  - `stock`
  - `precio`

### 3. Movimientos de Inventario
- Registrar **entradas, salidas y transferencias** entre bodegas.
- Cada movimiento debe almacenar:
  - Fecha
  - Tipo de movimiento (`ENTRADA`, `SALIDA`, `TRANSFERENCIA`)
  - Usuario responsable (empleado logueado)
  - Bodega origen/destino
  - Productos y cantidades

### 4. Auditoría de Cambios
- Crear una entidad **Auditoría** para registrar:
  - Tipo de operación (`INSERT`, `UPDATE`, `DELETE`)
  - Fecha y hora
  - Usuario que realizó la acción
  - Entidad afectada y valores anteriores/nuevos
- Implementación:
  - **Listeners de JPA (EntityListeners)** o
  - **Aspecto con anotaciones personalizadas** (opcional)

### 5. Autenticación y Seguridad
- Implementar seguridad con **Spring Security + JWT**
  - Endpoints: `/auth/login` y `/auth/register`
  - Rutas seguras para: `/bodegas`, `/productos`, `/movimientos`
  - Roles de usuario: `ADMIN` / `EMPLEADO`

### 6. Consultas Avanzadas y Reportes
- Endpoints con filtros:
  - Productos con stock bajo (< 10 unidades)
  - Movimientos por rango de fechas (`BETWEEN`)
  - Auditorías por usuario o tipo de operación
- Reporte REST de resumen general (JSON):
  - Stock total por bodega
  - Productos más movidos

---

## Tecnologías Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **JPA / Hibernate**
- **MySQL JDBC**
- **Swagger / OpenAPI** para documentación de endpoints

---
## Instalación

1. **Clonar el repositorio:**

```bash
git clone https://github.com/WILLIAMCODCPRO/Sistema_Gestion_Bodegas.git
cd logitrack
```

2. **Configurar la base de datos:**
3. Crear una base de datos llamada logitrack_db.
4. Editar src/main/resources/application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/logitrack_db
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
5. Construir el proyecto:

```bash
mvn clean install
```

6. Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

# Endpoints Principales

```bash
Gestión de bodegas: /api/bodega

Gestión de productos: /api/producto

Movimientos de inventario: /api/movimientos

Auditoría de cambios: /api/auditoria

Autenticación: /auth/login y /auth/register

Autenticación JWT

Registrar usuario:

POST /auth/register
{
  "nombreUsuario": "admin",
  "password": "12345",
  "rol": "ADMIN"
}

Iniciar sesión y obtener token:

POST /auth/login
{
  "nombreUsuario": "admin",
  "password": "12345"
}

Usar token en headers:

Authorization: Bearer <TOKEN>
Consultas y Reportes

Productos con stock bajo: GET /productos/stockbajo

Movimientos por rango de fechas: GET /movimientos/fecha1/feca2

Auditorías por usuario : GET /auditoria/idusuario

Reporte general de stock por bodega: GET /api/reporte/bodega/stock

productos más movidos: GET /api/reporte/productos/masmovidos
```
#Capturas Swagger

<img width="1816" height="563" alt="imagen" src="https://github.com/user-attachments/assets/ef81d7d4-7da7-495f-8009-b81f251b7c53" />

<img width="1851" height="581" alt="imagen" src="https://github.com/user-attachments/assets/13651fb7-bdfd-4783-a807-9cd7c5fa2d22" />


# Arquitectura del Proyecto — Gestión de Bodegas (LogiTrack)

## 1. Descripción General

**LogiTrack** es una API REST desarrollada con **Spring Boot 3.5** que gestiona el inventario de bodegas de una empresa. Permite registrar bodegas, productos, movimientos de inventario y usuarios, con autenticación JWT y un sistema de auditoría automática sobre las operaciones de persistencia.

---

## 2. Stack Tecnológico

| Capa | Tecnología |
|------|-----------|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.5.11 |
| Persistencia | Spring Data JPA + Hibernate |
| Base de datos | MySQL (esquema `LogiTrack`) |
| Seguridad | Spring Security + JWT (jjwt 0.11.5) |
| Validación | Spring Boot Validation (Jakarta Bean Validation) |
| Documentación | SpringDoc OpenAPI 2.8 (Swagger UI) |
| Utilidades | Lombok |
| Build | Maven |

---

## 3. Estructura de Paquetes

```
com.s1.gestion_bodegas/
│
├── auth/                        # Autenticación
│   ├── AuthController.java      # POST /auth/login, POST /auth/register
│   ├── AuthService.java         # Lógica de login y registro
│   ├── LoginRequest.java        # DTO entrada: nombreUsuario + password
│   └── LoginResponse.java       # DTO salida: token JWT
│
├── config/                      # Configuración de seguridad
│   ├── SecurityConfig.java      # Cadena de filtros, CORS, rutas públicas
│   ├── JwtFilter.java           # Filtro que intercepta y valida el token
│   ├── JwtService.java          # Generación y validación de tokens JWT
│   └── OpenApiConfig.java       # Configuración de Swagger UI
│
├── controller/                  # Capa de presentación (REST)
│   ├── BodegaController.java
│   ├── ProductoController.java
│   ├── MovimientoInventarioController.java
│   ├── UsuarioController.java
│   ├── ReporteController.java
│   └── AuditoriaController.java
│
├── service/                     # Interfaces de servicio
│   ├── impl/                    # Implementaciones de los servicios
│   │   ├── BodegaServiceImpl.java
│   │   ├── ProductoServiceimpl.java
│   │   ├── MovimientoInventarioServiceimpl.java
│   │   ├── UsuarioServiceImpl.java
│   │   ├── AuditoriaServiceimpl.java
│   │   └── ReporteServiceImpl.java
│   ├── BodegaService.java
│   ├── ProductoService.java
│   ├── MovimientoInventarioService.java
│   ├── UsuarioService.java
│   ├── AuditoriaService.java
│   └── ReporteService.java
│
├── repository/                  # Capa de acceso a datos (Spring Data JPA)
│   ├── BodegaRepository.java
│   ├── ProductoRepository.java
│   ├── MovimientoInventarioRepository.java
│   ├── UsuarioRepository.java
│   └── AuditoriaRepository.java
│
├── model/                       # Entidades JPA
│   ├── Bodega.java
│   ├── Producto.java
│   ├── MovimientoInventario.java
│   ├── Usuario.java
│   ├── Auditoria.java
│   ├── Rol.java                 # Enum: ADMIN, EMPLEADO
│   ├── TipoMovimiento.java      # Enum: ENTRADA, SALIDA, TRASNFERENCIA
│   └── TipoOperacion.java       # Enum: INSERT, UPDATE, DELETE
│
├── dto/
│   ├── request/                 # DTOs de entrada
│   │   ├── BodegaRequestDTO.java
│   │   ├── ProductoRequestDTO.java
│   │   ├── MovimientoInventarioRequestDTO.java
│   │   └── UsuarioRequestDTO.java
│   └── response/                # DTOs de salida
│       ├── BodegaResponseDTO.java
│       ├── ProductoResponseDTO.java
│       ├── MovimientoInventarioResponseDTO.java
│       ├── UsuarioResponseDTO.java
│       ├── AuditoriaResponseDTO.java
│       ├── StockBodegaResponseDTO.java
│       └── ProductoMasMovidoResponseDTO.java
│
├── mapper/                      # Conversión entidad ↔ DTO (manual)
│   ├── BodegaMapper.java
│   ├── ProductoMapper.java
│   ├── MovimientoInventarioMapper.java
│   ├── UsuarioMapper.java
│   └── AuditoriaMapper.java
│
├── listener/
│   └── AuditoriaListener.java   # JPA EntityListener para auditoría automática
│
└── exception/                   # Manejo global de errores
    ├── GlobalExceptionHandler.java
    ├── BusinessRuleException.java
    └── ErrorResponse.java
```

---

## 4. Capas de la Arquitectura

El proyecto sigue una arquitectura en **capas** clásica de Spring Boot:

```
Cliente HTTP
     │
     ▼
┌─────────────────────┐
│     Controller      │  ← Recibe requests, valida entrada, delega al servicio
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│      Service        │  ← Lógica de negocio, orquestación, uso de mappers
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│     Repository      │  ← Acceso a BD con Spring Data JPA
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│    Base de Datos    │  ← MySQL (LogiTrack)
└─────────────────────┘
```

Los **Mappers** convierten entidades a DTOs y viceversa en la capa de servicio, garantizando que nunca se expongan directamente las entidades JPA al cliente.

---

## 5. Seguridad

### Autenticación con JWT

El flujo de autenticación es completamente **stateless**:

```
1. Cliente → POST /auth/login { nombreUsuario, password }
2. AuthService valida credenciales contra la BD (comparación directa de contraseñas)
3. JwtService genera un token HS256 con expiración de 30 minutos
4. Cliente recibe { token: "eyJ..." }
5. En cada request posterior, el cliente envía: Authorization: Bearer <token>
6. JwtFilter intercepta el request, valida el token y carga el contexto de seguridad
```

### Rutas públicas

Las siguientes rutas no requieren autenticación:

- `POST /auth/login`
- `POST /auth/register`
- `OPTIONS /**` (preflight CORS)
- `/swagger-ui/**` y `/v3/api-docs/**`

### CORS

Configurado para aceptar peticiones únicamente desde:
- `http://localhost:5500`
- `http://127.0.0.1:5500`

---

## 6. Modelos de Datos

### Diagrama de relaciones

```
┌──────────┐       ┌───────────────┐       ┌──────────┐
│  Bodega  │◄──────┤    Producto   │       │ Usuario  │
│          │       │               │       │          │
│ id       │       │ id            │       │ id       │
│ nombre   │       │ nombre        │       │ nombreUs.|
│ ubicacion│       │ categoria     │       │ password │
│ capacidad│       │ precio        │       │ rol      │
│ encargado│       │ stockProducto │       └────┬─────┘
└──────────┘       │ id_bodega (FK)│            │
     ▲             └──────┬────────┘            │
     │                    │                     │
     │             ┌──────▼──────────────────────▼──┐
     │             │     MovimientoInventario         │
     │             │                                  │
     └─────────────┤ id          fecha                │
    (origen/dest)  │ tipoMovimiento  cantidadProducto │
                   │ id_usuario (FK)                  │
                   │ id_producto (FK)                 │
                   │ id_bodega_origen (FK, nullable)  │
                   │ id_bodega_destino (FK, nullable) │
                   └──────────────────────────────────┘
                                  │
                                  │ (via AuditoriaListener)
                                  ▼
                         ┌─────────────────┐
                         │    Auditoria    │
                         │                 │
                         │ id              │
                         │ tipoOperacion   │
                         │ fecha           │
                         │ id_usuario (FK) │
                         │ entidad         │
                         │ valorAntiguo    │
                         │ valorNuevo      │
                         └─────────────────┘
```

### Entidades principales

**Bodega** — almacén físico con nombre, ubicación, capacidad y encargado.

**Producto** — artículo del inventario asociado a una bodega. Tiene precio, categoría y stock actual. Tiene un umbral de alerta de stock bajo en 10 unidades.

**MovimientoInventario** — registro de cada movimiento de mercancía. La fecha se asigna automáticamente con `@CreationTimestamp`. El usuario se resuelve automáticamente desde el contexto de seguridad JWT en el servicio.

**Usuario** — cuenta del sistema con rol `ADMIN` o `EMPLEADO`. Las contraseñas se almacenan en texto plano (sin hashing).

**Auditoria** — historial de operaciones sobre entidades. Se genera automáticamente vía `AuditoriaListener`.

---

## 7. Sistema de Auditoría

La auditoría es **automática** gracias a `AuditoriaListener`, un `@Component` anotado como `@EntityListeners` en las entidades `Bodega`, `Producto` y `MovimientoInventario`.

### Funcionamiento

```
Operación JPA  →  @PrePersist / @PreRemove  →  AuditoriaListener
                                                      │
                                          Obtiene usuario autenticado
                                          desde SecurityContextHolder
                                                      │
                                          Serializa datos relevantes
                                          a JSON (ObjectMapper)
                                                      │
                                          Guarda registro en tabla auditoria
```

| Evento JPA | Tipo Operación | valorAntiguo | valorNuevo |
|------------|---------------|--------------|------------|
| `@PrePersist` | `INSERT` | `null` | JSON con datos de la entidad |
| `@PreRemove` | `DELETE` | JSON con datos de la entidad | `null` |

> **Nota:** el método `preUpdate` existe pero no está anotado con `@PreUpdate`, por lo que las actualizaciones no generan registros de auditoría en el estado actual del código.

---

## 8. Endpoints de la API

### Autenticación — `/auth`

| Método | Ruta | Descripción | Autenticación |
|--------|------|-------------|---------------|
| POST | `/auth/login` | Inicia sesión y retorna token JWT | No |
| POST | `/auth/register` | Registra un nuevo usuario | No |

### Bodegas — `/api/bodega`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/bodega` | Listar todas las bodegas |
| POST | `/api/bodega` | Crear una bodega |
| GET | `/api/bodega/{id}` | Buscar bodega por ID |
| PUT | `/api/bodega/{id}` | Actualizar bodega |
| DELETE | `/api/bodega/{id}` | Eliminar bodega |

### Productos — `/api/producto`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/producto` | Listar todos los productos |
| POST | `/api/producto` | Crear un producto |
| GET | `/api/producto/{id}` | Buscar producto por ID |
| PUT | `/api/producto/{id}` | Actualizar producto |
| DELETE | `/api/producto/{id}` | Eliminar producto |
| GET | `/api/producto/stockbajo` | Productos con stock < 10 |

### Movimientos — `/api/movimientos`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/movimientos` | Listar todos los movimientos |
| POST | `/api/movimientos` | Registrar un movimiento |
| GET | `/api/movimientos/{id}` | Buscar movimiento por ID |
| GET | `/api/movimientos/{fecha1}/{fecha2}` | Movimientos por rango de fecha |

### Usuarios — `/api/usuario`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/usuario` | Listar todos los usuarios |

### Reportes — `/api/reporte`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/reporte/bodega/stock` | Stock total agrupado por bodega |
| GET | `/api/reporte/productos/masmovidos` | Productos con más movimientos |

### Auditoría — `/api/auditoria`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/auditoria` | Listar todas las auditorías |
| GET | `/api/auditoria/{id}` | Buscar auditoría por ID |
| GET | `/api/auditoria/usuario/{idUsuario}` | Auditorías de un usuario específico |

---

## 9. Manejo de Errores

El `GlobalExceptionHandler` centraliza el manejo de excepciones y retorna respuestas estructuradas:

```json
// Validación de campos (400)
{
  "errorCode": "VALIDATION_FAILED",
  "errors": {
    "nombre": "El nombre debe tener entre 2 y 50 caracteres",
    "capacidad": "La capacidad debe ser mayor a 0"
  },
  "timestamp": "2026-03-16T14:26:33",
  "status": 400
}

// Regla de negocio (400)
{
  "errorCode": "BUSINESS_RULE_VIOLATION",
  "message": "Usuario no encontrado",
  "timestamp": "2026-03-16T12:29:58",
  "status": 400
}

// Error interno (500)
{
  "errorCode": "INTERNAL_SERVER_ERROR",
  "message": "Error interno del servidor",
  "timestamp": "2026-03-16T12:56:12",
  "status": 500
}
```

> Las restricciones de clave foránea en MySQL (por ejemplo, eliminar una bodega con productos asociados) generan un error 500 ya que no son capturadas explícitamente por el manejador global.

---

## 10. Configuración

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/LogiTrack
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=none   # El esquema se gestiona manualmente
spring.jpa.show-sql=true
```

El esquema de base de datos se inicializa manualmente con los scripts en `database/schema.sql` y `database/data.sql`.

---

#Ejemplo de token JWT y uso.

```bash
public class JwtService {

    private final String SECRET = "clave_supersecreta";

    private final long EXPIRATION = 1000 * 60 * 30; // 30 minutos


    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    public String generateToken(String username) {

        return Jwts.builder()

                .setSubject(username)


                .setIssuedAt(new Date())


                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))


                .signWith(getKey(), SignatureAlgorithm.HS256)

                .compact();
    }


    public String validateToken(String token) {

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()


                    .parseClaimsJws(token)


                    .getBody()


                    .getSubject();
        } catch (Exception e) {



            return null;
        }
    }
}

public LoginResponse login(LoginRequest request){

        Usuario usuario = usuarioRepository
                .findByNombreUsuario(request.nombreUsuario())
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado"));

        if(!usuario.getPassword().equals(request.password())){
            throw new BusinessRuleException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario.getNombreUsuario());

        return new LoginResponse(token);
    }
```


