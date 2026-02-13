-- base de datos: crud_clientes
CREATE DATABASE IF NOT EXISTS crud_clientes;
USE crud_clientes;

-- tabla: clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL,
    telefono VARCHAR(30),
    direccion VARCHAR(200),
    foto_path VARCHAR(255),
    pago1 DECIMAL(10,2) DEFAULT 0,
    pago2 DECIMAL(10,2) DEFAULT 0,
    pago3 DECIMAL(10,2) DEFAULT 0,
    pago_final DECIMAL(10,2) DEFAULT 0,
    balance_total DECIMAL(10,2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE(email)
);

-- inserts de ejemplo
INSERT INTO clientes (nombre, apellidos, email, telefono, direccion, foto_path) 
VALUES ('Juan', 'Pérez', 'juan.perez@example.com', '600111222', 'Calle Falsa 123', NULL);

INSERT INTO clientes (nombre, apellidos, email, telefono, direccion, foto_path) 
VALUES ('María', 'García', 'maria.garcia@example.com', '600333444', 'Avenida Libertad 45', 'uploads/demo.png');

INSERT INTO clientes (nombre, apellidos, email, telefono, direccion, foto_path) 
VALUES ('Carlos', 'López', 'carlos.lopez@example.com', '600555666', 'Plaza Mayor 1', NULL);
