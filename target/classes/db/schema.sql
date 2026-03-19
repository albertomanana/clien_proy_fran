-- base de datos: crud_clientes
CREATE DATABASE IF NOT EXISTS crud_clientes;
USE crud_clientes;

-- tabla de tipos de cliente (1:N -> un tipo puede tener muchos clientes)
CREATE TABLE IF NOT EXISTS tipo_cliente (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE
);

INSERT INTO tipo_cliente (nombre) VALUES
('Particular'),
('Empresa'),
('Premium')
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

-- tabla: clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL,
    telefono VARCHAR(30),
    direccion VARCHAR(200),
    foto_path VARCHAR(255),
    tipo_cliente_id INT UNSIGNED NOT NULL DEFAULT 1,
    pago1 DECIMAL(10,2) DEFAULT 0,
    pago2 DECIMAL(10,2) DEFAULT 0,
    pago3 DECIMAL(10,2) DEFAULT 0,
    pago_final DECIMAL(10,2) DEFAULT 0,
    balance_total DECIMAL(10,2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE(email),
    CONSTRAINT fk_clientes_tipo_cliente
        FOREIGN KEY (tipo_cliente_id) REFERENCES tipo_cliente(id)
);

-- inserts de ejemplo
INSERT INTO clientes (nombre, apellidos, email, telefono, direccion, foto_path, tipo_cliente_id, pago1, pago2, pago3, pago_final, balance_total)
VALUES
('Juan', 'Perez', 'juan.perez@example.com', '600111222', 'Calle Falsa 123', NULL, 1, 7.00, 8.00, 9.00, 10.00, 8.20),
('Maria', 'Garcia', 'maria.garcia@example.com', '600333444', 'Avenida Libertad 45', 'uploads/demo.png', 2, 8.00, 8.50, 9.00, 9.50, 8.75),
('Carlos', 'Lopez', 'carlos.lopez@example.com', '600555666', 'Plaza Mayor 1', NULL, 3, 9.00, 9.20, 9.10, 9.50, 9.19)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);
