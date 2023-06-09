CREATE TABLE usuario
(
    codigo BIGINT(20) PRIMARY KEY,
    nome   VARCHAR(50)  NOT NULL,
    email  VARCHAR(50)  NOT NULL,
    senha  VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao
(
    codigo    BIGINT(20) PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao
(
    codigo_usuario   BIGINT(20) NOT NULL,
    codigo_permissao BIGINT(20) NOT NULL,
    PRIMARY KEY (codigo_usuario, codigo_permissao),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario (codigo),
    FOREIGN KEY (codigo_permissao) REFERENCES permissao (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, senha)
values (1, 'Administrador', 'admin@financectrl.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.'),
       (2, 'Maria Silva', 'maria@financectrl.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissao (codigo, descricao)
values (1, 'ROLE_CADASTRAR_CATEGORIA'),
       (2, 'ROLE_PESQUISAR_CATEGORIA'),
       (3, 'ROLE_CADASTRAR_PESSOA'),
       (4, 'ROLE_REMOVER_PESSOA'),
       (5, 'ROLE_PESQUISAR_PESSOA'),
       (6, 'ROLE_CADASTRAR_LANCAMENTO'),
       (7, 'ROLE_REMOVER_LANCAMENTO'),
       (8, 'ROLE_PESQUISAR_LANCAMENTO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8);

-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
values (2, 2),
       (2, 5),
       (2, 8);