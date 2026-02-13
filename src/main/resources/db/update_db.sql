-- Ejecuta estos comandos en tu MySQL (phpMyAdmin o terminal) para añadir los nuevos campos a tu tabla actual sin perder tus clientes
USE crud_clientes;

ALTER TABLE clientes 
ADD COLUMN pago1 DECIMAL(10,2) DEFAULT 0 AFTER foto_path,
ADD COLUMN pago2 DECIMAL(10,2) DEFAULT 0 AFTER pago1,
ADD COLUMN pago3 DECIMAL(10,2) DEFAULT 0 AFTER pago2,
ADD COLUMN pago_final DECIMAL(10,2) DEFAULT 0 AFTER pago3,
ADD COLUMN balance_total DECIMAL(10,2) DEFAULT 0 AFTER pago_final;
