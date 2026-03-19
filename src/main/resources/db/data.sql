USE crud_clientes;

INSERT INTO tipo_cliente (nombre) VALUES
('Particular'),
('Empresa'),
('Premium')
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO clientes (nombre, apellidos, email, telefono, direccion, foto_path, tipo_cliente_id, pago1, pago2, pago3, pago_final, balance_total) VALUES
('Ana', 'Lopez Martinez', 'ana.lopez@example.com', '611222333', 'Avenida Libertad 5, Barcelona', NULL, 1, 8.00, 8.20, 8.50, 9.00, 8.41),
('David', 'Rodriguez Ruiz', 'david.rod@example.com', '622333444', 'Plaza Espana 10, Sevilla', NULL, 2, 7.50, 7.80, 8.10, 8.80, 7.99)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);
