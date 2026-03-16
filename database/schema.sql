create database LogiTrack_William;

use LogiTrack_William;

CREATE TABLE bodega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    capacidad INT NOT NULL,
    encargado VARCHAR(50) NOT NULL
);

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio DECIMAL NOT NULL,
    stock_producto INT NOT NULL,
    id_bodega INT NOT NULL,

    FOREIGN KEY (id_bodega) REFERENCES bodega(id)
);


CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(200) UNIQUE NOT NULL,
    password VARCHAR(200)UNIQUE NOT NULL,
    rol ENUM('ADMIN','EMPLEADO') NOT NULL
);

CREATE TABLE movimiento_inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    id_usuario INT NOT NULL,
    tipo_movimiento ENUM('ENTRADA','SALIDA','TRASNFERENCIA') NOT NULL,
    id_producto INT NOT NULL,
    cantidad_producto INT NOT NULL,
    id_bodega_origen INT ,
    id_bodega_destino INT,

    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_bodega_origen) REFERENCES bodega(id),
    FOREIGN KEY (id_producto) REFERENCES producto(id),
    FOREIGN KEY (id_bodega_destino) REFERENCES bodega(id)
);


CREATE TABLE auditoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_operacion ENUM('INSERT','UPDATE','DELETE') NOT NULL,
    fecha DATETIME NOT NULL,
    id_usuario INT NOT NULL,
    entidad VARCHAR(50) NOT NULL,
    valor_antiguo JSON,
    valor_nuevo JSON,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);