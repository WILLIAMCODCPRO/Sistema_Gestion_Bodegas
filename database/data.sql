INSERT INTO producto (nombre, categoria, precio) VALUES
('Laptop Lenovo', 'Tecnologia', 2500.00),
('Mouse Logitech', 'Accesorios', 80.00),
('Teclado Mecánico', 'Accesorios', 150.00),
('Monitor Samsung', 'Tecnologia', 900.00),
('Disco SSD 1TB', 'Almacenamiento', 450.00);

INSERT INTO bodega (id, nombre, ubicacion, capacidad, encargado) VALUES
(1, 'Bodega Central', 'Bogotá', 1000, 'Carlos Ramirez'),
(2, 'Bodega Norte', 'Medellín', 800, 'Ana Torres'),
(3, 'Bodega Sur', 'Cali', 600, 'Luis Mendoza');

INSERT INTO usuario (rol) VALUES('ADMIN'), ('EMPLEADO');

INSERT INTO inventario (id_bodega, id_producto, stock_producto) VALUES
(1,1,20),
(1,2,100),
(1,3,50),
(2,1,10),
(2,4,30),
(3,5,40);

INSERT INTO movimiento_inventario 
(fecha, id_usuario, tipo_movimiento, id_bodega_origen, id_bodega_destino) VALUES
('2026-03-10 10:00:00',1,'ENTRADA',1,2),
('2026-03-10 11:00:00',2,'SALIDA',1,2),
('2026-03-10 12:00:00',2,'TRASNFERENCIA',1,2);

INSERT INTO detalle_movimiento (id_producto, cantidad_producto, id_movimiento_inventario) VALUES
(1,5,1),
(2,10,2),
(3,7,3);