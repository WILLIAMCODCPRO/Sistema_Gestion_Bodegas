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
- **H2 / PostgreSQL**
- **Swagger / OpenAPI** para documentación de endpoints

---

## Instalación y Ejecución
1. Clonar el repositorio:
```bash
git clone https://github.com/usuario/logitrack-backend.git
