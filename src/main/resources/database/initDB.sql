DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS users_id_seq;
CREATE TABLE if not exists users
(
    id    INTEGER PRIMARY KEY,
    name  VARCHAR(200),
    email VARCHAR(254)
);
CREATE SEQUENCE if not exists users_id_seq START WITH 3 INCREMENT BY 1;