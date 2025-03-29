CREATE SCHEMA investimentos;
USE investimentos;

CREATE TABLE fundos (
	id INT NOT NULL AUTO_INCREMENT CHECK (id > 0),-- embora já seja auto_increment, estou testando
    nome VARCHAR(255) NOT NULL,
    ticker VARCHAR(10) NOT NULL,
    valor DECIMAL(10,2) NOT NULL CHECK (valor > 0),
    quantidade INT NOT NULL CHECK (quantidade > 0),
    data DATE NOT NULL,
    
    PRIMARY KEY (id)
);