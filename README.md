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

