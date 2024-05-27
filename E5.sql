CREATE DATABASE alke_wallet_E5;

USE alke_wallet_E5;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    clave VARCHAR(100) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL
);

CREATE TABLE transaccion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

INSERT INTO usuario (nombre, clave, saldo) VALUES ('Lalo Landa', 'Lalo123', 1000.00);
select * from transaccion;
select * from usuario;
