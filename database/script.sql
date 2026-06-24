CREATE DATABASE SistemaVentas;
GO

USE SistemaVentas;
GO

CREATE TABLE cliente(
    id INT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE venta(
    id INT PRIMARY KEY,
    cliente_id INT,
    CONSTRAINT FK_VENTA_CLIENTE
        FOREIGN KEY(cliente_id)
        REFERENCES cliente(id)
);

INSERT INTO cliente VALUES(1,'Juan');