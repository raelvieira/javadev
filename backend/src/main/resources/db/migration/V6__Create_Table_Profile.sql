CREATE TABLE IF NOT EXISTS profile(
    id  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_id_profile PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARSET=utf8;