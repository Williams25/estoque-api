CREATE TABLE USUARIO(
	id INT NOT NULL auto_increment,
    user_name VARCHAR(32) UNIQUE,
    senha VARCHAR(32) CHECK (senha > 3),
    CONSTRAINT PK_USUARIO PRIMARY KEY (id) 
);

CREATE TABLE PRODUTO(
	id INT NOT NULL auto_increment,
    id_usuario INT,
    nome_prod VARCHAR(100),
    descricao VARCHAR(250),
	quantidade INT CHECK (quantidade >= 0),
    valor DECIMAL(10,2) CHECK (valor > 0),
    CONSTRAINT PK_PRODUTO PRIMARY KEY (id),
    CONSTRAINT FK_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);

