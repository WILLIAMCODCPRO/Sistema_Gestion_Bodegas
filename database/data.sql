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

INSERT INTO auditoria (tipo_operacion, fecha, id_usuario, entidad, valor_antiguo, valor_nuevo)
VALUES
('INSERT', NOW(), 1, 'MovimientoInventario', NULL, '{"cantidadProducto": 15, "bodegaDestino": 3, "productoId": 1}'),

('UPDATE', NOW(), 1, 'MovimientoInventario', '{"cantidadProducto": 15, "bodegaDestino": 3}', '{"cantidadProducto": 20, "bodegaDestino": 4}'),

('DELETE', NOW(), 2, 'MovimientoInventario', '{"cantidadProducto": 20, "bodegaDestino": 4}', NULL),

('INSERT', NOW(), 1, 'Producto', NULL, '{"nombre": "Laptop Lenovo", "categoria": "Tecnologia", "precio": 3500000, "stockProducto": 15}'),

('UPDATE', NOW(), 2, 'Producto', '{"nombre": "Laptop Lenovo", "precio": 3500000}', '{"precio": 3600000}');