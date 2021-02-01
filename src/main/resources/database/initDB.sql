DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS role;
DROP SEQUENCE IF EXISTS users_id_seq;
DROP SEQUENCE IF EXISTS roles_id_seq;
CREATE TABLE if not exists users
(
    id        INTEGER PRIMARY KEY,
    firstname VARCHAR(255)        NOT NULL,
    lastname  VARCHAR(255)        NOT NULL,
    email     VARCHAR(255) UNIQUE NOT NULL,
    password  varchar(255)        NOT NULL
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
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE SEQUENCE if not exists users_id_seq START WITH 3 INCREMENT BY 1;
CREATE SEQUENCE if not exists roles_id_seq START WITH 3 INCREMENT BY 1;
