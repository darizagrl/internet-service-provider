-- DROP TABLE IF EXISTS users cascade;
-- DROP TABLE IF EXISTS users_roles;
-- DROP TABLE IF EXISTS role;
-- DROP TABLE IF EXISTS tariff cascade;
-- DROP TABLE IF EXISTS users_tariffs cascade;
-- DROP SEQUENCE IF EXISTS users_id_seq;
-- DROP SEQUENCE IF EXISTS roles_id_seq;
-- DROP SEQUENCE IF EXISTS tariff_id_seq;
CREATE TABLE IF NOT EXISTS users
(
    id        INTEGER PRIMARY KEY,
    firstname VARCHAR(255)        NOT NULL,
    lastname  VARCHAR(255)        NOT NULL,
    email     VARCHAR(255) UNIQUE NOT NULL,
    password  VARCHAR(255)        NOT NULL,
    balance   NUMERIC default 0.0,
    isBlocked boolean default false

);
CREATE TABLE IF NOT EXISTS role
(
    id   INTEGER PRIMARY KEY,
    name VARCHAR(255) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS users_roles
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE IF NOT EXISTS service
(
    id   INTEGER PRIMARY KEY,
    name varchar(45) UNIQUE
);
CREATE TABLE IF NOT EXISTS tariff
(
    id          INTEGER PRIMARY KEY,
    name        varchar(45)  NOT NULL,
    description varchar(255) NOT NULL,
    price       NUMERIC      NOT NULL,
    type        varchar      not null,
    FOREIGN KEY (type) REFERENCES service (name)
);
CREATE TABLE IF NOT EXISTS users_tariffs
(
    user_id   INTEGER NOT NULL,
    tariff_id INTEGER NOT NULL,
    FOREIGN KEY (tariff_id) REFERENCES tariff (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE SEQUENCE IF NOT EXISTS users_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS roles_id_seq START WITH 3 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS tariff_id_seq START WITH 1 INCREMENT BY 1;