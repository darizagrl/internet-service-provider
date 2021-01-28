-- DROP TABLE IF EXISTS users cascade;
-- DROP TABLE IF EXISTS users_roles;
-- DROP TABLE IF EXISTS role;
-- DROP SEQUENCE IF EXISTS users_id_seq;
CREATE TABLE if not exists users
(
    id       INTEGER PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password varchar(255) NOT NULL
);
CREATE TABLE if not exists role
(
    id   INTEGER PRIMARY KEY,
    name varchar(255) DEFAULT NULL
);
CREATE TABLE if not exists users_roles
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id)
);
CREATE SEQUENCE if not exists users_id_seq START WITH 3 INCREMENT BY 1;