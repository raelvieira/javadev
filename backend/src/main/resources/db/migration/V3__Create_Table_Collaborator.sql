CREATE TABLE IF NOT EXISTS collaborator(
    id  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    phone BIGINT(11) UNSIGNED,
    email VARCHAR(150) UNIQUE NOT NULL,
    birthday DATE NOT NULL,
    id_sector INT(11) UNSIGNED NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    CONSTRAINT pk_id_collaborator PRIMARY KEY (id),
    CONSTRAINT fk_id_sector FOREIGN KEY (id_sector) REFERENCES sector(id)
);