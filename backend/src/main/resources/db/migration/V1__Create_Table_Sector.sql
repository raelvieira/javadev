CREATE TABLE IF NOT EXISTS sector(
    id  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(150) NOT NULL,
    CONSTRAINT pk_id_sector PRIMARY KEY (id)
);