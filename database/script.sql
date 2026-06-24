CREATE DATABASE TestDB;
GO

USE TestDB;
GO

CREATE TABLE customer (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
GO

CREATE TABLE product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    description VARCHAR(100) NOT NULL
);
GO

CREATE TABLE sale (
    id INT IDENTITY(1,1) PRIMARY KEY,
    customer_id INT NOT NULL,
    CONSTRAINT FK_SALE_CUSTOMER
        FOREIGN KEY(customer_id)
        REFERENCES customer(id)
);
GO

INSERT INTO customer(name)
VALUES ('Juan');
GO

INSERT INTO product(description)
VALUES ('Laptop');
GO


