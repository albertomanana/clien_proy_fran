-- Script incremental para una BD ya existente
USE crud_clientes;

CREATE TABLE IF NOT EXISTS tipo_cliente (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE
);

INSERT INTO tipo_cliente (nombre) VALUES
('Particular'),
('Empresa'),
('Premium')
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

ALTER TABLE clientes
ADD COLUMN IF NOT EXISTS tipo_cliente_id INT UNSIGNED NOT NULL DEFAULT 1 AFTER foto_path;

ALTER TABLE clientes
ADD COLUMN IF NOT EXISTS pago1 DECIMAL(10,2) DEFAULT 0 AFTER tipo_cliente_id,
ADD COLUMN IF NOT EXISTS pago2 DECIMAL(10,2) DEFAULT 0 AFTER pago1,
ADD COLUMN IF NOT EXISTS pago3 DECIMAL(10,2) DEFAULT 0 AFTER pago2,
ADD COLUMN IF NOT EXISTS pago_final DECIMAL(10,2) DEFAULT 0 AFTER pago3,
ADD COLUMN IF NOT EXISTS balance_total DECIMAL(10,2) DEFAULT 0 AFTER pago_final;

-- Si no existe la FK, crearla manualmente
-- ALTER TABLE clientes
-- ADD CONSTRAINT fk_clientes_tipo_cliente
-- FOREIGN KEY (tipo_cliente_id) REFERENCES tipo_cliente(id);
