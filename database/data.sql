INSERT INTO bodega (nombre, ubicacion, capacidad, encargado) VALUES
('Bodega Central', 'Bogotá Centro', 500, 'Carlos Pérez'),
('Bodega Norte', 'Bogotá Norte', 300, 'Laura Gómez'),
('Bodega Sur', 'Bogotá Sur', 250, 'Andrés Rojas'),
('Bodega Occidente', 'Bogotá Occidente', 200, 'Marta Díaz');

INSERT INTO usuario (nombre_usuario, password, rol) VALUES
('admin', '12344', 'ADMIN'),
('juan', '12314', 'EMPLEADO'),
('maria', '12734', 'EMPLEADO'),
('carlos', '11234', 'EMPLEADO'),
('ana', '12834', 'ADMIN');

INSERT INTO producto (nombre, categoria, precio, stock_producto, id_bodega) VALUES
('Laptop Lenovo', 'Tecnologia', 3500000, 15, 1),
('Mouse Logitech', 'Tecnologia', 80000, 50, 1),
('Teclado Mecánico', 'Tecnologia', 250000, 8, 1),
('Monitor Samsung 24"', 'Tecnologia', 900000, 6, 2),
('Silla Gamer', 'Muebles', 1200000, 20, 2),
('Escritorio Oficina', 'Muebles', 700000, 5, 3),
('Disco SSD 1TB', 'Tecnologia', 450000, 12, 3),
('Memoria RAM 16GB', 'Tecnologia', 300000, 9, 4),
('Impresora HP', 'Oficina', 600000, 7, 4),
('Router TP-Link', 'Redes', 150000, 30, 2);

INSERT INTO movimiento_inventario 
(fecha, id_usuario, tipo_movimiento, id_producto, cantidad_producto, id_bodega_origen, id_bodega_destino)
VALUES
('2026-03-10 09:00:00', 1, 'ENTRADA', 1, 10, NULL, 1),
('2026-03-10 10:00:00', 2, 'SALIDA', 2, 5, 1, NULL),
('2026-03-10 11:30:00', 3, 'TRASNFERENCIA', 3, 3, 1, 2),
('2026-03-10 14:00:00', 2, 'ENTRADA', 4, 4, NULL, 2),
('2026-03-11 09:15:00', 3, 'SALIDA', 5, 2, 2, NULL),
('2026-03-11 10:45:00', 1, 'TRASNFERENCIA', 6, 1, 3, 4),
('2026-03-11 13:20:00', 2, 'ENTRADA', 7, 6, NULL, 3),
('2026-03-11 15:00:00', 4, 'SALIDA', 8, 2, 4, NULL);

INSERT INTO auditoria 
(tipo_operacion, fecha, id_usuario, entidad, id_fila_modificada, columna_modificada, valor_antiguo, valor_nuevo)
VALUES
('INSERT', '2026-03-10 09:01:00', 1, 'producto', 1, 'stock_producto', NULL, '15'),
('UPDATE', '2026-03-10 10:05:00', 2, 'producto', 2, 'stock_producto', '50', '45'),
('UPDATE', '2026-03-10 11:35:00', 3, 'producto', 3, 'stock_producto', '8', '5'),
('DELETE', '2026-03-11 09:20:00', 2, 'producto', 5, 'stock_producto', '20', NULL),
('UPDATE', '2026-03-11 10:50:00', 1, 'bodega', 3, 'capacidad', '250', '300');