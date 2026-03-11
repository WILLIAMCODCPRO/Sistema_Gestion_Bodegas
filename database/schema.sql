create database LogiTrack;

use LogiTrack;

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio DECIMAL NOT NULL
);

CREATE TABLE bodega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    capacidad INT NOT NULL,
    encargado VARCHAR(50) NOT NULL
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rol ENUM('ADMIN','EMPLEADO') NOT NULL
);

CREATE TABLE movimiento_inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    id_usuario INT NOT NULL,
    tipo_movimiento ENUM('ENTRADA','SALIDA','TRASNFERENCIA') NOT NULL,
    id_bodega_origen INT ,
    id_bodega_destino INT,

    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_bodega_origen) REFERENCES bodega(id),
    FOREIGN KEY (id_bodega_destino) REFERENCES bodega(id)
);

CREATE TABLE detalle_movimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    cantidad_producto INT NOT NULL,
    id_movimiento_inventario INT NOT NULL,

    FOREIGN KEY (id_producto) REFERENCES producto(id),
    FOREIGN KEY (id_movimiento_inventario) REFERENCES movimiento_inventario(id)
);

CREATE TABLE inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_bodega int NOT NULL,
    id_producto INT NOT NULL,
    stock_producto INT NOT NULL,
    UNIQUE(id_bodega, id_producto),

    FOREIGN KEY (id_bodega) REFERENCES bodega(id),
    FOREIGN KEY (id_producto) REFERENCES producto(id)
);

CREATE TABLE auditoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_operacion ENUM('INSERT','UPDATE','DELETE') NOT NULL,
    fecha DATETIME NOT NULL,
    id_usuario INT NOT NULL,
    entidad VARCHAR(50) NOT NULL,
    id_fila_modificada INT NOT NULL,
    columna_modificada VARCHAR(70) NOT NULL,
    valor_antiguo VARCHAR(200) ,
    valor_nuevo VARCHAR(200) ,

    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);