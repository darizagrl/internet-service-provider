-- DROP TABLE IF EXISTS users cascade;
-- DROP TABLE IF EXISTS users_roles;
-- DROP TABLE IF EXISTS role;
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
    balance NUMERIC
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
DROP table account;
CREATE TABLE IF NOT EXISTS account
(
    account_id INTEGER PRIMARY KEY,
    account    BIGINT  NOT NULL,
    balance    NUMERIC NOT NULL
);
CREATE TABLE IF NOT EXISTS service
(
    id          INTEGER PRIMARY KEY,
    name        varchar(45)  NOT NULL,
    description varchar(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS tariff
(
    id          INTEGER PRIMARY KEY,
    name        varchar(45)  NOT NULL,
    description varchar(255) NOT NULL,
    price       NUMERIC      NOT NULL,
    type        varchar      not null
);
CREATE TABLE IF NOT EXISTS users_tariffs
(
    user_id   INTEGER NOT NULL,
    tariff_id INTEGER NOT NULL,
    FOREIGN KEY (tariff_id) REFERENCES tariff (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE SEQUENCE IF NOT EXISTS users_id_seq START WITH 3 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS roles_id_seq START WITH 3 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS tariff_id_seq START WITH 4 INCREMENT BY 1;